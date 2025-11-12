package org.example.현대오토에버.투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다_1253_투포인터 {
    /***
     * 투포인터
     * https://adjh54.tistory.com/384
     *
     * 문제
     * https://www.acmicpc.net/problem/1253
     *
     */
    public static void main(String[] args) throws Exception {

        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        int size =Integer.parseInt(buf.readLine());
        int []arr = new int[size];

        StringTokenizer st = new StringTokenizer(buf.readLine());

        for(int i=0; i<size; i++){
            if (st.hasMoreTokens()){
                arr[i] = Integer.parseInt(st.nextToken());
            }else{
                System.out.println("StringTokenizer 오류");
                break;
            }
        }

        Arrays.sort(arr);

        int result = 0;
        for(int k=0; k<size; k++){
            long find = arr[k];
            int i = 0;
            int j = size-1;

            while (i<j){
                if ( arr[i] + arr[j] == find ){
                    if (i != k && j != k){
                        result++;
                        break;
                    }else if(i == k){
                        i++;
                    }else if(j==k){
                        j--;
                    }
                }else if (arr[i] + arr[j] > find) {
                    j--;
                }else{
                    i++;
                }
            }

        }

        System.out.println(result);

    }
}
