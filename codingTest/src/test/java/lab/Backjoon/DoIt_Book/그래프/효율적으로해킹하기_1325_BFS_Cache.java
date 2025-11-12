package org.example.DoIt_Book.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 효율적으로해킹하기_1325_BFS_Cache {
    static int N;
    static int M;
    static List<Integer>[] arr;
    static int[] cnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        cnt = new int[N + 1];

        for(int i = 1; i < N + 1; i++){
            arr[i] = new ArrayList<>();
        }

        //b를 해킹하면 a도 해킹 가능 ( 원래 : A -> B , 역방향 : B -> A )
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[b].add(a);
        }

        for(int i = 1; i < N + 1; i++){
            visited = new boolean[N + 1];
            cnt[i] = BFS(i);
        }


        // stream을 사용하면 for문 대신해서 간략하게 max값을 구할 수 있음
        /* int max = Arrays.stream(count).max().getAsInt(); */
        int max = 0;
        for(int i = 1; i < N + 1; i++){
            if (max < cnt[i]){
                max = cnt[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i< N + 1; i++){
            if(cnt[i] == max){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());

    }


    public static int BFS(int num){
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        visited[num] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next : arr[now]){
                if(!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    result++;
                }
            }
        }

        return result;
    }
}
