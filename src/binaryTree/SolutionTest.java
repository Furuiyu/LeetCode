package binaryTree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2021/1/17 10:03
 */
public class SolutionTest {
    public static Solution st;


    @Before
    public void setUp() throws Exception {
        st = new Solution();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void deleteNode() {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(7);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(9);
        root.right = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;

        st.deleteNode(root,7);
    }

    @Test
    public void kthSmallest() {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(1);
        root.right = node2;
        root.left = node1;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        int i = st.kthSmallest(root, 3);
        System.out.println(i);
    }
}