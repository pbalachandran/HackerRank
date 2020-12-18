package com.hackerrank.queue;

import java.util.*;

public class Solution {

    public static class MyQueue<T> {
        public T type;
        public Stack<T> stack = new Stack<>();

        public void enqueue(T item) {
            if (isOfType(item)) {
                stack.push(item);
            }
        }

        public T dequeue() {
            if (!isEmpty()) {
                T target = stack.elementAt(0);
                stack.removeElementAt(0);
                return target;
            } else {
                return null;
            }
        }

        public T peek() {
            if (!isEmpty()) {
                T target = stack.elementAt(0);
                System.out.println(target);
                return target;
            } else {
                return null;
            }
        }

        public boolean isOfType(T value) {
            return value instanceof Integer;
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                queue.peek();
            }
        }

        System.out.println("Done");
        scan.close();
    }
}