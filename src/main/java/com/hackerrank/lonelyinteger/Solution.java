package com.hackerrank.lonelyinteger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    // Complete the lonelyinteger function below.
    static int lonelyinteger(int[] a) {
        Map<String, Integer> map = new HashMap();

        Arrays.stream(a).forEach(value -> {
            Integer count = map.get(String.valueOf(value));
            if (count != null) {
                count = new Integer(count.intValue() + 1);
                map.put(String.valueOf(value), count);
            } else {
                map.put(String.valueOf(value), 1);
            }
        });

        String lonelyKey = map.keySet().stream().filter(key -> map.get(key).intValue() == 1).findFirst().orElse(null);
        return Integer.valueOf(lonelyKey).intValue();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = lonelyinteger(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
