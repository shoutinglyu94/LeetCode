package dfstodp.knapsack;

public class CoinChange1 {
    // DFS
    public int change(int amount, int[] coins) {
        int res = dfs(amount, 0, 0, coins);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(int amount, int curSum, int index, int[] coins) {
        if(index == coins.length) {
            if(curSum == amount) return 0;
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= (amount - curSum) / coins[index]; i++) {
            int num = dfs(amount, curSum + i * coins[index], index + 1, coins);
            if(num == -1) continue;
            min = Math.min(min, num + i);
        }
        return min < Integer.MAX_VALUE ? min : -1;
    }


    public int coinChangeMemo(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }


    // dfstodp
    public int coinChangeDP(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        dp[0][0] = 0;
        for(int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) continue;
                int min = Integer.MAX_VALUE;
                if (i > 0) {
                    min = Math.min(min, dp[i - 1][j]);
                }
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    min = Math.min(min, dp[i][j - coins[i]] + 1);
                }
                dp[i][j] = min;
            }
        }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? - 1 : dp[coins.length - 1][amount];
    }
}
