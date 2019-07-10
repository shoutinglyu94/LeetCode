package hybrid;

import java.util.*;

public class GetRandomO1 {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random random;
    /** Initialize your data structure here. */
    public GetRandomO1() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int idx = map.get(val);
        swap(list, idx, list.size() - 1);
        map.put(list.get(idx), idx);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    private void swap(List<Integer> list, int i, int j) {
        list.set(i, list.get(j));
        list.set(j, null);
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if(list.isEmpty()) return 0;
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */