package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class swea_주사위굴리기2 {
    static int n, m, k;
    static int map[][];
    static boolean visit[][];

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int d = 1;
    static int[] dice = {2,6,5,1,4,3}; //0 1 2 3 4 5
    static int score = 0;
    static XY xy = new XY(0,0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        k = Integer.parseInt(split[2]);
        map = new int[n][m];
        visit = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for(int j = 0; j < m ; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        while(k > 0) {
            k--;
            int cnt = 0; //방문할 수 있는 칸 수
            clearVisit();

            // 일단 이동을 하고 (가능여부에 따라 방향 바꾸고)
            int nx = xy.x + dx[d];
            int ny = xy.y + dy[d];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                d = (d+2) % 4;
                nx = xy.x + dx[d];
                ny = xy.y + dy[d];
            }
            xy.x = nx;
            xy.y = ny;

            // 이동된 칸에 대해 bfs 돌리고
            // cnt 구했으면 이동할 칸의 B * cnt를 score에 더하고
            // cnt를 초기화하고 visit을 초기화하고.
            Queue<XY> q = new LinkedList<>();
            q.add(xy);
            visit[xy.x][xy.y] = true;

            while(!q.isEmpty()) {
                XY poll = q.poll();
                cnt++;
                for(int i = 0; i < 4; i++) {
                    int nextX = poll.x + dx[i];
                    int nextY = poll.y + dy[i];
                    if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visit[nextX][nextY]) {
                        continue;
                    }
                    if(map[nextX][nextY] == map[xy.x][xy.y]) {
                        q.add(new XY(nextX, nextY));
                        visit[nextX][nextY] = true;
                    }
                }
            }

            score += (map[xy.x][xy.y] * cnt);

            // 주사위를 굴린 다음 A:map, B:dice[1]을 비교해서 d 정하기.
            rotateDice(d);
            int B = map[xy.x][xy.y];
            int A = dice[1];
            if(A > B) {
                d = (d + 1) % 4;
            } else if(A < B) {
//                d = d -1;
//                if(d < 0) {
//                    d = 3;
//                }
                d = (d + 3) % 4;
            }
            // 암것도 아니면 d는 그대로임.
        }
        System.out.println(score);
    }

    static class XY {
        int x;
        int y;
        XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void clearVisit() {
        for(boolean[] row : visit) {
            Arrays.fill(row, false);
        }
    }

    public static void rotateDice(int d) {
        if (d == 0) { //북
            dice = new int[]{dice[2], dice[0], dice[1], dice[3], dice[4], dice[5]};
        } else if (d == 1) { // 동
            dice = new int[]{dice[0], dice[5], dice[2], dice[4], dice[1], dice[3]};
        } else if (d == 2) { // 남
            dice = new int[]{dice[1], dice[2], dice[3], dice[0], dice[4], dice[5]};
        } else { // 서
            dice = new int[]{dice[0], dice[4], dice[2], dice[5], dice[3], dice[1]};
        }
    }
}
