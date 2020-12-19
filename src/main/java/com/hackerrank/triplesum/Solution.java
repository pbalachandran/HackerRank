package com.hackerrank.triplesum;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        Set<Integer> aSet = new HashSet<>();
        Arrays.stream(a).forEach(aValue -> aSet.add(aValue));
        Integer[] aArray = aSet.toArray(new Integer[aSet.size()]);
        Arrays.sort(aArray);

        Set<Integer> bSet = new HashSet<>();
        Arrays.stream(b).forEach(bValue -> bSet.add(bValue));
        Integer[] bArray = bSet.toArray(new Integer[bSet.size()]);
        Arrays.sort(bArray);

        Set<Integer> cSet = new HashSet<>();
        Arrays.stream(c).forEach(cValue -> cSet.add(cValue));
        Integer[] cArray = cSet.toArray(new Integer[cSet.size()]);
        Arrays.sort(cArray);

        int distinctTriplets = 0;

        List<String> triplets = new ArrayList<>();
        for (int bIndex = 0; bIndex < bArray.length; bIndex++) {
            int bValue = bArray[bIndex];
            int aStop = findBValueIndexInTargetArray(bValue, aArray);
            int cStop = findBValueIndexInTargetArray(bValue, cArray);

            if (aStop == -1 || cStop == -1) {
                continue;
            }
            distinctTriplets += (aStop + 1) * (cStop + 1);
        }
        return distinctTriplets;
    }

    private static int findBValueIndexInTargetArray(int bValue, Integer[] targetArray) {
        int lastIndex = -1;
        for (int i = 0; i < targetArray.length; i++) {
            int targetValue = targetArray[i];
            if (targetValue < bValue) {
                lastIndex = i;
                continue;
            } else if (targetValue == bValue) {
                return i;
            } else {
                return i - 1;
            }
        }
        return lastIndex;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
