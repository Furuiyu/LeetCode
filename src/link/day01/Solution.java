package link.day01;

import java.util.*;

/**
 * @author : Fr
 * @date : 2020/12/9 22:35
 */


class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode[] reverseList(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode temp = head;
        int count = 0;
        while (temp != null && count != k) {
            temp = temp.next;
            count++;
        }
        if (count == k) {
            while (cur != null && count > 0) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                count--;
            }
            head.next = next;
            return new ListNode[]{pre, head};
        } else {
            return new ListNode[]{head, head};
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (n == m) {
            return head;
        }

        boolean flag = true;
        ListNode h;
        if (m == 1) {
            h = new ListNode(-1000);
            h.next = head;
            flag = false;
        } else {
            h = head;
            while (m > 2) {
                h = h.next;
                m--;
                n--;
            }
        }
        ListNode next;
        ListNode cur = h.next;
        ListNode temp = h.next;
        ListNode pre = null;
        while (m <= n && cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            m++;
        }
        h.next = pre;
        assert temp != null;
        temp.next = cur;
        if (flag) {
            return head;
        } else {
            return h.next;
        }


    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        int tempValue = head.val;
        ListNode tempNode = head.next;
        ListNode tempHead = head;
        while (tempNode != null) {
            while (tempNode != null && tempNode.val == tempValue) {
                tempHead.next = tempNode.next;
                tempNode = tempNode.next;
            }
            if (tempNode != null) {
                tempHead = tempNode;
                tempValue = tempNode.val;
                tempNode = tempNode.next;
            }


        }
        return head;

    }

    public ListNode partition(ListNode head, int x) {

        ListNode lessThan = new ListNode(-100);
        ListNode lessTemp = lessThan;

        ListNode moreThan = new ListNode(100);

        ListNode moreTemp = moreThan;

        ListNode temp = head;
        ListNode next;
        while (temp != null) {
            next = temp.next;
            if (temp.val < x) {
                lessThan.next = temp;
                lessThan = temp;
                lessThan.next = null;
            } else {
                moreThan.next = temp;
                moreThan = temp;
                moreThan.next = null;
            }
            temp = next;
        }
        lessThan.next = moreTemp.next;

        return lessTemp.next;

    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        int pos = 1;
        ListNode temp = head;
        ListNode odd = null;
        ListNode oddTemp = null;

        ListNode even = null;
        ListNode evenTemp = null;

        ListNode next;
        while (temp != null) {
            next = temp.next;
            if (pos % 2 != 0) {
                if (odd == null) {
                    odd = temp;
                    oddTemp = odd;
                } else {
                    odd.next = temp;
                    odd = temp;
                }
            } else {
                if (even == null) {
                    even = temp;
                    evenTemp = even;
                } else {
                    even.next = temp;
                    even = temp;
                }

            }
            temp.next = null;
            pos++;
            temp = next;
        }
        odd.next = evenTemp;
        return oddTemp;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode temp = null;
        ListNode response = null;
        int carry = 0;
        while (l1 != null && l2 != null) {

            int val = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            if (temp == null) {
                temp = new ListNode(val);
                response = temp;
            } else {
                temp.next = new ListNode(val);
                temp = temp.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 != null) {
            while (l1 != null) {
                int valTemp = l1.val;
                temp.next = l1;
                temp.next.val = (valTemp + carry) % 10;
                carry = (valTemp + carry) / 10;
                temp = temp.next;
                l1 = l1.next;
            }
        } else {
            while (l2 != null) {
                int valTemp = l2.val;
                temp.next = l2;
                temp.next.val = (valTemp + carry) % 10;
                carry = (valTemp + carry) / 10;
                temp = temp.next;
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            temp.next = new ListNode(carry);
        }
        return response;


    }

    public ListNode addTwoNumbersBetter(ListNode l1, ListNode l2) {

        ListNode temp1 = l1, temp2 = l2;
        while (temp1 != null || temp2 != null) {
            while (temp1 != null && temp2 != null) {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            if (temp1 != null || temp2 != null) {
                ListNode insert = new ListNode(0);
                if (temp1 == null) {
                    insert.next = l1;
                    l1 = insert;
                    temp2 = temp2.next;
                } else {
                    insert.next = l2;
                    l2 = insert;
                    temp1 = temp1.next;
                }
            }

        }
        ListNode response = doAdd(l1, l2);
        if (response.val / 10 > 0) {
            ListNode node = new ListNode(response.val / 10);
            response.val = response.val % 10;
            node.next = response;
            response = node;
        }
        return response;
    }

    private ListNode doAdd(ListNode l1, ListNode l2) {
        ListNode curNode;
        ListNode nextNode;
        if (l1.next == null && l2.next == null) {
            curNode = new ListNode(l1.val + l2.val);
        } else {
            nextNode = doAdd(l1.next, l2.next);
            int carry = nextNode.val / 10;
            nextNode.val = nextNode.val % 10;
            curNode = new ListNode(carry + l1.val + l2.val);
            curNode.next = nextNode;
        }

        return curNode;

    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode response = head;

        while (head != null && head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }

        }
        return response;

    }

    public ListNode deleteDuplicatesAll(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        ListNode pre = new ListNode(-1000);
        ListNode response = pre;
        ListNode cur = head;
        pre.next = cur;


        while ((next != null)) {
            if (next.val == cur.val) {
                while (next != null && next.val == cur.val) {
                    next = next.next;
                }
                cur = next;
                pre.next = next;
                if (next != null) {
                    next = next.next;
                }
            } else {
                cur = next;
                next = next.next;
                pre = pre.next;
            }
        }
        return response.next;

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == l2) {
            return null;
        }
        ListNode temp = null;
        ListNode response = null;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (temp == null) {
                    temp = l1;
                    response = temp;
                } else {
                    temp.next = l1;
                    temp = l1;
                }
                l1 = l1.next;
            } else {
                if (temp == null) {
                    temp = l2;
                    response = temp;

                } else {
                    temp.next = l2;
                    temp = l2;
                }
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            if (temp != null) {
                temp.next = l2;
            } else {
                response = l2;
            }

        } else {
            if (temp != null) {
                temp.next = l1;
            } else {
                response = l1;
            }
        }
        return response;

    }

    public int[] reversePrint(ListNode head) {

        return getNums(head, 1);

    }

    private int[] getNums(ListNode head, int i) {
        int[] response;
        if (head.next == null) {
            response = new int[i];
        } else {
            response = getNums(head.next, i + 1);

        }
        response[response.length - i] = head.val;
        return response;

    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode frontNode = head, behindNode = head;
        while (frontNode != null && k > 0) {

            frontNode = frontNode.next;
            k--;
        }
        while (frontNode != null) {
            frontNode = frontNode.next;
            behindNode = behindNode.next;
        }

        return behindNode;

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }


        ListNode help = new ListNode(-1);
        ListNode response = help;


        ListNode node1 = head;
        ListNode node2 = node1.next;
        ListNode next = node2.next;
        while (true) {
            help.next = node2;
            node2.next = node1;
            node1.next = next;
            help = node1;

            if (next != null && next.next != null) {
                node1 = next;
                node2 = node1.next;
                next = next.next.next;
            } else {
                break;
            }

        }
        return response.next;


    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        ListNode help = new ListNode(-1);

        ListNode response = help;
        help.next = head;
        while (help.next != null) {
            ListNode[] nodes = reverseList(help.next, k);
            help.next = nodes[0];
            help = nodes[1];
        }
        return response.next;

    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head.next;
        ListNode next;
        ListNode response = new ListNode(-1);
        response.next = head;
        ListNode last = head;
        ListNode iteration;
        ListNode pre;
        while (temp != null) {
            pre = response;
            iteration = response.next;
            next = temp.next;
            while (temp.val > iteration.val) {
                iteration = iteration.next;
                pre = pre.next;
            }
            if (iteration.val > temp.val || iteration != temp) {
                temp.next = iteration;
                pre.next = temp;
                last.next = next;
            } else {
                last = temp;
            }

            temp = next;
        }
        return response.next;

    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            //prev:指向链表待裁剪节点的上一个节点,curr:遍历链表的节点
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                //第一个链表
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                //第二个链表从第一个末尾这里开始
                ListNode head2 = curr.next;
                //将第一个链表的末尾砍掉
                curr.next = null;

                curr = head2;
                for (int i = 1; i < subLength && curr != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                //截断链表二同时赋值next
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                prev.next = mergeTwoLists(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public void deleteNode(ListNode node) {
//        ListNode pre = null;
//        while (node.next!=null){
//            node.val = node.next.val;
//            pre = node;
//            node = node.next;
//
//        }
//        pre.next = null;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public ListNode rotateRight(ListNode head, int k) {

        ListNode fast = head;
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
        }
        if (len == 0) {
            return null;
        }
        k %= len;
        //待裁剪点前一个位置
        ListNode pre = new ListNode(0);
        ListNode response;
        pre.next = head;
        fast = head;
        if (k == 0) {
            return head;
        }
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        //这里是为了少遍历一次直接获取尾部节点,所以应该对pre再多遍历一次,才是目标节点的的前一个节点
        while (fast.next != null) {
            fast = fast.next;
            pre = pre.next;
        }
        pre = pre.next;
        //这里的response就是目标节点
        response = pre.next;
        fast.next = head;
        pre.next = null;
        return response;


    }
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverseList(slow);
        fast = head;

        boolean choice = true;
        ListNode response = new ListNode(-1);
        while ( slow!=null){
            if (choice){
                response.next = fast;
                fast = fast.next;
            }else {
                response.next = slow;
                slow = slow.next;
            }
            choice = !choice;
            response = response.next;
            response.next = null;
        }
//        if(head==null) {
//            return;
//        }
//        ListNode quick = head;
//        ListNode slow = head;
//        //获得中间的指针
//        while(quick!=null&&quick.next!=null){
//            quick = quick.next.next;
//            slow = slow.next;
//        }
//        //获得需要反转的链表的头节点
//        ListNode reverHead = slow.next;
//        slow.next = null;
//        //反转链表 ---> 使用哨兵，然后利用头插法
//        ListNode pp = new ListNode(-1);
//        pp.next = null;
//        while(reverHead!=null){
//            ListNode temp = reverHead;
//            reverHead = reverHead.next;
//            temp.next = pp.next;
//            pp.next = temp;
//        }
//        //反转链表后，开始依次插入前半段的，遍历后半段的即可,画图模拟一下
//        reverHead = pp.next;
//
//        ListNode p = head;
//        while(reverHead!=null){
//            ListNode temp = reverHead;
//            reverHead = reverHead.next;
//            temp.next = p.next;
//            p.next = temp;
//            p = temp.next;
//        }

    }



    public boolean isPalindrome(ListNode head) {
        if (head==null ){
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if (slow.next==null){
            return slow.val == head.val;
        }
        ListNode node2 = reverseList(slow);
        while (node2!=null){
            if (node2.val==head.val){
                node2 = node2.next;
                head = head.next;
            }else {
                return false;
            }
        }
        return true;

    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = new ListNode(-1);
        ListNode response = pre;
        pre.next = head;

        while (head.val!=val){
            pre = pre.next;
            head = head.next;
        }
        pre.next = head.next;

        return response;

    }

    public ListNode copyRandomList(ListNode head) {
        ListNode help = new ListNode(-1);
        ListNode response = help;
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode iterator = head;
        ListNode node;
        while (iterator != null) {
            if (map.get(iterator) == null) {
                node = new ListNode(iterator.val);
                map.put(iterator,node);
            } else {
                node = map.get(iterator);
            }
            getRandom(map, iterator, node);
            map.put(iterator,node);
            help.next = node;
            help = node;
            iterator = iterator.next;

        }
        return response.next;




    }

    private void getRandom(HashMap<ListNode, ListNode> map, ListNode iterator, ListNode node) {
        if (iterator.random!=null){
            if (map.get(iterator.random)==null){
                ListNode random = new ListNode(iterator.random.val);
                node.random = random;
                map.put(iterator.random,random);
            }else {
                node.random = map.get(iterator.random);
            }
        }else {
            node.random = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, ListNode> mapA = new HashMap<>();
        HashMap<ListNode, ListNode> mapB = new HashMap<>();

        while (headA!=null && headB!=null){
            if (mapA.get(headB)!=null){
                return mapA.get(headB);
            }
            if (mapB.get(headA)!=null){
                return mapB.get(headA);
            }
            mapA.put(headA, headA);
            mapB.put(headA, headB);
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    public char findTheDifference(String s, String t) {

        char[] chars = new char[26];
        for(int i = 0;i<s.length();i++){
            chars[s.charAt(i)-'a']++;
        }
        for(int i = 0;i<t.length();i++){
            char c = t.charAt(i);
            if(chars[c-'a']>0){
                chars[c-'a']--;
            }else{
                return c;
            }
        }
        return 'A';

    }


}
