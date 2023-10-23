package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12865_평범한배낭 {

    static int N, K;
    static int[][] dp; // i개의 물건을 j만큼의 무게 안에서 채울 때의 최대 가치
    static int[] v; // 가치 모음
    static int[] w; //무게 모음

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        dp = new int[N+1][K+1];

        //물품 번호가 인덱스로, 0부터 N-1까지의 물품 가능.
        v = new int[N + 1];
        w = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            s = br.readLine().split(" ");
            w[i] = Integer.parseInt(s[0]);
            v[i] = Integer.parseInt(s[1]);
        }

        for(int i = 1; i <=N; i++) {
            for(int j = 1; j <= K; j++) {
                if(j - w[i] >= 0) { // 현 무게에 지금 물건을 넣을수 있다면,
                    // 물건을 넣지 않는 것(기존까지의 물건에 동일한 무게)과 물건을 넣는 것(기존까지의 물건에 현무게 제거한 것 + 현가치)중에 최대 가치로 세팅
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i]);
                } else { // 물건을 못 넣는다면
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]); // N개까지 다 보면서 K 이내인 것을 만족하는 최대치


    }
}
