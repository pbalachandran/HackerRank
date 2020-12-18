package com.hackerrank.ransomnote;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {


    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magazineMap = new HashMap<>();
        Map<String, Integer> noteMap = new HashMap<>();

        List<String> listOfMagazines = Arrays.asList(magazine);
        listOfMagazines.stream().forEach(mValue -> insert(mValue, magazineMap));

        List<String> listOfNotes = Arrays.asList(note);
        listOfNotes.stream().forEach(mValue -> insert(mValue, noteMap));

        Iterator<String> iter = noteMap.keySet().iterator();
        while(iter.hasNext()) {
            String noteKey = iter.next();
            if (magazineMap.keySet().contains(noteKey) && magazineMap.get(noteKey) >= noteMap.get(noteKey)) {
                continue;
            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static void insert(String key, Map<String, Integer> map) {
        if (map.keySet().contains(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
