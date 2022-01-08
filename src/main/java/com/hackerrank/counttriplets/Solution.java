package com.hackerrank.counttriplets;

import jdk.javadoc.internal.doclets.toolkit.util.ElementListWriter;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class Solution {

    public static class Element {
        Integer index;

        public Element(Integer index) {
            this.index = index;
        }
    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, List<Element>> map = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            Long key = arr.get(i);
            if (map.get(key) == null) {
                List<Element> list = new ArrayList<>();
                list.add(new Element(i));
                map.put(key, list);
            } else {
                List<Element> list = map.get(key);
                list.add(new Element(i));
                map.put(key, list);
            }
        }

        long tripletCount = 0;
        for (int i = 0; i < arr.size(); i++) {
            Long k1 = arr.get(i);
            Integer k1Index = i;

            Long k2 = k1 * r;
            if (map.get(k2) != null) {
                List<Element> k2List = map.get(k2).stream().filter(element -> element.index > k1Index).collect(toList());
                if (!k2List.isEmpty()) {
                    int k2Count = k2List.size();

                    Long k3 = k2 * r;

                    Integer k3Index = k2List.get(0).index;
                    if (map.get(k3) != null) {
                        List<Element> k3List = map.get(k3).stream().filter(element -> element.index > k3Index).collect(toList());
                        if (!k3List.isEmpty()) {
                            int k3Count = k3List.size();
                            System.out.println(i + "-" + "(" + k2Count + "," + k3Count + ")");
                            tripletCount += (k2Count * k3Count);
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
