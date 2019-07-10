package math;

public class MathBenchmark {
    public static void main(String[] args) {
        MinimumAreaRectangle2 test = new MinimumAreaRectangle2();
        System.out.println(test.minAreaFreeRect(new int[][] {{1, 2}, {2, 1}, {1, 0}, {0, 1}}));
    }
}
