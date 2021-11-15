package com.hackerrank.lca;

import java.util.*;
import java.io.*;

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
    public static Node lca(Node root, int v1, int v2) {
        List<Node> v1Chain = traverse(root, v1, new ArrayList<>());
        List<Node> v2Chain = traverse(root, v2, new ArrayList<>());
        return findMatch(v1Chain, v2Chain);
    }

    public static Node findMatch(List<Node> v1Chain, List<Node> v2Chain) {
        int limit = Math.min(v1Chain.size(), v2Chain.size());
        Node match = null;
        for (int i = 0; i < limit; i++) {
            if (v1Chain.get(i).data == v2Chain.get(i).data) {
                match = v1Chain.get(i);
            }
        }
        return match;
    }

    public static List<Node> traverse(Node start, int value, List<Node> nodeChain) {
        if (value < start.data) {
            nodeChain.add(start);
            traverse(start.left, value, nodeChain);
        } else if (value == start.data) {
            nodeChain.add(start);
        } else {
            nodeChain.add(start);
            traverse(start.right, value, nodeChain);
        }
        return nodeChain;
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
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root, v1, v2);
        System.out.println(ans.data);
    }
}