package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

//모의고사
public class PracticeTest {

    @Test
    @DisplayName("한사람이 맞힌 개수")
    public void test(){
        //given
        int[] answers = new int[]{1,2,3,4,5};
        int[] person1 = new int[]{1,1,1,1,1};
        //when
        int success = getSuccess(answers, person1);
        //then
        Assertions.assertEquals(1, success);
    }

    @Test
    @DisplayName("다 맞힌 개수")
    public void test2(){
        int[] answers = new int[]{1,2,3,4,5};
        int[] person1 = new int[]{1,2,3,4,5};
        int success = getSuccess(answers, person1);
        Assertions.assertEquals(5, success);
    }

    @Test
    @DisplayName("세명의 학생 중에 더 많이 맞힌 개수")
    public void test3(){
        int[] answers = new int[]{1,2,3,4,5};
        int[] person1 = new int[]{1,1,1,1,1};
        int[] person2 = new int[]{1,2,3,4,5};
        int[] person3 = new int[]{1,1,1,1,1};

        PriorityQueue<Data> queue = initQueue();

        queue.offer(new Data(1, getSuccess(answers, person1)));
        queue.offer(new Data(2, getSuccess(answers, person2)));
        queue.offer(new Data(3, getSuccess(answers, person3)));

        List<Integer> array = getResults(queue);

        Assertions.assertArrayEquals(new int[]{2}, array.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("세명중 맞힌개수가 가장 많은 두명의 학생이 동일한 경우")
    public void test4(){
        int[] answers = new int[]{1,2,3,4,5};
        int[] person1 = new int[]{1,1,1,1,1};
        int[] person2 = new int[]{3,1,2,5,4};
        int[] person3 = new int[]{1,1,1,1,1};

        PriorityQueue<Data> queue = initQueue();

        queue.offer(new Data(1, getSuccess(answers, person1)));
        queue.offer(new Data(2, getSuccess(answers, person2)));
        queue.offer(new Data(3, getSuccess(answers, person3)));

        List<Integer> array = getResults(queue);

        Assertions.assertArrayEquals(new int[]{1,3}, array.stream().mapToInt(i -> i).toArray());
    }

    @Test
    @DisplayName("수포자들 설정하기")
    public void test5(){
        int[] answers = new int[]{1,3,2,4,2};
        // 1,2,3,4,5
        // 2, 1, 2, 3, 2, 4, 2, 5,
        //  3, 3, 1, 1, 2, 2, 4, 4, 5, 5
        int[] person1_loop = new int[]{1,2,3,4,5};
        int[] person2_loop = new int[]{2,1,2,3,2,4,2,5};
        int[] person3_loop = new int[]{3,3,1,1,2,2,4,4,5,5};

        int[] person1 = getPersonAnswer(answers, person1_loop);
        int[] person2 = getPersonAnswer(answers, person2_loop);
        int[] person3 = getPersonAnswer(answers, person3_loop);


        PriorityQueue<Data> queue = initQueue();

        queue.offer(new Data(1, getSuccess(answers, person1)));
        queue.offer(new Data(2, getSuccess(answers, person2)));
        queue.offer(new Data(3, getSuccess(answers, person3)));

        List<Integer> array = getResults(queue);

        array.sort(Comparator.comparingInt(o -> o));

        Assertions.assertArrayEquals(new int[]{1,2,3}, array.stream().mapToInt(i -> i).toArray());
    }

    private static int[] getPersonAnswer(int[] answers, int[] person1_loop) {
        int[] person1 = new int[answers.length];

        for(int i = 0; i < answers.length; i++){
            int j = i % person1_loop.length;
            person1[i] = person1_loop[j];
        }
        return person1;
    }

    private static PriorityQueue<Data> initQueue() {
        return new PriorityQueue<>((o1, o2) -> o2.success - o1.success);
    }

    private static List<Integer> getResults(PriorityQueue<Data> queue) {
        List<Integer> array = new ArrayList<>();
        while(!queue.isEmpty()){
            Data current = queue.poll();
            array.add(current.no);

            if(queue.peek() != null && (current.success != queue.peek().success)){
                break;
            }
        }
        return array;
    }

    static class Data{
        int no;
        int success;

        public Data(int no, int success) {
            this.no = no;
            this.success = success;
        }
    }

    private static int getSuccess(int[] answers, int[] person1) {
        int success = 0;
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == person1[i]){
                success++;
            }
        }
        return success;
    }
}
