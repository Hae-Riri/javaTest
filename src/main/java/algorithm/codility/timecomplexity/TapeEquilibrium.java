package algorithm.codility.timecomplexity;

import java.util.Arrays;

public class TapeEquilibrium {
    public int solution(int[] A) {
        int ans = Integer.MAX_VALUE;

        // P는 1부터 N-1까지 가능.
        int [] sum = new int[2];
        sum[1] = Arrays.stream(A).sum();

        for(int p = 1; p <= A.length -1; p++) {
            sum[0] += A[p-1];
            sum[1] -= A[p-1];

            ans = Math.min(ans, Math.abs(sum[0] - sum[1]));
        }
        return ans;
    }
}
