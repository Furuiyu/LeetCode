package queueAndStack;




import java.util.*;

/**
 * @author : Fr
 * @date : 2020/12/18 22:01
 */
public class Solution {


    public boolean isValid(String s) {

        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if ((stack.size() <= 0) || map.get(stack.pop()) != s.charAt(i)) {
                    return false;
                }
            }
        }
        return stack.size() == 0;

    }

    public boolean isValid2(String s) {

        while (s.contains("{}") || s.contains("()") || s.contains("[]")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        return s.length() == 0;

    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();
        Integer num1;
        Integer num2;
        for (String s : tokens) {
            if ('0' <= s.charAt(s.length() - 1) && s.charAt(s.length() - 1) <= '9') {
                nums.push(Integer.parseInt(s));
            } else {
                switch (s) {
                    case "+":
                        nums.push(nums.pop() + nums.pop());
                        break;
                    case "-":
                        num1 = nums.pop();
                        num2 = nums.pop();
                        nums.push(num2 - num1);
                        break;
                    case "*":
                        nums.push(nums.pop() * nums.pop());
                        break;
                    case "/":
                        num1 = nums.pop();
                        num2 = nums.pop();
                        nums.push(num2 / num1);
                        break;
                    default:
                        break;
                }

            }

        }
        return nums.pop();

    }

    public String simplifyPath(String path) {
        path = path.replace("//", "/");

        String[] exp = path.split("/");
        Stack<String> directory = new Stack<>();
        directory.push("/");
        for (int i = 1; i < exp.length; i++) {
            if (!"".equals(exp[i])) {
                if (".".equals(exp[i])) {
                    continue;
                }
                if ("..".equals((exp[i]))) {
                    if (directory.size() != 1) {
                        directory.pop();
                    }
                } else {
                    directory.push(exp[i]);
                }
            }


        }
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        directory.remove(0);
        while (directory.size() > 1) {
            sb.append(directory.get(0));
            sb.append("/");
            directory.remove(0);
        }
        if (directory.size() == 1) {
            sb.append(directory.pop());
        }

        return sb.toString();

    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        ArrayList<Integer> nums = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();
        while (root != null || !nodes.isEmpty()) {
            while (root != null) {
                nums.add(root.val);
                nodes.add(root);
                root = root.left;
            }
            root = nodes.pop().right;
        }
        return nums;

    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        ArrayList<Integer> nums = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();

        while (root != null || nodes.size() != 0) {
            while (root != null) {
                nodes.add(root);
                root = root.left;
            }
            root = nodes.pop();
            nums.add(root.val);
            root = root.right;

        }
        return nums;

    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        ArrayList<Integer> nums = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode p = null;
        while (root != null || nodes.size() != 0) {
            while (root != null) {
                nodes.add(root);
                root = root.left;
            }
            root = nodes.peek();
//            后序遍历的过程中在遍历完左子树跟右子树cur都会回到根结点。所以当前不管是从左子树还是右子树回到根结点都不应该再操作了，应该退回上层。
//            如果是从右边再返回根结点，应该回到上层。
//            主要就是判断出来的是不是右子树，是的话就可以把根节点=加入到list了
            if (root.right == null || root.right == p) {
                nums.add(root.val);
                nodes.pop();
                p = root;
                root = null;
            } else {
                root = root.right;
            }


        }
        return nums;
    }

    public List<Integer> postOrderTraversalByCommand(TreeNode root) {
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(root, "go"));
        ArrayList<Integer> res = new ArrayList<>();
        while (stack.size() != 0) {
            Command command = stack.pop();
            if (command.ins.equals("print")) {
                res.add(command.node.val);
            } else {
                stack.push(new Command(command.node, "print"));
                if (command.node.right != null) {
                    stack.push(new Command(command.node.right, "go"));
                }
                if (command.node.left != null) {
                    stack.push(new Command(command.node.left, "go"));
                }

            }
        }
        return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> response = new ArrayList<>();
        queue.offer(root);
        while (queue.size() != 0) {

            ArrayList<Integer> list = new ArrayList<>();

            int count = queue.size();
            while (count > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                count--;
            }
            response.add(list);

        }
        return response;

    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> response = new ArrayList<>();
        queue.offer(root);
        while (queue.size() != 0) {

            ArrayList<Integer> list = new ArrayList<>();

            int count = queue.size();
            while (count > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                count--;
            }
            response.add(0, list);

        }
        return response;

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> queue = new ArrayList<>();
        List<List<Integer>> response = new ArrayList<>();

        queue.add(0, root);
        boolean flag = true;
        while (queue.size() != 0) {
            ArrayList<Integer> list = new ArrayList<>();
            int count = queue.size();

            while (count > 0) {
                TreeNode node = queue.get(0);
                queue.remove(0);
                if (flag) {
                    list.add(node.val);
                } else {
                    list.add(0, node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
            response.add(list);
            flag = !flag;

        }
        return response;


    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> response = new ArrayList<>();
        List<Integer> left = null;
        List<Integer> right = null;

        response.add(root.val);

        if (root.right != null) {
            right = rightSideView(root.right);
        }
        if (root.left != null) {
            left = rightSideView(root.left);
        }
        if (left != null || right != null) {
            if (left != null) {
                if (right == null) {
                    right = new ArrayList<>();
                }
                for (int i = right.size(); i < left.size(); i++) {
                    right.add(left.get(right.size()));
                }
            }

            response.addAll(right);
        }


        return response;


    }

    public int numSquares(int n) {

        Queue<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        queue.offer(new Pair(n));
        while (queue.size() != 0) {
            Pair pair = queue.poll();
            if (pair.num == 0) {
                return pair.step;
            }
            for (int i = 1, count = pair.num - i * i; count >= 0; i++, count = pair.num - i * i) {
                if (!visited[count]) {
                    Pair item = new Pair(count, pair.step + 1);
                    queue.add(item);
                    visited[count] = true;
                }

            }
        }
        return 0;

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)){
            return new ArrayList<>();
        }


        wordList.add(0, beginWord);
        LinkedHashSet<String> set = new LinkedHashSet<>(wordList);
        HashMap<String, List<String>> map = new HashMap<>();
        wordList.clear();
        wordList.addAll(set);
        wordList.remove(endWord);
        wordList.add(endWord);

        createMap(map,wordList);
        int length = bfs2(beginWord, endWord, map);
        if (length==0){
            return new ArrayList<>();
        }
        List<List<String>> response = new ArrayList<>();

        return start_dfs(response,length,beginWord,endWord,map);


    }


    private List<List<String>> start_dfs(List<List<String>> response ,int length,String beginWord, String endWord, HashMap<String, List<String>> map) {


        List<String> nodes = map.get(beginWord);


        for (String node : nodes) {
            List<String> item = new ArrayList<>();
            item.add(beginWord);
            dfs(response,item,length, node, endWord, map);
        }

        return response;
    }

    private void dfs(List<List<String>> response ,List<String> current, int length, String beginWord, String endWord, HashMap<String, List<String>> map) {
        if (current.size()==length-1 ){
            if (endWord.equals(beginWord)){
                current.add(beginWord);
                response.add(current);
            }
        }else {
            current.add(beginWord);
            List<String> nodes = map.get(beginWord);
            for (String node : nodes) {
                ArrayList<String> list = new ArrayList<>(current);
                dfs(response,list,length, node, endWord, map);
            }

        }

    }

    private int bfs2(String beginWord, String endWord, HashMap<String, List<String>> map) {
        LinkedList<MapNode> queue = new LinkedList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        queue.add(new MapNode(beginWord, 1));
        while (queue.size() != 0 ) {
            MapNode currentNode= queue.poll();
            List<String> nodes = map.get(currentNode.nodeValue);
            if (nodes != null) {
                for (String node : nodes) {
                    if (node.equals(endWord)) {
                        return currentNode.step + 1;
                    } else {
                        if (!visited.getOrDefault(node, false)) {
                            visited.put(node, true);
                            queue.add(new MapNode(node, currentNode.step + 1));
                        }
                    }
                }
            }
        }
        return 0;

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        wordList.add(0, beginWord);
        LinkedHashSet<String> set = new LinkedHashSet<>(wordList);
        HashMap<String, List<String>> map = new HashMap<>();
        wordList.clear();
        wordList.addAll(set);

        createMap(map, wordList);
        wordList.remove(beginWord);

        return bfs(beginWord, endWord, map);


    }

    private int bfs(String beginWord, String endWord, HashMap<String, List<String>> map) {
        LinkedList<MapNode> queue1 = new LinkedList<>();
        LinkedList<MapNode> queue2 = new LinkedList<>();

        HashMap<String, Boolean> visited1 = new HashMap<>();
        HashMap<String, Boolean> visited2 = new HashMap<>();
        queue1.add(new MapNode(beginWord, 1));
        queue2.add(new MapNode(endWord,1));
        while (queue1.size() != 0 && queue2.size()!=0) {
            MapNode currentNode1 = queue1.poll();
            MapNode currentNode2 = queue2.poll();
            List<String> nodes1 = map.get(currentNode1.nodeValue);
            List<String> nodes2 = map.get(currentNode1.nodeValue);
            if (iteration(endWord, queue1, visited1, currentNode1, nodes1)) {
                return currentNode1.step + 1;
            }
            if (iteration(endWord, queue2, visited2, currentNode2, nodes2)) {
                return currentNode2.step + 1;
            }


        }
        return 0;
    }

    private boolean iteration(String endWord, LinkedList<MapNode> queue, HashMap<String, Boolean> visited, MapNode currentNode, List<String> nodes) {
        if (nodes != null) {
            for (String node : nodes) {
                if (node.equals(endWord)) {
                    return true;
                } else {
                    if (!visited.getOrDefault(node, false)) {
                        visited.put(node, true);
                        queue.add(new MapNode(node, currentNode.step + 1));
                    }
                }
            }
        }
        return false;
    }

    private void createMap(HashMap<String, List<String>> map, List<String> wordList) {

        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 1; j < wordList.size(); j++) {
                if (i != j) {
                    String temp = wordList.get(i);
                    String tempNext = wordList.get(j);

                    checkGenerateNode(map, temp, tempNext);


                }
            }
        }
    }


    private void checkGenerateNode(HashMap<String, List<String>> map, String temp, String tempNext) {
        int count = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (count <= 1) {
                if (tempNext.charAt(i) != temp.charAt(i)) {
                    count++;
                }
            } else {
                return;
            }
        }
        if (count == 1) {
            List<String> nextNode = map.getOrDefault(temp, new ArrayList<>());
            nextNode.add(tempNext);
            map.put(temp, nextNode);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] response = new int[k];
        Map<Integer, Integer> freq = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<numAndFreq> queue = new PriorityQueue<>((o1, o2) -> o2.freq-o1.freq);
        for (Integer key : freq.keySet()) {
            queue.offer(new numAndFreq(key,freq.get(key)));
        }


        for (int i = 0; i < k; i++) {
            response[i] = queue.poll().num;

        }
        return response;


    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        for (ListNode list : lists) {
            while (list != null) {
                curr = list.next;
                list.next = null;
                pq.add(list);
                list = curr;
            }
        }
        dummyHead.next = pq.poll();
        curr = dummyHead.next;
        while (pq.size()!=0){
            curr.next = pq.poll();
            curr = curr.next;
        }


        return dummyHead.next;
    }


}
class numAndFreq{
    int num;
    int freq;

    public numAndFreq(int num, int freq) {
        this.num = num;
        this.freq = freq;
    }
}

class Pair {
    int num;
    int step;

    public Pair(int num) {
        this.num = num;
        this.step = 0;
    }

    public Pair(int num, int step) {
        this.num = num;
        this.step = step;
    }
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
class NestedIterator implements Iterator<Integer> {
    private final List<Integer> list = new ArrayList<>();
    private int index;

    private void add(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                add(nestedInteger.getList());
            }
        }
    }


    public NestedIterator(List<NestedInteger> nestedList) {
        add(nestedList);
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }
}


/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
class Command {
    TreeNode node;
    String ins;

    public Command(TreeNode node, String ins) {
        this.node = node;
        this.ins = ins;
    }
}

class MapNode {
    String nodeValue;
    int step;

    public MapNode(String nodeValue, int step) {
        this.nodeValue = nodeValue;
        this.step = step;
    }
}

