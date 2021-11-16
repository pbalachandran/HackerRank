package com.hackerrank.isbst;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> referenceCount = new HashMap<>();

    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {

        if (root.left != null) {
            if (root.left.data <= root.data) {
                if (isDuplicate(root.left.data)) {
                    return false;
                }
                checkBST(root.left);
            } else {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.data > root.data) {
                if (isDuplicate(root.right.data)) {
                    return false;
                }
                checkBST(root.right);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isDuplicate(Integer key) {
        boolean isDuplicate = false;
        if (referenceCount.keySet().contains(key)) {
            Integer count = referenceCount.get(key);
            referenceCount.put(key, count + 1);
            isDuplicate = true;
        } else {
            referenceCount.put(key, 1);
        }
        return isDuplicate;
    }
}
