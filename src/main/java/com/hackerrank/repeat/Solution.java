package com.hackerrank.repeat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {

        long numberOfAs = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a') {
                numberOfAs++;
            }
        }

        long sLength = s.length();
        long mFactor = n / sLength;

        long aCount;
        long firstPart = n % sLength;
        if (firstPart > 0) {
            long additionalNumberOfAs = 0;
            for (int i = 0; i < firstPart; i++) {
                if (chars[i] == 'a') {
                    additionalNumberOfAs++;
                }
            }
            aCount = numberOfAs * mFactor + additionalNumberOfAs;
        } else {
            aCount = numberOfAs * mFactor;
        }
        return aCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./output.txt"));

        System.out.println("Enter s:");
        String s = scanner.nextLine();

        System.out.println("Enter n:");
        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
