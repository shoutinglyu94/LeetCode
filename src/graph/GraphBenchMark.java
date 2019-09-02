package graph;

import graph.bfs.MinimumHeightTree;
import graph.dfs.NumberOfDistinctIsland;
import graph.dfs.TheMaze;
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
        //testCrossRiver();
        //testTheMaze();
        testMinimumHeightTree();
    }

    private static void testMinimumHeightTree() {
        MinimumHeightTree tree = new MinimumHeightTree();
        tree.findMinHeightTrees(7, new int[][] {{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}});
    }

    private static void testTheMaze() {
        TheMaze maze = new TheMaze();
        // test1
//        System.out.println(maze.findShortestWay(new int[][]{
//                {0, 0, 0, 0, 0},
//                {1, 1, 0, 0, 1},
//                {0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1},
//                {0, 1, 0, 0, 0}
//        }, new int[] {4, 3}, new int[] {0, 1}));

        // test2
        maze.findShortestWay(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
        }, new int[] {0, 4}, new int[] {3, 5});
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
