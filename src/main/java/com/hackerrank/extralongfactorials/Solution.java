package com.hackerrank.extralongfactorials;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static void extraLongFactorials(int n) {
        BigInteger bigInteger = BigInteger.valueOf(n);
        BigInteger result = factorial(bigInteger);
        System.out.println(result);
    }

    public static BigInteger factorial(BigInteger value) {
        if (value.equals(BigInteger.ONE)) {
            return value;
        } else {
            return value.multiply(factorial(value.subtract(BigInteger.ONE)));
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.extraLongFactorials(n);

        bufferedReader.close();
    }
}