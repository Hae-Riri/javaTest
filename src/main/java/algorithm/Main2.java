package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
    static int n, k;
    static int[][] arr;
    static int [] dx = {-1,1, 0, 0};
    static int [] dy = {0,0,-1,1};
    static int diff = Integer.MAX_VALUE;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        init();

        while(diff > k) {
            answer++;
            System.out.println("answer : " + answer);

            fill1();
            move1();

            makeUp();

            spread();

            move2(1, n/2);
            move2(2, n/4);

            makeUp();

            spread();

            diff = getDiff();
            System.out.println("------------");
        }

        System.out.println(answer);
    }

    static void print() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void fill1() {
        List<Integer> idxs = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; ++i){
            if(arr[0][i]==min){
                idxs.add(i);
            }else if(arr[0][i]<min){
                idxs.clear();
                idxs.add(i);
                min = arr[0][i];
            }
        }
        for(int idx: idxs) arr[0][idx]++;
    }

    static void move1() {

        boolean flag = false;
        int r = 1;
        int c = 1;
        while(true){

            System.out.println("처음 모습 ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }

            //회전 영역 따로 보
            int[][] tempArr = new int[c][r];
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    tempArr[j][r-i-1] = arr[i][j];
                }
            }


            // 보관된 회전 영역을을 arr에 적용
            for(int i = 0; i < c; i++) {
                for(int j = 0; j < r; j++) {
                    arr[i][j] = tempArr[i][j];
                }
            }

            System.out.println("회전할 영역을 arr에 적용한 결과  ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }


            //회전영역 이외의 부분들 땡겨오기
            for(int j = c; j < n - r*c + c; j++) {
                arr[c][j-c] = arr[r-1][j];
            }
//			for(int j = c; j<n; ++j){
//			arr[c][j-c] = arr[r-1][j];
//		}

            System.out.println("회전 이외의 부분을 땡겨온 결과 ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }


            // 회전영역 옆부분 0으로 채우기. 땡겨온 부분은 안해도 됨.
            for(int i = 0; i < c; i++) {
                for(int j = r; j < 101; j++) {
                    arr[i][j] = 0;
                }
            }

            System.out.println("다 끝나고 필요 없는 부분들 0으로 처리 ");
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }


            if(!flag) r++;
            else c++;

            if(n - r*c < r) {
                break;
            }

            flag = !flag;
        }
    }

    static void makeUp() {
        int[][] temp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(arr[i][j] == 0)
                    continue;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= n || ny <0 || ny >= n) continue;
                    if(arr[nx][ny] >= arr[i][j]) continue; //작은 에 대해서만 확인해서 중복계산 방지
                    if(arr[nx][ny] == 0) continue;

                    int d = (arr[i][j] - arr[nx][ny]) / 5;
                    if(d > 0) {
                        temp[i][j] -= d;
                        temp[nx][ny] += d;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] += temp[i][j];
            }
        }
    }


    static void spread() {
        int[] temp = new int[2*n];
        int idx = 0;

        for(int j = 0; j<n; j++) {
            for(int i = n-1; i >= 0; i--) {
                if(arr[i][j]==0) continue;
                temp[idx] = arr[i][j];
                idx++;
            }
        }

        arr = new int[101][101];
        for(int i = 0; i < n; i++) {
            arr[0][i] = temp[i];
        }
    }

    static int getDiff() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int y=0; y<n; ++y){
            int cur = arr[0][y];
            max = Math.max(cur,max);
            min = Math.min(cur,min);
        }
        return max-min;
    }

    static void move2(int r, int c){
        int[][] temp = new int[n][n];

        //r,c 영역부분 읽으면서 행을 마지막부터, 열도 마지막부터 0,0에서부터 채워나감.
        // 나머지 행을 0부터 열을 c부터 2c 전까지 읽으면서 ,,, 읽어서저장 후 0으로 만들기
        for(int i = r-1; i >= 0; i--) {
            for(int j = c-1; j >=0; j--) {
                temp[r-1-i][c-1-j] = arr[i][j];
            }
        }


        for(int i = 0; i < r; i++) {
            for(int j = c; j < 2*c; j++) {
                temp[r+i][j-c] = arr[i][j];
                arr[i][j] = 0;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        k = Integer.parseInt(split[1]);

        arr = new int[101][101];
        split = br.readLine().split(" ");
        for(int i = 0; i < split.length; i++) {
            arr[0][i] = Integer.parseInt(split[i]);
        }
    }

}

