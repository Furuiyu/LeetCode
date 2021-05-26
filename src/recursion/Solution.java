package recursion;

import java.util.*;

/**
 * @author : Fr
 * @date : 2021/1/17 20:24
 */
public class Solution {
    static HashMap<String, Boolean> mapPalindrome = new HashMap<>();


    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        HashMap<Character, List<Character>> map = new HashMap<>();
        char temp = 'a';
        for (int i = 2; i <= 9; i++) {
            List<Character> list = null;
            list = map.getOrDefault((char) (i + '0'), new ArrayList<>());
            if (i != 7 && i != 9) {
                for (int j = 0; j < 3; j++) {
                    list.add(temp++);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    list.add(temp++);
                }
            }
            map.put((char) (i + '0'), list);

        }
        ArrayList<String> response = new ArrayList<>();
        //循环次数

        List<Character> characters = map.get(digits.charAt(0));
        for (Character character : characters) {
            appendChar(new StringBuilder().append(character), 0, digits, response, map);

        }

        return response;

    }

    private void appendChar(StringBuilder builder, int i, String digits, ArrayList<String> response, HashMap<Character, List<Character>> map) {
        if (i < digits.length() - 1) {
            List<Character> characters = map.get(digits.charAt(++i));
            for (Character character : characters) {
                int start = builder.length();
                builder.append(character);
                appendChar(new StringBuilder().append(character).append(builder), i, digits, response, map);
                builder.delete(start, builder.length());

            }
        } else {
            response.add(builder.toString());
        }

    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder ip = new StringBuilder();

        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        /*
                         * 1、保障下面subString不会越界
                         * 2、保障截取的字符串与输入字符串长度相同
                         * //1、2比较好理解，3比较有意思
                         * 3、不能保障截取的字符串转成int后与输入字符串长度相同
                         * 如：字符串010010，a=1，b=1，c=1，d=3，对应字符串0，1，0，010
                         * 转成int后seg1=0，seg2=1，seg3=0，seg4=10
                         * //所以需要下面这处判断if (ip.length() == s.length() + 3)
                         */
                        if (a + b + c + d == s.length()) {
                            int seg1 = Integer.parseInt(s.substring(0, a));
                            int seg2 = Integer.parseInt(s.substring(a, a + b));
                            int seg3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int seg4 = Integer.parseInt(s.substring(a + b + c, a + b + c + d));
                            // 四个段数值满足0~255
                            if (seg1 <= 255 && seg2 <= 255 && seg3 <= 255 && seg4 <= 255) {
                                ip.append(seg1).append(".").append(seg2).append(".").
                                        append(seg3).append(".").append(seg4);
                                // 保障截取的字符串转成int后与输入字符串长度相同
                                if (ip.length() == s.length() + 3) {
                                    result.add(ip.toString());
                                }
                                ip.delete(0, ip.length());
                            }
                        }
                    }
                }
            }
        }
        return result;


    }

    /**
     * 首先前n个字母是否回文,如果回文,后面回文的情况
     */
    public List<List<String>> partition(String s) {
        if (s.length() == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            ArrayList<List<String>> response = new ArrayList<>();
            response.add(list);
            return response;
        }
        ArrayList<List<String>> curRes = new ArrayList<>();
        StringBuilder pre = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            pre.append(s.charAt(i));
            String key = pre.toString();
            if (mapPalindrome.containsKey(key) || checkPalindrome(pre)) {
                mapPalindrome.put(key, true);
                list.add(key);
                if (s.length() - 1 - i > 0) {
                    List<List<String>> next = partition(s.substring(i + 1));
                    for (List<String> strings : next) {
                        strings.add(0, key);
                    }
                    curRes.addAll(next);
                    list.clear();
                } else {
                    curRes.add(list);
                }

            }
        }
        return curRes;

    }

    private boolean checkPalindrome(StringBuilder pre) {
        for (int i = 0; i < pre.length() / 2; i++) {
            if (pre.charAt(i) != pre.charAt(pre.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> response = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            visited[i] = true;
            generatingPermutation(response, nums, visited, list);
            visited[i] = false;
        }
        return response;

    }

    private void generatingPermutation(List<List<Integer>> response, int[] nums, boolean[] visited, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            response.add(list);
        } else {
            for (int j = 0; j < nums.length; j++) {
                if (!visited[j]) {
                    ArrayList<Integer> toNext = new ArrayList<>(list);
                    toNext.add(nums[j]);
                    visited[j] = true;
                    generatingPermutation(response, nums, visited, toNext);
                    visited[j] = false;
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> response = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];
//        HashSet<Integer> set = new HashSet<>();
        int last = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != last) {
                last = nums[i];
                ArrayList<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                visited[i] = true;
                generatingPermutationUnique(response, nums, visited, list);
                visited[i] = false;
            }

        }
        return response;

    }

    private void generatingPermutationUnique(List<List<Integer>> response, int[] nums, boolean[] visited, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            response.add(list);
        } else {
            int last = Integer.MAX_VALUE;
//            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < nums.length; j++) {
                if (!visited[j] && last != nums[j]) {
                    last = nums[j];
                    ArrayList<Integer> toNext = new ArrayList<>(list);
                    toNext.add(nums[j]);
                    visited[j] = true;
                    generatingPermutationUnique(response, nums, visited, toNext);
                    visited[j] = false;
                }
            }
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> response = new ArrayList<>();
        if (k <= 0 || n <= 0 || k > n) {
            return response;

        }
        List<Integer> c = new ArrayList<>();
        generationCombination(n, k, 1, c, response);
        return response;


    }

    private void generationCombination(int n, int k, int start, List<Integer> c, List<List<Integer>> response) {
        if (c.size() == k) {
            response.add(c);
            return;
        }
        for (int i = start; i <= n - (k - c.size()) + 1; i++) {
            ArrayList<Integer> list = new ArrayList<>(c);
            list.add(i);
            generationCombination(n, k, i + 1, list, response);
        }


    }

    static List<List<Integer>> response;
    static HashSet<String> set;
    static int tar;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        response = new ArrayList<>();
        set = new HashSet<>();
        tar = target;
        Arrays.sort(candidates);
        for (int i = 1; i <= target / candidates[0]; i++) {
            List<Integer> c = new ArrayList<>();
            generationCombination(i, target, candidates, c, 0);
        }
        return response;

    }

    private void generationCombination(int len, int target, int[] candidates, List<Integer> c, int curSum) {

        if (c.size() == len) {
            if (curSum == tar) {
                Collections.sort(c);
                if (!set.contains(c.toString())) {
                    set.add(c.toString());
                    response.add(c);
                }
            }

            return;
        }
        for (int candidate : candidates) {

            List<Integer> list = new ArrayList<>(c);
            list.add(candidate);

            curSum += candidate;
            if (curSum > tar && c.size() <= len) {
                return;
            }
            if (target > candidate) {
                generationCombination(len, target - candidate, candidates, list, curSum);
                curSum -= candidate;
                target += candidate;
            } else {
                return;
            }

        }

    }

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        } else {
            response = new ArrayList<>();

            for (int i = 0; i <= nums.length; i++) {
                List<Integer> list = new ArrayList<Integer>();
                generateList(i, -1, list, nums);
            }
            return response;


        }
    }

    private void generateList(int len, int index, List<Integer> list, int[] nums) {
        if (len == list.size()) {
            response.add(list);
            return;
        }
        for (int i = index + 1; i < nums.length; i++) {
            ArrayList<Integer> c = new ArrayList<>(list);
            c.add(nums[i]);
            generateList(len, i, c, nums);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        } else {
            Arrays.sort(nums);
            response = new ArrayList<>();
            set = new HashSet<String>();

            for (int i = 0; i <= nums.length; i++) {
                List<Integer> list = new ArrayList<Integer>();
                generateListTwo(i, -1, list, nums);
            }
            return response;
        }

    }

    private void generateListTwo(int len, int index, List<Integer> list, int[] nums) {
        if (len == list.size() && !set.contains(list.toString())) {
            set.add(list.toString());
            response.add(list);
            return;
        }
        for (int i = index + 1; i < nums.length; i++) {


            ArrayList<Integer> c = new ArrayList<>(list);
            c.add(nums[i]);
            generateListTwo(len, i, c, nums);
        }
    }

    static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visited;
    static boolean[][] accessAble;

    public boolean exist(char[][] board, String word) {
        int[] freq1 = new int[255];
        int[] freq2 = new int[255];
        for (int i = 0; i < word.length(); i++) {
            freq1[word.charAt(i)]++;
        }
        for (char[] chars : board) {
            for (char aChar : chars) {
                freq2[aChar]++;
            }
        }
        for (int i = 0; i < freq1.length; i++) {
            if (freq1[i] > freq2[i]) {
                return false;
            }
        }
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (searchWord(board, word, 1, j, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, int y, int x) {
        if (index == word.length() - 1) {
            return board[y][x] == word.charAt(index);
        }
        if (word.charAt(index) == board[y][x]) {
            visited[y][x] = true;
            for (int i = 0; i < 4; i++) {
                y = y + direction[i][0];
                x = x + direction[i][1];
                if (inArea(y, x, board.length, board[y].length) && !visited[y][x] && searchWord(board, word, index + 1, y, x)) {
                    return true;
                }
                y = y - direction[i][0];
                x = x - direction[i][1];
            }
        }
        visited[y][x] = false;
        return false;

    }

    private boolean inArea(int y, int x, int yLen, int xLen) {
        return x >= 0 && y >= 0 && y < yLen && x < xLen;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    sign(grid, i, j);
                }
            }
        }
        return res;

    }

    /**
     * 这里可以直接将当前点转化为'0',如果下一个点是'1',就直接进入下一点
     *
     * @param grid char
     * @param y    纵坐标
     * @param x    横坐标
     */
    private void sign(char[][] grid, int y, int x) {
        if (grid[y][x] == '0') {
            return;
        }
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int newY = y + direction[i][0];
            int newX = x + direction[i][1];

            if (inArea(newY, newX, grid.length, grid[0].length) && !visited[newY][newX]) {
                sign(grid, newY, newX);
            }
        }

    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        visited = new boolean[board.length][board[0].length];
        accessAble = new boolean[board.length][board[0].length];

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    ArrayList<Node> path = new ArrayList<>();
                    path.add(new Node(i, j));
                    if (!accessAble[i][j] && beSurrounded(board, i, j, path)) {
                        for (Node node : path) {
                            board[node.y][node.x] = 'X';
                        }
                    } else {
                        for (Node node : path) {
                            accessAble[node.y][node.x] = true;
                        }
                    }
                }

            }
        }
    }

    private boolean beSurrounded(char[][] board, int y, int x, ArrayList<Node> path) {

        if ((x == 0 || y == 0 || x == board[0].length - 1 || y == board.length - 1) && board[y][x] == 'O') {
            return false;
        }
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int newY = y + direction[i][0];
            int newX = x + direction[i][1];
            if (inArea(newY, newX, board.length, board[0].length) ){
                if (accessAble[newY][newX]){
                    return false;
                }
                if (board[newY][newX]=='O' ){
                    path.add(new Node(y, x));
                    if ((!visited[newY][newX]&&!beSurrounded(board, newY, newX, path))){
                        return false;
                    }
                }
            }
        }

        return true;
    }
    static boolean[] diagonal1;
    static boolean[] diagonal2;
    static boolean[] col;

    List<List<String>> responseString;

    public List<List<String>> solveNQueens(int n) {
        if(n==0){
            return new ArrayList<>();
        }
        responseString= new ArrayList<>();
        diagonal1 = new boolean[2*n-1];
        diagonal2 = new boolean[2*n-1];
        col = new boolean[n];

        place(n,0,new ArrayList<>());
        return responseString;


    }

    private void place(int n, int index,List<Integer> row) {

        if (index==n){
            responseString.add(generateBoard(row));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i] && !diagonal1[i+index] && !diagonal2[index-i+n-1]){
                row.add(i);
                assignment(index,i,n,row);
                place(n,index+1,row);
                assignment(index,i,n,row);
                row.remove(index);
            }
        }

    }
    private void assignment(int index,int i,int n,List<Integer> row){

        col[i] = !col[i];
        diagonal1[i+index] =!diagonal1[i+index];
        diagonal2[index-i+n-1] = !diagonal2[index-i+n-1];
    }

    private List<String> generateBoard(List<Integer> row) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder builder;
        for (int i = 0; i < row.size(); i++) {
            builder = new StringBuilder();
            for (int j = 0; j < row.size(); j++) {
                if (row.get(i)==j){
                    builder.append("Q");
                }else {
                    builder.append(",");
                }
            }
            list.add(builder.toString());
        }


        return list;
    }


}

class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

