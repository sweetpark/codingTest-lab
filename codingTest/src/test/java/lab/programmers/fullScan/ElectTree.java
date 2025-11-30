package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971#
public class ElectTree {

    // [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]
//    private int[][] wires = new int[][]{{1,3}, {2,3}, {3,4} , {4,5} , {4,6}, {4,7}, {7,8}, {7,9}};

    @Test
    public void test(){

        Assertions.assertEquals( 0, Solution( new int[][]{}, 1));
        Assertions.assertEquals( 0, Solution( new int[][]{{1,2}}, 2));
        Assertions.assertEquals( 1, Solution( new int[][]{{1,2}, {2,3}}, 3));
        Assertions.assertEquals( 3, Solution( new int[][]{{1,3}, {2,3}, {3,4} , {4,5} , {4,6}, {4,7}, {7,8}, {7,9}}, 9));
        Assertions.assertEquals( 0, Solution( new int[][]{{1,2},{2,3},{3,4}}, 4));
        Assertions.assertEquals( 1, Solution( new int[][]{ {1,2},{2,7},{3,7},{3,4},{4,5},{6,7} }, 7));


    }

    private int Solution(int[][] wires, int n) {

        if(n == 1){
            return 0;
        }


        int length = wires.length;
        int result = 100;
        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();
        for(int i = 0; i < length; i++){

            if(length == 1){
                left.add(wires[0][0]);
                right.add(wires[0][1]);
                result = 0;
                break;
            }


            //FIXME) 나눠지는 구간 (지금은 처음만 나눴을경우) >> 현재는 0 번째에서 나눔
            //FIXME) 구간별로 결과값(Math.min(currentResult, result);) 최소값 => DFS/BFS 사용해야할듯하다 ==> DFS/BFS로 구현 안해봄
            int calc = split(i, wires, n);
            result = Math.min(result, calc);

        }

        return result;
    }

    private int split(int splitSpot, int[][] wires, int n) {

        Set<Integer> left = new HashSet<>();
        Set<Integer> right = new HashSet<>();

        boolean updated = true;
        while(updated){
            updated = false;
            left.add(wires[splitSpot][0]);
            right.add(wires[splitSpot][1]);
            for (int i = 0; i < wires.length; i++) {

                //끊은 전선 패스
                if (i == splitSpot) continue;

                int x = wires[i][0];
                int y = wires[i][1];

                boolean xl = left.contains(x);
                boolean yl = left.contains(y);

                boolean xr = right.contains(x);
                boolean yr = right.contains(y);

                // left와 연결 → left로 전파
                if ((xl || yl) && !(xr || yr)) {
                    if (left.add(x)) updated = true;
                    if (left.add(y)) updated = true;
                    continue;
                }

                // right와 연결 → right로 전파
                if ((xr || yr) && !(xl || yl)) {
                    if (right.add(x)) updated = true;
                    if (right.add(y)) updated = true;
                    continue;
                }

                // 양쪽 그룹 모두와 연결 → 나눌 수 없음 (잘못된 분할)
                if ((xl || yl) && (xr || yr)) {
                    return 101; // 매우 큰 값
                }
            }
        }

        if (left.size() + right.size() != n) {
            return 101; // 분할 불가 → 실패 처리
        }

        return Math.abs(left.size() - right.size());
    }

}
