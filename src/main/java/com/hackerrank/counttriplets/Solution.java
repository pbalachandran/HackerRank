package com.hackerrank.counttriplets;

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

public class Solution {

    static long countTriplets(List<Long> arr, long r) {
        List<Long> list = new ArrayList<>();
        for (Long element : arr) {
            if (element != 0 && element % r == 0) {
                list.add(element);
            }
        }

        long tripletCount = 0;
        long first, second, third;
        for(int i=0;i<list.size();i++) {
            first = list.get(i);
                for (int j = i + 1; j < list.size(); j++) {
                    second = list.get(j);
                    if (second / first == r) {
                        for (int k = j + 1; k < list.size(); k++) {
                            third = list.get(k);

                            if (third / second == r) {
                                tripletCount++;
                            }
                        }
                    }
            }
        }
        return tripletCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
