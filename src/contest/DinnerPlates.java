package contest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class DinnerPlates {

    List<Deque<Integer>> stacks;
    int l;
    int r;
    int cap;
    public DinnerPlates(int capacity) {
        this.stacks = new ArrayList<>();
        this.l = 0;
        this.r = 0;
        this.cap = capacity;
    }

    public void push(int val) {
        if(l == stacks.size()) {
            stacks.add(new ArrayDeque<>());
        }
        stacks.get(l).push(val);
        while(l < stacks.size() && stacks.get(l).size() == cap) {
            l++;
        }
    }

    public int pop() {
        if(r < 0 || stacks.get(r).isEmpty()) return -1;
        int res = stacks.get(r).pop();
        while(r >= 0 && stacks.get(r).isEmpty()) {
            r--;
        }
        return res;
    }

    public int popAtStack(int index) {
        if(index < 0 || index > stacks.size() - 1) return -1;
        Deque<Integer> stack = stacks.get(index);
        if(stack.isEmpty()) return -1;
        int res = stack.pop();
        if(index < l) {
            l = index;
        }
        return res;
    }
}
