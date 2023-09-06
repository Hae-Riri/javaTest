package algorithm.basic.search;

import java.util.LinkedList;

public class Dfs_recursion {
    private int n;
    private LinkedList<Integer> adj[]; //LinkedList 배열

    // 생성자
    public Dfs_recursion(int n) {
        this.n = n;
        adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge (int v, int w) {// v번째 LinkedList에 w 삽입
        adj[v].add(w);
    }

    public void dfs(int v) { //v가 시작노드
        boolean visited[] = new boolean[n];
        DFSUtil(v, visited); // 재귀를 감싸는 코드
    }

    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true; // 현재 노드는 방문
        System.out.print(v + " ");

        // 방문 노드와 인접한 노드들 가져오기
        for(Integer n : adj[v]) {
            if(!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }
}
