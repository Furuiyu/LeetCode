package find.day01;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2020/12/3 20:01
 */
public class SolutionTest {
    Solution solution;




    @Before
    public void setUp() throws Exception {
         solution = new Solution();
    }
    @Test
    public void test1() {
        System.out.println(94911151*1.0/94911150);
        System.out.println(94911151*1.0/94911152);
        System.out.println(94911152*1.0/94911151);

        System.out.println(94911150*1.0/94911151==94911151*1.0/94911152);


    }

    @Test
    public void intersection() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2,2};
        int[] intersection = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));


    }

    @Test
    public void isAnagram() {
        String s = "aacc";
        String t = "ccac";
        boolean anagram = solution.isAnagram(s, t);
        System.out.println(anagram);

    }

    @Test
    public void isHappy() {
        System.out.println(solution.isHappy(19));
    }

    @Test
    public void isPossible() {
        int[] input = {1,2,3,3,4,4,5,5};
        System.out.println(solution.isPossible(input));

    }

    @Test
    public void wordPattern() {
        String  pattern = "abba", str = "dog dog dog dog";
        System.out.println(solution.wordPattern(pattern, str));

    }

    @Test
    public void isIsomorphic() {
        String s = "paper", t = "title";
        System.out.println(solution.isIsomorphic(s, t));
    }


    @Test
    public void frequencySort() {
        System.out.println(solution.frequencySort("aaaaccccddsaafgfergfdgbvrtdshbxbhrtgvsdfvgd"));
    }

    @Test
    public void twoSum() {

        int[] nums = {3,2,4};
        int target = 6;
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }

    @Test
    public void subsequence() {
        String s = "atpaaabpabttpcat";
        String t = "pat";
        System.out.println(solution.subsequence(s, t));
    }

    @Test
    public void threeSum() {
        int[] input = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        System.out.println(solution.threeSum(input));
    }

    @Test
    public void fourSum() {
        int[] input = {-1,2,1,-4};
        System.out.println(solution.fourSum(input, 3));
    }



    @Test
    public void threeSumClosest() {
        int[] input = {-1,2,1,-4};
        int target = 1;
        System.out.println(solution.threeSumClosest(input, target));
    }

    @Test
    public void fourSumCount() {
        int[] A = { 1, 2} ,B = {-2,-1},C = {-1, 2},D = { 0, 2};
        System.out.println(solution.fourSumCount(A, B, C, D));
    }

    @Test
    public void groupAnagrams() {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strings));
    }

    @Test
    public void numberOfBoomerangs() {
        int[][] input = {{1,0},{0,0},{2,0}};
        System.out.println(solution.numberOfBoomerangs(input));
    }


    @Test
    public void maxPoints() {
        int[][] input = {{0,0},{94911150,94911151},{94911151,94911152}};
        System.out.println(solution.maxPoints(input));
    }

    @Test
    public void containsNearbyDuplicate() {
        int[] input  ={1,0,1,1};
        System.out.println(solution.containsNearbyDuplicate(input,1));
    }

    @Test
    public void containsNearbyAlmostDuplicate() {
        int[] input={3,6,0,4};
        int k = 2, t = 2;
        System.out.println(solution.containsNearbyAlmostDuplicate(input, k, t));
    }

    @Test
    public void minArray() {
        int[] input = {3,3,1,3};
        System.out.println(solution.minArray(input));
    }

    @Test
    public void pondSizes() {
        int[][] in ={{0,2,1,0},{0,1,0,1},{1,1,0,1},{0,1,0,1 }};
        solution.pondSizes(in);
    }
}