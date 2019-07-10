package twopointers;

import java.util.*;

public class TwoSum {

    public static void main(String[] args) {
        int[] array1 = new int[]{2, 1, 3, 3, 5};
        int[] array2 = new int[]{4, 4, 9, 4, 9};
        List<Pair> res = findPairsSmallerOrEquals2(array1, array2, 8);
        for (Pair p : res) {
            System.out.println("Pair:" + p.i1 + " " + p.i2);
        }
    }

    static class Pair {
        int i1;
        int i2;

        public Pair(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }
    }

    /*Solution 1 Sort first
    A → 1 2 3 3 5
    B → 4 4 4 9 9
    for each j in B,
	    find largest A[i] <= target - B[j]
    Time O(mlogm + nlogn + m + n) Space O(1)
    */
    public static List<Pair> findPairsSmallerOrEquals(int[] array1, int[] array2, int target) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        int i = 0, j = array2.length - 1;
        List<Pair> res = new ArrayList<>();
        while (i < array1.length && j >= 0) {
            int sum = array1[i] + array2[j];
            if (sum <= target) {
                int lastVal = res.isEmpty() ? Integer.MIN_VALUE :
                        array1[res.get(res.size() - 1).i1] + array2[res.get(res.size() - 1).i2];
                if (sum < lastVal) {
                    i++;
                } else {
                    if (sum > lastVal) {
                        res.clear();
                    }
                    int val = array2[j];
                    int index = j;
                    while (index >= 0 && array2[index] == val) {
                        res.add(new Pair(i, index));
                        index--;
                    }
                    i++;
                }

            } else {
                j--;
            }
        }
        return res;
    }


    /*
    * Solution 2 Brute Force + Optimization with BBST
    * for each j in B
	    check how many index in A, A[i] <= target - B[j]
	  use TreeMap<Integer, List<Integer>> to store A, → floor() operation
      key - value, value - list of index
      Time O(mlogm + nlogm)  → O((m+n)logm)
      Space O(m)
    * */
    public static List<Pair> findPairsSmallerOrEquals2(int[] array1, int[] array2, int target) {
        int[] small = array1, large = array2;
        if(array1.length > array2.length) {
            small = array2;
            large = array1;
        }
        List<Pair> res = new ArrayList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for(int i = 0; i < large.length; i++) {
            List<Integer>list = map.getOrDefault(large[i], new ArrayList<>());
            list.add(i);
            map.put(large[i], list);
        }

        int minDiff = Integer.MAX_VALUE;
        for(int i = 0; i < small.length; i++) {
            if(small[i] >= target) continue;
            int sum = target - small[i];
            Integer floorKey = map.floorKey(sum);
            if(floorKey != null) {
                int diff = sum - floorKey;
                if(diff <= minDiff) {
                    if(diff < minDiff) {
                        res.clear();
                        minDiff = diff;
                    }
                    for(int index : map.get(floorKey)) {
                        res.add(new Pair(i, index));
                    }
                }
            }
        }
        return res;
    }


}
