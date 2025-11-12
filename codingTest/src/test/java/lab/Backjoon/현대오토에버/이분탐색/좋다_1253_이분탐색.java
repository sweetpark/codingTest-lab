package org.example.현대오토에버.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다_1253_이분탐색 {
    /***
     * 이분탐색 (Binary Search)
     * https://velog.io/@kimdukbae/%EC%9D%B4%EB%B6%84-%ED%83%90%EC%83%89-%EC%9D%B4%EC%A7%84-%ED%83%90%EC%83%89-Binary-Search"
     * 문제
     * https://www.acmicpc.net/problem/1253
     */
    static int[] arr;
    static int N;
    static int cnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            if(isGood(i)){
                cnt++;
            }
        }

        System.out.println(cnt);

    }

    public static boolean isGood(int index){
        int target = arr[index];

        for(int j = 0; j < N; j++){
            if (j == index) continue;

            int need = target - arr[j];

            if(binarySerach(need, j, index)){
                return true;
            }
        }
        return false;
    }

    public static boolean binarySerach(int key, int exclude1, int exclude2){
        int left = 0;
        int right = N-1;
        while(left <= right){
            int mid = left + (right - left)/2;

            if (mid == exclude1 || mid == exclude2) {
                if (arr[mid] < key) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
                continue;
            }

            if(arr[mid] == key){ return true;}
            else if(arr[mid] < key){ left = mid +1;}
            else right = mid - 1;
        }
        return false;
    }
}
