package org.example.DoIt_Book.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class 효율적으로해킹하기_1325 {

    /***
     * [시간초과 발생]
     * 1. Integer[] 배열은 Unboxing하는데 시간이 더 소요되므로, 기본 타입형 사용(*int)
     * 2. StringBuilder를 이용하여, 출력 간소화
     * 3. visited -> boolean처리  (초기화 : for문 이 아닌, new boolean[N+1]로 초기화 진행 -> 기본 false)
     */

    // 노드 개수
    static Integer M;
    // 에지 개수
    static Integer N;
    //맵핑 정보
    static List<Integer> []arr;
    //방문 노드
    static boolean[] visited;
    //신뢰 카운트
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        M = Integer.parseInt(s[0]);
        N = Integer.parseInt(s[1]);
        arr = new ArrayList[M+1];
        cnt = new int[M+1];

        for(int i = 1; i < M + 1; i++){
            arr[i] = new ArrayList<>();
            cnt[i] = 0;
        }

        for(int i = 0; i < N; i++){
            String[] s1 = br.readLine().split(" ");
            arr[Integer.parseInt(s1[0])].add(Integer.parseInt(s1[1]));
        }

        for(int i = 1; i < M + 1; i++){
            visited = new boolean[M+1];
            BFS(i);
        }

        int max = 0;
        //최고 개수
        for(int i = 1; i < M + 1; i++){
            if ( max < cnt[i] ){
                max = cnt[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < M + 1; i++){
            if ( cnt[i] == max ) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);

    }

    public static void BFS(int num){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        while(!queue.isEmpty()){
            Integer now = queue.poll();
            for(Integer i :arr[now]){
                if(!visited[i]){
                    cnt[i]++;
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

    }
}
