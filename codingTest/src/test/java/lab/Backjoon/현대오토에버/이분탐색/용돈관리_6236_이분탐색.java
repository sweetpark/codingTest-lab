package org.example.현대오토에버.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용돈관리_6236_이분탐색 {

    /**
     * 문제
     * https://www.acmicpc.net/problem/6236
     */

    static int N;
    static int M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr= new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += arr[i];
            if (max < arr[i]){
                max = arr[i];
            }
        }

        /***
         * 최소(하루 최대값) ~ 촤대 (합산값)
         * 가운데값 해보고 -> M의 횟수를 넘어가면 오른쪽 / 안넘으면 왼쪽 (마지막값이 결과값)
         */
        Integer i = binarySearch(max, sum);
        System.out.println(i);
    }

    public static Integer binarySearch(int start, int end){
        int left = start;
        int right = end;
        int result = 0;

        while(left <= right){
            int mid = left + (right - left)/2;
            if(compare(mid)){
                result = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }

    public static boolean compare(int value){
        int cnt = 1; // 첫번째 인출
        int remain = value;
        for(int i = 0; i < N; i++){
            if ( arr[i] > remain){
                remain = value;
                cnt++;
            }
            remain -= arr[i];
        }
        if (cnt <= M){
            return true;
        }else{
            return false;
        }

    }
}
