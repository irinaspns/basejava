package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class MainStreams {

    public static void main(String[] args) {
        int[] a =  {1, 2, 3, 3, 4, 2, 3} ;
        System.out.println(minValue(a));

        List <Integer> integers = Arrays.asList(5, 7, 8, 3, 2, 5);
        System.out.println(oddOrEven(integers));
        System.out.println(oddOrEven2(integers));
    }

    private static int minValue(int[] values) {
        return IntStream.of(values)
               .distinct()
               .sorted()
               .reduce(0, (subSum, number) -> subSum * 10 + number);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().filter(integers
                .stream()
                .reduce(0, Integer::sum) % 2 == 0 ? p -> p % 2 != 0 : p1 -> p1 % 2 == 0)
                .collect(toList());
    }

    private static List<Integer> oddOrEven2(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers
                .stream()
                .collect(partitioningBy(p -> p % 2 == 0, toList()));

        return (integers
                .stream()
                .reduce(0, Integer::sum) % 2 == 0) ? map.get(false) : map.get(true);
    }
}
