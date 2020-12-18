package com.hackerrank.newyearchaos;

import java.util.*;

public class Solution {

    static void minimumBribes(int[] q) {
        Map<String, Integer> qMap = new HashMap<>();
        Map<String, Integer> oMap = new HashMap<>();
        for (int i = 0; i < q.length; i++) {
            qMap.put(String.valueOf(i), q[i]);
            oMap.put(String.valueOf(i), i + 1);
        }

        boolean done = false;

        Iterator<String> qIter = qMap.keySet().iterator();
        Iterator<String> oIter = oMap.keySet().iterator();

        int numberOfBribes = 0;
        while (!done) {
            String theKey = qIter.next();

            int qValue = qMap.get(theKey);
            int oValue = oMap.get(theKey);
            if (qValue != oValue) {
                // bribe happened
                int perPersonBribes = qValue - Integer.valueOf(theKey) - 1;

                if (perPersonBribes > 2) {
                    System.out.println("Too chaotic");
                    return;
                }

                int index = 1;
                if (perPersonBribes > 0) {
                    numberOfBribes += perPersonBribes;
                    while (perPersonBribes > 0) {
                        final int qValueIndex = findValueIndex(qValue, oMap) - index++;
                        String initialKey = oMap.keySet().stream().filter(key -> key.equals(String.valueOf(qValueIndex))).findFirst().orElse(null);
                        int initialValue = oMap.get(initialKey);

                        String upKey = oMap.keySet().stream().filter(key -> key.equals(String.valueOf(Integer.valueOf(initialKey) - 1))).findFirst().orElse(null);
                        int tempUpValue = oMap.get(String.valueOf(Integer.valueOf(initialKey) - 1));
                        oMap.put(upKey, initialValue);
                        oMap.put(initialKey, tempUpValue);
                        perPersonBribes--;
                    }
                } else {
                    numberOfBribes += perPersonBribes * -1;
                    while (perPersonBribes < 0) {
                        final int qValueIndex = findValueIndex(qValue, oMap) + index++;
                        String initialKey = oMap.keySet().stream().filter(key -> key.equals(String.valueOf(qValueIndex))).findFirst().orElse(null);
                        int initialValue = oMap.get(initialKey);

                        String downKey = oMap.keySet().stream().filter(key -> key.equals(String.valueOf(Integer.valueOf(initialKey) - 1))).findFirst().orElse(null);
                        int tempDownValue = oMap.get(String.valueOf(Integer.valueOf(initialKey) - 1));
                        oMap.put(downKey, initialValue);
                        oMap.put(initialKey, tempDownValue);
                        perPersonBribes++;
                    }
                }
            }
            done = checkDone(qMap, oMap);
        }
        System.out.println(numberOfBribes);
    }

    private static int findValueIndex(int value, Map<String, Integer> target) {
        return target.values().stream().filter(tValue -> tValue.equals(value)).findFirst().orElse(-1);
    }

    private static boolean checkDone(Map<String, Integer> qMap, Map<String, Integer> oMap) {
        Iterator<String> iter = qMap.keySet().iterator();

        while (iter.hasNext()) {
            String key = iter.next();
            if (qMap.get(key) != oMap.get(key)) {
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}