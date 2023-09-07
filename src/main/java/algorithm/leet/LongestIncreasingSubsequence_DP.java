package algorithm.leet;

import java.util.Arrays;

public class LongestIncreasingSubsequence_DP {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;

        for(int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = Arrays.stream(dp)
                .max()
                .getAsInt();
        return answer;
    }
}
