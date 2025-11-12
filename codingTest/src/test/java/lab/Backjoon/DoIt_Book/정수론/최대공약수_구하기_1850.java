package org.example.DoIt_Book.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 최대공약수_구하기_1850 {

    /**
     * [Backjoon]
     * https://www.acmicpc.net/problem/1850
     *
     * [최대 공약수 문제 풀이법]
     * - 유클리드호제법 사용 (알고리즘)
     * (A,B)가 주어지면,
     *      1. A % B = 나머지 (C)
     *      2. B % C = 나머지 (D)
     *      3. C % D = 나머지 (E)...
     *      최종) D % E = 0 // E가 최대 공약수
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        Long big_numA = Long.parseLong(input[0]);
        Long small_numB = Long.parseLong(input[1]);

        Long gcd = gcd(big_numA, small_numB);

        StringBuilder sb = new StringBuilder();

        for(Long i = 0L; i< gcd; i++){
            sb.append("1");
        }

        System.out.println(sb.toString());

    }


    private static Long gcd(Long big_numA, Long small_numB){

        Long result = 0L;
        Long remain = 0L;
        while(true)
        {
            remain = big_numA % small_numB;
            if(remain == 0L) {
                result = small_numB;
                break;
            }
            big_numA = small_numB;
            small_numB = remain;
        }
        return result;
    }
}
