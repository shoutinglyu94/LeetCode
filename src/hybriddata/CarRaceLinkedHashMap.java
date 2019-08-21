package hybriddata;

import java.util.*;

public class CarRaceLinkedHashMap {
    class Node {
        Node prev, next;
        Set<Integer> set;
        int freq;
        Node(int freq) {
            this.set = new HashSet<>();
            this.freq = freq;
        }
    }
    Map<Integer, Node> map;
    Map<Integer, Integer> freqMap;
    Node head;
    Node tail;

    public CarRaceLinkedHashMap(int n) {
        map = new HashMap<>();
        freqMap = new HashMap<>();
        head = new Node(-1);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev =head;
        Node start = new Node(0);
        for(int i = 0; i < n; i++) {
            start.set.add(i);
            freqMap.put(i, 0);
        }
        map.put(0, start);
        append(head, start);
    }

    public void check(int id) {
        int f = freqMap.get(id);
        freqMap.put(id, f + 1);
        // Remove from last freq
        Set<Integer> set = map.get(f).set;
        set.remove(id);
        Node next = map.get(f).next;
        if(set.isEmpty()) {
            deleteNode(map.get(f));
            map.remove(f);
        }
        // Insert into new freq
        if(!map.containsKey(f + 1)) {
            map.put(f + 1, new Node(f + 1));
            append(next.prev, map.get(f + 1));
        }

        map.get(f + 1).set.add(id);
    }

    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
    }

    private void append(Node prev, Node cur) {
        Node next = prev.next;
        prev.next = cur;
        cur.prev = prev;
        cur.next = next;
        next.prev = cur;
    }

    public List<Integer> leading(int k) {
        List<Integer> res = new ArrayList<>();
        Node cur = tail.prev;
        while(cur != head) {
            Set<Integer> set = cur.set;
            for(int a : set) {
                res.add(a);
                if(res.size() == k) break;
            }
            cur = cur.prev;
        }
        return res;
    }
}
