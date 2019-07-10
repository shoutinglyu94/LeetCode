package greedy;

public class GreedyBenchmark {
    public static void main(String[] args) {
        //TaskScheduler scheduler = new TaskScheduler();
        //scheduler.leastInterval(new char[] {'A', 'A', 'A', 'B', 'B', 'B'}, 2);

        DistanceBarcode barcodeScheduler = new DistanceBarcode();
        int[] res = barcodeScheduler.rearrangeBarcodes(new int[] {1, 1, 1, 2, 2, 2});

        for(int r : res) {
            System.out.println(r);
        }
    }
}
