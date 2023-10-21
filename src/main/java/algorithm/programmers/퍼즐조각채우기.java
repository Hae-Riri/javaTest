package algorithm.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://tmdrl5779.tistory.com/206
public class 퍼즐조각채우기 {
    public static void main(String[] args) {
        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

        퍼즐조각채우기 solve = new 퍼즐조각채우기();
        int solution = solve.solution(game_board, table);
        System.out.println(solution);
    }

    static int n;
    static List<List<Point>> puzzles;
    static List<List<Point>> empty;
    static int answer;

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        // 테이블에서 퍼즐 조각 저장해오기


        simulation();

        return answer;
    }

    public void simulation() {
        // 순열로 퍼즐 조각 순서 정하고, 그 순서대로 맞춰지는지 확인해서 answer에 칸 개수로 갱신
        boolean[] visit = new boolean[puzzles.size()];
        List<List<Point>> result = new ArrayList<>();
        permutation(result, visit);
    }

    public void permutation(List<List<Point>> result, boolean[] visit) {
        if(result.size() == puzzles.size()) {
            updateAnswer(result);
            return;
        }
        for(int i = 0; i < puzzles.size(); i++) {
            if(visit[i]) continue;
            visit[i] = true;
            result.add(puzzles.get(i));
            permutation(result, visit);
            visit[i] = false;
            result.remove(result.size()-1);
        }
    }

    public void updateAnswer(List<List<Point>> puzzles) {
        boolean[] visitEmpty = new boolean[empty.size()];
        int cnt = 0;

        for(List<Point> puzzle : puzzles) {
            // 빈칸에 하나씩 대 보기
            for(int i = 0; i < empty.size(); i++) {
                if(empty.get(i).size() != puzzle.size() || visitEmpty[i]) {
                    continue;
                }

                if(fitAll(puzzle, empty.get(i))) {
                    cnt += puzzle.size();
                    visitEmpty[i] = true;
                    break;
                }
            }
        }

        answer = Math.max(answer, cnt);
    }

    public boolean fitAll(List<Point> puzzle, List<Point> emptyBlock) {
        // 맨 처음 fit 인지 확인
        if(fit(puzzle, emptyBlock)) {
            return true;
        }
        // 퍼즐을 90도 회전시켜 가면서 fit 확인
        for(int r = 0; r < 3; r++) {
            List<Point> rotatedPuzzle = new ArrayList<>();
            for(int i = 0; i < puzzle.size(); i++) {
                rotatedPuzzle.add(new Point(puzzle.get(i).y, n - 1 - puzzle.get(i).x));
            }

            if(fit(rotatedPuzzle, emptyBlock)) {
                return true;
            }
        }
        return false;
    }

    public boolean fit(List<Point> puzzle, List<Point> emptyBlock) {
        sort(puzzle);
        sort(emptyBlock);

        int distX = Math.abs(puzzle.get(0).x - emptyBlock.get(0).x);
        int distY = Math.abs(puzzle.get(0).y - emptyBlock.get(0).y);

        for(int i = 1; i < puzzle.size(); i++) {
            if(!isSameDist(puzzle.get(i), emptyBlock.get(i), distX, distY)) {
                return false;
            }
        }
        return true;
    }

    public void sort(List<Point> points) {
        points.sort((a,b) -> {
            if(a.x == b.x) {
                return Integer.compare(a.y, b.y);
            }
            return Integer.compare(a.x, b.x);
        });
    }
    public boolean isSameDist(Point a, Point b, int distX, int distY) {
        return Math.abs(a.x -b.x) == distX && Math.abs(a.y - b.y) == distY;
    }

    public void getPieces(int[][] map, int condition) { //게임보드면 0인애를, 퍼즐이면
        boolean[][] visit = new boolean[n][n];
        List<List<Point>> puzzles = new ArrayList<>();
        List<List<Point>> empty = new ArrayList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                if(visit[i][j] || map[i][j] != condition) {
                    continue;
                }

                Queue<Point> q = new LinkedList<>();
                List<Point> points = new ArrayList<>();

                q.add(new Point(i, j));
                visit[i][j] = true;
                points.add(new Point(i, j));

                while(!q.isEmpty()) {
                    Point poll = q.poll();
                    for(int d = 0; d < 4; d++) {
                        int nx = poll.x + dx[d];
                        int ny = poll.y + dy[d];
                        if(nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny] || map[nx][ny] != condition) {
                            continue;
                        }
                        q.add(new Point(nx, ny));
                        visit[nx][ny] = true;
                        points.add(new Point(nx, ny));
                    }
                }

                puzzles.add(points);
            }
        }

        return;
    }

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void rotateGameBoard(int[][] gameBoard, int n) {
        int[][] rotated = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[j][n-1-i] = gameBoard[i][j];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                gameBoard[i][j] = rotated[i][j];
            }
        }
    }

    public void print(int[][] map) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
