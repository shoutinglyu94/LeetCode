package segmenttree;

public class SegmentTreeBenchmark {
    public static void main(String[] args) {
        testRangeQuery();
    }

    private static void testRangeQuery() {
        RangeSum sum = new RangeSum(new int[] {1, 2, 3, 4});
        System.out.println(sum.sumRange(0, 3));
        sum.update(0, 0);
        System.out.println(sum.sumRange(0, 3));
    }

    private static void testQueryMatrix() {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(0, 0, 3, 3));
    }
}
