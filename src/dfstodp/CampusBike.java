package dfstodp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

 */
public class CampusBike {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        List<Set<Integer>> prevPath = new ArrayList<>();
        Set<Integer> remain = new HashSet<>();
        for(int i = 0; i < m; i++) {
            remain.add(i);
        }
        for(int i = 0; i < m; i++) {
            prevPath.add(new HashSet<>(remain));
        }
        int[][] dp = new int[m][n];
        for(int j = 0; j < n; j++) {
            List<Set<Integer>> curPath = new ArrayList<>();
            for(int i = 0; i < m; i++) {
                int minid = -1;
                int dist = dist(workers[j], bikes[i]);
                if(j == 0) {
                    dp[i][j] = dist;
                    minid = i;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = 0; k < m; k++) {
                        if(i != k && prevPath.get(k) != null && prevPath.get(k).contains(i)) {
                            if(dist + dp[k][j - 1] < dp[i][j]) {
                                minid = k;
                                dp[i][j] = dist + dp[k][j - 1];
                            }
                        }
                    }
                }
                if(minid == - 1) {
                    curPath.add(null);
                } else {
                    Set<Integer> last = prevPath.get(minid);
                    last.remove(i);
                    curPath.add(new HashSet<>(last));
                }
            }
            prevPath = curPath;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            min = Math.min(min, dp[i][n - 1]);
        }
        return min;
    }

    private int dist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
