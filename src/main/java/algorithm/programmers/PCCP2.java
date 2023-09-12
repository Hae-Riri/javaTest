package algorithm.programmers;

public class PCCP2 {
    public static void main(String[] args) {
        PCCP2 pccp2 = new PCCP2();
        int [][] ability = {{40, 10, 10},{20, 5, 0},{30, 30, 30},{70, 0, 70},{100, 100, 100}};
        pccp2.solution(ability);
    }

    // permutation 으로 student를 돌면서 순서를 고려해서 kindCnt만큼을 순서 고려해서 뽑기.
    int answer = 0;
    int kindCnt;
    int studentCnt;
    boolean[] visit;

    public int solution(int[][] ability) {
        kindCnt = ability[0].length;
        studentCnt = ability.length;
        visit = new boolean[studentCnt];

        permutation(0, ability, 0);
        System.out.println(answer);
        return answer;
    }

    public void permutation(int depth, int[][] ability, int value) {
        if(depth == kindCnt) {
            answer = Math.max(value, answer);
            return;
        }
        for(int i = 0; i < studentCnt; i++) {
            if(!visit[i]) {
                visit[i] = true;
                permutation(depth + 1, ability, value + ability[i][depth]);
                visit[i] = false;
            }
        }
    }
}
