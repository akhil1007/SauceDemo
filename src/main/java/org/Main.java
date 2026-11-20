package org;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    String firstName;
    String lastName;
    public static void main(String[] args){
        String str = "Programming";
        System.out.println(str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity()
                        , LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(e->e.getValue()==1)
                .map(Map.Entry::getKey).findFirst().orElse(null));

    }
}
