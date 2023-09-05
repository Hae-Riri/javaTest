package algorithm.search;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {
    private int n;
    private LinkedList<Integer> adj[];

    public Bfs(int n, LinkedList<Integer>[] adj) {
        this.n = n;
        this.adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void bfs(int v) {
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();

            for(Integer n : adj[v]) {
                if(!visited[v]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }
    }
}