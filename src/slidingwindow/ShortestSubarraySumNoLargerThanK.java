package slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubarraySumNoLargerThanK {

    public int shortestSubarray(int[] A, int K) {
        int[] prefixSum = new int[A.length + 1];
        for(int i = 1; i < A.length + 1; i++) {
            prefixSum[i] += A[i - 1] + prefixSum[i - 1];
        }
        int min = A.length + 1;
        Deque<Integer> deque = new LinkedList<>();
        for(int j = 0; j < A.length + 1; j++) {
            while(!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[j]) {
                deque.pollLast();
            }
            deque.offerLast(j);
            while(!deque.isEmpty() && prefixSum[j] - prefixSum[deque.peekFirst()] >= K) {
                int i = deque.pollFirst();
                min = Math.min(min, j - i);
            }
        }
        return min > A.length ? -1 : min;
    }
}
