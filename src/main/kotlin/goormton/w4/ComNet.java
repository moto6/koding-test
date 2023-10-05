package goormton.w4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/*
7 6
1 3
5 3
3 7
7 1
2 4
4 6


1 3 5 7

 */
public class ComNet {

    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        //graph = new ArrayList[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];


        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        List<Integer> result = new ArrayList<>();
        double density = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                Node graph = doBfs(i);
                List<Integer> temp = graph.adjacentNodes;
                double tempDensity = graph.value;

                if (Math.abs(tempDensity - density) < 1e-8) {
                    if (result.size() > temp.size()) {
                        result = temp;
                        density = tempDensity;
                    } else if (result.size() == temp.size()) {
                        if (temp.get(0) < result.get(0)) {
                            result = temp;
                            density = tempDensity;
                        }
                    }
                } else if (tempDensity > density) {
                    result = temp;
                    density = tempDensity;
                }
            }
        }

        for (int node : result) {
            System.out.print(node + " ");
        }

    }

    private static Node doBfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        Set<Integer> components = new HashSet<>();
        for (; !q.isEmpty(); ) {
            int now = q.poll();

            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            components.add(now);
            for (int to : graph[now]) {
                if (!visited[to]) {
                    q.add(to);
                }
            }
        }
        int edge = 0;
        for (int i : components) {
            for (int to : graph[i]) {
                if (components.contains(to)) {
                    edge++;
                }
            }
        }
        List<Integer> sortedList = new ArrayList<>(components);
        Collections.sort(sortedList);
        double finalDensity = (double) edge / (double) components.size();
        return new Node(sortedList, finalDensity);
    }

    static class Node {

        List<Integer> adjacentNodes;
        double value;

        Node(List<Integer> adjacentNodes, double value) {
            this.adjacentNodes = adjacentNodes;
            this.value = value;
        }
    }
}
