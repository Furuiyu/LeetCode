package recursion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2021/1/17 21:53
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
    public void letterCombinations() {
        List<String> strings = st.letterCombinations("27");
        System.out.println("strings = " + strings);
        System.out.println("strings = " + strings.size());
    }

    @Test
    public void partition() {
        System.out.println(st.partition("aabbcft"));
    }

    @Test
    public void permute() {
        int[] input = {1, 2, 3};
        System.out.println(st.permute(input));
    }

    @Test
    public void permuteUnique() {
        int[] input = {1, 1, 3};
        System.out.println(st.permuteUnique(input));
    }

    @Test
    public void combine() {
        System.out.println(st.combine(5, 4));
    }


    @Test
    public void combinationSum() {
        int[] input = {5, 10, 8, 4, 3, 12, 9};
        System.out.println(st.combinationSum(input, 27));
    }

    @Test
    public void subsets() {
        int[] input = {1, 2, 3};
        System.out.println(st.subsets(input));
    }


    @Test
    public void subsetsWithDup() {
        int[] input = {1, 2, 2};
        System.out.println(st.subsetsWithDup(input));
    }

    @Test
    public void solve() {
        char[][] input = {
                {'O','X','O','O','X','X','X','O','O','O','O','O','X','O','O','O','O','X','O','X'},
                {'X','O','X','O','O','X','X','O','O','X','O','X','O','X','O','X','X','O','O','O'},
                {'O','X','O','O','O','X','X','X','X','O','O','O','O','O','X','X','X','X','O','X'},
                {'X','X','O','O','O','X','X','O','O','O','X','X','X','O','O','X','O','X','X','O'},
                {'O','X','O','X','X','O','X','O','O','O','X','O','O','X','O','O','O','O','O','X'},
                {'X','O','O','X','O','X','O','O','O','X','X','O','X','O','O','X','O','O','O','O'},
                {'X','O','O','O','X','X','O','O','O','O','O','X','O','O','X','O','O','O','O','X'},
                {'X','O','O','O','X','O','X','X','X','O','X','O','X','X','X','X','O','O','O','X'},
                {'X','O','O','X','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','X'},
                {'O','O','O','X','O','X','X','X','X','X','X','X','X','X','O','O','O','O','X','O'},
                {'X','O','X','O','X','O','O','X','X','X','O','X','X','O','O','X','X','O','O','O'},
                {'O','X','O','O','X','O','O','O','O','O','O','X','X','X','X','O','O','O','X','O'},
                {'X','O','O','O','X','X','X','O','X','O','O','O','X','O','X','O','X','O','O','X'},
                {'O','O','O','O','X','O','X','X','O','X','O','X','O','X','X','X','X','O','O','O'},
                {'O','X','X','O','O','O','O','X','O','O','X','X','X','O','O','X','X','O','X','O'},
                {'X','O','X','X','X','X','X','X','O','X','X','O','X','O','O','X','O','O','O','X'},
                {'X','O','O','O','X','O','X','O','O','X','O','X','O','O','X','O','O','X','X','X'},
                {'O','O','X','O','O','O','O','X','O','O','X','X','O','X','X','X','O','O','O','O'},
                {'O','O','X','O','O','O','O','O','O','X','X','O','X','O','X','O','O','O','X','X'},
                {'X','O','O','O','X','O','X','X','X','O','O','X','O','X','O','X','X','O','O','O'}};

        st.solve(input);
        for (char[] chars : input) {
            for (char aChar : chars) {
                System.out.print(aChar + ",");
            }
            System.out.println();
        }

    }

    @Test
    public void solveNQueens() {
        List<List<String>> lists = st.solveNQueens(8);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }
    }
}