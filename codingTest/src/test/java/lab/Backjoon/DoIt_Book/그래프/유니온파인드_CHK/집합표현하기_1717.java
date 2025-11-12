package org.example.DoIt_Book.그래프.유니온파인드_CHK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합표현하기_1717 {
    /***
     * 문제
     * https://www.acmicpc.net/problem/1717
     *
     * [집합]
     *  노드      > 1 2 3 4 5
     *  root 노드 > 1 2 3 4 5
     *
     * [합집합]
     * - a와 b중 어떤걸 root로 할지는 마음대로 해도 상관없음
     * 1 Union 3 (a를 root)
     *
     * 노드      > 1 2 3 4 5
     * root 노드 > 1 2 1 4 5
     *
     * [찾기]
     * - 2와 3 같은집합인지 확인
     * > 2 : 2번노드
     * > 3 : 1번노드 -> 1번노드 : 1번노드
     *
     * 결과) 다름(NO)
     *
     * [합집합]
     * - 3 UNION 2
     * > 3 : 1번노드 -> 1번노드 : 1번노드
     * > 2 : 2번노드
     *
     * 노드      > 1 2 3 4 5
     * root 노드 > 1 1 1 4 5
     */

    static int N; // 노드 개수
    static int M; // 관계 개수
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++){
            arr[i] = i;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (type == 0){
                union(a,b);
            }else if(type == 1){
                boolean result = check(a,b);
                if(result){
                    System.out.println("YES");
                }else{
                    System.out.printf("NO");
                }
            }
        }


    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b){
            arr[a] = b;
        }
    }

    private static int find(int a){
        if(arr[a] == a){
            return a;
        }else{
            return arr[a] = find(arr[a]);
        }
    }

    private static boolean check(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b){
            return true;
        }else{
            return false;
        }
    }

}
