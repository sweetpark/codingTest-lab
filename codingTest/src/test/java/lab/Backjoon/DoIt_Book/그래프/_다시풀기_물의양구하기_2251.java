package org.example.DoIt_Book.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _다시풀기_물의양구하기_2251 {
    /***
     * 문제)
     * https://www.acmicpc.net/problem/2251
     *
     * 중요)
     * - 경우의수 : A -> B / A -> C / B -> A / B -> C / C -> A / C -> B (6가지)
     * - 3차원 배열 사용 ( + 클래스 만들어서 사용 )
     */
    static int A;
    static int B;
    static int C;
    static Set<Integer> result;
    static Bottle[] arr;

    public static class Bottle{
        public int box;
        public int current;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = new HashSet<>();
        arr = new Bottle[3];
        for(int i = 0; i < 3; i++){
            arr[i] = new Bottle();
        }

        // 0 번쨰 : 물의부피 ,물의 양
        // 1 번째 : 물의부피 , 물의양
        arr[0].box = A;
        arr[0].current = 0;

        arr[1].box = B;
        arr[1].current = 0;

        arr[2].box = C;
        arr[2].current = C;

        BFS();
        Stream<Integer> sorted = result.stream().sorted();

        //백준 java11 호환이라서 -> sorted.toList() 지원 안되는 문제로 해당 코드 삽입 필요 (Collectors.toList()로 변환)
        List<Integer> sortedList = sorted.collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for(int i : sortedList){
            sb.append(i).append(" ");
        }
        System.out.println(sb);

    }

    public static void BFS(){
        boolean[][][] visited = new boolean[A+1][B+1][C+1];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,C});
        visited[0][0][C] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            int a = now[0];
            int b = now[1];
            int c = now[2];

            // a , b, c 현재 물의 양
            if( a == 0){
                result.add(c);
            }

            int[] current = new int[]{a,b,c};
            for(int from = 0; from < 3; from++){
                for(int to = 0; to < 3; to ++){
                    if(from == to) continue;

                    int[] next = Arrays.copyOf(current, 3);

                    int amount = Math.min(next[from], arr[to].box - next[to]);
                    next[from] -= amount;
                    next[to] += amount;

                    if(!visited[next[0]][next[1]][next[2]]){
                        visited[next[0]][next[1]][next[2]] = true;
                        q.add(next);
                    }
                }
            }
        }
    }
}
