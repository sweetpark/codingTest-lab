package lab.programmers.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class BIgNumber {


    // 1) 자릿수가 가장 크게 만들 수 있는 값들 중 제일 큰 값
    // 2) N^2 가능 >> 2자리를 빼기떄문에, number는 10,000 이하 (NlogN 이 이상적)


    @Test
    public void test(){
        Assertions.assertEquals("1", Solution("10", 1));
        Assertions.assertEquals("94", Solution("1924", 2));
        Assertions.assertEquals("3234", Solution("1231234", 3));
        Assertions.assertEquals("775841", Solution("4177252841", 4));
        Assertions.assertEquals("7", Solution("1276", 3));
        Assertions.assertEquals("7", Solution("12765", 4));
        Assertions.assertEquals("7", Solution("12567", 4));
    }


    private String Solution(String number, int removeCount){

//        Deque<String> stack = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        for(int i = 0; i < number.length(); i++){
            String compareChar = String.valueOf(number.charAt(i));

            while(!stack.isEmpty()
                    && removeCount >  0
                    && Integer.parseInt(compareChar) > Integer.parseInt(stack.peekLast())
            ){
                stack.pollLast();
                removeCount--;
            }

            stack.addLast(compareChar);

        }


        for(int i = 0; i < removeCount; i++){
            stack.pollLast();
        }


        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pollFirst());
        }



        return sb.toString();
    }

}
