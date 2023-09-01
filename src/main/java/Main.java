import search.Dfs_recursion;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        int answer = solution.solution(maps);
        System.out.println(answer);
    }
}

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visit = new boolean[n][m];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Point> points = new LinkedList<>();

        visit[0][0] = true;
        points.add(new Point(0, 0, 1));

        while(!points.isEmpty()) {
            Point poll = points.poll();
            if(poll.x == n -1 && poll.y == m-1) {
                return poll.dist;
            }
            for(int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];
                if(x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                if(!visit[x][y] && maps[x][y] == 1) {
                    visit[x][y] = true;
                    points.add(new Point(x, y, poll.dist + 1));
                }
            }
        }

        return -1;
    }

    class Point {
        int x;
        int y;
        int dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
