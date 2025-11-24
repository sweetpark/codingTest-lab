package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// 피로도 문제
// https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class Tired {

    private int count = 0;
    private int k = 80;

    @Test
    public void test(){
        //[[80,20],[50,40],[30,10]]
        int[][] cave = new int[][] {{80,20}, {50,40}, {30,10}};
        boolean[] visited = new boolean[cave.length];


        dfs(cave, visited);

        Assertions.assertEquals(3, count);
    }

    private void dfs(int[][] cave, boolean[] visited) {
        int explored = 0;
        for(int i = 0; i < visited.length; i++){
            if(visited[i]){
                explored++;
            }
        }

        count = Math.max(count, explored);

        /*

        (0) → (1)           depth=2
        (0) → (2) → (1)     depth=3   ← 최대
        (1) → (2)           depth=2
        (2) → (1)           depth=2

        >> 디버깅 잡아보기 ( DFS + 백트래킹 )
         */
        for(int i = 0; i < cave.length; i++){
            if(!visited[i] && cave[i][0] <= k){
                // 입장
                k -= cave[i][1];
                visited[i] = true;

                dfs(cave, visited);

                // 복구
                k += cave[i][1];
                visited[i] = false;
            }
        }
    }
}
