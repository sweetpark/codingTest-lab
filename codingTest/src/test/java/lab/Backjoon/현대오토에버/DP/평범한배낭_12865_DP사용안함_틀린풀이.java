package org.example.현대오토에버.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 평범한배낭_12865_DP사용안함_틀린풀이 {
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
    static List<Integer> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 2차원 배열 정렬 조건
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 오름차순
            }
        });

        for (int i = 0; i < N; i++) {
            int compare = 0;
            int sum = 0;
            for(int j = i; j < N; j++) {
                compare += arr[j][0];
                if(compare <= K){
                    sum += arr[j][1];
                }else{
                    result.add(sum);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.stream().max(Integer::compare).get());



    }
}
