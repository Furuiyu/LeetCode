package find.day01;



import java.util.*;

/**
 * @author : Fr
 * @date : 2020/12/3 19:37
 */
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> set2 = new TreeSet<>();
        for (int item : nums1) {
            set1.add(item);
        }
        for (int value : nums2) {
            if (set1.contains(value)) {
                set2.add(value);
            }
        }
        int[] response = new int[set2.size()];
        int index = 0;
        for (int it : set2) {
            response[index++] = it;
        }
        return response;

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] s1 = new int[26];
        int[] t1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            s1[s.charAt(i) - 'a'] += 1;
            t1[t.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (s1[i] != t1[i]) {
                return false;
            }
        }
        return true;

    }

    private int theSquareOfEachDigit(int num) {
        int sum = 0;
        while (num > 0) {
            sum += Math.pow(num % 10, 2);
            num /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            n = theSquareOfEachDigit(n);
            if (!set.add(n)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 未完成
     *
     * @param nums 传入数组
     * @return 返回结果
     */
    public boolean isPossible(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int len = 1;
        int temp = nums[0];

        int count = 0;
//        int[] input = {1,2,3,3,4,4,5,5};


        for (int i = 1; i < nums.length; ) {
            while (nums[i] == temp + 1 && i + 1 < nums.length) {
                len++;
                temp = nums[i++];
            }
            while (nums[i + count] - temp != 1 && nums[i + count] - temp <= 2) {
                count++;
            }
            if (nums[i + count] - temp == 1) {
                i += count;
                count = 0;
                continue;
            }
            if (len >= 3) {
                temp = nums[i];
                len = 0;
            } else {
                return false;
            }
        }
        return true;


    }

    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        if (strings.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map1.get(pattern.charAt(i)) == null && map2.get(strings[i]) == null) {
                map2.put(strings[i], pattern.charAt(i));
                map1.put(pattern.charAt(i), strings[i]);
            } else {
                if ((map1.get(pattern.charAt(i)) == null || map2.get(strings[i]) == null) || (!map1.get(pattern.charAt(i)).equals(strings[i]) || !map2.get(strings[i]).equals(pattern.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * 未解决
     *
     * @param s x
     * @param t y
     * @return 结果
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if ((map1.get(s.charAt(i)) == null && map2.get(s.charAt(i)) == null) && (map1.get(t.charAt(i)) == null && map2.get(t.charAt(i)) == null)) {
                map1.put(s.charAt(i), t.charAt(i));
                map2.put(t.charAt(i), s.charAt(i));
            } else {
                if (map1.containsKey(s.charAt(i))) {
                    if (map1.get(s.charAt(i)) != t.charAt(i)) {
                        return false;
                    }

                }
                if (map2.containsKey(s.charAt(i))) {
                    if (map2.get(s.charAt(i)) != t.charAt(i)) {
                        return false;
                    }
                }
                if (map1.containsKey(t.charAt(i))) {
                    if (map1.get(t.charAt(i)) != s.charAt(i)) {
                        return false;
                    }
                }
                if (map2.containsKey(t.charAt(i))) {
                    if (map2.get(t.charAt(i)) != s.charAt(i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String frequencySort(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        StringBuilder builder = new StringBuilder("");


        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((o1, o2) -> o2.getValue() - (o1.getValue()));

        for (Map.Entry<Character, Integer> e : list) {
            for (int i = 0; i < e.getValue(); i++) {
                builder.append(e.getKey());
            }

        }
        return builder.toString();


    }


    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);

        }

        return null;
    }

    /**
     * 已经完成,时空复杂度太高
     *
     * @param nums 入参
     * @return 回执
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int left;
        int right = nums.length - 1;
        int mid;
        ArrayList<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> temp;
        HashSet<String> set = new HashSet<>();

        while (right > 0 && nums[right] > 0) {
            mid = right - 1;
            left = 0;

            while (mid > left && nums[left] <= 0) {
                if (nums[mid] + nums[left] == -nums[right]) {
                    if (!set.contains(nums[left] + "" + nums[mid] + "" + nums[right])) {
                        set.add(nums[left] + "" + nums[mid] + "" + nums[right]);
                        temp = new ArrayList<>(3);
                        temp.add(nums[left]);
                        temp.add(nums[mid]);
                        temp.add(nums[right]);
                        list.add(temp);
                    }
                    mid--;
                } else if (nums[mid] + nums[left] < -nums[right]) {
                    left++;
                } else {
                    mid--;
                }
            }

            right--;
        }

        return list;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        ArrayList<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> temp;
        int one=0, two, three, four = nums.length - 1;

        int tempTwo;
        int tempOne;
        int tempThree;
        int tempFour = nums[nums.length - 1] + 1;
        while (four > 2) {
            while (nums[four] == tempFour && four>2) {
                four--;
            }
            tempThree = nums[nums.length - 1] + 1;
            for (three = four - 1; three > 1; three--) {

                while (nums[three] == tempThree && three>1) {
                    three--;
                }
                tempTwo = nums[nums.length - 1] + 1;
                for (two = three - 1; two>0 && two > one;) {

                    while (nums[two] == tempTwo && two>one) {
                        two--;
                    }
                    one = 0;
                    tempOne = nums[one];
                    while (one < two) {
                        while (nums[two] == tempTwo && two>one) {
                            two--;
                        }
                        if (two==one){
                            break;
                        }
                        if (nums[four] + nums[three] + nums[two] + nums[one] == target) {
                            temp = new ArrayList<>();
                            temp.add(nums[four]);
                            temp.add(nums[three]);
                            temp.add(nums[two]);
                            temp.add(nums[one]);
                            list.add(temp);
                        }
                        else if (nums[four] + nums[three] + nums[two] + nums[one] < target) {
                            while (nums[one] == tempOne && one<two) {
                                one++;
                            }
                            tempOne = nums[one];
                            continue;
                        }
                        tempTwo = nums[two];

                    }
                    tempTwo = nums[two];
                }
                tempThree = nums[three];
            }
            tempFour = nums[four];
            four--;
        }
        return list;


    }

    public String subsequence(String s, String t) {


        ArrayList<Integer> list = new ArrayList<>();
        int pos = s.indexOf(t.charAt(0));

        while (pos != -1) {
            list.add(pos + 1);
            pos = s.indexOf(t.charAt(0), pos + 1);

        }

        //末尾结果数组
        int[] poise = new int[list.size()];

        //起点数组
        int[] back = new int[list.size()];

        //子序列指针数组
        int[] pointers = new int[t.length()];

        //差距长度数组
        int[] lens = new int[t.length()];
        for (int i = 0; i < list.size(); i++) {
            poise[i] = list.get(i);
            back[i] = list.get(i);

            //指针位置都是从1开始,也就是a的位置
            pointers[i] = 1;
            //每一个都差一个长度
            lens[i] = t.length() - 1;
        }

        //指针数组下标
        int count = 0;

        boolean flag = true;
        while (flag) {
            for (count = 0; count < list.size(); count++) {
                if (poise[count] < s.length()) {
                    if (s.charAt(poise[count]) == t.charAt(pointers[count])) {
                        poise[count]++;
                        pointers[count]++;
                        lens[count]--;
                        if (lens[count] == 0) {
                            flag = false;
                            break;
                        }
                    } else {
                        poise[count]++;
                    }
                }

            }
        }


        return s.substring(back[count] - 1, poise[count]);


    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int left;
        int right = nums.length-1;
        int mid ;
        int close = nums[0]+nums[1]+nums[2];
        while (right>1){
            mid = right-1;
            left = 0;
            while (mid>left){
                if (nums[right]+nums[mid]+nums[left]>target){
                    if (Math.abs(nums[right]+nums[mid]+nums[left]-target)<Math.abs(close-target)){
                        close = (nums[right]+nums[mid]+nums[left]);
                    }
                    mid--;
                }else if(nums[right]+nums[mid]+nums[left]<target){
                    if (Math.abs(nums[right]+nums[mid]+nums[left]-target)<Math.abs(close-target)){
                        close = (nums[right]+nums[mid]+nums[left]);
                    }
                    left++;
                }else if (nums[right]+nums[mid]+nums[left]==target){
                    return target;
                }
            }
            right--;

        }
        return close;



    }
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int item : B) {
            for (int i : A) {
                map1.put(item + i, map1.getOrDefault(item + i, 0) + 1);
            }
        }
        for (int value : C) {
            for (int i : D) {
                count += (map1.getOrDefault(-(value + i), 0));
            }
        }

        return count;

    }

    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length<=0){
            return new ArrayList<>();
        }
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<String> temp;

        int[] chars = new int[26];
        int key;

        for (String str : strs) {
            for (int i = 0; i < str.length(); i++) {
                chars[str.charAt(i)-'a']++;
            }
            key = Arrays.hashCode(chars);
            if (map.get(key) == null) {
                temp = new ArrayList<>();
                temp.add(str);
                map.put(Arrays.hashCode(chars),temp);

            }else {
                map.get(key).add(str);
            }
            Arrays.fill(chars,0);

        }
        return new ArrayList<>(map.values());


    }

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j!=i){
                    map.put(getDistance(points[i],points[j]),map.getOrDefault(getDistance(points[i],points[j]),0)+1);
                }
            }
            for (int k : map.values()) {
                res += k*(k-1);
            }

        }
        return res;
    }

    public int maxPoints(int[][] points) {
        if (points.length<=2){
            return points.length;
        }
        int[][] ints = new int[points.length-2][];
        System.arraycopy(points, 0, ints, 0, points.length-2);

        int max = 0;
        int count ;
        HashMap<String , Integer> map = new HashMap<>();
        for (int[] point1:ints) {
            count = 0;
            for (int[] point2 : points) {
                //point1 x y
                if (point1[0]!=point2[0] || point1[1]!=point2[1]) {

                    String k = getTheSlope(point1, point2);
//                    key value
                    map.put(k, map.getOrDefault(k, 0) + 1);
                } else {
                    count++;
                }
            }

            if (map.values().size()!=0){
                for (int len : map.values()) {
                    max = Math.max(max,count+len);
                }
            }else  {
                max = Math.max(max,count);
            }

            map.clear();

        }

        return max;

    }

    private String getTheSlope(int[] point1, int[] point2) {

        if (point1[1]-point2[1]==0){
            return "0";
        }

        if (point1[0]-point2[0]==0){
            return "NaN";
        }
        return ((point1[1]-point2[1])/gcd(point1[1]-point2[1],point1[0]-point2[0]))+""+((point1[0]-point2[0]))/gcd(point1[1]-point2[1],point1[0]-point2[0]);
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int getDistance(int[] point1, int[] point2) {
        return (point1[0]-point2[0])*(point1[0]-point2[0])+(point1[1]-point2[1])*(point1[1]-point2[1]);

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(k);

        int left=0;
        int right=0;
        while (right<nums.length){
            while (right-left>k){
                map.remove(nums[left]);
                left++;
            }
            if (map.get(nums[right])!=null){

                if (Math.abs(map.get(nums[right])-right)<=k){
                    return true;
                }else {
                    map.put(nums[right],right);
                }
            }else{
                map.put(nums[right],right);
            }
            right++;
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {


        int left = 0;
        int right = 0;
        TreeMap<Long, Integer> treeMap = new TreeMap<>();


        while (right<nums.length){
            while (left+k+1<=right){
                treeMap.remove((long)(nums[left++]));
            }

            if (treeMap.ceilingKey( ((long)nums[right]-(long)t))!=null && treeMap.ceilingKey((long)nums[right]-(long)t)<=((long)nums[right]+(long)t)){
                if ((right-treeMap.get(treeMap.ceilingKey((long)nums[right]-(long)t)))<=k){
                    return true;
                }
            }
            treeMap.put((long)nums[right],(right++));


        }
        return false;

    }
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length-1;
        while (left<right){
            int mid = left+right>>>1;

            if (numbers[mid]<numbers[right]){
                right = mid;
            }else if (numbers[mid]==numbers[right]){
                right--;
            }else {
                left = mid+1;
            }
        }

        return numbers[left];


    }
    static  PriorityQueue<Integer> queue = new PriorityQueue<>();

    public int[] pondSizes(int[][] land) {

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if(land[i][j]==0  ){
                    System.out.println(markAndCount( i, j, land));
                }
            }
        }
        return null;

    }

    private int markAndCount(int row, int column, int[][] land) {
        if (row<0 || column<0|| row >= land.length||column>=land[0].length  || land[row][column]!=0){
            return 0;
        }else {
            int curCount = 1;
            land[row][column] = -1;

            int down = markAndCount( row+1, column, land);
            int right = markAndCount (row, column+1, land);
            int up = markAndCount(row-1, column, land);
            int left =  markAndCount(row, column-1, land);

            int downleft = markAndCount( row+1, column+1, land);
            int rightup = markAndCount (row-1, column+1, land);
            int upleft = markAndCount(row-1, column-1, land);
            int leftdown =  markAndCount(row+1, column-1, land);

            return down+right+up+left+curCount+downleft+rightup+upleft+leftdown;

        }

    }


}

