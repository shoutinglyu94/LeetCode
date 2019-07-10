package priorityqueue;

import java.util.TreeSet;

class MedianFinder {
    class Cell implements Comparable<Cell>{
        int index, val;
        Cell(int idx, int v) {
            index = idx;
            val = v;
        }
        @Override
        public int compareTo(Cell other) {
            if(this.val != other.val) return Integer.compare(this.val, other.val);
            return Integer.compare(this.index, other.index);
        }
    }
    private TreeSet<Cell> treeSet;
    private Cell midSmall, midLarge;
    /** initialize your data structure here. */
    public MedianFinder() {
        treeSet = new TreeSet<>();
        midSmall = null;
        midLarge = null;
    }

    public void addNum(int num) {
        Cell cell = new Cell(treeSet.size(), num);
        treeSet.add(cell);
        updateMedian(cell);
    }

    private void updateMedian(Cell cell) {
        if(treeSet.size() == 1) {
            midSmall = cell;
            midLarge = cell;
        } else if(treeSet.size() % 2 == 0) {
            if(cell.val >= midSmall.val) {
                midLarge = treeSet.higher(midSmall);
            } else {
                midSmall = treeSet.lower(midSmall);
            }
        } else {
            if(cell.val >= midSmall.val && cell.val < midLarge.val) {
                midSmall = cell;
                midLarge = cell;
            } else if(cell.val >= midLarge.val){
                midSmall = midLarge;
            } else {
                midLarge = midSmall;
            }
        }
    }

    public double findMedian() {
        return (midSmall.val + midLarge.val) / 2.0;
    }

}

