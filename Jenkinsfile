// Declarative pipeline: runs the Selenium/TestNG suite and publishes results.
// Prereqs on Jenkins: JDK 17 tool named 'jdk17', Maven tool named 'maven3', JUnit plugin, target browser on agent.
pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'edge', 'firefox'], description: 'Browser to run tests on')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser headless')
        string(name: 'SUITE', defaultValue: 'testng.xml', description: 'TestNG suite file')
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                script {
                    def cmd = "mvn -B clean test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -DsuiteXml=${params.SUITE}"
                    if (isUnix()) {
                        sh cmd
                    } else {
                        bat cmd
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/**/*.xml'
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
    }
}

