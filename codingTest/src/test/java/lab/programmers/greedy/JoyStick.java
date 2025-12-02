package lab.programmers.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JoyStick {

    @Test
    public void test(){

        Assertions.assertEquals(1,findWord("z".toUpperCase()));
        Assertions.assertEquals(9,findWord("j".toUpperCase()));
        Assertions.assertEquals(11,solution("jaz".toUpperCase()));
    }

    private int solution(String name){

        int upDownCount = 0;
        for(int i = 0; i < name.length(); i++){
            upDownCount += findWord(String.valueOf(name.charAt(i)));
        }

        int leftRightCount = findLeftRightMove(name);


        return leftRightCount + upDownCount;
    }


    //그리디가 들어간 부분
    private int findLeftRightMove(String name) {
        int length = name.length();
        int minMove = length -1; // 오른쪽으로 쭉 이동


        for(int i = 0; i < length; i++){


            int next = i+1;
            while(next < length && name.charAt(next) == 'A'){
                next++;
            }

            // 오른쪽 -> 다시 뒤로돌리기 역방향
            int move1 = i * 2 + length - next;

            // 왼쪽 -> 다시 오른쪽으로 순방향
            int move2 = (length - next) * 2 + i;

            minMove = Math.min(minMove, Math.min(move1, move2));
        }

        return minMove;
    }

    private int findWord(String part){
        String[] word = new String[] {"A", "B", "C", "D", "E", "F",
                                        "G", "H", "I", "J", "K", "L",
                                        "M", "N", "O", "P", "Q", "R",
                                        "S", "T", "U", "V", "W", "X","Y", "Z"};

        if(part.equalsIgnoreCase("A")){
            return 0;
        }

        int ascCount = 0;
        for(int i = 0; i < word.length; i++){
            if(word[i].equals(part)){
                break;
            }else{
                ascCount++;
            }
        }

        // 마지막값에 이미 가 있으므로,
        int descCount = 1;
        for(int i = word.length - 1; i >= 0; i--){
            if(word[i].equals(part)){
                break;
            } else{
                descCount++;
            }
        }

        return Math.min(ascCount, descCount);
    }
}
