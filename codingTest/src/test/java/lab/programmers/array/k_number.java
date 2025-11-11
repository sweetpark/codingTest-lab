package lab.programmers.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 *
 * 먼가 테스트케이스를 나누기가 애매하다... ( 어떻게 하면 잘 나눌수 있을까...?)
 *
 */

public class k_number {

    private int[] array;
    private int[] copyArray;

    @BeforeEach
    void setUp() {
        array = new int[]{1, 5, 2, 6, 3, 7, 4};
    }

    @Test
    @DisplayName("Junit 테스트")
    public void test(){

    }

    @Test
    @DisplayName(" 첫번째 배열 나누기")
    public void divideArray(){
        int start = 0;
        int end = 1;
        // 배열 복사) 0 이상 1미만 (인덱스 번호)
        copyArray = getCopyArray(start, end);
        assertThat(copyArray).isEqualTo(new int[] {2});
    }

    private int[] getCopyArray(int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }


    @Test
    @DisplayName("1~4까지 배열 나누기")
    public void divideArrayRange(){
        int start = 0;
        int end = 4;

        copyArray = getCopyArray(start, end);
        assertThat(copyArray).isEqualTo(new int[] {2,3,1,4});
    }

    @Test
    @DisplayName("array 정렬")
    public void arraySort(){
        Arrays.sort(array);
        assertThat(array).isEqualTo(new int[] {1,2,3,4,5,6});
    }

    @Test
    @DisplayName("서브 배열 + 정렬 후 원하는 값 꺼내기 (첫번째 값 꺼내기)")
    public void extractNum(){
        // 0~3번째에서 첫번째값 추출 (1)
        int start = 0;
        int end = 4;
        int find = 1;

        int[] copyArray1 = getCopyArray(start, end);
        Arrays.sort(copyArray1);

        Assertions.assertThat(copyArray1[find - 1]).isEqualTo(1);
    }

    @Test
    @DisplayName("command 조합에 따른 값 추출")
    public void command(){
        int[][] command = new int[][] {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] results = new int[command.length];
        for (int i = 0; i < command.length; i++) {
            int[] ints = command[i];
            int[] copyArray = getCopyArray(ints[0] - 1, ints[1]);
            Arrays.sort(copyArray);
            results[i] = copyArray[ints[2] - 1];
        }

        Assertions.assertThat(results).isEqualTo(new int[] {5,6,3});
    }

}
