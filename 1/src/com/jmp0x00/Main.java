package com.jmp0x00;

import java.util.*;

public class Main {

    private static boolean has_two_numbers_with_sum(int[] numbers, int k) {
        Set<Integer> diff = new HashSet<>();

        for (int n : numbers) {
            if (diff.contains(n))
                return true;
            diff.add(k - n);
        }
        return false;
    }

    // Input is numbers, the last number is k
    public static void main(String[] args) {
        int[] input = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] numbers = Arrays.copyOf(input, input.length - 1);
        int k = input[input.length - 1];
        System.out.println(has_two_numbers_with_sum(numbers, k));
    }
}
