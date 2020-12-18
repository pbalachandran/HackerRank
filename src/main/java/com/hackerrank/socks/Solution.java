package com.hackerrank.socks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        Map<String, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            String colorKey = String.valueOf(ar[i]);

            if (map.keySet().contains(colorKey)) {
                int count = map.get(colorKey) + 1;
                map.put(colorKey, count++);
            } else {
                map.put(colorKey, 1);
            }
        }

        System.out.println("Map: " + map);


        int numPairs = 0;
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String colorKey = iter.next();
            int numSocks = map.get(colorKey);
            numPairs += numSocks / 2;
        }
        return numPairs;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./output"));

        System.out.println("Enter Total");
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        System.out.println("Enter space separated list of numbers");
        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        System.out.println(result);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
