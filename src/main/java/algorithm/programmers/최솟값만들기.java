package algorithm.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 최솟값만들기 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] A = {1,4,2};
        int[] B = {5,4,4};

        solution.solution(A, B);
    }
}

class Solution2
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B); //Collections.reverseOrder 적용하려면 Integer 타입이어야 함.

        for(int i = 0; i < A.length; i++) {
            int j = B.length - i - 1;
            answer += A[i] * B[j];
        }

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println(answer);

        return answer;
    }
}