package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_주사위굴리기 {

    static int dice[] = {0,0,0,0,0,0};

    // 동서북남
    static int dx[] = {0, 0, 0, -1, 1};
    static int dy[] = {0, 1, -1, 0, 0};

    static int n, m, x, y, k;
    static int[][] map;
    static int[] controls;

    public static void main(String[] args) throws IOException {
        init();

        for(int i = 0; i < controls.length; i++) {
            int direction = controls[i];

            int nextX = x + dx[direction];
            int nextY = y + dy[direction];

            if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue;
            }

            // 이동
            x = nextX;
            y = nextY;
            dice = rotate(direction);

            //이동한 칸과 주사위 바닥 비교하여 숫자 세팅
            if(map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            // 주사위 윗면 출력
            System.out.println(dice[3]);
        }
    }

    // 주사위 바닥은 항상 1번째 인덱스. 윗면은 항상 3번째 인덱스
    // 이동한 칸에 쓰인 수가 0 이면 : 이동한 칸 <= 주사위바닥
    // 이동한 칸에 쓰인 수가 0이 아니면 : 주사위 바닥 <= 이동한 칸, 이동한 칸 = 0;

    // 방향에 따른 주사위 이동
    private static int[] rotate(int direction) {
        if(direction == 1) { //동
            return new int[]{dice[0], dice[5], dice[2], dice[4], dice[1], dice[3]};
        } else if(direction == 2) { //서
            return new int[]{dice[0], dice[4], dice[2], dice[5], dice[3], dice[1]};
        } else if(direction == 3) { //북
            return new int[]{dice[3], dice[0], dice[1], dice[2], dice[4], dice[5]};
        } else { // 남
            return new int[]{dice[1], dice[2], dice[3], dice[0], dice[4], dice[5]};
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        x = Integer.parseInt(split[2]);
        y = Integer.parseInt(split[3]);
        k = Integer.parseInt(split[4]);

        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        controls = new int[k];
        split = br.readLine().split(" ");
        for(int i = 0; i < k; i++) {
            controls[i] = Integer.parseInt(split[i]);
        }
    }
}
