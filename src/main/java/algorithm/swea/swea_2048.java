package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_2048 {

    static int n, answer,map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = 0;
        map = new int[n][n];
        for(int i = 0 ; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        game(0);
    }

    public static void game(int count) {
        if(count == 5) {
//            findMax();
            return;
        }
        // map 초기화
        int copy[][] = new int[n][n];
        for(int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        for(int i = 0; i < 4; i++) {
            move(i);
            game(count+1);
            // map 초기화
            for(int a = 0; a < n; a++) {
                map[a] = copy[a].clone();
            }
        }
    }

    public static void move(int dir) {
        switch(dir) {
            case  0:
                for(int i = 0; i < n; i++) {

                }
        }
    }
}

