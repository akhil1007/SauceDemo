package org.automation.config;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Creates WebDriver instances from system properties. Selenium Manager resolves driver binaries automatically.
 */
public final class DriverFactory {

    private static final String WINDOW_SIZE = "--window-size=1920,1080";

    private DriverFactory() {
    }

    /**
     * Builds a driver for the configured browser.
     *
     * @return configured {@link WebDriver}
     * @throws IllegalArgumentException if the {@code browser} property is unsupported
     */
    public static WebDriver create() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        WebDriver driver = switch (browser) {
            case "chrome" -> new ChromeDriver(chromeOptions(headless));
            case "edge" -> new EdgeDriver(edgeOptions(headless));
            case "firefox" -> new FirefoxDriver(firefoxOptions(headless));
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        if (!headless) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        // Suppress Chrome's password-breach popup, which blocks SauceDemo's shared demo credentials.
        options.setExperimentalOption("prefs", Map.<String, Object>of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        options.addArguments(WINDOW_SIZE, "--disable-notifications");
        if (headless) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        }
        return options;
    }

    private static EdgeOptions edgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(WINDOW_SIZE);
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return options;
    }
}

