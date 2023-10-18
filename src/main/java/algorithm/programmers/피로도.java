package algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

public class 피로도 {

    public static void main(String[] args) {
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        int solution = solution(80, dungeons);
        System.out.println(solution);
    }

    static int answer = -1;

    public static int solution(int k, int[][] dungeons) {

        permutation(k, new boolean[dungeons.length], dungeons, new ArrayList<>());

        return answer;
    }

    public static void permutation(int k, boolean[] visit, int[][] dungeons, List<Integer> tired) {
        if(tired.size() == dungeons.length * 2) {
            int cnt = 0;
            for(int i = 0; i < tired.size()-1; i +=2) {
                Integer min = tired.get(i);
                Integer spend = tired.get(i+1);
                if(k >= min) {
                    k -= spend;
                    cnt++;
                } else {
                    break;
                }
            }
            answer = Integer.max(cnt, answer);
        }

        for(int i = 0; i < dungeons.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                tired.add(dungeons[i][0]);
                tired.add(dungeons[i][1]);
                permutation(k, visit, dungeons, tired);
                visit[i] = false;
                tired.remove(tired.size()-1);
                tired.remove(tired.size()-1);
            }
        }
    }
}
