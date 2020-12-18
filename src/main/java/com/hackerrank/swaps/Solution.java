package com.hackerrank.swaps;

import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    private static int swaps = 0;

    // Complete the countSwaps function below.
    static void countSwaps(int[] a) {

        boolean done = false;
        while (!done) {
            a = swap(a);
            done = checkDone(a);
        }
        System.out.println("Array is sorted in " + swaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[a.length - 1]);
    }

    private static int[] swap(int[] a) {

        int currentIndex;
        int previousIndex = -1;
        for (int i = 0; i < a.length; i++) {
            currentIndex = i;

            if (previousIndex == -1) {
                previousIndex = currentIndex;
                continue;
            }

            if (a[currentIndex] < a[previousIndex]) {
                int temp = a[currentIndex];
                a[currentIndex] = a[previousIndex];
                a[previousIndex] = temp;
                swaps++;
            }
            previousIndex = currentIndex;
        }
        return a;
    }

    private static boolean checkDone(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        countSwaps(a);

        scanner.close();
    }
}
