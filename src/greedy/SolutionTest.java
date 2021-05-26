package greedy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2021/1/26 18:12
 */
public class SolutionTest {

    static Solution st;

    @Before
    public void setUp() throws Exception {
        st = new Solution();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void eraseOverlapIntervals() {
        int[][] input = {{1,2},{1,2},{1,2}};
        System.out.println(st.eraseOverlapIntervals(input));
    }
}