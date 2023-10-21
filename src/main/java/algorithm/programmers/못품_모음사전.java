package algorithm.programmers;

import java.util.*;

public class 못품_모음사전 {
    static final char[] WORDS = {'A', 'E', 'I', 'O', 'U'};
    static final int MAX_LENGTH = 5;
    static final List<String> elements = new ArrayList<>();

    public static void main(String[] args) {
        못품_모음사전 못품_모음사전 = new 못품_모음사전();
        int answer = 못품_모음사전.solution("AAAAE");
        System.out.println(answer);
    }

    public int solution(String word) {
        int answer = 0;
        for(int i = 0; i < WORDS.length; i++) {
            dfs("" + WORDS[i]);
        }

        for(int i = 0; i < elements.size(); i++) {
            if(word.equals(elements.get(i))) {
                return 1 + i;
            }
        }
        return answer;
    }

    public void dfs(String str) {
        if(str.length() > MAX_LENGTH) {
            return;
        }

        if(!elements.contains(str)) {
            elements.add(str);
        }

        for(int i = 0; i < WORDS.length; i++) {
            dfs(str + WORDS[i]);
        }
    }
}
