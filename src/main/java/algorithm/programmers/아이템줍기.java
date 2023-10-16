package algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 아이템줍기 {
    public static void main(String[] args) {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int solution = solution(rectangle, 1, 3, 7, 8);
        System.out.println(solution);
    }



    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[] dx = {-1, 1, 0 ,0};
        int[] dy = {0, 0, -1, 1};
        int minDist = Integer.MAX_VALUE;
        int[][] map = new int[101][101];
        boolean[][] visit = new boolean[101][101];

        for(int[] data : rectangle) {
            // 0, 2번째가 x값, 1,3번째가 y값
            // 꼭짓점 테두리를 포함해서 내부까지 전부 1로 만듦
            for(int i = data[0] * 2; i <= data[2] * 2; i++) {
                for(int j = data[1] * 2; j <= data[3] * 2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        for(int[] data : rectangle) {
            // 테두리 제외하고는 전부 0으로 바꿈. (이러면 테두리만 1로 남는다)
            for(int i = data[0] * 2 + 1; i < data[2] * 2; i++) {
                for(int j = data[1] * 2 + 1; j < data[3] * 2; j++) {
                    map[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(characterX * 2, characterY * 2, 0));
        visit[characterX * 2][characterY * 2] = true;

        while(!q.isEmpty()) {
            Point poll = q.poll();
            if(poll.x == itemX * 2 && poll.y == itemY * 2) {
                minDist = Integer.min(poll.dist, minDist);
            }

            for(int d = 0; d < 4; d++) {
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];
                if(nx < 0 || nx >= 101 || ny < 0 || ny >= 101 || map[nx][ny] == 0 || visit[nx][ny]) {
                    continue;
                }

                q.add(new Point(nx, ny, poll.dist + 1));
                visit[nx][ny] = true;
            }
        }

        return minDist / 2;
    }

    static class Point {
        int x;
        int y;
        int dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist= dist;
        }
    }
}
