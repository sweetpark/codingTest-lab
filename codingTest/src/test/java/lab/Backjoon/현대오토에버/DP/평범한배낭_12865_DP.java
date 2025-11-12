package org.example.현대오토에버.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 평범한배낭_12865_DP {
    /***
     * 문제
     * https://www.acmicpc.net/problem/12865
     *
     * 이론
     * https://hongjw1938.tistory.com/47
     *
     */

    static int N;
    static int [][] arr;
    static int K;

    static int [][]dp; // N까지 합산한 결과 K => value == 가중치
    //ex) dp[1][5] == 1까지 합산한 무게 5의 가중치를 뜻함

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][2];
        dp = new int[N+1][K+1]; // 1~N까지, 0~K까지 구해야하므로 +1
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }


        for(int i = 1; i < N + 1; i++){
            int weight = arr[i][0];
            int value = arr[i][1];
            // dp 값 채우기
            for(int w = 0; w <= K; w++){
                if (weight <= w){
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - weight] + value);
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
