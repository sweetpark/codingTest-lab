package org.example.현대오토에버.DP.메모이제이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 신나는함수실행_9184_DP {
    /***
     * 문제
     * https://www.acmicpc.net/problem/9184
     */
    // -값이 하나라도 있으면, -1 출력
    static int N = 20;
    static int [][][]dp; // w(?,?,?)
    static int a;
    static int b;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[N + 1][N + 1][N + 1];

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1){
                break;
            }

            int result = calculationW(a,b,c);
            //w(1, 1, 1) = 2
            StringBuilder sb = new StringBuilder();
            sb.append("w(");
            sb.append(a);
            sb.append(", ");
            sb.append(b);
            sb.append(", ");
            sb.append(c);
            sb.append(") = ");
            sb.append(result);

            System.out.println(sb);
        }
    }

    public static int calculationW(int a, int b, int c){

        if( a <= 0 || b <= 0 || c <= 0){
            return 1;
        }
        if( a > 20 || b > 20 || c > 20){
            return calculationW(20,20,20);
        }

        if(dp[a][b][c] != 0){ return dp[a][b][c]; }

        if( a < b && b < c){
            return dp[a][b][c] =  calculationW(a,b,c-1) + calculationW(a, b-1,c-1) - calculationW(a, b-1, c);
        }else{
            return dp[a][b][c] = calculationW(a-1,b,c) + calculationW(a-1, b-1, c) + calculationW(a-1,b ,c-1) - calculationW(a-1, b-1, c-1);
        }

    }

}
