package dp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Fr
 * @date : 2021/1/21 11:52
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
    public void fib() {
        System.out.println(st.fib(1000));

    }

    @Test
    public void minimumTotal() {


        List<Integer> list1 = new ArrayList<Integer>();
        Collections.addAll(list1,2);
        List<Integer> list2 = new ArrayList<Integer>();
        Collections.addAll(list2,3,4);
        List<Integer> list3 = new ArrayList<Integer>();
        Collections.addAll(list3,6,5,7);
        List<Integer> list4 = new ArrayList<Integer>();
        Collections.addAll(list4,4,1,8,3);
        ArrayList<List<Integer>> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list1,list2,list3,list4);
        System.out.println(st.minimumTotal(arrayList));

    }

    @Test
    public void minPathSum() {
        int[][] input =  {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(st.minPathSum(input));
    }

    @Test
    public void integerBreak() {
        System.out.println(st.integerBreak(10));
    }

    @Test
    public void numSquares() {
        System.out.println(st.numSquares(9));
    }

    @Test
    public void uniquePaths() {
        System.out.println(st.uniquePaths(3, 7));
    }

    @Test
    public void rob() {
        int[] input= {2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(st.rob(input));
    }

    @Test
    public void rob2() {
        int[] input = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(st.rob2(input));

    }

    @Test
    public void lengthOfLIS() {
        int[] input= {2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(st.lengthOfLIS(input));
    }

    @Test
    public void longestCommonSubsequence() {
        String text1 = "abcdvdsfssfsde", text2 = "acfsdfdsf";
        System.out.println(st.longestCommonSubsequence(text1, text2));
    }

    @Test
    public void coinChange() {
        int[] input= {1,2,5};
        int amount = 11;
        System.out.println(st.coinChange(input,amount));
    }

    @Test
    public void combinationSum4() {
       int[]  nums = {1, 2};
        int target = 2;
        System.out.println(st.combinationSum4(nums, target));
    }

    @Test
    public void longestPalindrome() {
        System.out.println(st.longestPalindrome("aaaa"));
    }

    @Test
    public void maxValue() {
        int[][] arr = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(st.maxValue(arr));
    }

    @Test
    public void waysToStep() {
        System.out.println(st.waysToStep(5));
    }

    @Test
    public void checkSubarraySum() {
        int[] in = {0,1,0};
        System.out.println(st.checkSubarraySum(in, 0));
    }

    @Test
    public void kConcatenationMaxSum() {
        int[] in = {10000,10000,10000,10000,10000,10000,10000,10000,10000,10000};
        System.out.println(st.kConcatenationMaxSum(in, 100000));
    }
}