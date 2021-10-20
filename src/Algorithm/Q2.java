
class Q2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode tail = null;
        int carry = 0;

        // 当 两个链表中 任意一个不为 null
        while (l1 != null || l2 != null) {
            // 取值
            int num_1 = l1 != null ? l1.val : 0;
            int num_2 = l2 != null ? l2.val : 0;
            // 加和
            int number = num_1 + num_2 + carry;
            // 结点赋值
            if (head == null) {
                head = tail = new ListNode(number % 10);
            } else {
                tail.next = new ListNode(number % 10);
                tail = tail.next;
            }
            // 计算进位
            carry = number / 10;
            // 继续遍历
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

        }
        // 最后 结点 进位
        if (carry > 0) {
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] s1 = new int[]{2, 4, 3};
        int[] s2 = new int[]{5, 6, 4};

        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        l1.val = s1[0];
        l1.next = null;

        l2.val = s2[0];
        l2.next = null;


        ListNode l1_c = l1;
        ListNode l2_c = l2;


        for (int i = 1; i < s1.length; i++) {
            ListNode temp = new ListNode();
            temp.val = s1[i];
            temp.next = null;
            l1_c.next = temp;
            l1_c = l1_c.next;

        }

        for (int i = 1; i < s2.length; i++) {
            ListNode temp = new ListNode();
            temp.val = s2[i];
            temp.next = null;
            l2_c.next = temp;
            l2_c = l2_c.next;
        }

        Q2 demo = new Q2();
        ListNode result = demo.addTwoNumbers(l1, l2);
        StringBuffer res = new StringBuffer();
        while (result != null) {
            res.append(result.val);
            result = result.next;
        }
        System.out.println(res);
    }

    static class ListNode {
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
