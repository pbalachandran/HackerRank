package com.hackerrank.bst.buildetc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

    public static class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        public boolean hasChildren() {
            return leftChild != null || rightChild != null;
        }
    }


    public static Node build() {
        int[] arr = {1, 2, 3};
        return recurse(arr, 0, arr.length - 1);
    }

    public static Node recurse(int[] input, int start, int end) {
        if (end < start) {
            return null;
        }

        int midpoint = (start + end) / 2;
        Node parent = new Node(input[midpoint]);

        Node leftChild = recurse(input, start, midpoint - 1);
        Node rightChild = recurse(input, midpoint + 1, end);
        parent.leftChild = leftChild;
        parent.rightChild = rightChild;

        return parent;
    }

    public static List<LinkedList<Node>> bfsBuildDepthLists(Node root) {
        List<LinkedList<Node>> depthLists = new ArrayList<>();

        LinkedList<Node> current = new LinkedList<>();

        current.add(root);

        while (!current.isEmpty()) {
            depthLists.add(current);

            LinkedList<Node> parents = new LinkedList<>();
            parents.addAll(current);

            current = new LinkedList<>();
            for (Node parent : parents) {
                if (parent.leftChild != null) {
                    current.add(parent.leftChild);
                }

                if (parent.rightChild != null) {
                    current.add(parent.rightChild);
                }
            }
        }
        return depthLists;
    }

    public static Node search(int target, Node parent) {
        if (parent != null && target < parent.value) {
            return search(target, parent.leftChild);
        } else if (parent != null && target > parent.value) {
            return search(target, parent.rightChild);
        } else {
            return parent;
        }
    }

    public static int height(Node root) {
        return computeHeight(root) - 1;
    }

    private static int computeHeight(Node parent) {
        if (parent == null) {
            return 0;
        }
        int leftHeight =  computeHeight(parent.leftChild);
        int rightHeight = computeHeight(parent.rightChild);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static int count(Node root) {
        return countNodes(root);
    }

    private static int countNodes(Node parent) {
        if (parent == null) {
            return 0;
        }
        int leftCount =  countNodes(parent.leftChild);
        int rightCount = countNodes(parent.rightChild);
        return leftCount + rightCount + 1;
    }

    public static void main(String[] args) {
        Node theRoot = build();

        Node targetNode = search(13, theRoot);
        int totalNodes = count(theRoot);
        int height = height(theRoot);
        List<LinkedList<Node>> depthLists = bfsBuildDepthLists(theRoot);
    }
}
