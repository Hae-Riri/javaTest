package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class swea_21608_상어초등학교 {
    static int n;
    static int logN;
    static int[][] map;
    static List<Integer>[] likeStds; //좋아하는 학생 보관

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Point> sortByCondition = new ArrayList<>();
    static Queue<Integer> stds = new LinkedList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        logN = Integer.parseInt(br.readLine());
        n = logN * logN;
        map = new int[n][n];;
        likeStds = new ArrayList[n+1];
        for(int i = 0; i < n + 1; i++) {
            likeStds[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            String[] splits = br.readLine().split(" ");
            int std = Integer.parseInt(splits[0]);
            stds.add(std);
            for(int j = 1; j < splits.length; j++) {
                likeStds[std].add(Integer.parseInt(splits[j]));
            }
        }

        // 자리배치 시작
        while(!stds.isEmpty()) {
            int nowStd = stds.poll();

            //전체 맵을 순회하면서 조건 개수 확인
            checkAllMap(nowStd);

            // 자리배치
            Point pickedPoint = pickOnePoint();
            clearCollections();
            map[pickedPoint.x][pickedPoint.y] = nowStd;
        }

        // 모든 학생의 자리배치 완료되면
        printAnswer();
    }

    private static void printAnswer() {
        for(int i = 0; i < logN; i++) {
            for(int j = 0; j < logN; j++) {
                int cnt = 0;
                for(int k = 0; k < 4; k++) {
                    int closeX = i + dx[k];
                    int closeY = j + dy[k];

                    if(closeX < 0 || closeX >= logN || closeY < 0 || closeY >= logN) {
                        continue;
                    } else if(likeStds[map[i][j]].contains(map[closeX][closeY])) {
                        cnt++;
                    }
                }

                if(cnt == 1) {
                    answer++;
                } else if(cnt == 2) {
                    answer += 10;
                } else if(cnt == 3) {
                    answer += 100;
                } else if(cnt == 4) {
                    answer += 1000;
                }
            }
        }

        System.out.println(answer);
    }

    private static void clearCollections() {
        sortByCondition.clear();
    }

    private static Point pickOnePoint() {

        // like이 많은 순 정렬
        Collections.sort(sortByCondition, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int result = Integer.compare(o2.like, o1.like);
                if(result == 0) {
                    result = Integer.compare(o2.empty, o1.empty);
                }

                if(result == 0) {
                    result = Integer.compare(o1.x, o2.x);
                }

                if(result == 0) {
                    result = Integer.compare(o1.y, o2.y);
                }
                return result;
            }
        });

        return sortByCondition.get(0);
    }

    static void checkAllMap(int nowStd) {
        for(int i = 0; i < logN; i++) {
            for(int j = 0; j < logN; j++) {
                // 이미 채워진 자리면 skip
                if(map[i][j] != 0) {
                    continue;
                }

                // 상하좌우 돌면서 확인하기
                int like = 0;
                int empty = 0;
                for(int k = 0; k < 4; k++) {
                    int closeX = i + dx[k];
                    int closeY = j + dy[k];

                    if(closeX < 0 || closeX >= logN || closeY < 0 || closeY >= logN) {
                        continue;
                    }
                    else if(map[closeX][closeY] == 0){
                        empty++;
                    }else if(likeStds[nowStd].contains(map[closeX][closeY])) {
                        like++;
                    }
                }

                Point point = new Point(i, j, like, empty);
                sortByCondition.add(point);
            }
        }
    }


    static class Point{
        int x;
        int y;
        int like;
        int empty;

        public Point(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }
    }
}

