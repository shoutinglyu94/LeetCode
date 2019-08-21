package hybriddata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class AllOne {
    class Node {
        Node prev, next;
        Set<String> set;
        int freq;
        Node(int freq) {
            this.freq = freq;
            this.set = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }
    Node head, tail;
    Map<Integer, Node> freqMap;
    Map<String, Integer> stringMap;
    /** Initialize your data structure here. */
    public AllOne() {
        freqMap = new HashMap<>();
        stringMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(stringMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            stringMap.put(key, 1);
            if(!freqMap.containsKey(1)) {
                Node newNode = new Node(1);
                addNode(newNode, head);
                freqMap.put(1, newNode);
            }
            head.next.set.add(key);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(stringMap.containsKey(key)) {
            int curCount = stringMap.get(key);
            if(curCount == 1) {
                stringMap.remove(key);
                removeKeyFromNode(key, head.next);
            } else {
                changeKey(key, -1);
            }
        } else {
            return;
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail.prev == head)  {
            return "";
        }
        return tail.prev.set.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head.next == tail) {
            return "";
        }
        return head.next.set.iterator().next();
    }

    // curNode always exists.
    private void changeKey(String target, int offset) {
        int curCount = stringMap.get(target);
        // Update freq.
        stringMap.put(target, curCount + offset);
        // Deal with target Node.
        Node curNode = freqMap.get(curCount);
        Node targetNode;
        if(freqMap.containsKey(curCount + offset)) {
            targetNode = freqMap.get(curCount + offset);
        } else {
            targetNode = new Node(curCount + offset);
            if(offset == 1) {
                addNode(targetNode, curNode);
            } else {
                addNode(targetNode, curNode.prev);
            }
            freqMap.put(curCount + offset, targetNode);
        }
        targetNode.set.add(target);

        // Remove from curNode
        removeKeyFromNode(target, curNode);
    }

    private void addNode(Node curNode, Node prevNode) {
        Node next = prevNode.next;
        curNode.next = next;
        next.prev = curNode;
        prevNode.next = curNode;
        curNode.prev = prevNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void removeKeyFromNode(String key, Node node) {
        node.set.remove(key);
        if(node.set.isEmpty()) {
            removeNode(node);
            freqMap.remove(node.freq);
        }
    }
}
