package contest;

public class Contest {
    public static void main(String[] args) {

    }

    private static void testDinner() {
        DinnerPlates D = new DinnerPlates(2);
        D.push(1);
        D.push(2);
        D.push(3);
        D.push(4);
        D.push(5);
        D.popAtStack(0);   // Returns 2.  The stacks are now:     4
        D.push(20);        // The stacks are now: 20  4
        D.push(21);        // The stacks are now: 20  4 21
        D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
        D.popAtStack(2);   // Returns 21.  The stacks are now:     4
        D.pop();         // Returns 5.  The stacks are now:      4
        D.pop();            // Returns 4.  The stacks are now:   1  3
        D.pop();            // Returns 3.  The stacks are now:   1
        D.pop();            // Returns 1.  There are no stacks.
        D.pop();            // Returns -1.  There are still no stacks.
    }
}
