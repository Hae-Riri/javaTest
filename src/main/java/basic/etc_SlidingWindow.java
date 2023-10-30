package basic;

public class etc_SlidingWindow {
    public static void main(String[] args) {
        int [] arr = {2,1,5,1,3,2};
        int k = 3;

        int maxSum = findMaxSumSubarray(arr, k);
        System.out.println("Maximum sum of " + k + " elements" + maxSum);
    }

    private static int findMaxSumSubarray(int[] arr, int k) {
        int n = arr.length;
        if(n < k) {
            return -1;
        }

        int maxSum = 0;
        int currentSum = 0;

        for(int i = 0; i < k; i++) { //k 개까지 더해서 일단 currentSum 시작점 만들기
            currentSum += arr[i];
        }

        maxSum = currentSum;

        // 슬라이딩윈도우 시작하면서 maxSum을 업데이트하자
        for(int i = k; i < n; i++) {
            currentSum += arr[i] - arr[i-k];
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }
}
