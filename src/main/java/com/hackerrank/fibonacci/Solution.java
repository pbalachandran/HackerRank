package com.hackerrank.fibonacci;

import java.util.Scanner;

public class Solution {

    public static int fibonacci(int n) {
        // f(0) = 0
        // f(1) = 1
        // f(n) = f(n-1) + f(n-2)

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
