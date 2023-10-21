package algorithm.programmers;
import java.util.*;

public class 못품_전력망을_돌로나누기 {
    static int[][] map = new int[101][101];
    public int solution(int n, int[][] wires) {
        int diff = Integer.MAX_VALUE;

        for(int i = 0; i < wires.length; i++) {
            map[wires[i][0]][wires[i][1]] = 1;
            map[wires[i][1]][wires[i][0]] = 1;
        }

        for(int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            // 잠시 끊고
            map[a][b] = 0;
            map[b][a] = 0;

            diff = Math.min(diff, bfs(n, a));

            // 다시 잇고
            map[a][b] = 1;
            map[b][a] = 1;
        }
        return diff;
    }

    public int bfs(int n, int a) {
        int visitCnt = 0;
        boolean[] visit = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();

        visit[a] = true;
        q.add(a);
        visitCnt++;

        while(!q.isEmpty()) {
            int poll = q.poll();
            for(int i = 1; i <= n; i++) {
                if(visit[i]) continue;
                if(map[poll][i] == 1) {
                    visit[i] = true;
                    visitCnt++;
                    q.add(i);
                }
            }
        }

        return Math.abs((n - visitCnt) - visitCnt);
    }
}
