package recursion;

public class MyPow {
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        if(n == 0) return 1;
        long N = n;
        if(n < 0) {
            N = -N;
            x = 1.0 / x;
        }
        return recursion(x, N);
    }

    private double recursion(double x, long n) {
        if(n == 1) return x;
        System.out.println(n);
        double half = recursion(x, n / 2);
        if(n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
