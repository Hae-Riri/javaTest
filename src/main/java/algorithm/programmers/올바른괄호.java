package algorithm.programmers;

import java.util.Stack;

public class 올바른괄호 {
    public static void main(String[] args) {
        boolean solution = solution("(()))))");
        System.out.println(solution);
    }

    public static boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        answer = stack.isEmpty();
        return answer;
    }
}
