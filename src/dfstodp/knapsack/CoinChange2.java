package dfstodp.knapsack;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */

public class CoinChange2 {

    public static void main(String[] args) {
        System.out.println(coinChange(50,
                new int[]{3,5,7,8,9,10,11}));

    }
    /*
    *  Sol 1: Recursion Backtracking
        f(remain) return how many ways to form target amount, start from now
        f(remain) = sum of f(remain - coins[i]) where remain - coins[i] >= 0
    */
    static Map<Integer, Integer> map;
    public static int coinChange(int target, int[] coins) {
        map = new HashMap<>();
        return dfs(target, 0, coins);
    }

    private static int dfs(int remain, int index, int[] coins) {
        //if(map.containsKey(remain)) return map.get(remain);
        if(remain == 0) {
            return 1;
        }
        int res = 0;
        for(int i = index; i < coins.length; i++) {
            if(remain - coins[i] >= 0) {
                res += dfs(remain - coins[i], i, coins);
            }
        }
        //map.put(remain, res);
        return res;
    }



//    private int dfs(int[] coins, int i, int j) {
//        if(j == 0)
//    }

}
