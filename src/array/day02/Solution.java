package array.day02;



import java.util.*;


/**
 * @author : Fr
 * @date : 2020/11/21 22:20
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        int i, j = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j++);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            swap(s, i, s.length - i - 1);
        }

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n         int整型 玩偶数
     * @param m         int整型 区间数
     * @param intervals Interval类一维数组 表示区间
     * @return int整型
     */
    public int doll(int n, int m, int[] intervals) {
        LinkedHashMap<Integer, Boolean> map = new LinkedHashMap<>();
        for (int pos : intervals) {
            map.put(pos, false);
        }
        int start = intervals[0];
        int end = intervals[intervals.length - 1];
        int mid = intervals[(start + end) / 2];
        for (int i = 0; i < n; i++) {
            if (!map.get(start)) {
                map.put(start, true);
            } else if (!map.get(end)) {
                map.put(end, true);
            } else if (!map.get(mid)) {
                map.put(mid, true);
            }

        }

        return 0;
        // write code here
    }

    public int removeDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int temp;
        int pos;
        boolean flag = true;
        int count = 0;
        int length = nums.length;
        for (int i = 0; flag && i < length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                if (map.get(nums[i]) == 2) {
                    temp = nums[i];
                    pos = i + 1;
                    count++;
                    for (int j = i; pos < length && j < length; j++) {
                        while (pos < length && nums[pos] == temp) {
                            pos++;
                            count++;
                        }
                        if (pos == length && nums[pos - 1] == temp) {
                            count++;
                            flag = false;
                        } else {
                            nums[j] = nums[pos++];
                        }
                    }
                    length -= count;
                } else {
                    map.put(nums[i], (map.get(nums[i])) + 1);
                }
            }
        }

        return length;

    }

    public int removeElement(int  [] nums, int val) {
        int response = 0;
        for (int i = 0; i < nums.length - response; i++) {
            if (nums[i] == val) {
                if (nums.length - 1 - response - i >= 0) {
                    System.arraycopy(nums, i + 1, nums, i, nums.length - 1 - response - i);
                }
                i--;
                response++;
            }
        }
        return nums.length - response;

    }

    public void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        for (int num : nums) {
            switch (num) {
                case 0:
                    i++;
                    break;
                case 1:
                    j++;
                    break;
                default:
                    break;
            }
        }
        Arrays.fill(nums, 0, i, 0);
        Arrays.fill(nums, i, i + j, 1);
        Arrays.fill(nums, i + j, nums.length, 2);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] response = new int[nums1.length];
        int posNum1 = 0;
        int posNum2 = 0;
        for (int i = 0; posNum1 < m && posNum2 < n; i++) {
            if (nums1[posNum1] <= nums2[posNum2]) {
                response[i] = nums1[posNum1++];
            } else {
                response[i] = nums2[posNum2++];
            }
        }
        if (posNum1 == m) {
            for (int i = posNum1 + posNum2; i < nums1.length; i++) {
                response[i] = nums2[posNum2++];
            }
        } else {
            for (int i = posNum2 + posNum1; i < nums1.length; i++) {
                response[i] = nums1[posNum1++];
            }
        }
        System.arraycopy(response, 0, nums1, 0, nums1.length);

    }

    public int findKthLargest(int[] nums, int k) {
        //注意这里的k已经变了
        k = nums.length - k;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int i = low;
            //这里把数组以A[low]的大小分为两部分，
            //一部分是小于A[low]的，一部分是大于A[low]的
            // [low,i]<A[low]，[i+1,j)>=A[low]
            for (int j = low + 1; j <= high; j++) {
                if (nums[j] < nums[low]) {
                    swap(nums, j, ++i);
                }
            }
            swap(nums, low, i);
            if (k == i) {
                return nums[i];
            } else if (k < i) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return -1;
    }

    public int[] twoSum(int[] numbers, int target) {
        HashMap<String, List<Integer>> map = new LinkedHashMap<>();
        List<Integer> list;
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(String.valueOf(numbers[i]))) {
                map.get(String.valueOf(numbers[i])).add(i);
            } else {
                list = new ArrayList<>();
                list.add(i);
                map.put(String.valueOf(numbers[i]), list);
            }
        }
        String key2;
        for (String key1 : map.keySet()) {
            key2 = String.valueOf(target - Integer.parseInt(key1));
            if (map.containsKey(key2)) {
                if (!key1.equals(key2)) {
                    return new int[]{map.get(key1).get(0) + 1, map.get(key2).get(0) + 1};
                }
                if (map.get(key1).size() > 1) {
                    return new int[]{map.get(key1).get(0) + 1, map.get(key1).get(1) + 1};
                }

            }
        }

        return null;

    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        int column = 0;
        int raw = 0;
        Boolean flag = false;
        if (matrix.length > 0) {
            boolean[][] booleans = new boolean[matrix.length][matrix[0].length];
            return find(matrix, column, raw, target, false, booleans);
        }
        return false;


    }

    private boolean find(int[][] matrix, int column, int raw, int target, Boolean flag, boolean[][] booleans) {
        if (column < matrix.length && raw < matrix[0].length && !booleans[column][raw]) {

            if (matrix[column][raw] == target) {
                return true;
            }
            booleans[column][raw] = true;
            if (matrix[column][raw] > target) {
                return false;
            }
            System.out.printf("%d行%d列%d\n", column, raw, matrix[column][raw]);


            if (column + 1 < matrix.length && !flag && matrix[column + 1][raw] <= target) {
                flag = find(matrix, column + 1, raw, target, false, booleans);
            }
            if (raw + 1 < matrix[0].length && !flag && matrix[column][raw + 1] <= target) {
                flag = find(matrix, column, raw + 1, target, false, booleans);

            }
            if (raw - 1 > 0 && !flag && matrix[column][raw - 1] <= target) {
                flag = find(matrix, column, raw - 1, target, false, booleans);

            }
            if (column - 1 > 0 && !flag && matrix[column - 1][raw] <= target) {
                flag = find(matrix, column - 1, raw, target, false, booleans);
            }
        }
        return flag;
    }

    public String sortString(String s) {
        char min = '0';
        String temp = s;

        char max = '0';
        StringBuilder res = new StringBuilder("");
        boolean begin = true;
        boolean flag = true;
        while (s.length() > 0) {
            if (flag) {
                temp = temp.replace(String.valueOf(min), "");
                if (temp.length() > 0) {
                    min = temp.charAt(0);
                    for (int i = 1; i < temp.length(); i++) {
                        if (temp.charAt(i) <= min) {
                            min = temp.charAt(i);
                        }
                    }
                    if (begin || res.charAt(res.length() - 1) < min) {
                        res.append(min);
                        s = s.substring(0, s.indexOf(min)) + s.substring(s.indexOf(min) + 1);
                        begin = false;
                    } else {
                        flag = false;
                        begin = true;
                        temp = s;
                        min = '0';
                    }
                } else {
                    flag = false;
                    begin = true;
                    temp = s;
                    min = '0';
                }

            } else {
                temp = temp.replace(String.valueOf(max), "");
                if (temp.length() > 0) {
                    max = temp.charAt(0);
                    for (int i = 0; i < temp.length(); i++) {
                        if (temp.charAt(i) >= max) {
                            max = temp.charAt(i);
                        }
                    }
                    if (begin || res.charAt(res.length() - 1) > max) {
                        res.append(max);
                        s = s.substring(0, s.indexOf(max)) + s.substring(s.indexOf(max) + 1);
                        begin = false;
                    } else {
                        flag = true;
                        begin = true;
                        temp = s;
                        max = '0';
                    }
                } else {
                    flag = true;
                    begin = true;
                    temp = s;
                    max = '0';
                }


            }

        }
        return res.toString();
    }

    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int len = nums.length + 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= s) {
                if ((right - left + 1) < len) {
                    len = right - left + 1;
                }
                sum -= nums[left++];
            }
            right++;


        }
        return len == nums.length + 1 ? 0 : len;

    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        boolean[] freq = new boolean[128];
        while (right < s.length()) {
            if (freq[s.charAt(right)]) {
                if (right - left > max) {
                    max = right - left;
                }
                while (freq[s.charAt(right)]) {
                    freq[s.charAt(left++)] = false;
                }


            }
            freq[s.charAt(right)] = true;
            right++;

        }

        return Math.max(max, right - left);

    }

    public int maxArea(int[] height) {
        int area;
        area = Math.min(height[0], height[height.length - 1]) * (height.length - 1);
        int pre = 0;
        int suf = height.length - 1;
        int areaTemp;
        for (int i = 1; i < height.length - 1; i++) {
            if (height[pre] < height[suf]) {
                pre++;
            } else {
                suf--;
            }
            areaTemp = Math.min(height[pre], height[suf]) * (suf - pre);
            if (areaTemp > area) {
                area = areaTemp;
            }
        }
        return area;

    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        ArrayList<Integer> back = new ArrayList<>(nums.length);
        back.add(nums[0]);
        back.add(nums[1]);
        int max = Math.abs(nums[1] - nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (back.get(i - 1) > nums[i]) {
                back.add(i - 1, nums[i]);
            } else {
                back.add(i, nums[i]);
            }

        }
        return 0;

    }

    public int largestPerimeter(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i] < A[i - 1] + A[i - 2]) {
                if (A[i] - A[i - 2] < A[i - 1]) {
                    return A[i] + A[i - 1] + A[i - 2];
                }
            }

        }
        return 0;

    }

    public List<Integer> findAnagrams(String s, String p) {
        int up;
        int down;
        int count;
        int pos;
        String find;
        boolean[] booleans = new boolean[20100];
        ArrayList<Integer> list = new ArrayList<>();
        String temp;
        for (up = 0; (s.length() - up >= p.length()); up++) {
            if (booleans[up]) {
                continue;
            }

            temp = p;
            count = 0;
            while (count < p.length() && count + up < s.length()) {
                if (temp.indexOf(s.charAt(count + up)) != -1) {
                    if (count == p.length() - 1) {
                        count++;
                        break;
                    }
                    down = temp.indexOf(s.charAt(count + up));
                    temp = temp.substring(0, down) + temp.substring(down + 1);
                    count++;
                } else {
                    if (p.indexOf(s.charAt(count + up)) == -1) {
                        up += count;
                    }

                    break;
                }
            }
            if (count == p.length()) {
                pos = up;
                find = s.substring(pos, pos + p.length());
                while (pos != -1) {
                    list.add(pos);
                    booleans[pos] = true;
                    pos = s.indexOf(find, pos + 1);

                }

            }

        }
        return list;

    }

    public String minWindow(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }
        if (t.length() == s.length()) {
            if (t.equals(s)) {
                return t;
            }
        }
        int[] freq = new int[127];
        int[] needs = new int[127];

        String temp = "";

        //t的长度,每次发现一个元素,就将窗口减小一次,代表执行了一次频率变化
        int len = t.length();
        int left;
        int right = 0;
        for (left = 0; left < t.length(); left++) {
            freq[t.charAt(left)]++;
            needs[t.charAt(left)]++;
        }
        left = 0;
        while (right < s.length()) {
            while (len != 0 && right < s.length()) {
                if (needs[s.charAt(right)] >= 1) {
                    //入窗口
                    len--;
                    needs[s.charAt(right)]--;
                } else if (freq[s.charAt(right)] >= 1) {
                    //对于多余数字插入
                    needs[s.charAt(right)]--;
                }
                right++;
            }
            while (len == 0) {
                if (freq[s.charAt(left)] > 0) {

                    //需求过多
                    if (needs[s.charAt(left)] < 0) {
                        needs[s.charAt(left)]++;
                    }
                    //需求刚好用光
                    else if ((needs[s.charAt(left)] == 0)) {
                        len++;
                        needs[s.charAt(left)]++;
                    }
                }
                if (right - left < temp.length() || "".equals(temp)) {
                    temp = s.substring(left, right);
                }
                left++;
            }
        }
        return temp;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    private void swap(char[] nums, int i, int j) {
        if (i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int x = -1, y = -1;
        int[] response = new int[matrix.length * matrix[0].length];
        int pos = 0;

        int vCount = 0;
        int hCount = 0;
        while (pos < response.length) {
            //消顶,x++,y++代表进入新矩形中
            x++;
            y++;

            for (int count = 0; count < matrix[0].length - hCount; count++) {
                response[pos++] = matrix[x][y++];
            }
            if (pos >= response.length) {
                break;
            }
            y--;
            x++;
            //顶消完,纵向长度减少一个
            vCount++;
            //开始消右侧
            for (int count = 0; count < matrix.length - vCount; count++) {
                response[pos++] = matrix[x++][y];
            }
            if (pos >= response.length) {
                break;
            }

            //右侧消完,横向长度减少一个
            hCount++;
            //消底
            x--;
            y--;
            for (int count = 0; count < matrix[0].length - hCount; count++) {
                response[pos++] = matrix[x][y--];

            }
            if (pos >= response.length) {
                break;
            }
//            1,2,3,4
//            5,6,7,8
//            9,10,11,12
//            13,14,15,16

            //消左侧
            vCount++;
            y++;
            x--;
            for (int count = 0; count < matrix.length - vCount; count++) {
                response[pos++] = matrix[x--][y];
            }

            hCount++;

        }


        return response;


    }

    public int search(int[] nums, int target) {
        int pos = Arrays.binarySearch(nums, target);
        if (pos < 0) {
            return 0;
        }
        int response = 1;
        int count = 1;
        while (pos + count < nums.length && nums[pos + count] == target) {
            response++;
            count++;
        }
        count = 1;
        while (pos - count >= 0 && nums[pos - count] == target) {
            response++;
            count++;
        }
        return response;

    }

    public int missingNumber(int[] nums) {

        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] == middle) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return right;


    }

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int k = i; k < matrix.length - i - 1; k++) {
                int x1 = i, y1 = k;
                int x2 = k, y2 = matrix.length - i - 1;
                int x3 = matrix.length - i - 1, y3 = matrix.length - k - 1;
                int x4 = matrix.length - k - 1, y4 = i;

                swap(matrix, x1, y1, x2, y2, x3, y3, x4, y4);
            }


        }

    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x4][y4];
        matrix[x4][y4] = matrix[x3][y3];
        matrix[x3][y3] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count =(nums1.length+nums2.length) >>> 1, mid= (nums1.length+nums2.length) >>> 1;
        int pos1 = 0;
        int pos2 = 0;
        int[] res = new int[nums1.length + nums2.length];
        int i = 0;
        while (count>=0){
            while (pos1<nums1.length && pos2<nums2.length && count>=0){
                if ( nums1[pos1]<=nums2[pos2] ){
                    res[i++] = nums1[pos1];
                    pos1++;
                }else {
                    res[i++] = nums2[pos2];
                    pos2++;
                }
                count--;
            }
            if (pos1>=nums1.length){
                res[i++] = nums2[pos2];
                pos2++;
            }else {
                res[i++] = nums1[pos1];
                pos1++;
            }

            count--;

        }
        if ((nums1.length+nums2.length) % 2!=0){
            return res[mid];
        }else {
            return (res[res.length/2]+res[res.length/2-1])/2.0;
        }




    }

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int i=0,j = 0; i<g.length && j < s.length; ) {
            if (g[i] <= s[j]) {
                count++;
                i++;
            }
            j++;
        }
        return count;

    }

    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length<=2){
            return true;
        }
        double k = Long.MAX_VALUE;
        //y2-y1/x2-x1
        k = getSlope(coordinates[0],coordinates[1]);
        for (int i = 2; i < coordinates.length; i++) {
            if (k!=getSlope(coordinates[i],coordinates[0])){
                return false;
            }
        }
        return true;


    }

    private double getSlope(int[] m, int[] n) {
        int[][] ints = new int[3][];
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            ints[0][i] = list.get(i);
        }

        if (m[0]==n[0]){
            return Long.MAX_VALUE;
        }
        else {
            return 1.0*(m[1]-n[1])/(m[0]-n[0]);
        }

    }
    public String trans(String s, int n) {
        String[] arr= s.split(" ");
        StringBuilder sb =  new StringBuilder("");
        for(int i=arr.length-1;i>=0;i--){
            for(int j=0; j<arr[i].length();j++){
                if(Character.isLowerCase(arr[i].charAt(j))){
                    sb.append(Character.toUpperCase(arr[i].charAt(j)));
                }
                else{
                    sb.append(Character.toLowerCase((arr[i].charAt(j))));
                }
            }
            sb.append(' ');
        }
        return sb.deleteCharAt(sb.length()-1).toString();


    }
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }


}



