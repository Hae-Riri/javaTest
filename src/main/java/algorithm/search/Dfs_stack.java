package algorithm.search;

import java.util.LinkedList;
import java.util.Stack;

public class Dfs_stack {
    private int n;
    private LinkedList<Integer> adj[];

    public Dfs_stack(int n) {
        this.n = n;
        this.adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void dfs(int v) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while(!stack.isEmpty()) {
            v = stack.pop();
            if(!visited[v]) {
                System.out.print(v + " ");
                visited[v] = true;
            }

            for(Integer n : adj[v]) {
                if(!visited[n]) {
                    stack.push(n);
                }
            }
        }
    }
}
