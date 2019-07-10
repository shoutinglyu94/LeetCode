package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumAreaRectangle2 {
    private final int BASE = 4001;
    public double minAreaFreeRect(int[][] points) {
        Map<Double, List<int[][]>> midMap = new HashMap<>();
        for(int i = 0; i < points.length; i++) {
            for(int j = i + 1; j < points.length; j++) {
                double[] mid = new double[] {
                        (points[i][0] + points[j][0]) / 2.0,
                        (points[i][1] + points[j][1]) / 2.0
                };
                double code = BASE * mid[0] + mid[1];
                List<int[][]> pairList = midMap.get(code);
                if(pairList == null) pairList = new ArrayList<>();
                pairList.add(new int[][]{points[i], points[j]});
                midMap.put(code, pairList);
            }
        }
        double min = 1.0 * BASE * BASE;
        for(double mid : midMap.keySet()) {
            List<int[][]> pairList = midMap.get(mid);
            if(pairList.size() <= 1) continue;
            for(int i = 0; i < pairList.size(); i++) {
                for(int j = i + 1; j < pairList.size(); j++) {
                    int[][] pairA = pairList.get(i);
                    int[][] pairB = pairList.get(j);
                    if(valid(pairA, pairB)) {
                        min = Math.min(min, area(pairA, pairB));
                    }
                }
            }
        }
        return min;
    }

    private boolean valid(int[][] pairA, int[][] pairB) {
        int[] a1 = pairA[0], a2 = pairA[1];
        int[] b1 = pairB[0], b2 = pairB[1];
        if(a1[0] == a2[0] && a1[0] == b1[0]) return false;
        if(a1[0] != a2[0] && a1[0] != b1[0]) {
            double k1 = 1.0 * (a1[1] - a2[1]) / (a1[0] - a2[0]);
            double k2 = 1.0 * (a1[1] - b1[1]) / (a1[0] - b1[0]);
            if(k1 == k2) return false;
        }

        return (Math.abs(a1[0] - b1[0]) == Math.abs(a2[0] - b2[0])) && (Math.abs(a1[1] - b1[1]) == Math.abs(a2[1] - b2[1]));
    }

    private double area(int[][] pairA, int[][] pairB) {
        int maxWidth = Math.max(Math.abs(pairA[0][0] - pairA[1][0]), Math.abs(pairB[0][0] - pairB[1][0]));
        int maxHeight = Math.max(Math.abs(pairA[0][1] - pairA[1][1]), Math.abs(pairB[0][1] - pairB[1][1]));
        int areaToRemove = Math.abs(pairA[0][0] - pairB[0][0]) * Math.abs(pairA[0][1] - pairB[0][1]) +
                Math.abs(pairA[0][0] - pairB[1][0]) * Math.abs(pairA[0][1] - pairB[1][1]);
        return (double) (maxHeight * maxWidth - areaToRemove);
    }
}
