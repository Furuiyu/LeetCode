package array.day01;

import java.util.HashMap;

/**
 * @author Fr
 */
public class FindRepeatNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = solution.findRepeatNumber(nums);
        System.out.println(repeatNumber);
    }


}

class Solution {
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return num;
            } else {
                map.put(num, Boolean.TRUE);
            }
        }

        return 0;

    }

}
