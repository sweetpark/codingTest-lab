package lab.programmers.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * 입력 크기 N	제한 시간 1초 기준 가능한 복잡도
 * N ≤ 10	O(N!) 가능
 * N ≤ 20	O(2^N) 가능
 * N ≤ 100	O(N³) 가능
 * N ≤ 1,000	O(N²) 가능
 * N ≤ 100,000	O(N log N) 가능
 * N ≤ 10⁷  (천만)	O(N) 가능
 * N ≥ 10⁸ (억)	O(1) ~ O(log N)만 가능
 *
 * numbers의 최대 개수 100,000 => NlogN
 */
public class BigNumber {

    private int[] numbers;

    @Test
    @DisplayName("1,2,3 을 뽑아서 숫자로 이어 붙이기")
    public void solution(){

        //given

        numbers = new int[] {1,2,3};

        //when

        // StringBuilder로 이어붙이기
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numbers.length; i++){
            sb.append(String.valueOf(numbers[i]));
        }


        // then
        Assertions.assertEquals(123, Integer.parseInt(sb.toString()));
    }

    @Test
    @DisplayName("6,10,12 에서 맨앞자리 기준 정렬 > 틀린케이스 [3, 30, 34, 5, 9] > 9534330")
    public void sort() {
        //given
        numbers = new int[]{6, 10, 12};
        List<Integer> list = new ArrayList<>();
        for (int num : numbers) {
            list.add(num);
        }

        //when

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String o1Str = String.valueOf(o1);
                String o2Str = String.valueOf(o2);


                // 문자 하나씩 비교 (앞자리부터 큰거 순서대로 정렬)
                for(int i = 0; i < o1Str.length() && i < o2Str.length(); i++){
                    if(o1Str.charAt(i) != o2Str.charAt(i)){
                        // 내림차순
                        return o2Str.charAt(i) -o1Str.charAt(i);
                    }
                }
                // 앞자리들이 다같으면, 길이 짧은것이 우선 (오름차순)
                return o1Str.length() - o2Str.length();
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int number : list){
            sb.append(String.valueOf(number));
        }


        //then

        Assertions.assertEquals(61210, Integer.parseInt(sb.toString()));


        /**
         * Wrapper된 리스트의 경우에 Comparator사용 가능 (일반 기본타입 불가능 >> int[], double[] 불가..)
         */
//        Arrays.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
    }

    @Test
    @DisplayName("[3, 30, 34, 5, 9] >> 9534330 고려한 풀이 (두개씩 합쳐서 더 큰것대로 내림차순)")
    public void success(){
        //given
        numbers = new int[]{3, 30, 34, 5, 9};
        List<Integer> list = new ArrayList<>();
        for (int num : numbers) {
            list.add(num);
        }

        //when
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String o1Str = String.valueOf(o1);
                String o2Str = String.valueOf(o2);

                String o1Sum = o1Str + o2Str;
                String o2Sum = o2Str + o1Str;

                // 문자열 비교 (Integer.parseInt 금지!) >> 문자열 더하기 때문에 int범위를 쉽게 벗어남
//                return Integer.parseInt(o2Sum) - Integer.parseInt(o1Sum);
                return o2Sum.compareTo(o1Sum); // compareTo를 하면 사전적으로 비교가 가능
            }
        });

        //모든 수가 0인 경우의수 >> list는 큰값순서대로 정렬된 상태인데, 첫번째가 0이면 모든 수가 0
        if(list.get(0) == 0){
            Assertions.assertEquals(0, list.get(0));
        }else{
            StringBuilder sb = new StringBuilder();
            for(int number : list){
                sb.append(String.valueOf(number));
            }
            Assertions.assertEquals("9534330", sb.toString());
        }
    }

    @Test
    @DisplayName("자릿수별 출력")
    public void printNum(){
        int num = 112345;

        int 십만 = (num % 1000000) / 100000;
        int 일만 = (num % 100000) / 10000;
        int 천 = (num % 10000) / 1000;
        int 백 = (num % 1000) / 100;
        int 십 = (num % 100) / 10;
        int 일 = (num % 10) / 1;

        System.out.println(십만);
        System.out.println(일만);
        System.out.println(천);
        System.out.println(백);
        System.out.println(십);
        System.out.println(일);
    }

}
