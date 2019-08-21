package graph;

import graph.dfs.NumberOfDistinctIsland;
import graph.graphrepresentation.CrossRiver;
import graph.graphrepresentation.Get4With2Cups;
import graph.graphrepresentation.ToggleBulb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphBenchMark {

    public static void main(String[] args) {
        //testToggleBulb();
        //testNumberOfDistinctIsland();
        //testGet4();
        testCrossRiver();
    }

    private static void testCrossRiver() {
        CrossRiver crossRiver = new CrossRiver();
        Set<String> all = new HashSet<>(Arrays.asList("W", "G", "S"));
        List<List<String>> moves = crossRiver.crossRiver(all);
        for(List<String> move : moves) {
            System.out.println("-----------");
            for(String m : move) {
                System.out.println(m);
            }
        }
    }

    private static void testGet4() {
        Get4With2Cups cup = new Get4With2Cups();
        List<Integer> order = cup.minimumStepsGet4();

        for(int step : order) {
            int v1 = step / 10;
            int v2 = step % 10;
            System.out.println("cup1:" + v1 + " " + "cup2:" + v2);
        }
    }

    private static void testNumberOfDistinctIsland() {
        NumberOfDistinctIsland numberOfDistinctIsland = new NumberOfDistinctIsland();
        numberOfDistinctIsland.numDistinctIslands2(new int[][] {
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        });
    }

    private static void testToggleBulb() {
        ToggleBulb toggleBulb = new ToggleBulb();
        int min = toggleBulb.shortestToggleBulb2(new int[][] {
                {0, 0},
                {0, 0}
        });
        System.out.println(min);

    }

    private static void testFindPath() {
        FindPathIn2DArray findPathIn2DArray = new FindPathIn2DArray();
        int[][] matrix = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 0, -1, 0, 0},
                {0, -1, -1, 0, -1 },
                {0, -1, 0, 0, 0}
        };
        List<int[]> path = findPathIn2DArray.shortestPath(matrix, new int[] {3, 0}, new int[] {3, 4});
        for(int[] point : path) {
            System.out.println(point[0] + " " + point[1]);
        }
    }
}
