package com.hackerrank.bfs.shortestreach;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static class Graph {
        public int nodeCount;
        public List<Node> nodes = new ArrayList<>();
        public List<Edge> edges = new ArrayList<>();

        public static class Node {
            public int value;
            public int distanceFromOrigin;
            public boolean isVisited = false;
            public List<Node> children = new ArrayList<>();

            public Node(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return value == node.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        public static class Edge {
            Node u;
            Node v;
            int weight;

            public Edge(Node u, Node v, int weight) {
                this.u = u;
                this.v = v;
                this.weight = weight;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Edge edge = (Edge) o;
                return weight == edge.weight &&
                        u.equals(edge.u) &&
                        v.equals(edge.v);
            }

            @Override
            public int hashCode() {
                return Objects.hash(u, v, weight);
            }
        }

        public Graph(int size) {
            this.nodeCount = size;
            for (int i = 0; i < nodeCount; i++) {
                nodes.add(new Node(i));
            }
        }

        public void addEdge(int first, int second) {
            Node u = findNodeWithValue(first);
            Node v = findNodeWithValue(second);

            Edge e = findEdge(u, v);
            if (e == null) {
                edges.add(new Edge(u, v, 6));
                edges.add(new Edge(v, u, 6));

                u.children.add(v);
                v.children.add(u);
            }
        }

        private Node findNodeWithValue(int value) {
            return nodes
                    .stream()
                    .filter(node -> node.value == value)
                    .findFirst()
                    .orElse(null);
        }

        private int findEdgeWeight(Node parent, Node child) {
            Edge e = findEdge(parent, child);
            return e.weight;
        }

        private Edge findEdge(Node parent, Node child) {
            return edges
                    .stream()
                    .filter(e -> e.u.value == parent.value && e.v.value == child.value)
                    .findFirst()
                    .orElse(null);
        }

        public int[] shortestReach(int nodeValue) {
            Node parent = findNodeWithValue(nodeValue);
            setShortestReaches(parent);

            List<Integer> shortestReaches = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                if (node.value != nodeValue && node.distanceFromOrigin == 0) {
                    node.distanceFromOrigin = -1;
                }
                shortestReaches.add(node.distanceFromOrigin);
            }
            return shortestReaches.stream().mapToInt(Integer::intValue).toArray();
        }

        private void setShortestReaches(Node parent) {
            parent.isVisited = true;
            List<Node> children = findChildren(parent);

            if (!children.isEmpty()) {
                Iterator<Node> iterator = children.iterator();
                while (iterator.hasNext()) {
                    Node child = iterator.next();
                    int distanceFromOrigin = parent.distanceFromOrigin + 6;
                    if (child.distanceFromOrigin == 0) {
                        child.distanceFromOrigin = distanceFromOrigin;
                    } else if (distanceFromOrigin < child.distanceFromOrigin) {
                        child.distanceFromOrigin = distanceFromOrigin;
                    }
                    setShortestReaches(child);
                }
            }
        }

        private List<Node> findChildren(Node parent) {
            List<Node> children = new ArrayList<>();
            for (int i = 0; i < parent.children.size(); i++) {
                Node c = parent.children.get(i);
                if (!c.isVisited) {
                    children.add(c);
                    c.isVisited = false;
                }
            }
            return children;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}