/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/12 10:55 下午
 */

/**
 * 合并两个有序的 升序 链表 单链表
 */
public class Q21 {

    ListNode l1 = null;
    ListNode l2 = null;

    public Q21() {

        l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = null;

        l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next = null;


    }

    public static void main(String[] args) {
        Q21 demo = new Q21();
        ListNode result = demo.mergeTwoLists(demo.l1, demo.l2);

        while (result != null) {
            System.out.println(result.val + "\t");
            result = result.next;
        }

    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 保证 可以从l1 开始
        if (l1.val > l2.val) {
            return mergeTwoLists(l2, l1);
        }
        ListNode head = new ListNode(0);
        ListNode start = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                start.next = l1;
                l1 = l1.next;
            } else {
                start.next = l2;
                l2 = l2.next;
            }
            start = start.next;
        }
        start.next = l1 == null ? l2 : l1;
        return head.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
