package org.example.현대오토에버.우선순위큐;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 문제집_우선순위큐_1766 {
    /***
     * 문제
     * https://www.acmicpc.net/problem/1766
     *
     * 우선순위큐
     * ( PriorityQueue<Integer> queue2 = new PriorityQueue<>(new Custom()); ) // Custom 내림차순? 오름차순?
     * - 내부적으로 정렬되어있지 않음 -> poll()로 빼야지 우선 정렬됨
     * - o1 - o2 : 오름차순 (Default 값)
     * - o2 - o1 : 내림차순
     */
    static int N;
    static int M;
    static List<Integer>[]arr;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 표현할때, 초기화 진행 꼭 해주기!!
        arr = new ArrayList[N + 1];
        for(int i = 0; i < N+1; i++){
            arr[i] = new ArrayList<>();
        }

        indegree = new int[N + 1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(to);
            indegree[to]++;
        }

        //초반문제가 더 쉽다는 정렬 조건이 있음 (우선순위큐 사용)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()){
            int current = queue.poll();
            sb.append(current).append(" ");

            //current를 풀었으니, 후에 풀어야할 인덱스의 차수를 하나씩 뺴줌 (여기서 0 이면, queue에 삽입)
            for(int next : arr[current]){
                indegree[next]--;
                if(indegree[next] == 0){
                    queue.add(next);
                }
            }
        }

        System.out.println(sb);


        PriorityQueue<Integer> queue2 = new PriorityQueue<>(new Priority());
        queue2.add(-1);
        queue2.add(100);
        queue2.add(0);
        queue2.add(-2);

        StringBuilder sb2 = new StringBuilder();
        /*
        PriorityQueue는 내부적으로 정렬해서 가지고 있지 않음 -> poll() 사용해야함

        for (Integer i : queue2) {
            sb2.append(i).append(" ");
        }

         */
        while(!queue2.isEmpty()){
            sb2.append(queue2.poll()).append(" ");
        }
        System.out.println(sb2);
    }


    public static class Priority implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
//            return o1 - o2; //오름차순 ( default 값 )
            return o2 - o1; // 내림차순
        }

    }
}
