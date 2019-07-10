package recursion;

public class MemoBenchMark {
    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        myPow.myPow(1.0, -2147483648);
    }
}
