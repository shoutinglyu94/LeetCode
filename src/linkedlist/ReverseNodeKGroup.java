public class ReverseNodeKGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode tail = head;
        for(int i = 2; i <= 5; i++) {
            tail = append(tail, i);
        }
        ListNode newHead = reverseKGroup(head, 2);
        while(newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    private static ListNode append(ListNode tail, int val) {
        tail.next = new ListNode(val);
        return tail.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0 || k == 1) return head;
        return helper(head, k);
    }

    private static ListNode helper(ListNode head, int k) {
        if (head == null) return head;
        ListNode fast = head;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (fast == null) return head;
        ListNode remain = helper(fast.next, k);
        fast.next = null;
        reverseAll(head);
        head.next = remain;
        return fast;
    }

    private static ListNode reverseAll(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseAll(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
