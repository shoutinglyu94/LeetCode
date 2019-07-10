package sweepline;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom2 {

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][] {
            {13,15}, {1, 13}
        }));
    }

    static class Interval implements Comparable<Interval> {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Interval other) {
            return this.end - other.end;
        }
    }

    public static int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (int[] o1, int[]o2) -> (o1[0] - o2[0]));
        PriorityQueue<Interval> pq = new PriorityQueue<>();
        pq.offer(new Interval(intervals[0][0], intervals[0][1]));
        for(int i = 1; i < intervals.length; i++) {
            Interval cur = pq.poll();
            if(cur.end < intervals[i][0]) {
                cur.end = intervals[i][1];
            } else {
                pq.offer(new Interval(intervals[i][0], intervals[i][1]));
            }
            pq.offer(cur);
        }
        return pq.size();
    }
}
