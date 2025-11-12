package org.example.DoIt_Book.정수론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Ax_By_C_21568 {
    /***
     *
     * [유클리드호제법 확장]
     * Ax+By = C
     * 1. gcd(A,B)를 구해서 계산식 순서대로 저장
     * 2. 값을 거꾸로 계산하며 x, y 값 계산
     * 3. 계산 진행 (첫 x = 1 , y = 0)
     * x = pre_x , y = pre_y - (pre_x * 몫)
     * 4. ax + by = gcd(a,b) 이며, c / gcd(a,b) = K를 가정하면,
     * x,y의 값은 : Kx , Ky 로 구할 수 있다
     *
     * 특징) C는 gcd(a,b)의 배수값 (아닐경우, 구할수가 없다)
     */

    static Deque<Integer> stack = new ArrayDeque<>();
    static String[] input;
    static List<Integer> numInput = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        for (String string : input) {
            numInput.add(Integer.parseInt(string));
        }
        Integer remain = gcd(numInput.get(0), numInput.get(1));

        if (numInput.get(2) % remain != 0){
            System.out.println("-1");
        }else{
            Node node = getNode(new Node(1, 0));
            System.out.println(numInput.get(2) * node.getX()+" "+numInput.get(2) * node.getY());
        }
    }

    static class Node{
        private int x;
        private int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return this.x;
        }
        public int getY(){
            return this.y;
        }
        public void setX(int x){
            this.x = x;
        }
        public void setY(int y){
            this.y = y;
        }
    }

    static Integer gcd(int a, int b){
        if(b == 0){
            return a;
        }else{
            stack.add(a/b);
            return gcd(b, a%b);
        }
    }

    static Node getNode(Node node){
        if(stack.isEmpty()){
            return node;
        }
        int preX= node.getY();
        int preY = node.getX();

        node.setX(preX);
        node.setY(preY - preX * stack.pollLast());

        return getNode(node);
    }
}
