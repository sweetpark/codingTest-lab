package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//최소 직사각형 문제
public class MinSquare {

    @Test
    @DisplayName("명함이 한개도 없는 경우")
    public void no_card(){
        int[][] card = new int[][]{};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(0, actual);
    }

    @Test
    @DisplayName("명함이 한개만 있는 경우")
    public void one_card(){
        int[][] card = new int[][]{{10,20}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(200, actual);
    }

    @Test
    @DisplayName("명함이 2개가 있는 경우")
    public void two_card(){
        int[][] card = new int[][]{{10,20},{20,40}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(800, actual);
    }

    @Test
    @DisplayName("서로의 최대값이 다른 구간에 있는 경우")
    public void third_cad(){
        int[][] card = new int[][]{{10,20},{20,40}, {5, 50}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(1000, actual);
    }

    /*ß
        [60, 50], [30, 70], [60, 30], [80, 40]]	4000
        [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	120
        [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	133
     */
    @Test
    @DisplayName("프로그래머스 예제1)")
    public void programmers_ex1(){
        int[][] card = new int[][]{{60,50}, {30,70}, {60,30}, {80,40}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(4000, actual);
    }

    @Test
    @DisplayName("프로그래머스 예제2)")
    public void programmers_ex2(){
        int[][] card = new int[][]{{10,7}, {12,3}, {8, 15}, {14, 7}, {5,15}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(120, actual);
    }

    @Test
    @DisplayName("프로그래머스 예제3)")
    public void programmers_ex3(){
        int[][] card = new int[][]{{14,4}, {19,6}, {6, 16}, {18,7}, {7,11}};
        int actual = getMaxWallet(card);
        Assertions.assertEquals(133, actual);
    }

    private static int getMaxWallet(int[][] card) {
        int  leftMax = 0;
        int rightMax = 0;


        if(card.length > 0){
            for(int i = 0; i < card.length; i++){

                // 왼쪽을 명함의 짧은쪽으로 규정
                if(card[i][0] > card[i][1]){
                    int swap = card[i][0];
                    card[i][0] = card[i][1];
                    card[i][1] = swap;
                }

                leftMax = Math.max(leftMax ,card[i][0]);
                rightMax = Math.max(rightMax,card[i][1]);
            }
        }

        return leftMax * rightMax;
    }


}
