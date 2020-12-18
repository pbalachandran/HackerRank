package com.hackerrank.valleys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

class Result {

    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    public static int countingValleys(int steps, String path) {
        char[] pathUnits = path.toCharArray();

        int numberOfValleys = 0;


        int index = 0;

        int uNumber = 0;
        int dNumber = 0;

        char currentStep = ' ';
        while (index < steps) {
            currentStep = pathUnits[index];
            if (currentStep == 'U') {
                uNumber++;
                if (uNumber == dNumber) {
                    numberOfValleys++;
                    uNumber = 0;
                    dNumber = 0;
                }
                index++;
            } else {
                dNumber++;
                if (uNumber == dNumber) {
                    uNumber = 0;
                    dNumber = 0;
                }
                index++;
            }
        }
        return numberOfValleys;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./output.txt"));

        System.out.println("Enter steps: ");

        int steps = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println("Steps: " + steps);

        System.out.println("Enter path: ");

        String path = bufferedReader.readLine();

        System.out.println("Path: " + path);

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
