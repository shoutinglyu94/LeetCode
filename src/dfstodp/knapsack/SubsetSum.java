package dfstodp.knapsack;

public class SubsetSum {

    public int subsetSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        if(nums[0] <= target) dp[nums[0]] = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[j] += (j >= nums[i] ? dp[j - nums[i]] : 0);
            }
        }
        return dp[target];
    }

}
