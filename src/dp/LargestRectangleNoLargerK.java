package dp;

public class LargestRectangleNoLargerK {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        // vertical prefixSum
        int[][] v_sum = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                v_sum[i][j] = (i == 0 ? 0 : v_sum[i - 1][j]) + matrix[i][j];
                System.out.print(v_sum[i][j] + " ");
            }
            System.out.println("\n");
        }
        int max = Integer.MIN_VALUE;
        for(int top_r = 0; top_r < matrix.length; top_r++) {
            int[] nums = (top_r == 0 ? new int[matrix[0].length] : v_sum[top_r - 1]);
            for(int bottom_r = top_r; bottom_r < matrix.length; bottom_r++) {
                int[] p_sum = new int[matrix[0].length];
                for(int j = 0; j < matrix[0].length; j++) {
                    p_sum[j] = (j == 0 ? 0 : p_sum[j - 1]) + v_sum[bottom_r][j] - nums[j];
                    System.out.print(p_sum[j] + " ");
                }
                System.out.println("\n");
                for(int j1 = 0; j1 < matrix[0].length; j1++) {
                    for(int j2 = j1; j2 < matrix[0].length; j2++) {
                        int pre = (j1 == 0 ? 0 : p_sum[j1 - 1]);
                        if(p_sum[j2] - pre <= k) {
                            System.out.print(p_sum[j2] - pre + " ");
                            max = Math.max(max, p_sum[j2] - pre);
                        }
                    }
                    System.out.println("\n");
                }
            }
        }
        return max;
    }
}
