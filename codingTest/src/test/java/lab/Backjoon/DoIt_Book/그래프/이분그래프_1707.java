package org.example.DoIt_Book.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 이분그래프_1707 {
    /**
     * 문제
     * https://www.acmicpc.net/problem/1707
     */

    static int K, M, N;
    static List<Integer>[] arr;
    static int[] visited;
    static boolean resultChk;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        for(int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            arr = new List[M+1];
            visited = new int[M+1]; //* 0 : 미방문, 1 : 집합1, 2 : 집합2

            for(int j = 0; j < M+1; j++){
                arr[j] = new ArrayList<>();
            }

            N = Integer.parseInt(st.nextToken());
            for(int k = 0; k < N; k++){
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                int edge = Integer.parseInt(st.nextToken());

                //*무방향
                arr[node].add(edge);
                arr[edge].add(node);
            }


            resultChk = true;

            for(int q = 1; q < M+1; q++){
                if(visited[q] == 0){
                    DFS(q,1);
                }
            }

            System.out.println(resultChk ? "YES" : "NO");
        }


    }

    public static void DFS(int node, int setNum){
        visited[node] = setNum;

        for(Integer next : arr[node]){
            if(visited[next] == 0){
                DFS(next, -setNum);
            }else if(visited[next] == visited[node]){
                resultChk = false;
            }
        }

    }

}
