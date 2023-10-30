package algorithm.codility.timecomplexity;

import java.util.Arrays;

public class PermMissingElem {
    public static void main(String[] args) {
        int[] A = {2,3,4,5};
        int solution = solution(A);
        System.out.println(solution);
    }

    public static int solution(int[] A) {
        if(A.length == 0) {
            return 1;
        }
        Arrays.sort(A);
        for(int i = 1; i < A.length; i++) {
            if(A[i] - A[i-1] > 1) {
                return A[i-1] + 1;
            }
        }

        if(A.length == A[A.length -1]) {
            return A[A.length-1] + 1;
        }

        return 1;
    }
}
