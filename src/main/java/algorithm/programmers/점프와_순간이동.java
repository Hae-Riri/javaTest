package algorithm.programmers;

public class 점프와_순간이동 {
    public static void main(String[] args) {
        int solution = solution(6);
        System.out.println(solution);
    }

    public static int solution(int n) {
        int ans = 0;
        String s = Integer.toBinaryString(n);
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1') {
                ans++;
            }
        }
        return ans;
    }
}