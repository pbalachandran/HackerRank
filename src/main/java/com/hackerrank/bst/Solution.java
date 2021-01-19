package com.hackerrank.bst;

import java.util.Scanner;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    public static int height(Node root) {
        return computeHeight(root) - 1;
    }

    private static int computeHeight(Node parent) {
        if (parent == null) {
            return 0;
        }
        int leftHeight =  computeHeight(parent.left);
        int rightHeight = computeHeight(parent.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static int count(Node root) {
        return countNodes(root);
    }

    private static int countNodes(Node parent) {
        if (parent == null) {
            return 0;
        }
        int leftCount =  countNodes(parent.left);
        int rightCount = countNodes(parent.right);
        return leftCount + rightCount + 1;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        int nodes = count(root);
        System.out.println(height);
        System.out.println(nodes);
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
