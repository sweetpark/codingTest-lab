package lab.programmers.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BIgNumber {


    // 1) 자릿수가 가장 크게 만들 수 있는 값들 중 제일 큰 값
    // 2) N^2 가능 >> 2자리를 빼기떄문에, number는 10,000 이하 (NlogN 이 이상적)


    @Test
    public void test(){
        Assertions.assertEquals(1, Solution("10", 1));
    }

    private int Solution(String number, int removeCount){

        char[] array = new char[number.length()];
        for(int i = 0; i < number.length(); i++){
            array[i] = number.charAt(i);
        }


        List<Integer> result = new ArrayList<>();

        //TODO) removeCount 제외한 여러 종류의 수
        for(int i = 0; i < number.length(); i++){
            result.add(removeNum(number, removeCount, array));
        }


        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        return result.get(0);
    }


    private static Integer removeNum(String number, int removeCount, char[] array) {
        char[] afterArray = new char[number.length() - removeCount];
        int index = 0;
        int temp = 0;
        for (char c : array) {
            // TODO) 숫자 개수 만큼 제거
            if(temp < removeCount){
                temp++;
            }else{
                afterArray[index] = c;
                index++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < afterArray.length; i++)
        {
            sb.append(afterArray[i]);
        }

        return Integer.parseInt(sb.toString());
    }
}
