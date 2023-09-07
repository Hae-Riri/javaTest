package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class PG_다단계칫솔판매 {
    public static void main(String[] args) {
        String [] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String [] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String [] seller = {"sam", "emily", "jaimie", "edward"};
        int[] amount = {2, 3, 5, 4};
        Solution solution = new Solution();
        int[] solution1 = solution.solution(enroll, referral, seller, amount);
        for(int i = 0; i < solution1.length; i++) {
            System.out.print(solution1[i] + " ");
        }
    }
}

class Solution {

    private Map<String, String> parents;// child -> Parent
    private Map<String, Integer> results; // -는 민호.

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        init(enroll, referral);
        calculate(seller, amount);

        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = results.get(enroll[i]);
        }
        return answer;
    }

    private void init(String[] enroll, String[] referral) {
        parents = new HashMap<>();
        results = new HashMap<>();
        results.put("-", 0);

        for(int i = 0; i < enroll.length; i++) {
            String child = enroll[i];
            results.put(child, 0);

            String parent = referral[i];
            parents.put(child, parent);
        }
    }

    private void calculate(String[] seller, int[] amount) {
        for(int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            double sellPrice = amount[i] * 100;

            getParent(sellerName, sellPrice);

            // 디버깅용 출력
            System.out.println("---");
            for(String key : results.keySet()) {
                System.out.println(key + ": " + results.get(key));
            }
        }
    }

    private void getParent(String child, double price) {
        if("-".equals(child)) {
            results.put("-", results.getOrDefault("-", 0) + (int)price);
            return;
        }

        double calculated = price * 0.1;
        if(calculated >= 1) {
            results.put(child, results.getOrDefault(child, 0) + (int)(price) - (int)calculated); // mary: 11
            getParent(parents.get(child), calculated); // -, 1
        } else {
            results.put(child, results.getOrDefault(child, 0) + (int)price);
            return;
        }
    }
}
