package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStreams {

    public static void main(String[] args) {
        int[] a =  {1, 2, 3, 3, 2, 3} ;
        System.out.println(minValue(a));

        List <Integer> integers = Arrays.asList(5, 7, 8, 3, 2, 5);
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        String value = IntStream.of(values)
                .distinct()
                .sorted()
                .mapToObj(v -> Integer.valueOf(v).toString())
                .reduce("", (partialString, element) -> partialString + element);

        return Integer.parseInt(value);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().filter(integers
                .stream()
                .reduce(0, Integer::sum) % 2 == 0 ? p -> p % 2 != 0 : p1 -> p1 % 2 == 0).collect(Collectors.toList());
    }

}
