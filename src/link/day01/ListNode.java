package link.day01;

/**
 * @author : Fr
 * @date : 2020/12/9 22:37
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode random;

    public ListNode(int val) {
        this.val = val;
        this.random = null;
        this.next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", random=" + random +
                '}';
    }
}
