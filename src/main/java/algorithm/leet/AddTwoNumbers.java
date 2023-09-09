package algorithm.leet;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String str1 = getStr(l1);
        String str2 = getStr(l2);
        String shorter, longer;

        if(str2.length() <= str1.length()) {
            shorter = str2;
            longer = str1;
        }else{
            shorter = str1;
            longer = str2;
        }

        StringBuilder sb = new StringBuilder();
        int upAmount = 0;
        for(int i = 0; i < shorter.length(); i++) {
            int sum = (shorter.charAt(i) - '0') + (shorter.charAt(i) - '0') + upAmount;
            if(sum >= 10) {
                sb.append(String.valueOf(sum - 10));
                upAmount = 1;
            } else {
                sb.append(String.valueOf(sum));
                upAmount = 0;
            }
        }

        for(int i = shorter.length(); i < longer.length(); i++) {
            int sum = (longer.charAt(i) - '0') + (longer.charAt(i) - '0') + upAmount;
            if(sum >= 10) {
                sb.append(sum - 10);
                upAmount = 1;
            } else {
                sb.append(sum);
                upAmount = 0;
            }
        }

        System.out.println("str : " + sb);

        // List Node 생성
        ListNode answer = new ListNode(sb.charAt(sb.length() -1));
        for(int i = sb.length() -2; i >= 0; i--) {
            int val = sb.charAt(i) - '0';
            answer = new ListNode(val, answer);
        }
        return answer;
    }

    public String getStr(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node.next != null) {
            sb.append(String.valueOf(node.val));
            node = node.next;
        }
        sb.append(String.valueOf(node.val));
        return sb.toString();
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
