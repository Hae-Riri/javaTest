package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 통계학 {
    static int sum;
    static int max = Integer.MIN_VALUE; // 평균, 중앙값, 젤자주나오는 값, 범위
    static int min = Integer.MAX_VALUE;
    static int n;
    static Map<Integer, Integer> map;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nums = new int[n];
        map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
            map.put(num, map.getOrDefault(num, 0) + 1);
            sum += num;

            max = Integer.max(num, max);
            min = Integer.min(num, min);
        }

        double avg = (double) sum / (double) n;
        String avgStr = String.valueOf((int)(avg * 10));
        if(avgStr.charAt(avgStr.length()-1) -'0' >= 5 && avg > 0) {
            System.out.println((int) avg + 1);
        }
        else if(avgStr.charAt(avgStr.length()-1) -'0' >= 5 && avg < 0) {
            System.out.println((int) avg -1);
        }
        else {
            System.out.println((int)avg);
        }

        Arrays.sort(nums);
        System.out.println(nums[n/2]);

        List<Info> infos = new ArrayList<>();
        for(Integer key: map.keySet()) {
            infos.add(new Info(key, map.get(key)));
        }

        infos.sort((e1, e2) -> {
            if(e1.cnt == e2.cnt) {
                return Integer.compare(e1.n, e2.n);
            }
            return Integer.compare(e2.cnt, e1.cnt);
        });
        if(infos.size() > 1 && infos.get(0).cnt == infos.get(1).cnt) {
            System.out.println(infos.get(1).n);
        } else {
            System.out.println(infos.get(0).n);
        }

        System.out.println(max - min);
    }

    public static class Info{
        public int n;
        public int cnt;
        public Info(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
}
