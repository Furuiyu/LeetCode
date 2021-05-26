package binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author : Fr
 * @date : 2021/1/1 15:02
 */
public class Solution {
    static boolean flag = false;
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth((root.right))) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
        return 1;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            root.left = invertTree(root.left);
            root.right = invertTree(root.right);
            return root;

        }

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return equal(root.left, root.right);
        }

    }

    private boolean equal(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left == right) {
            return true;
        }
        if (left.right.val == right.left.val && left.left.val == right.right.val) {
            return equal(left.left, right.right) && equal(right.left, left.right);
        }
        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left), rh = height(root.right);
        if (lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) {
            return Math.max(lh, rh) + 1;
        } else {
            return -1;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        if (hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        return hasPathSum(root.right, sum - root.val);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        //判断节点是否是左叶子节点，如果是则将它的和累计起来
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res += root.left.val;
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> response = new ArrayList<>();
        if (root == null) {
            return response;
        }
        if (root.left == null && root.right == null) {
            response.add("" + root.val);
            return response;
        }
        List<String> left = binaryTreePaths(root.left);
        for (String s : left) {
            response.add(root.val + "->" + s);
        }

        List<String> right = binaryTreePaths(root.right);
        for (String s : right) {
            response.add(root.val + "->" + s);
        }
        return response;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<List<Integer>> response = new ArrayList<>();
        if (root == null) {
            return response;
        }

        ArrayList<Integer> list = new ArrayList<>();

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                response.add(list);
                list.add(root.val);
            }
            return response;
        }
        if (root.left != null) {
            List<List<Integer>> left = pathSum(root.left, sum - root.val);
            for (List<Integer> integers : left) {
                if (integers.size() > 0) {
                    integers.add(0, root.val);
                    response.add(integers);
                }
            }
        }
        if (root.right != null) {
            List<List<Integer>> right = pathSum(root.right, sum - root.val);
            for (List<Integer> integers : right) {
                if (integers.size() > 0) {
                    integers.add(0, root.val);
                    response.add(integers);
                }
            }
        }
        return response;
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        pre = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            return pre;
        }
        return (dfs(root.left, pre) + dfs(root.right, pre));
    }

    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = findPath(root, sum);
        res += pathSum2(root.left, sum);
        res += pathSum2(root.right, sum);
        return res;


    }

    private int findPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val == sum) {
            res += 1;
        }

        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);

        return res;


    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }
        if (p.val < q.val) {
            return lowestCommonAncestor2(root, p, q);
        } else {
            return lowestCommonAncestor2(root, q, p);
        }


    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }
        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }
        return root.val > q.val ? lowestCommonAncestor2(root.left, p, q) : lowestCommonAncestor2(root.right, p, q);

    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return  validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode current = null;
        if (root.val==key){

             current =  findMidNode(root);
            if (current==null){
                return null;
            }else {
                current.left=root.left;
                current.right = root.right;
            }
            return current;
        }
        TreeNode pre = null;
        pre = findNode(null,root,key);

        if (pre==null){
            return root;
        }
        TreeNode target;
        if (key>pre.val){
            target = pre.right;
            current= findMidNode(target);
            if (current==null){
                pre.right=null;
                return root;
            }
            current.left = target.left;
            current.right = target.right;
            pre.right = current;
        }else {
            target = pre.left;
            current= findMidNode(target);
            if (current==null){
                pre.left=null;
                return root;
            }
            current.left = target.left;
            current.right = target.right;
            pre.left = current;
        }
        return root;


    }



    private TreeNode findMidNode(TreeNode root) {
        TreeNode response = null;
        TreeNode pre = root;
        boolean flag = true;
        if (root.left!=null){
            response = root.left;
            while (response.right!=null){
                pre = response;
                flag = false;
                response = response.right;

            }
            if (flag){
                pre.left = response.left;
            }else {
                pre.right= response.left;
            }

        }else if (root.right!=null){
            response =root.right;
            while (response.left!=null){
                pre = response;
                flag = false;
                response = response.left;
            }
            if (flag){
                pre.right=response.right;
            }else {
                pre.left = response.right;
            }
        }
        return response;

    }

    private TreeNode findNode(TreeNode pre,TreeNode root, int key) {

        if(root==null){
            return null;
        }
        if (key==root.val){
            return pre;
        }
        pre = root;
        return key>root.val?findNode(pre,root.right,key): findNode(pre,root.left,key);

    }
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = new ArrayList<>();

        TreeNode minNode = root;
        while (minNode.left!=null){
            minNode = minNode.left;
        }
//        nums.add(minNode.val);
        postOrder(root,minNode,k,nums);
        return nums.get(k-1);


    }

    private void postOrder(TreeNode root, TreeNode minNode, int k, ArrayList<Integer> nums) {
        if (flag||root==minNode){
            flag = true;
        }
        if (nums.size()<k){
            if (root.left!=null){
                postOrder(root.left,minNode,k,nums);
            }

            if (flag){
                nums.add(root.val);
            }
            if (root.right!=null){
                postOrder(root.right,minNode,k,nums);
            }
        }



    }
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        if(root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p,q);
        TreeNode right = lowestCommonAncestor( root.right,p,q);
        if(left!= null && right != null) {
            return root;
        }

        if(left == null) {
            return right;
        }
        return left;

    }
}
class BSTIterator {
    TreeNode root;
    int index = 0;
    int[] values = new int[10001];

    public BSTIterator(TreeNode root) {
        this.root = root;
        Arrays.fill(values,Integer.MIN_VALUE);
        mid(root);
        index = 0;



    }
    public void mid(TreeNode root){
        if(root.left!=null){
            mid(root.left);
        }
        values[index++] = root.val;
        if(root.right!=null){
            mid(root.right);
        }
    }

    public int next() {
        return values[index++];


    }

    public boolean hasNext() {
        return values[index] != Integer.MIN_VALUE;

    }
}
