package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ABCDE {
    static int n, m;
    static List<Integer>[] people;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        people = new List[n];
        for(int i = 0; i < n; i++) {
            people[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);

            people[a].add(b);
            people[b].add(a);
        }

        for(int i = 0; i < n; i++) {
            visit = new boolean[n];
            dfs(i, 0);
        }
        System.out.println(1);
    }

    public static void dfs(int x, int len) {
        if(len == 4) {
            System.out.println(1);
            System.exit(0);
        }
        visit[x] = true;
        for(int i = 0; i < people[x].size(); i++) {
            int now = people[x].get(i);
            if(!visit[now]) {
                visit[now]= true;
                dfs(now, len+1);
                visit[now] = false;
            }
        }
    }
}
