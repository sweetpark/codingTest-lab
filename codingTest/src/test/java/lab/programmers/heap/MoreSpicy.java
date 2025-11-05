package lab.programmers.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/* https://school.programmers.co.kr/learn/courses/30/lessons/42626 */

/***
 *
 * 문제 설명)
 * 매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
 *
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
 * Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 사항
 * scoville의 길이는 2 이상 1,000,000 이하입니다.
 * K는 0 이상 1,000,000,000 이하입니다.
 * scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 *
 * 입출력 예
 *
 * |        scoville	     |    K	  | return
 * |  [1, 2, 3, 9, 10, 12]	|    7	  |     2
 *
 *
 * 입출력 예 설명
 *
 * 스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
 * 새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5
 * 가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]
 *
 * 스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
 * 새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13
 * 가진 음식의 스코빌 지수 = [13, 9, 10, 12]
 *
 * 모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회입니다.
 *
 *
 *
 */

// FIXME) 객체지향적 풀이 진행 필요 ( 객체 지향 코드는 src/main/java에 적용 필요 )
public class MoreSpicy {


    @Test
    @DisplayName("더 맵게")
    public void run(){
        //givenZ
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;

        //when
        int result = queueSolution(scoville, k);

        //then
        Assertions.assertEquals(result, 2);
    }

    //queue 풀이 (우선순위큐)
    private int queueSolution(int[] scoville, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }

        int count = 0;

        while(pq.size() >= 2){

            if(pq.peek() >= k){
                break;
            }

            count++;
            int first = pq.poll();
            int second = pq.poll();

            int mixResult = queueMixFood(first, second);
            pq.add(mixResult);
        }

        if(!pq.isEmpty() && pq.peek() >= k) return count;
        else return -1;
    }

    private int queueMixFood(int first, int second) {
        return first + (second * 2);
    }


    // Arrays 정렬 풀이 (효율성 체크 실패)
    private int solution(int[] scoville, int k) {
        Arrays.sort(scoville);
        int result = 0;

        while(true){
            // 갱신된 scoville 비교 진행
            if(scoville[0] < k && scoville.length != 1) {
                // 음식섞기 진행
                scoville = mixFood(scoville);
                Arrays.sort(scoville);
                result++;
            }else{
                // 7이상 안될경우
                if(scoville.length == 1 && scoville[0] < k){
                    result = -1;
                }
                break;
            }
        }



        return result;
    }

    private int[] mixFood(int[] scoville) {
        //가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
        int mixResult = scoville[0] + (scoville[1] * 2);
        int[] newScoville = new int[scoville.length -1];
        for(int i = 2 ; i < scoville.length ; i++){
            newScoville[i - 1] = scoville[i];
        }
        newScoville[0] = mixResult;

        return newScoville;
    }
}
