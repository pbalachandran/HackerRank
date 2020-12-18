package com.hackerrank.commonstring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        int index = 0;

        List<String> s1List = populateDictionary(s1);
        List<String> s2List = populateDictionary(s2);


        Iterator<String> iter = s2List.iterator();

        while(iter.hasNext()) {
            String word = iter.next();

            if (s1List.contains(word)) {
                return "YES";
            }
        }
        return "NO";
    }

    private static List<String> populateDictionary(String s) {
        int begin = 0;
        List<String> sList = new ArrayList<>();
        while(begin < s.length()) {
            int len = 1;
            while (len <= s.length() - begin) {
                String key = s.substring(begin, begin + len);
                if (!sList.contains(key)) {
                    sList.add(key);
                }
                len++;
            }
            begin++;
        }
        return sList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
