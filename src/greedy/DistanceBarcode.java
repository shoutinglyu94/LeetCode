package greedy;

import java.util.PriorityQueue;

public class DistanceBarcode {
    class Pair {
        int val;
        int ct;
        Pair(int val, int ct) {
            this.val = val;
            this.ct = ct;
        }
    }
    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] counts = new int[10001];
        for(int barcode : barcodes) {
            counts[barcode] += 1;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.ct == b.ct ? a.val - b.val : a.ct - b.ct));
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] != 0) {
                pq.offer(new Pair(i, counts[i]));
            }
        }
        int i = 0;
        while(pq.size() >= 2){
            Pair fi = pq.poll();
            Pair se = pq.poll();
            barcodes[i] = fi.val;
            barcodes[i + 1] = se.val;
            fi.ct--;
            se.ct--;
            if(fi.ct >= 1) pq.offer(fi);
            if(se.ct >= 1) pq.offer(se);
            i += 2;
        }
        if(!pq.isEmpty()) barcodes[barcodes.length - 1] = pq.poll().val;

        return barcodes;
    }
}
