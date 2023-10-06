package algorithm.programmers;

public class 피보나치수 {

    public static void main(String[] args) {
        fibo fibo = new fibo();
        int solution = fibo.solution(5);
        System.out.println(solution);
    }

    static class fibo{

        int[] arr = new int[100001];

        public int solution(int n) {
            arr[0] = 0;
            arr[1] = 1;

            for(int i = 2; i <= n; i++) {
                arr[i] = arr[i-2] + arr[i-1];
            }
            return arr[n] % 1234567;
        }
    }
}
