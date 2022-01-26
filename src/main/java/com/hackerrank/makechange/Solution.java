package com.hackerrank.makechange;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Make change for a given amount, using available denominations.
 */
public class Solution {

    public static class Coin {
        private int denomination;
        private int numberOfCoins;

        public Coin(int denomination, int numberOfCoins) {
            this.denomination = denomination;
            this.numberOfCoins = numberOfCoins;
        }

        public String toString() {
            return "Denomination: " + this.denomination + ", " + "NumberOfCoins: " + this.numberOfCoins;
        }
    }

    private static List<Coin> makeChange(int amount) {
       return process(amount, new ArrayList<>());
    }

    private static List<Coin> process(int amount, List<Coin> coins) {
        if (amount >= 25) {
            coins.add(new Coin(25, amount / 25));
            int balance = amount % 25;
            if (balance > 0) {
                process(balance, coins);
            }
        } else if (amount >= 10) {
            coins.add(new Coin(10, amount / 10));
            int balance = amount % 10;
            if (balance > 0) {
                process(balance, coins);
            }
        } else if (amount >= 5) {
            coins.add(new Coin(5, amount / 5));
            int balance = amount % 5;
            if (balance > 0) {
                process(balance, coins);
            }
        } else {
            coins.add(new Coin(1, amount));
        }
        return coins;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int amount = n;
        List<Coin> change = makeChange(amount);
        change.stream().forEach(coin -> System.out.println(coin.toString()));

        bufferedWriter.close();
        scanner.close();
    }
}
