package hybriddata;

import java.util.*;

public class AllOnes {
    class Node {
        Node prev, next;
        Set<String> set;
        int freq;
        Node(int freq) {
            this.freq = freq;
            set = new HashSet<>();
        }
    }
    Node head, tail;
    Map<Integer, Node> freqMap;
    Map<String, Integer> stringMap;
    /** Initialize your data structure here. */
    public AllOnes() {
        freqMap = new HashMap<>();
        stringMap = new HashMap<>();
        head = null;
        tail = null;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int freq = stringMap.getOrDefault(key, 0);

        if(freq != 0) {
            Node from = freqMap.get(freq);
            from.set.remove(key);
        }

        Node to = freqMap.get(freq + 1);
        if(to == null) {
            to = new Node(freq + 1);
            appendTail(to);
        }
        to.set.add(key);
        freqMap.put(freq + 1, to);
        stringMap.put(key, freq + 1);
    }

    private void appendTail(Node newNode) {
        if(head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        }
    }

    private void removeTail() {
        if(head != null && tail != null && head == tail) {
            head = null;
            tail = null;
        } else {
            Node newTail = tail.prev;
            newTail.next = null;
            tail.prev = null;
            tail = newTail;
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!stringMap.containsKey(key)) return;
        int freq = stringMap.get(key);
        Node from = freqMap.get(freq);
        from.set.remove(key);
        if(freq > 1) {
            Node to = freqMap.get(freq - 1);
            to.set.add(key);
            freqMap.put(freq - 1, to);
            stringMap.put(key, freq - 1);
        }

        if(from.set.isEmpty() && from == tail) {
            removeTail();
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail == null) return "";
        List<String> list = new ArrayList<>(tail.set);
        return list.get(0);
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head == null) return "";
        List<String> list = new ArrayList<>(head.set);
        return list.get(0);
    }
}
