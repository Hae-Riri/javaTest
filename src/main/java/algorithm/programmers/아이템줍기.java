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
        int[][] map = new int[101][101]; // 1~100
        int answer = Integer.MAX_VALUE;
        initMap(map, rectangle);

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<XY> q = new LinkedList<>();
        boolean[][] visit = new boolean[101][101];

        q.add(new XY(characterX, characterY, 0));
        visit[characterX][characterY] = true;

        while(!q.isEmpty()) {
            XY now = q.poll();
            if(now.x == itemX && now.y == itemY) {
                answer = Integer.min(answer, now.dist / 2);
            }
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || nx >= 101 || ny < 0 || ny >= 101 || visit[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                q.add(new XY(nx, ny, now.dist+1));
                visit[nx][ny] = true;
            }
        }
        return answer;
    }

    static class XY {
        int x;
        int y;
        int dist;
        public XY(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void initMap(int[][] map, int[][] rectangle) {
        for(int[] points: rectangle) {
            // 전체를 1로 만들고
            for(int i = points[0] * 2; i <= points[2] * 2; i++) {
                for(int j = points[1] * 2; j <= points[3] * 2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        for(int[] points : rectangle) {
            // 내부를 0으로 만들어서 테두리만 1로 남기기
            for(int i = points[0] * 2 + 1; i < points[2] * 2 ; i++) {
                for(int j = points[1] * 2 + 1; j < points[3] * 2; j++) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
