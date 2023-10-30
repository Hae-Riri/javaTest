package algorithm.codility.arrays;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CyclicRotation {
    public static int[] solution(int[] A, int K) {
        if(A.length == 0 || K == 0) {
            return A;
        }
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < A.length; i++) {
            deque.add(A[i]);
        }

        for(int i = 0; i < K; i++) {
            Integer last = deque.pollLast();
            deque.addFirst(last);
        }

        List<Integer> collect = new ArrayList<>(deque);
        int[] ans = new int[collect.size()];
        for(int i = 0; i < collect.size(); i++) {
            ans[i] = collect.get(i);
        }
        return ans;
    }

    public static int[] 답안(int[] A, int K) {
        if(A.length == 0) return A;

        for(int i = 0; i < K; i++) {
            int firstNum = A[A.length-1];

            for(int j = A.length-1; j > 0; j--) {
                A[j] = A[j-1];
            }
            A[0] = firstNum;
        }
        return A;
    }
}
