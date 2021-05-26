package sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2020/12/13 18:59
 */
public class SolutionTest {
    private static Solution st;
    public static int[] input;
    public static int len = 10;
    public static int level  = 5;
    public static long start;

    @Before
    public void setUp() {
        input = new int[(int) Math.pow(len,level)];
        st = new Solution();

        for (int i = 0; i < Math.pow(len,level); i++) {
            input[i] = (int) (Math.random() * Math.pow(len,level) + 1);
        }
//        System.out.println(Arrays.toString(input));
//        start = System.currentTimeMillis();


    }

    @After
    public void tearDown() {
        System.out.println(Arrays.toString(input));
    }

    @Test
    public void insertSort() {
        st.insertSort(input);
    }


    @Test
    public void shellSort() {
        st.shellSort(input);
    }

    @Test
    public void bubbleSort() {
        st.bubbleSort(input);
    }

    @Test
    public void quickSort() {
        st.quickSort(input,0,input.length-1);
//        Arrays.sort(input);
    }

    @Test
    public void selectSort() {
        st.selectSort(input);
    }
}