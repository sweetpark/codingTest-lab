package lab.programmers.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.channels.Pipe;
import java.util.*;

/***
 조건)
 1. 하드디스크는 하나의 하나 작업만 진행
 2. 우선순위에 따른 작업 진행 (소요시간 짧은것 > 요청시각이 빠른 것 > 작업의 번호가 작은 순서)
 3. 작업이 끝났을때를 기준으로, 대기큐에 존재하는 우선순위 가장 높은 것을 실행함

 반환시간 : 요청시간에서부터 작업이완료된 시간

 반환시간의 평균 : sum(작업 반환시간) / 작업 개수


 job :[[작업요청시각, 작업 진행 소요시각], ...]
 [[0, 3], [1, 9], [3, 5]]

 return : 작업 평균 반환시각
 8

 ***/


public class HardDisk {


    /* (비교)
    객체화
    1. 소요시간 (rangeTime)
    2. 요청시간 (requestTime)
    3. 작업순서 (index)
    4. 작업시작 (workingTime)

    반환값 > 요청시간 - 작업시작 >> 합산

    >> 저거 순서대로 우선순위큐
     */

    public static class Work{
        private int requestTime;
        private int rangeTime;
        private int index;


        public Work(int requestTime, int rangeTime,  int index) {
            this.requestTime = requestTime;
            this.rangeTime = rangeTime;
            this.index = index;
        }

        public int returnTime(int time){
            return time + this.rangeTime - this.requestTime;
        }



        public int getRequestTime(){
            return this.requestTime;
        }


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Work [requestTime=");
            builder.append(requestTime);
            builder.append(", rangeTime=");
            builder.append(rangeTime);
            builder.append(", index=");
            builder.append(index);
            builder.append("]");
            return builder.toString();

        }

    }


    @Test
    public void example(){


        int[][] jobs = {{0,3}, {1,9}, {3,5}};
        int result = 0;
        int completed = 0;
        int currentTime = 0;


        // 요청시간 기준
        Queue<Work> requestList = new PriorityQueue<>(new Comparator<Work>(){
            @Override
            public int compare(Work o1, Work o2) {
                return o1.getRequestTime() - o2.getRequestTime();
            }
        });

        // 실행시간 기준 정렬
        Queue<Work> rangeQueue = new PriorityQueue<>((o1, o2) -> {
            if(o1.rangeTime != o2.rangeTime) return o1.rangeTime - o2.rangeTime;
            if(o1.requestTime != o2.requestTime) return o1.requestTime - o2.requestTime;
            return o1.index - o2.index;
        });

        //1. 요청시간 기준 정렬 ()
        for(int i = 0; i < jobs.length; i++){
            requestList.add(new Work(jobs[i][0], jobs[i][1], i));
        }



        //2.jobs 개수가 완료될때까지 반복
        while(completed < jobs.length){

            // 해당 시간에 포함되는 모든 작업들 넣기
            while(!requestList.isEmpty() && requestList.peek().getRequestTime() <= currentTime){
                rangeQueue.offer(requestList.poll());
            }

            // 실행 가능한 작업이 없음 > 다음 작업으로 시간 점프
            if(rangeQueue.isEmpty()){
                currentTime = requestList.peek().getRequestTime();
                continue;
            }

            // 우선순위 높은 거 빼기
            Work work = rangeQueue.poll();
            result += work.returnTime(currentTime);
            completed++;
            currentTime += work.rangeTime;

        }

        Assertions.assertEquals(8, result/jobs.length);

    }

}
