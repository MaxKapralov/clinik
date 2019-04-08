package com.strotska.prychodnia;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static <T> Set<T> setOf(T... args) {
        return Arrays.stream(args).collect(Collectors.toSet());
    }
    public static <T> List<T> listOf(T... args) {
        return Arrays.stream(args).collect(Collectors.toList());
    }
}
