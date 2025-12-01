package lab.programmers.fullScan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class WordBook {


    public static final char[] WORD_SET = new char[]{'A', 'I', 'O', 'E', 'U'};

    @BeforeEach
    void setUp() {

    }

    @Test
    public void test(){
        Assertions.assertEquals(0,getCount(""));
        Assertions.assertEquals(1,getCount("A"));
        Assertions.assertEquals(2,getCount("AA"));
        Assertions.assertEquals(5,getCount("AAAAA"));
        Assertions.assertEquals(6,getCount("AAAAE"));
        Assertions.assertEquals(10,getCount("AAAE"));
        Assertions.assertEquals(16,getCount("AAAI"));
        Assertions.assertEquals(1563,getCount("I"));
    }

    private int getCount(String word) {

        int append = 0;
        if(word.length() == 4){
            char c = word.charAt(word.length() - 1);
            switch (c){
                case 'A':
                    append = 5 * 0;
                    break;
                case 'E':
                    append = 5 * 1;
                    break;
                case 'I':
                    append = 5 * 2;
                    break;
                case 'O':
                    append = 5 * 3;
                    break;
                case 'U':
                    append = 5 * 4;
                    break;
            }
        } else if (word.length() == 3) {
            char c = word.charAt(word.length() - 1);
            switch (c){
                case 'A':
                    append =5 * 5 * 0;
                    break;
                case 'E':
                    append =5 * 5 * 1;
                    break;
                case 'I':
                    append =5 * 5 * 2;
                    break;
                case 'O':
                    append =5 * 5 * 3;
                    break;
                case 'U':
                    append =5 * 5 * 4;
                    break;
            }
        }else if(word.length() == 2) {
            char c = word.charAt(word.length() - 1);
            switch (c){
                case 'A':
                    append =5 * 5 * 5 * 0;
                    break;
                case 'E':
                    append =5 * 5 * 5 * 1;
                    break;
                case 'I':
                    append =5 * 5 * 5 * 2;
                    break;
                case 'O':
                    append =5 * 5 * 5 * 3;
                    break;
                case 'U':
                    append =5 * 5 * 5 * 4;
                    break;

            }

        }else if(word.length() == 1) {
            char c = word.charAt(0);
            switch (c) {
                case 'A':
                    append = 5 * 5 * 5 * 5 * 0;
                    break;
                case 'E':
                    append = 5 * 5 * 5 * 5 * 1;
                    break;
                case 'I':
                    append = 5 * 5 * 5 * 5 * 2;
                    break;
                case 'O':
                    append = 5 * 5 * 5 * 5 * 3;
                    break;
                case 'U':
                    append = 5 * 5 * 5 * 5 * 4;
                    break;
            }
        }

        int count  = 0;
        for (int i = 0; i <word.length(); i++) {
            char extractWord = word.charAt(i);

            switch(extractWord){
                case 'U':
                    count += 1;
                case 'O':
                    count += 1;
                case 'I':
                    count += 1;
                case 'E':
                    count += 1;
                case 'A':
                    count += 1;
                    break;
                default:
                    count = 0;
                    break;
            }
        }
        return count + append;
    }
}
