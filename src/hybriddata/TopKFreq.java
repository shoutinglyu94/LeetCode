package hybrid;

import java.util.*;

public class TopKFreq {

    class Node {
        int freq;
        Set<Character> chars;
        Node prev, next;
        Node (int freq) {
            this.freq = freq;
            chars = new HashSet<>();
        }
    }
    private Node head, tail;

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void addNode(Node node, Node prev) {
        Node next = prev.next;
        prev.next = node;
        node.prev = prev;
        node.next = next;
        next.prev = node;
    }

    public String frequencySort(String s) {
        Map<Character, Node> map = new HashMap<>();
        Map<Integer, Node> freqMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(map.containsKey(cur)) {
                Node from = map.get(cur);
                from.chars.remove(cur);
                Node to = freqMap.get(from.freq + 1);
                if(to == null) {
                    to = new Node(from.freq + 1);
                    addNode(to, from);
                }
                to.chars.add(cur);
                map.put(cur, to);
                freqMap.put(to.freq, to);

                if(from.chars.isEmpty()) {
                    freqMap.remove(from.freq);
                    removeNode(from);
                }

            } else {
                Node node = freqMap.get(1);
                if(node == null) {
                    node = new Node(1);
                    addNode(node, head);
                }
                node.chars.add(cur);
                map.put(cur, node);
                freqMap.put(1, node);
            }
        }
        StringBuilder sb  = new StringBuilder();
        Node curTail = tail.prev;
        while (curTail != head) {
            int f = curTail.freq;
            Set<Character> set = curTail.chars;
            Iterator it = set.iterator();
            while(it.hasNext()) {
                Character cur = (Character)it.next();
                int i = 0;
                while (i < f) {
                    sb.append(cur);
                    i++;
                }
            }
            curTail = curTail.prev;
        }
        return sb.toString();
    }
}
