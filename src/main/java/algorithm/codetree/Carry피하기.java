package algorithm.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Carry피하기 {

    static int n;
    static int[] nums;
    static boolean findAns;

    public static void main(String[] args) throws IOException {
        init();
        int nowCnt = n;

        while(nowCnt > 0) {
            combination(0, nowCnt, 0, new ArrayList<>());
            if(findAns) {
                System.out.println(nowCnt);
                break;
            }
            nowCnt--;
        }
    }

    public static void combination(int cnt, int n, int idx, List<Integer> numbers) {
        if(cnt == n) {
            if(!carry(numbers)) {
                findAns = true;
            }
            return;
        }
        for(int i = idx; i < nums.length; i++) {
            numbers.add(nums[i]);
            combination(cnt+1, n, i +1, numbers);
            numbers.remove(numbers.size() - 1);
        }
    }

    public static boolean carry(List<Integer> numbers) {
        int[] sum = new int[10];

        for(int number : numbers) {
            int position = 0;
            while(number > 0) {
                int digit = number % 10;
                sum[position] += digit;
                if(sum[position] >= 10) {
                    return true;
                }
                number /= 10;
                position++;
            }
        }
        return false;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
    }
}
