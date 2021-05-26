package link.day01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : Fr
 * @date : 2020/12/9 22:48
 */
public class SolutionTest {
    static Solution st;
    static ListNode point;

    @Before
    public void setUp() throws Exception {
        st = new Solution();
    }

    @After
    public void tearDown() throws Exception {
        while (point!=null){
            System.out.printf("%s\t",point);
            point = point.next;
        }
    }

    @Test
    public void reverseList() {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 0; i < 10; i++) {
            cur.next = new ListNode(i+2);
            cur = cur.next;
        }
        cur = st.reverseList(head);
        while (cur!=null){
            System.out.println(cur);
            cur = cur.next;
        }

    }

    @Test
    public void reverseBetween() {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i = 0; i < 10; i++) {
            cur.next = new ListNode(i+2);
            cur = cur.next;
        }
        cur = st.reverseBetween(head,1,5);
        while (cur!=null){
            System.out.printf("%s\t",cur);
            cur = cur.next;
        }
    }

    @Test
    public void deleteDuplicates() {
        ListNode head = new ListNode(1);
//        ListNode head1 = new ListNode(1);
//        ListNode head2 = new ListNode(2);
//        head.next = head1;
//        head1.next = head2;
        ListNode cur = head;
        for (int i = 0; i < 43; i++) {
            for (int j = 0; j < 12; j++) {
                cur.next = new ListNode(i+2);
                cur = cur.next;
            }

        }
        cur = head;
        while (cur!=null){
            System.out.printf("%s\t",cur);
            cur = cur.next;
        }
        System.out.println("\n×ª»»ºó");
        cur = st.deleteDuplicates(head);
        while (cur!=null){
            System.out.printf("%s\t",cur);
            cur = cur.next;
        }
    }

    @Test
    public void partition() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);

        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;


        point = st.partition(node0, 3);



    }

    @Test
    public void oddEvenList() {
        ListNode node0 = new ListNode(2);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(7);

        node5.next = node6;

        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;

        point = st.oddEvenList(node0);

    }

    @Test
    public void addTwoNumbers() {
        ListNode node0 = new ListNode(9);
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(9);

        ListNode node4_2 = new ListNode(9);

        node1.next = node3;
        node3.next = node2;

        node4.next = node5;
//        node5.next = node4_2;

        point = st.addTwoNumbers(node1, node4);


    }

    @Test
    public void addTwoNumbersBetter() {
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(4);

        node5.next = node6;

        node6.next = node7;

        node3.next = node4;
        node2.next = node3;
        node1.next = node2;

        point = st.addTwoNumbersBetter(node1, node5);




    }

    @Test
    public void removeElements() {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
//        node6.next = node7;
//        node5.next = node6;
//
//        node4.next = node5;
//        node3.next = node4;
//        node2.next = node3;
//        node1.next = node2;
        point = st.removeElements(node1,1);

    }

    @Test
    public void deleteDuplicatesAll() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.deleteDuplicatesAll(node0);
    }

    @Test
    public void mergeTwoLists() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);


        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
//        ListNode node6 = new ListNode(5);

//        node5.next = node6;
        node4.next = node5;
        node3.next = node4;

        node1.next = node2;
        node0.next = node1;
        point = st.mergeTwoLists(node0,node3);

    }

    @Test
    public void reversePrint() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);


        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
//        ListNode node6 = new ListNode(5);

//        node5.next = node6;
        node4.next = node5;
        node3.next = node4;

        node1.next = node2;
        node0.next = node1;
        int[] print = st.reversePrint(node0);
        for (int value : print) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void swapPairs() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
//        ListNode node4 = new ListNode(5);
//        ListNode node5 = new ListNode(4);
//        ListNode node6 = new ListNode(5);

//        node5.next = node6;
//        node4.next = node5;
//        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.swapPairs(node0);
    }

    @Test
    public void reverseKGroup() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.reverseKGroup(node0,4);
    }

    @Test
    public void insertionSortList() {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(8);
        ListNode node6 = new ListNode(1);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.insertionSortList(node0);
    }

    @Test
    public void sortList() {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(8);
        ListNode node6 = new ListNode(1);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.sortList(node0);
    }

    @Test
    public void deleteNode() {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);

        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        st.deleteNode(node1);
        point = node0;
    }

    @Test
    public void removeNthFromEnd() {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(8);
        ListNode node6 = new ListNode(1);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;
        point = st.removeNthFromEnd(node0,1);
    }

    @Test
    public void rotateRight() {
        ListNode node0 = new ListNode(4);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(8);
        ListNode node6 = new ListNode(1);

        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;

        point = st.rotateRight(node0,7);
    }

    @Test
    public void reorderList() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(3);
//        ListNode node3 = new ListNode(4);
//        ListNode node4 = new ListNode(5);
//        ListNode node5 = new ListNode(6);
//        ListNode node6 = new ListNode(7);

//        node5.next = node6;
//        node4.next = node5;
//        node3.next = node4;
//        node2.next = node3;
//        node1.next = node2;
        node0.next = node1;

         st.reorderList(node0);
         point = node0;
    }

    @Test
    public void isPalindrome() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(5);
//        ListNode node5 = new ListNode(6);
//        ListNode node6 = new ListNode(7);

//        node5.next = node6;
//        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        node0.next = node1;

        System.out.println(st.isPalindrome(node0));
    }

    @Test
    public void copyRandomList() {
        ListNode node0 = new ListNode(7);
//        ListNode node1 = new ListNode(13);
//        ListNode node2 = new ListNode(11);
//        ListNode node3 = new ListNode(10);
//        ListNode node4 = new ListNode(1);

//        node3.next = node4;
//        node2.next = node3;
//        node1.next = node2;
//        node0.next = node1;


        node0.random = node0;
//        node1.random = node0;
//        node2.random = node4;
//        node3.random = node2;
//        node4.random = node0;

        point = st.copyRandomList(node0);


    }

    @Test
    public void findTheDifference() {
        String s = "abcd";
        String t = "abcde";
        st.findTheDifference(s,t);
    }
}