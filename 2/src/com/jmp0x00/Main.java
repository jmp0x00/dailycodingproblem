package com.jmp0x00;

import java.util.Arrays;

public class Main {

    private static int[] modify(int[] input) {
        Integer total = null;
        int zeroCount = 0;
        for (int n : input) {
            if (total == null) {
                total = n;
            } else {
                if (n == 0) {
                    zeroCount++;
                    continue;
                }
                total *= n;
            }
        }
        for (int i = 0; i < input.length; i++) {
            if (zeroCount > 1) {
                input[i] = 0;
            } else if (zeroCount > 0) {
                if (input[i] != 0) {
                    input[i] = 0;
                } else {
                    input[i] = total;
                }
            } else {
                input[i] = total / input[i];
            }
        }
        return input;
    }

    public static void main(String[] args) {
        int[] input = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(Arrays.toString(modify(input)));
    }
}
