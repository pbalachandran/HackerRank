package com.hackerrank.clouds;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int jumps = 0;

        int i = 0;
        if (c.length > 2 && c[i + 2] == 0) {
            // double jump
            i = i + 2;

            jumps++;

            int shortLen = c.length - 2;
            int[] cShortened = new int[shortLen];
            System.arraycopy(c, i, cShortened, 0, shortLen);
            jumps += jumpingOnClouds(cShortened);
        } else if (c.length > 1) {
            // single jump
            i = i + 1;

            jumps++;

            int shortLen = c.length - 1;
            int[] cShortened = new int[shortLen];
            System.arraycopy(c, i, cShortened, 0, shortLen);
            jumps += jumpingOnClouds(cShortened);
        }
        return jumps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        System.out.println("Enter Total Clouds: ");
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        System.out.println("Enter Cloud Sequence: ");
        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
