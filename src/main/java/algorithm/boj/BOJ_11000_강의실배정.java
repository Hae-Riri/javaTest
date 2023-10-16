package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_11000_강의실배정 {
    public static void main(String[] args) throws IOException {
        int emptyCnt = 0; // 빈 강의실 개수
        int roomCnt = 0;
        int time = 0;
        PriorityQueue<Class> startClass = new PriorityQueue<>(new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                if(o1.start == o2.start) {
                    return Integer.compare(o1.end, o2.end);
                }
                return Integer.compare(o1.start, o2.start);
            }
        });
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            startClass.add(new Class(start, end));
        }

        while(!startClass.isEmpty()) {
            time = startClass.peek().start;

            //끝난 강의 순회해서 비울 수 있는지 확인
            while(!endTimes.isEmpty() && endTimes.peek() <= time) {
                endTimes.poll();
                emptyCnt++;
            }

            // 시작된 강의 순회하기
            while(!startClass.isEmpty() && startClass.peek().start <= time) {
                Class nowClass = startClass.poll();
                if(roomCnt == 0 || emptyCnt == 0) { // 쓸 수 있는 강의실이 없을 때
                    roomCnt++; // 강의실 만들고
                } else if(emptyCnt > 0) {
                    emptyCnt--; // 빈 강의실 하나 들어가고
                }
                endTimes.add(nowClass.end);
                break;
            }
        }

        System.out.println(roomCnt);
    }

    static class Class {
        int start;
        int end;
        Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
