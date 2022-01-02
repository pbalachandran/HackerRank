package com.hackerrank.nearestclone;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */

    public static class Node implements Comparable<Node> {
        private boolean isVisited;
        private int nodeNumber;
        private long colorNumber;
        private List<Node> connections = new ArrayList<>();

        public Node(int nodeNumber, long colorNumber) {
            this.nodeNumber = nodeNumber;
            this.colorNumber = colorNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return nodeNumber == node.nodeNumber &&
                    colorNumber == node.colorNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeNumber, colorNumber);
        }

        @Override
        public int compareTo(Node that) {
            return Integer.valueOf(this.nodeNumber).compareTo(Integer.valueOf(that.nodeNumber));
        }
    }

    private static List<Node> nodes = new ArrayList<>();
    private static Map<Long, List<Node>> nodesByColor = new HashMap<>();
    private static int shortestPath = -1;

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {

        buildGraph(graphNodes, ids, graphFrom, graphTo);

        long targetColor = Long.valueOf(val);

        List<Node> targetColorNodes = nodesByColor.get(targetColor);

        if (targetColorNodes == null) {
            return shortestPath;
        }

        Iterator<Node> iterator = targetColorNodes.iterator();
        while (iterator.hasNext()) {
            Node origin = iterator.next();

            bfs(origin, targetColor);
            if (shortestPath == 1) {
                return shortestPath;
            }


//            origin.isVisited = true;
//
//            Iterator<Node> edgeNodesIterator = origin.connections.iterator();
//            while (edgeNodesIterator.hasNext()) {
//                Node edgeNode = edgeNodesIterator.next();
//                edgeNode.isVisited = true;
//                recurse(edgeNode, targetColor, 1);
//                if (shortestPath == 1) {
//                    return shortestPath;
//                }
//            }
        }
        return shortestPath;
    }

    private static void recurse(Node node, long targetColor, int minPath) {
        if (node.colorNumber == targetColor) {
            if (shortestPath == -1 || minPath < shortestPath) {
                shortestPath = minPath;
            }
        } else {
            Iterator<Node> iterator = node.connections.iterator();
            while (iterator.hasNext()) {
                Node edgeNode = iterator.next();
                if (!edgeNode.isVisited) {
                    minPath++;
                    edgeNode.isVisited = true;
                    recurse(edgeNode, targetColor, minPath);
                }
            }
        }
    }

    private static void bfs(Node origin, long targetColor) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(origin);

        List<Node> children = new ArrayList<>();

        int minPath = 0;
        while (true) {
            while (!pq.isEmpty()) {
                Node node = pq.remove();
                if (!node.isVisited) {
                    node.isVisited = true;
                }

                if (!node.equals(origin) && node.colorNumber == targetColor) {
                    if (shortestPath == -1 || minPath < shortestPath) {
                        shortestPath = minPath;
                    }

                    if (shortestPath == 1) {
                        return;
                    }
                }
                children.addAll(findAllChildren(node));
            }

            if (children.isEmpty()) {
                break;
            }

            pq.addAll(children);
            children = new ArrayList<>();
            minPath++;
        }
    }

    private static List<Node> findAllChildren(Node node) {
        List<Node> unvisited = new ArrayList<>();
        for (Node n : node.connections) {
            if (!n.isVisited) {
                unvisited.add(n);
            }
        }
        return unvisited;
    }

    private static void buildGraph(int graphNodes, long[] ids, int[] graphFrom, int[] graphTo) {
        for (int i = 0; i < graphNodes; i++) {
            long color = ids[i];
            Node node = new Node(i + 1, color);
            nodes.add(node);

            if (nodesByColor.keySet().contains(color)) {
                List<Node> nodes = nodesByColor.get(color);
                nodes.add(node);
            } else {
                List<Node> nodes = new ArrayList<>();
                nodes.add(node);
                nodesByColor.put(color, nodes);
            }
        }

        for (int j = 0; j < graphFrom.length; j++) {
            int from = graphFrom[j];
            int to = graphTo[j];

            Node fromNode = nodes.get(from - 1);
            Node toNode = nodes.get(to - 1);

            fromNode.connections.add(toNode);
            toNode.connections.add(fromNode);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
