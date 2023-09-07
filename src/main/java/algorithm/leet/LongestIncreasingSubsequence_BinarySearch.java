package algorithm.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence_BinarySearch {
    public int lengthOfLIS(int[] nums) {
        List<Integer> bs = new ArrayList<>();
        bs.add(nums[0]);

        for(int i = 1; i < nums.length; i++) {
            if(bs.get(bs.size()-1) < nums[i]) {
                bs.add(nums[i]);
            } else {
                int location = findLocation(bs, nums[i], 0, bs.size() - 1);
                bs.set(location, nums[i]);
            }
        }

        return bs.size();
    }

    public int findLocation(List<Integer> bs, int value, int startIdx, int endIdx) {
        while(startIdx < endIdx) {
            int midIdx = (startIdx + endIdx) / 2;
            if(value <= bs.get(midIdx)) {
                endIdx = midIdx;
            } else {
                startIdx = midIdx + 1;
            }
        }
        return startIdx;
    }
}
