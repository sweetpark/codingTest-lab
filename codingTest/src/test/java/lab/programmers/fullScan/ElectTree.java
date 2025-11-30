package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971#
public class ElectTree {

    // [[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]
//    private int[][] wires = new int[][]{{1,3}, {2,3}, {3,4} , {4,5} , {4,6}, {4,7}, {7,8}, {7,9}};

    @Test
    public void test(){

        // 반복문
        Assertions.assertEquals( 0, WrapperSolution( new int[][]{}, 1, false));
        Assertions.assertEquals( 0, WrapperSolution( new int[][]{{1,2}}, 2, false));
        Assertions.assertEquals( 1, WrapperSolution( new int[][]{{1,2}, {2,3}}, 3, false));
        Assertions.assertEquals( 3, WrapperSolution( new int[][]{{1,3}, {2,3}, {3,4} , {4,5} , {4,6}, {4,7}, {7,8}, {7,9}}, 9,false));
        Assertions.assertEquals( 0, WrapperSolution( new int[][]{{1,2},{2,3},{3,4}}, 4,false));
        Assertions.assertEquals( 1, WrapperSolution( new int[][]{ {1,2},{2,7},{3,7},{3,4},{4,5},{6,7} }, 7,false));

        // DFS
//        Assertions.assertEquals( 0, WrapperSolution( new int[][]{}, 1, true));
//        Assertions.assertEquals( 0, WrapperSolution( new int[][]{{1,2}}, 2, true));
//        Assertions.assertEquals( 1, WrapperSolution( new int[][]{{1,2}, {2,3}}, 3, true));
//        Assertions.assertEquals( 3, WrapperSolution( new int[][]{{1,3}, {2,3}, {3,4} , {4,5} , {4,6}, {4,7}, {7,8}, {7,9}}, 9,true));
//        Assertions.assertEquals( 0, WrapperSolution( new int[][]{{1,2},{2,3},{3,4}}, 4,true));
        Assertions.assertEquals( 1, WrapperSolution( new int[][]{ {1,2},{2,7},{3,7},{3,4},{4,5},{6,7} }, 7,true));


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



    public Integer WrapperSolution(int[][] wires, int n, boolean dfs){
        if(dfs){
            return Solution2(wires,n);
        }else{
            return Solution(wires,n);
        }
    }

    public Integer Solution2(int[][] wires, int n) {

        List<Integer>[] graph = new ArrayList[n+1];

        for(int i = 1; i < n +1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] wire : wires){
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }


        int result = Integer.MAX_VALUE;
        for (int[] wire : wires) {

            int removePart1 = wire[0];
            int removePart2 = wire[1];

            graph[removePart1].remove(Integer.valueOf(removePart2));
            graph[removePart2].remove(Integer.valueOf(removePart1));

            System.out.println("\n\n ============ [ graph ] =============");
            System.out.println(Arrays.toString(graph));
            System.out.println("================================\n\n");

            boolean[] visited = new boolean[n+1];

            System.out.println();
            System.out.println("================ [DFS 탐색 Stage] " + wire[0] + " ===================");
            int countA = dfs(wire[0], visited, graph);
            System.out.println("=================== END ==========================");
            System.out.println("\n\n\n\n");
            int countB = n - countA;

            result = Math.min(result,Math.abs(countA - countB));

            //복구
            graph[removePart1].add(removePart2);
            graph[removePart2].add(removePart1);
        }

        return result;
    }

    private int dfs(int current, boolean[] visited, List<Integer>[] graph) {

        int count = 1;
        visited[current] = true;

        for (int node : graph[current]) {
            if(!visited[node]){
                count+= dfs(node, visited, graph);
                System.out.println("current Node : " + current + " , Visite Node : " + node + ", count : " + count);
            }
        }

        return count;
    }

}
