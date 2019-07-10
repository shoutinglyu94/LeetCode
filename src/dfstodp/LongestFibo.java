package dfstodp;

import java.util.HashMap;
import java.util.Map;

public class LongestFibo {
    public static void main(String[] args) {
        lenLongestFibSubseq(new int[] {1,2,3,4,5,6,7,8});
    }
    public static int lenLongestFibSubseq(int[] A) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for(int i = 0; i < A.length; i++) {
            idxMap.put(A[i], i);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int n = A.length;
        for(int k = 2; k < A.length; k++) {
            int left = 0, right = k - 1;
            while(left < right) {
                int sum = A[left] + A[right];
                if(sum == A[k]) {
                    Integer prevIdx = idxMap.get(A[right] - A[left]);
                    int len = 3;
                    if(prevIdx != null && prevIdx < left) {
                        len = map.get(prevIdx * n + left) + 1;
                    }
                    max = Math.max(max, len);
                    map.put(left * n + right, len);
                    right--;
                } else if (sum < A[k]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return max;
    }
}
