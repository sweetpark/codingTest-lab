package org.example.DoIt_Book.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 특정거리의_도시찾기_18352_CHK {

    /***
     *
     *  [인접리스트 - 그래프 구현 (방향이 존재)]
     *  1 - 2,3
     *  2 - 3,4
     *  3
     *  4
     *
     *  도시개수(노드 : N)
     *  도로의개수(에지 : M)
     *  거리정보 (거쳐야할 길이)
     *  출발도시 (X)
     *
     */

    static int visited[];
    static ArrayList<Integer>[] arr;
    static int N,M,K,X;
    static List<Integer> answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        X = Integer.parseInt(input[3]);

        arr = new ArrayList[N + 1];
        answer = new ArrayList<>();

        for (int i = 1; i < N + 1; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            String[] road = br.readLine().split(" ");
            arr[Integer.parseInt(road[0])].add(Integer.parseInt(road[1]));
        }



        visited = new int[N + 1];
        for(int i = 0; i < N + 1; i++){
            visited[i] = -1;
        }

        BFS(X);

        for(int i = 0; i < N + 1; i++){
            if(visited[i] == K){
                answer.add(i);
            }
        }

        //출력
        if(answer.isEmpty()){
            System.out.println("-1");
        }else{
            Collections.sort(answer);
            for (Integer i : answer) {
                System.out.println(i);
            }
        }

    }

    private static void BFS(int startNode){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        visited[startNode]++;

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int i : arr[node]){
                if(visited[i] == -1){
                    visited[i] = visited[node] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
