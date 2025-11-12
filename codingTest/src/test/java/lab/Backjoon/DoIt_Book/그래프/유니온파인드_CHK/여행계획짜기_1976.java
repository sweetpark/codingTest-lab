package org.example.DoIt_Book.그래프.유니온파인드_CHK;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 여행계획짜기_1976 {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+1][N+1];

        for(int i = 1; i < N + 1; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j = 1; j < N + 1; j++){
                arr[i][j] = Integer.parseInt(tmp[j - 1]);
            }
        }

        //여행 도시 정보
        int[] route = new int[M+1];
        String[] city = br.readLine().split(" ");
        for(int i = 1; i< M + 1; i++){
            route[i] = Integer.parseInt(city[i - 1]);
        }

        // 대표노드 설정
        parent = new int[N+1];
        for(int i = 1; i < N + 1; i++){
            parent[i] = i;
        }


        for(int i=1; i< N+1; i++){
            for(int j =1; j< N+1; j++){
                if(arr[i][j] == 1) union(i, j);
            }
        }


        int index = find(route[1]);
        for(int i = 2; i< route.length; i++){
            if(index != find(route[i])){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void union(int i, int j){
        i = find(i);
        j = find(j);
        if( i != j){
            parent[j] = i;
        }
    }

    public static int find(int i){
        if( i == parent[i]) return i;
        else
            return parent[i] = find(parent[i]);
    }
}



