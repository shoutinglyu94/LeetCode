package dfstodp.knapsack;

public class KnapsackBenchmark {
    public static void main(String[] args) {
        //CoinChange1 coinChange1 = new CoinChange1();
        //System.out.println(coinChange1.coinChangeDP(new int[] {1, 2, 5}, 11));

        //SubsetSum tester = new SubsetSum();

        //System.out.println(tester.subsetSum(new int[] {1}, 4));

        TargetSum targetSum = new TargetSum();
        targetSum.findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3);
    }
}
