package algorithm.programmers;

public class 숫자의표현 {
    public static void main(String[] args) {
        int solution = solution(78);
        System.out.println(solution);
    }

    public static int solution(int n) {
        int answer = n;
        int cnt = getCntOfBinary(n);

        while(true) {
            answer++;
            if(cnt == getCntOfBinary(answer)) {
                break;
            }
            if(answer == 1000000) {
                break;
            }
        }
        return answer;
    }

    public static int getCntOfBinary(int n) {
        int cnt = 0;
        String s = Integer.toBinaryString(n);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }
}
