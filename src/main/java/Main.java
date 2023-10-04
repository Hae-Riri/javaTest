import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    // 상화좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visit;
    static int r;
    static int c;
    static int k;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        r = Integer.parseInt(s[0]);
        c = Integer.parseInt(s[1]);
        k = Integer.parseInt(s[2]);

        map = new char[r][c];
        visit = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            String s1 = br.readLine();
            for(int j = 0; j < c; j++) {
                map[i][j] = s1.charAt(j);
            }
        }

        // 시작: r-1, 0, 끝: 0, c-1
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(r-1, 0, 1));
        visit[r-1][0] = true;


        System.out.println(answer);
    }

    static class XY {
        int x;
        int y;
        int dist;
        List<XY> xy = new ArrayList<>();

        public XY(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}