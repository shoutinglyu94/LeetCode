package dp;

public class DPBenchmark {

    public static void main(String[] args) {
        LargestRectangleNoLargerK tester = new LargestRectangleNoLargerK();
        tester.maxSumSubmatrix(new int[][] {{5,-4,-3,4},{-3,-4,4,5},{5,1,5,-4}}, 10);
    }
}
