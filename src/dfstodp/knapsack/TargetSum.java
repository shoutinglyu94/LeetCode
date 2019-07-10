package dfstodp.knapsack;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum < S) return 0;// edge case
        int offset = sum;
        int[][] dp = new int[nums.length + 1][2 * offset + 1];
        dp[0][offset] = 1;
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 0; j <= 2 * offset; j++) {
                dp[i][j] = (j >= nums[i - 1] ? dp[i - 1][j - nums[i - 1]] : 0) +
                        + (j + nums[i - 1] <= 2 * offset ? dp[i - 1][j + nums[i - 1]] : 0);
                //System.out.print(dfstodp[i][j] + " ");
            }
            //System.out.println("\n");
        }
        return dp[nums.length][S + offset];
    }
}
