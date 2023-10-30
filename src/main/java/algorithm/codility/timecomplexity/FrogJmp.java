package algorithm.codility.timecomplexity;

public class FrogJmp {
    public int solution(int X, int Y, int D) {
        int distance = Y - X;
        if(distance % D == 0) {
            return distance / D;
        } else {
            return distance / D + 1;
        }
    }
}
