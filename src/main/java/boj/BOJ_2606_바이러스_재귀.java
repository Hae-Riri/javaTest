package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_2606_바이러스_재귀 {
    static int n;
    static LinkedList<Integer> adj[];
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // init
        answer = 0;
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n];
        adj = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }

        int networks = Integer.parseInt(br.readLine());
        for(int i = 0; i < networks; i++) {
            String[] splits = br.readLine().split(" ");
            adj[Integer.parseInt(splits[0]) - 1].add(Integer.parseInt(splits[1]) - 1);
            adj[Integer.parseInt(splits[1]) - 1].add(Integer.parseInt(splits[0]) - 1);
        }

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int v) {
        visit[v] = true;

        for(int i = 0; i < adj[v].size(); i++) {
            int num = adj[v].get(i);
            if(!visit[num]) {
                dfs(num);
                answer++;
            }
        }
    }
}
