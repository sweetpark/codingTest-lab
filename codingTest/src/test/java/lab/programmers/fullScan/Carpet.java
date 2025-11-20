package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Carpet {

    @Test
    public void test(){
        Assertions.assertArrayEquals(new int[]{0,0}, getCarpet(0, 0));
        Assertions.assertArrayEquals(new int[]{3,3}, getCarpet(8, 1));
        Assertions.assertArrayEquals(new int[]{8,6}, getCarpet(24, 24));
    }

    private int[] getCarpet(int brown, int yellow) {

        for(int height = 3; height <= 2500; height++) {
            for(int width = 3; width <= 2500; width++) {
                if(brown == width * 2 + (height - 2) * 2 && yellow == (height-2) * (width-2)){
                    return new int[]{width,height};
                }
            }
        }

        return new int[]{0,0};
    }
}
