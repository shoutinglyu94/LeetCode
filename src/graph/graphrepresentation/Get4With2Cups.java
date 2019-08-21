package graph.graphrepresentation;

import java.util.*;

public class Get4With2Cups {
    private final int cap1 = 3;
    private final int cap2 = 5;
    public List<Integer> minimumStepsGet4() {
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> prevMap = new HashMap<>();
        queue.offer(0);
        prevMap.put(0, -1);
        int step = 0;
        int last = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                int v1 = cur / 10;
                int v2 = cur % 10;
                for(int nei : getNeis(v1, v2)) {
                    if(!prevMap.containsKey(nei)) {
                        prevMap.put(nei, cur);
                        if((nei / 10) + (nei % 10) == 4) {
                            last = nei;
                            break;
                        }
                        queue.offer(nei);
                    }
                }
            }
            step++;
        }
        List<Integer> res = new ArrayList<>();
        while(last != -1) {
            res.add(0, last);
            last = prevMap.get(last);
        }
        return res;
    }

    private List<Integer> getNeis(int v1, int v2) {
        List<Integer> neis = new ArrayList<>();
        neis.add(v2);
        neis.add(v1 * 10);
        neis.add(cap1 * 10 + v2);
        neis.add(v1 * 10 + cap2);
        int sum = v1 + v2;

        if(sum <= cap1) {
            neis.add(sum * 10);
        } else {
            neis.add(cap1 * 10 + sum - cap1);
        }

        if(sum <= cap2) {
            neis.add(sum);
        } else {
            neis.add((sum - cap2) *  10 + cap2);
        }
        return neis;
    }

}
