package hybriddata;

public class HybridBenchmark {
    public static void main(String[] args) {
        AllOnes allOnes = new AllOnes();
        allOnes.inc("hello");
        allOnes.inc("hello");
        allOnes.inc("nihao");
        allOnes.inc("nihao");
        allOnes.inc("nihao");

        System.out.println(allOnes.getMaxKey());
        System.out.println(allOnes.getMinKey());
    }
}
