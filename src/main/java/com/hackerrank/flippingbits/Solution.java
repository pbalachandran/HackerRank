package com.hackerrank.flippingbits;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the flippingBits function below.
    static long flippingBits(long n) {
        String bits = pad(n);
        String flipped = flip(bits);
        Long l = Long.valueOf(flipped,2);
        return l.longValue();
    }

    public static String pad(long aNumber) {
        int pad = 32 - Long.toBinaryString(aNumber).length();
        char[] padArray = new char[pad];
        Arrays.fill(padArray, '0');
        String padString = new String(padArray);
        String binString = padString + Long.toBinaryString(aNumber);
        return binString;
    }

    public static String flip(String binString) {
        StringBuffer flipBinaryRep = new StringBuffer();
        char[] bInputChar = binString.toCharArray();
        for (int i = 0; i < bInputChar.length; i++) {
            if (bInputChar[i] == '0') {
                flipBinaryRep.append('1');
            } else {
                flipBinaryRep.append('0');
            }
        }

        String s = flipBinaryRep.toString();
        return s;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long n = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = flippingBits(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
