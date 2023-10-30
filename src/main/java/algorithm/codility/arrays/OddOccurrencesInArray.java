package algorithm.codility.arrays;

import java.util.*;
public class OddOccurrencesInArray {
    public int solution(int[] A) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i =0; i < A.length; i++) {
            cnt.put(A[i], cnt.getOrDefault(A[i], 0) + 1);
        }

        for(Integer key : cnt.keySet()) {
            if(cnt.get(key) % 2 != 0) {
                return key;
            }
        }

        return 0;
    }
}
