package hybriddata;

import java.util.List;

public class HybridBenchmark {
    public static void main(String[] args) {
        testCarRace();
    }

    private static void testCarRace() {
         CarRaceLinkedHashMap carRace = new CarRaceLinkedHashMap(3);
         carRace.check(1);
         carRace.check(1);
         List<Integer> res1 = carRace.leading(3);
         for(Integer a : res1) {
             System.out.println(a);
         }
         carRace.check(0);
         carRace.check(0);
         carRace.check(0);
         List<Integer> res = carRace.leading(3);
         for(Integer a : res) {
             System.out.println(a);
         }
    }

    private static void testAllOnes() {
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
