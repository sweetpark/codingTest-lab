package org.example.현대오토에버.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 두배열의합_누적합_틀린풀이_DFS부분배열풀이 {

    static int target;
    static int N;
    static int M;
    static int [] a;
    static List<Integer> aSum;

    static int [] b;
    static List<Integer> bSum;

    /***
     *
     * 문제)
     * https://www.acmicpc.net/problem/2143
     *
     * DFS를 활용하여 모둔 부분 배열을 골랐지만,
     * 문제 정의는 연속된 배열의 합임 ( 그래서 틀린 풀이 )
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        a = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        b = new int[M+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < M+1; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        aSum = new ArrayList<>();
        for(int i = 1; i < N +1; i++)
        {
            dfs(i, 0, a, aSum);
        }

        bSum = new ArrayList<>();
        for(int i = 1; i < M + 1; i++){
            dfs(i, 0, b, bSum);
        }

        int cnt = 0;
        for(int i = 0; i < aSum.size(); i++){
            if ( aSum.get(i) > target){ continue; }
            for(int j = 0; j < bSum.size(); j++){
                if(bSum.get(j) > target){continue; }
                if(target == aSum.get(i) + bSum.get(j)){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }


    static void dfs(int depth, int sum, int[] arr, List<Integer> result){
        if(depth == arr.length){
            result.add(sum);
            return;
        }

        dfs(depth+1, sum+arr[depth], arr, result);
        dfs(depth+1, sum, arr, result);
    }
}
