package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PrimeNumberTest {

    @Test
    @DisplayName("문자열 숫자 만들기")
    public void test(){
        strConvertTest(null, 0);
        strConvertTest("", 0);
        strConvertTest("1", 0);
        strConvertTest("11", 1);
        strConvertTest("17", 3);
    }

    private void strConvertTest(String strNumber, int answer) {
        Assertions.assertEquals(answer, extractNum(strNumber));
    }


    private int extractNum(String strNum) {
        if(strNum == null || strNum.isEmpty())
            return 0;
        else{
            //#1) 중복 제거 숫자 조합 생성
            Set<Integer> set = new HashSet<>();
            generateNumber(set, "", strNum);


            //#2) 중복제거 된 숫자중 소수인 부분 체크
            boolean[] primeArray = getPrimeArray(set);



            //#3) 소수가 몇개인지 확인
            int result = 0;
            for(Integer index : set){
                if(primeArray[index]){
                    result++;
                }
            }

            return result;
        }
    }

    private static boolean[] getPrimeArray(Set<Integer> set) {
        int max = 0;
        if(!set.isEmpty()){
            max = set.stream().max(Integer::compareTo).get();
        }

        boolean[] primeArray = new boolean[max + 1];
        Arrays.fill(primeArray, true);
        primeArray[0] = false;
        primeArray[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (primeArray[i]) {
                for (int j = i * i; j <= max; j += i) {
                    primeArray[j] = false;
                }
            }
        }
        return primeArray;
    }

    private void generateNumber(Set<Integer> set, String prefix, String remain) {
        if(!prefix.isEmpty()){
            set.add(Integer.parseInt(prefix));
        }

        for(int i = 0; i<remain.length(); i++){
            generateNumber(
                    set,
                    prefix + remain.charAt(i),
                    remain.substring(0, i) + remain.substring(i+1)
            );
        }

    }
}
