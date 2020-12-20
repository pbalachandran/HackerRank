package com.hackerrank.linkedlistcycle;


import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

    static class Node {
        int data;
        Node next;

        public Node() {

        }
    }

    static boolean hasCycle(Node head) {
        List<Node> visited = new ArrayList<>();
        Node current = head;
        while(current != null) {
            if (!isVisited(current, visited)) {
                visited.add(current);
            } else {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    static boolean isVisited(Node node, List<Node> visited) {
        return visited.contains(node);
    }

    public static void main(String[] args) {

        // hasCycle = false
        Node head = new Node();

        Node two = new Node();
        head.next = two;

        Node three = new Node();
        two.next = three;

        System.out.println(hasCycle(head));

        // hasCycle = true
        head = new Node();

        two = new Node();
        head.next = two;

        three = new Node();
        two.next = three;

        three.next = two;

        System.out.println(hasCycle(head));
    }
}
