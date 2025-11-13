package lab.programmers.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H_INDEX {


    private int[] citations;

    @Test
    public void 정렬하기(){
        //given
        citations = new int[] {3,0,6,1,5};
        //when
        Arrays.sort(citations);
        //then
        Assertions.assertArrayEquals(citations ,new int[] {0,1,3,5,6});
    }

    @Test
    void example1() {
        // [0, 1, 3, 5, 6] → H-index = 3
        assertEquals(3, HIndex.solution(new int[]{3, 0, 6, 1, 5}));
    }

    @Test
    void example2() {
        // [0, 1, 5, 6] → H-index = 2
        assertEquals(2, HIndex.solution(new int[]{0, 1, 5, 6}));
    }

    @Test
    void allSame() {
        // [1,1,1,1,1] → 모두 1 이상 인용 → H-index = 1
        assertEquals(1, HIndex.solution(new int[]{1,1,1,1,1}));
    }

    @Test
    void zeroOnly() {
        // [0,0,0] → 인용된 논문 없음 → H-index = 0
        assertEquals(0, HIndex.solution(new int[]{0,0,0}));
    }

    @Test
    void largeCitations() {
        // [100,100,1] → H-index = 2 (2편이 2번 이상)
        assertEquals(2, HIndex.solution(new int[]{100,100,1}));
    }



    public static class HIndex{
        public static int solution(int[] citations) {
            Arrays.sort(citations);
            int size = citations.length;

            // remainingPapers : 끝지점 부터 줄어드는 구조 (이 값이 촤대값)
            for (int i = 0; i < size; i++) {
                int remainingPapers = size - i;
                if (citations[i] >= remainingPapers) {
                    return remainingPapers;
                }
            }

            // 0 일경우,
            return 0;
        }
    }


}
