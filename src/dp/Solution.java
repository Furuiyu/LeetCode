package dp;



import java.util.Arrays;
import java.util.List;

/**
 * @author : Fr
 * @date : 2021/1/21 11:44
 */
public class Solution {
    public int fib(int n) {

        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i];
        }
        return memo[n] % 1000000007;

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[][] minValue = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int i = 0; i < minValue[0].length; i++) {
            minValue[minValue.length - 1][i] = triangle.get(minValue.length - 1).get(i);
        }
        for (int i = minValue.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minValue[i][j] = triangle.get(i).get(j) + Math.min(minValue[i + 1][j], minValue[i + 1][j + 1]);
            }
        }
        return minValue[0][0];


    }

    private int getMinValue(int i, int j, int[][] minValue, List<List<Integer>> triangle) {
        if (minValue[i][j] == Integer.MAX_VALUE) {
            minValue[i][j] = triangle.get(i).get(j) + Math.min(getMinValue(i + 1, j, minValue, triangle), getMinValue(i + 1, j + 1, minValue, triangle));
        }
        return minValue[i][j];
    }

    private int minimumTotalCal(int index, List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        } else {
            List<List<Integer>> next = triangle.subList(1, triangle.size());
            int a = triangle.get(0).get(index) + minimumTotalCal(index, next);
            int b = triangle.get(0).get(index) + minimumTotalCal(index + 1, next);
            return Math.min(a, b);
        }
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[][] minMap = new int[grid.length][grid[0].length];
        for (int[] ints : minMap) {
            Arrays.fill(ints, -1);
        }
        minMap[0][0] = grid[0][0];
        minMap[grid.length - 1][grid.length - 1] = grid[grid.length - 1][grid.length - 1];
        return getMinPath(grid.length - 1, grid[0].length - 1, grid, minMap);
    }

    private int getMinPath(int i, int j, int[][] grid, int[][] minMap) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            if (minMap[i - 1][j] < 0) {
                minMap[i - 1][j] = getMinPath(i - 1, j, grid, minMap);
            }
            a = minMap[i - 1][j];
        }
        if (j - 1 >= 0) {
            if (minMap[i][j - 1] < 0) {
                minMap[i][j - 1] = getMinPath(i, j - 1, grid, minMap);
            }
            b = minMap[i][j - 1];
        }
        return grid[i][j] + Math.min(a, b);


    }

    public int integerBreak(int n) {
        int[] maxValue = new int[n + 1];
        maxValue[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                maxValue[i] = max3(maxValue[i], j * (i - j), j * maxValue[i - j]);
            }
        }
        return maxValue[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public int numSquares(int n) {
        int[] pathValue = new int[n + 1];
        pathValue[1] = 1;
        for (int i = 1; i < n + 1; i++) {
            pathValue[i] = Integer.MAX_VALUE;
            for (int j = 1; i - j * j >= 0; j++) {
                pathValue[i] = Math.min(pathValue[i], pathValue[i - j * j] + 1);
            }
        }
        return pathValue[n];
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        //长度为n时，的排列组合种类
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (s.length() == 1) {
            return dp[1];
        }
        for (int i = 2; i <= s.length(); i++) {
            //得到当前数；
            int num = Integer.parseInt(String.valueOf(s.charAt(i - 1)));
            //得到当前数的前一个数
            int nums2 = Integer.parseInt(String.valueOf(s.charAt(i - 2)));
            if (nums2 + num == 0 || (num == 0 && nums2 > 2)) {
                return 0;
            } else if (num == 0 || nums2 == 0) {
                dp[i] = num == 0 ? dp[i - 2] : dp[i - 1];
            } else {
                dp[i] = nums2 * 10 + num > 26 ? dp[i - 1] : dp[i - 2] + dp[i - 1];
            }

        }
        return dp[s.length()];
    }

    public int uniquePaths(int m, int n) {
        int[][] step = new int[m][n];
        
//        step[m-1][n-1] = 0;
        Arrays.fill(step[m-1],1);
        for (int i = 0; i < m; i++) {
            step[i][n-1] =  1;
        }
        for (int i = m-2; i >=0 ; i--) {
            for (int j = n-2; j >=0; j--) {
                step[i][j] = step[i+1][j]+step[i][j+1];
            }
        }
        return step[0][0];

    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length+1,n=obstacleGrid[0].length+1;

        int[][]  step = new int[m][n];
        if (obstacleGrid[m-2][n-2]==1){
            return 0;
        }

        for (int i = m-2; i >=0 ; i--) {
            for (int j = n-2; j >=0; j--) {
                if (i==m-2 && j==n-2){
                    continue;
                }
                if (obstacleGrid[i][j]==1){
                    step[i][j] = 0;
                }else {
                    step[i][j] = step[i+1][j]+step[i][j+1];
                }

            }
        }
        return step[0][0];

    }

    public int rob(int[] nums) {
        if (nums==null || nums.length==0){
            return 0;
        }
        int[] memo = new int[nums.length];
//        Arrays.fill(memo,-1);
        //这里的nums[j]是为了将当前值设置为之前的,后面的相对于dp[x](x>=i)
        memo[nums.length-1] = nums[nums.length-1];
        for (int i = nums.length-2; i >=0 ; i--) {
            for (int j = i; j < nums.length; j++) {
                memo[i] = Math.max(memo[i],nums[j]+(j+2<nums.length?memo[j+2]:0));
            }
        }
        return memo[0];

    }

    /**
     * 考虑 nums从index起
     * @param nums 数组
     * @param index 索引
     * @return 价值
     */
    private int tryRob(int[] nums, int index,int[] memo) {
        if (index>=nums.length){
            return 0;
        }
        if (memo[index]!=-1){
            return memo[index];
        }

        for (int i = index; i < nums.length; i++) {
            memo[index] = Math.max( memo[index],nums[i]+tryRob(nums,i+2,memo));
        }
        return memo[index];
    }
    public int rob2(int[] nums) {
        if (nums==null || nums.length==0){
            return 0;
        }
        int[] dp1 = new int[nums.length-1],dp2 = new int[nums.length-1];
//        int[] dp1 = Arrays.copyOfRange(nums, 1, nums.length);
        dp1[dp1.length-1] = nums[nums.length-1];
//        int[] dp2 = Arrays.copyOfRange(nums, 0, nums.length-1);
        dp2[dp2.length-1] = nums[nums.length-2];
        for (int i = dp1.length-2; i >=0; i--) {
            for (int j = i; j < dp1.length; j++) {
                dp1[i] = Math.max(dp1[i],nums[i+1]+(j+2<dp1.length?dp1[j+2]:0));
            }
        }
        for (int i = dp2.length-2; i >=0; i--) {
            for (int j = i; j < dp2.length; j++) {
                dp2[i] = Math.max(dp2[i],nums[i]+(j+2<dp2.length?dp2[j+2]:0));
            }
        }
        return Math.max(dp1[0],dp2[0]);


    }
    public int lengthOfLIS(int[] nums) {
        if (nums==null ||nums.length==0){
            return 0;
        }
        int[] len = new int[nums.length];
        int max = 0;
        Arrays.fill(len,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j<i; j++) {
                if (nums[i]>nums[j]){
                    len[i] = Math.max(len[i],1+len[j]);
                    max = Math.max(max,len[i]);
                }
            }
        }
        return max;
    }
    public int wiggleMaxLength(int[] nums) {
        if (nums == null||nums.length<=2) {
            return 0;
        }
        int[] rise = new int[nums.length];
        Arrays.fill(rise,1);
        int max = 2;
        int[] decline = new int[nums.length];
        Arrays.fill(decline,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]){
                    rise[i] = Math.max(rise[i],1+decline[j]);
                    max = Math.max(max,rise[i]);
                }
                if (nums[i]<nums[j]){
                    decline[i] = Math.max(decline[i],1+rise[j]);
                    max = Math.max(max,decline[i]);
                }
            }
        }

        return max;

    }
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1==null||text2==null||text1.length()==0||text2.length()==0){
            return 0;
        }
        int[][] lens = new int[text1.length()][text2.length()];
        for (int[] len :lens){
            Arrays.fill(len,-1);
        }
        return lcs(text1,text2,text1.length()-1,text2.length()-1,lens);
    }

    private int lcs(String text1, String text2, int m, int n, int[][] lens) {
        if (m<0||n<0){
            return 0;
        }
        if (lens[m][n]!=-1){
            return lens[m][n];
        }

        if (text1.charAt(m)==text2.charAt(n)){
            lens[m][n] = 1;
            lens[m][n] +=  lcs(text1, text2, m-1, n-1, lens);
        }else {
            lens[m][n] = 0;
            lens[m][n] +=  Math.max(lcs(text1, text2, m-1, n, lens),lcs(text1, text2, m, n-1, lens));
        }
        return lens[m][n];

    }
    public boolean canPartition(int[] nums) {
        if (nums.length<2){
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum%2!=0){
            return false;
        }
        int[][] dp = new int[nums.length][sum / 2];
        return tryPartition(nums,nums.length-1,sum/2,dp);
    }

    private boolean tryPartition(int[] nums, int index, int size,int[][] dp) {
        if (size==0){
            return true;
        }
        if (size<0||index<0){
            return false;
        }
        if (dp[index][size]==0){
            if (tryPartition(nums, index-1, size,dp)||tryPartition(nums, index-1, size-nums[index],dp)){
                dp[index][size] = 1;
            }else {
                dp[index][size] = -1;
            }
        }
        return dp[index][size]==1;
    }
    int value = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if (amount==0){
            return 0;
        }
        Arrays.sort(coins);
        tryFill(amount,coins,coins.length-1,0);
        return value ==Integer.MAX_VALUE?-1: value;

    }

    private void tryFill(int amount, int[] coins, int index,int count) {
        if (amount==0){
            value = Math.min(value,count);
            return;
        }
        if (index<0 ){
            return;
        }
        int countStep = amount / coins[index];
        while (countStep>=0 && count+countStep< value){
            tryFill(amount - (coins[index]*countStep), coins, index-1,count+ countStep);
            countStep--;
        }


    }
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += combinationSum4(nums, target - num);
            }
        }
        return res;
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        String sb = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean b = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = b;
                    } else {
                        dp[i][j] = (b && dp[i + 1][j - 1]);
                    }
                }
                if (dp[i][j] && l + 1 > sb.length()) {
                    sb = s.substring(i, i + l + 1);
                }
            }
        }


        return sb;




    }
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        int[] freq = getMAndN(strs[0]);

        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (freq[0]<=i && freq[1]<=j){
                    dp[0][i][j] = 1;
                }

            }
        }
        for (int k = 1; k < strs.length; k++) {
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    freq = getMAndN(strs[k]);
                    dp[k][i][j] = dp[k-1][i][j];
                    if (freq[0]<=i && freq[1]<=j){
                        dp[k][i][j] = Math.max(dp[k][i][j],dp[k-1][i-freq[0]][j-freq[1]]+1);
                    }

                }
            }
        }
        return dp[strs.length-1][m][n];
    }

    private int[] getMAndN(String str) {
        int[] response = new int[2];
        for (int i = 0; i < str.length(); i++) {
            response[str.charAt(i)-'0']++;
        }
        return response;
    }
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length==0||arr.length==1||arr.length==2){
            return arr.length;
        }
        int[] dpUp = new int[arr.length + 1];
        int[] dpDown = new int[arr.length + 1];

        Arrays.fill(dpUp,1);
        Arrays.fill(dpDown,1);

        for (int i = 1; i <= arr.length; i++) {
            dpDown[i]=dpUp[i] =1;
            if (arr[i]>arr[i-1]){
                dpUp[i] = dpDown[i-1]+1;
            }
            else if (arr[i]<arr[i-1]){
                dpDown[i] = dpUp[i-1]+1;
            }

        }
        int ret = 1;
        for (int i = 1; i < arr.length; i++) {
            ret = Math.max(ret, dpUp[i]);
            ret = Math.max(ret, dpDown[i]);
        }
        return ret;

    }

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i < n + 1; i++){
            for(int j = 2; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = grid[0][i]+dp[0][i-1];
        }

        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0]+dp[i-1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = grid[i][j]+Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[grid.length-1][grid[0].length-1];
        

    }
    public int waysToStep(int n) {
        int[] dp =  new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4;i<=n;i++){
            dp[i] = (dp[i-3]+dp[i-2]+dp[i-1])%1000000007;
        }
        return dp[n];
    }
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];

    }
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        if (cost.length<3){
            return 0;
        }
        dp[2] = Math.min(cost[0],cost[1]);
        for (int i = 3; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
        }
        return dp[cost.length];

    }
    public boolean divisorGame(int N) {
        boolean[] f = new boolean[N + 5];

        f[1] = false;
        f[2] = true;
        for (int i = 3; i <= N; ++i) {
            for (int j = 1; j < i; ++j) {
                if ((i % j) == 0 && !f[i - j]) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[N];
    }
    public boolean checkSubarraySum(int[] nums, int k) {

        int[] dp = new int[nums.length+1];
        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }



        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = dp[j + 1] - dp[i];
                if(sum ==k||(k!=0 && sum %k==0)){
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 这里遍历应该采用二分遍历,或者轻微减枝
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < len; ++i) {
            person[i] = new int[]{height[i], weight[i]};
        }

        Arrays.sort(person, (a, b) -> a[0] == b[0] ? b[0] - a[0] : a[0] - b[0]);
        int[] dp = new int[len];
        dp[len-1] = 1;
        int res = 1;
        for (int i = len-2; i >= 0; i--) {
            int max_val = 0, base_weight = person[i][1];
            for (int j = i+1; j < len ; j++) {
                if (dp[j]<=max_val){
                    continue;
                }
                if(max_val>=len-j){
                    break;
                }
                if (person[i][0]<person[j][0]&&base_weight < person[j][1]) {
                    max_val = Math.max(max_val, dp[j]);
                }
            }
            dp[i] = max_val + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public int kConcatenationMaxSum(int[] arr, int k) {

        long pre = 0;
        long ans = 0;
        while (k>0){
            for (int num:arr){
                pre = Math.max((num+pre),num);
                ans = Math.max(pre,ans);
            }
            if(ans<0){

            }
            k--;
        }

        return (int)(ans%1000000007);

    }


}




