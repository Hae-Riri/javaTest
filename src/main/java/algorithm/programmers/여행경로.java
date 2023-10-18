package algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class 여행경로 {
    public static void main(String[] args) {
        String [][] tickets = {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}};
        String[] solution = solution(tickets);
        Arrays.stream(solution).forEach(System.out::println);
    }

    public static String[] solution(String[][] tickets) {
        sort(tickets);

        boolean [] visit = new boolean[tickets.length];
        List<String> cities = new ArrayList<>();

        cities.add("ICN");

        List<String> answer = new ArrayList<>();
        dfs(tickets, visit, cities, answer);

        return answer.toArray(new String[0]);
    }

    public static void dfs(String[][] tickets, boolean[] visit, List<String> cities, List<String> answer) {
        if(cities.size() == tickets.length + 1) {
            answer.addAll(cities);
            return;
        }
        for(int i = 0; i < tickets.length; i++) {
            if(!visit[i] && tickets[i][0].equals(cities.get(cities.size()-1))) {
                visit[i] = true;
                cities.add(tickets[i][1]);
                dfs(tickets, visit, cities, answer);
                if(answer.size() > 0) return;
                visit[i] = false;
                cities.remove(cities.size()-1);
            }
        }
    }

    public static void sort(String[][] tickets) {
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].equals(o2[0])) {
                    return o1[1].compareTo(o2[1]);
                }
                return o1[0].compareTo(o2[0]);
            }
        });
    }
}
