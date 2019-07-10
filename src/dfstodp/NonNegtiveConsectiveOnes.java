package dfstodp;

public class NonNegtiveConsectiveOnes {
    // Sol 1 Brute Force
    public int findIntegersBF(int num) {
        int res = 0;
        for(int i = 0; i <= num; i++) {
            if(check(i)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(int i) {
        int k = 1, j = 0;
        boolean pre = false;
        while((k << j) < i) {
            if(pre && ((i >> j) & k) == 1) return false;
            pre = ((i >> j) & k) == 1;
            j++;
        }
        return true;
    }

    //Sol 2 DFS
    public int findIntegersDFS(int num) {
        return findIntegersDFS(0, 0, num, false);
    }

    private int findIntegersDFS(int i, int sum, int num, boolean pre) {
        // Base case. sum > num
        if(sum > num) return 0;
        // Leaf node. sum <= num
        if((1 << i) > num) return 1;

        if(pre) {
            return findIntegersDFS(i + 1, sum, num, false);
        }

        return findIntegersDFS(i + 1, sum, num, false)
                + findIntegersDFS(i + 1, sum + (1 << i), num, true);
    }


}
