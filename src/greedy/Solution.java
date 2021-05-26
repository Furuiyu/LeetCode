package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : Fr
 * @date : 2021/1/26 16:07
 */
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length()>t.length()){
            return false;
        }
        int i=0,j = 0;
        while (i<s.length()&&j < t.length()) {
            if (s.charAt(i)==s.charAt(j)){
                i++;
            }
            j++;
        }
        return i == s.length();
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length==0){
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[1]!=o2[1]){
                return o1[1]-o2[1];
            }else {
                return o1[0]-o2[0];
            }
        });
//        int[] memo = new int[intervals.length];
//        Arrays.fill(memo,1);
//        for (int i = 1; i < intervals.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (intervals[i][0]>=intervals[j][1]){
//                    memo[i] = Math.max(memo[i],1+memo[j]);
//                }
//            }
//        }
        int res = 0;
        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0]>intervals[pre][i]){
                res++;
                pre = i;
            }
        }
//        for (int i : memo) {
//            res = Math.max(i,res);
//        }

        return intervals.length-res;
    }
}
