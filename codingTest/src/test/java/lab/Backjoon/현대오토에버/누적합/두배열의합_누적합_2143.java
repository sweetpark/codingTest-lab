package org.example.현대오토에버.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 두배열의합_누적합_2143 {

    /***
     * 문제
     * https://www.acmicpc.net/problem/2143
     *
     * [문법]
     * - 누적합
     * - 이분탐색
     *
     * [주의해야할 부분]
     * - count: int 형 오버플로우 ( long 형 변경 )
     * - 이분탐색 :  if(list.get(mid) < target) : target이 되는 첫번쨰 지점 //  if(list.get(mid) <= target) : target보다 다음 큰 수의 인덱스 위치
     *
     */

    static int T, N, M;
    static int[] A, B;
    static List<Integer> aSum = new ArrayList<>();
    static List<Integer> bSum = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        // 부분 배열 얀속된 합
        makeSum(A, aSum);
        makeSum(B, bSum);

        Collections.sort(bSum);

        long cnt = 0; // 정답 범위가 int로 하기에는 작을 수 있음 (조심)
        for (Integer a : aSum) {
            int target = T - a;
            cnt += upperBound(bSum, target) - lowerBound(bSum, target);
        }

        System.out.println(cnt);

    }

    public static void makeSum(int[] arr, List<Integer> result){
        for(int i = 0; i < arr.length; i++){
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                /***
                 * ex)
                 * 1
                 * 1 + 2
                 * 1 + 2 + 3
                 *
                 * 2
                 * 2 + 3
                 *
                 * 3
                 */
                sum += arr[j];
                result.add(sum);
            }
        }
    }

    public static int lowerBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) < target) left = mid + 1; // list.get(mid) <= target 이부분에서 같을경우 left = mid + 1 이기에, target보다 큰 다음 수가 선택됨
            else right = mid;
        }
        return left;
    }


    public static int upperBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) <= target) left = mid + 1; // list.get(mid) <= target 이부분에서 같을경우 left = mid + 1 이기에, target보다 큰 다음 수가 선택됨
            else right = mid;
        }
        return left;
    }



}
