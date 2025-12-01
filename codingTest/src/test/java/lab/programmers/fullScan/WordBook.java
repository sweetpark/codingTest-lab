package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class WordBook {


    private static final char[] WORD_SET = {'A', 'E', 'I', 'O', 'U'};
    private static final int[] WEIGHT = {781, 156, 31, 6, 1};


    @Test
    public void test(){
        Assertions.assertEquals(0,getCount(""));
        Assertions.assertEquals(1,getCount("A"));
        Assertions.assertEquals(2,getCount("AA"));
        Assertions.assertEquals(5,getCount("AAAAA"));
        Assertions.assertEquals(6,getCount("AAAAE"));
        Assertions.assertEquals(10,getCount("AAAE"));
        Assertions.assertEquals(16,getCount("AAAI"));
        Assertions.assertEquals(1563,getCount("I"));
    }

    private int getCount(String word) {

        int answer = 0;

        for (int i = 0; i < word.length(); i++) {
            int idx = 0;

            // 문자 위치 찾기
            for (int j = 0; j < WORD_SET.length; j++) {
                if (WORD_SET[j] == word.charAt(i)) {
                    idx = j;
                    break;
                }
            }

            // 현재 자리에서 더해지는 값 = idx * weight[i]
            answer += idx * WEIGHT[i];
        }

        // 자신의 길이만큼 +1 (사전에서 한 글자씩 완성되므로)
        return answer + word.length();
    }
}
