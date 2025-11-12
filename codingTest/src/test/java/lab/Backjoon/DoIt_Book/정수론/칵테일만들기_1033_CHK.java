package org.example.DoIt_Book.정수론;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 칵테일만들기_1033_CHK {
    /***
     *  [ 알고리즘 ]
     *  - 최대공약수 -> 유클리드 호제법
     *  - 최소공배수 -> A * B / 최대공약수 (공통적인 최대공약수 부분이 2번 중복되므로 한번 나눠줘야 최소공배수를 구할 수 있다)
     *  - 트리구조 -> 방향이 없으므로, 각각 노드에 저장을 해줘야한다.
     *  - DFS -> 전구간 탐색을 진행하며, 최소인 경우의 수를 구하기
     *
     *  ( 최소공배수, 최대공약수, 트리구조, DFS(재료 구하기) )
     *
     *  [비율을 통한 재료 질량 구하기]
     *  (a번 재료의 질량을 b번 재료의 질량으로 나눈 값이 p/q라는 뜻이다. -> 1번의 의미 == a와 b의 비율을 말하는 것)
     *  1. 재료의 개수 N , 비율은 a 와 b 의 비율은 p : q ex) 4노드 , 0노드 비율은 -> 1 : 1 // 4 0 1 1
     *  2. 방향이 없는 그래프 -> 트리 구조
     *  3. 한개의 노드를 기준으로, 최소공배수 구하기
     *  4. 각각의 재료의 질량을 구하면, 5개의 재료에 대한 최대공약수를 구하여 나눠준다 ( 5개의 재료에 질량에 대한 최소한의 질량 구하기 )
     */

    static int N;
    static ArrayList<Node>[] graph;

    // 재료 질량
    static long[] amount;
    // 방문 했는지 확인(DFS)
    static boolean[] visited;
    static long lcm = 1;

    static class Node{
        int to;
        int p;
        int q;

        public Node(int to, int p, int q){
            this.to = to;
            this.p = p;
            this.q = q;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N];
        for(int i =0; i < N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            // 트리 구조 (양쪽에 다 넣어야함)
            graph[a].add(new Node(b,p,q));
            graph[b].add(new Node(a,q,p));

            lcm *= (p * q / gcd(p, q));
        }

        amount = new long[N];
        visited = new boolean[N];

        amount[0] = lcm;
        dfs(0);

        long mgcd = amount[0];
        for(int i = 1; i < N; i++){
//            System.out.println("amount["+ i +"] : " + amount[i]);
            mgcd = gcd(mgcd, amount[i]);
        }

        for(int i = 0; i < N; i++){
//            System.out.println("amount[" + i + "] / mgcd :" + amount[i] + " / " + mgcd + " = "+  amount[i] / mgcd);
            System.out.print(amount[i] / mgcd + " ");
        }

    }

    public static void dfs(int current){
        visited[current] = true;

        for(Node next : graph[current]){
            if(!visited[next.to]){
                amount[next.to] = amount[current] * next.q / next.p;
                dfs(next.to);
            }
        }
    }

    public static long gcd(long a, long b){
        if(b == 0)
            return a;
        return gcd(b, a % b);
    }
}
