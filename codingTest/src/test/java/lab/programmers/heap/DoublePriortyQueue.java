package lab.programmers.heap;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 이중우선순위큐
 * I 숫자 : 숫자 인풋
 * D 1 : 최댓값 제거
 * D -1 : 최솟값 제거
 */

public class DoublePriortyQueue {


    @Test
    public void solve() {

        //테스트 케이스
//        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        // 우선순위큐
        Queue<Integer> minQueue = new PriorityQueue<>();
        Queue<Integer> maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });

        for(String operation : operations) {
            String[] splits = operation.split(" ");

            System.out.println("[max] " + maxQueue.toString());
            System.out.println("[min] " +minQueue.toString());
            switch (splits[0]) {
                case "I":
                    minQueue.add(Integer.parseInt(splits[1]));
                    maxQueue.add(Integer.parseInt(splits[1]));
                    break;
                case "D":
                    if(splits[1].equals("-1")){
                        if(!minQueue.isEmpty()){
                            // 최솟값 제거
                            Integer poll = minQueue.poll();
                            maxQueue.remove(poll);
                        }
                    }else{
                        if(!maxQueue.isEmpty()){
                            //최댓값 제거
                            Integer poll = maxQueue.poll();
                            minQueue.remove(poll);
                        }
                    }
                    break;
            }
        }

        int[] result = new int[2];

        //두개 같이 있는것들 출력 ( 그중 최대값 / 최소값 하지만, 최대값이 먼저 나와야함 )
        if(!maxQueue.isEmpty()){
            result[0] =  maxQueue.poll();
        }

        if(!minQueue.isEmpty()){
            result[1] = minQueue.poll();
        }



        //result
        Assertions.assertEquals(result[0], 0);
        Assertions.assertEquals(result[1], 0);
    }

    @Test
    public void parsing(){

        String type = "";
        String number = "";

        String word = "I 16";
        String[] splits =
                word.split(" ");
        type = splits[0].trim();
        number = splits[1].trim();

        Assertions.assertEquals(type, "I");
        Assertions.assertEquals(number, "16");


    }
}
