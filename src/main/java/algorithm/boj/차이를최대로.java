package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 차이를최대로 {
    static int answer = 0;
    static int n;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        String[] s = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        dfs(new ArrayList<>(), new boolean[n]);

        System.out.println(answer);
    }

    public static void dfs(List<Integer> result, boolean[] visit) {
        if(result.size() == n) {
            setAnswer(result);
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                result.add(nums[i]);
                dfs(result, visit);
                visit[i] = false;
                result.remove(result.size()-1);
            }
        }
    }

    public static void setAnswer(List<Integer> result) {
        int val = 0;
        for(int i = 0; i < n -1; i++) {
            val += Math.abs(result.get(i) - result.get(i+1));
        }
        answer = Math.max(answer, val);
    }
}
