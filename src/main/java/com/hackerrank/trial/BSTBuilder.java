package com.hackerrank.trial;

public class BSTBuilder {

    public static Node build(int[] array, int start, int end) {
        if (end < start) {
            return null;
        } else {
            int mid = (start + end) / 2;
            Node lChild = build(array, start, mid - 1);
            Node rChild = build(array, mid + 1, end);
            Node parent = new Node(array[mid], lChild, rChild);
            return parent;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        Node root = build(array, 0, array.length - 1);
        System.out.println("Done");
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
