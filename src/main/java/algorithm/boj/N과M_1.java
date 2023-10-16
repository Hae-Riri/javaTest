package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N과M_1 {
    static int n, m;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        nums = new int[n+1];

        for(int i = 1; i<= n; i++) {
            nums[i] = i;
        }

        backtracking(0, new int[n+1], new boolean[n+1]);
    }

    public static void backtracking(int cnt, int[] nums, boolean[] visit) {
        if(cnt == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1; i <= n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                nums[cnt] = i;
                backtracking(cnt+1, nums, visit);
                visit[i] = false;
            }
        }
    }
}
