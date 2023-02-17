package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {

        System.out.println(minValue(new int[]{9, 8}));
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));

        List<Integer> list = getRandList(20);
        System.out.println(oddOrEven1(list));
        System.out.println(oddOrEven2(list));
        System.out.println(oddOrEven3(list));
        System.out.println(oddOrEven4(list));
    }

    static List<Integer> oddOrEven1(List<Integer> integers) {
        return integers.stream().filter(
                        x -> !(integers.stream().reduce(0, Integer::sum) % 2 == 0) & x % 2 == 0
                                | ((integers.stream().reduce(0, Integer::sum) % 2 == 0) & x % 2 != 0))
                .toList();
    }

    static List<Integer> oddOrEven2(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0))
                .get(integers.stream()
                        .mapToInt(Integer::intValue)
                        .sum() % 2 != 0);
    }

    static List<Integer> oddOrEven3(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        return integers.stream()
                .filter(element -> (sum == 0) == (element % 2 != 0))
                .toList();
    }

    static List<Integer> oddOrEven4(List<Integer> integers) {
        return Stream.of(integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0)
                .flatMap(sum ->
                        integers.stream()
                                .filter(element -> sum == (element % 2 != 0))
                )
                .toList();
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (acc, x) -> acc * 10 + x);
    }

    private static List<Integer> getRandList(int size) {
        List<Integer> list = new Random().ints(size, 1, size * 2)
                .boxed()
                .peek(obj -> System.out.print(obj + " "))
                .toList();
        System.out.println("\nSum = " + list.stream().reduce(0, Integer::sum));
        return list;
    }
}
