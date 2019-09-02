package segmenttree;

import javax.swing.text.Segment;
import java.util.Arrays;

public class RangeSum {

    class SegmentTreeNode {
        SegmentTreeNode left, right;
        int l, r;
        int sum;
        SegmentTreeNode(int l, int r, int sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            left = null;
            right = null;
        }
    }
    SegmentTreeNode root;
    private int[] array;
    public RangeSum(int[] nums) {
        this.array = Arrays.copyOf(nums, nums.length);
        this.root = buildSegmentTree(array, 0, array.length - 1);
    }

    private SegmentTreeNode buildSegmentTree(int[] nums, int start, int end) {
        if(start == end) {
            return new SegmentTreeNode(start, end, nums[start]);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        root.left = buildSegmentTree(nums, start, mid);
        root.right = buildSegmentTree(nums, mid + 1, end);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    private int query(SegmentTreeNode root, int i, int j) {
        if(root.l == i && root.r == j) {
            return root.sum;
        }
        int mid = root.l + (root.r - root.l) / 2;
        if(i >= mid + 1) {
            return query(root.right, i, j);
        } else if (j <= mid) {
            return query(root.left, i, j);
        } else {
            return query(root.left, i, mid) + query(root.right, mid + 1, j);
        }
    }

    public int sumRange(int i, int j) {
        if(i < root.l || j < root.l || i > root.r || j > root.r) return -1;
        return query(root, i, j);
    }

    public void update(int i, int val) {
        if(i < root.l || i > root.r) return;
        int old = this.array[i];
        this.array[i] = val;
        update(root, i, val - old);
    }

    private void update(SegmentTreeNode root, int i, int offset) {
        if(root.l == i && root.r == i) {
            root.sum += offset;
            return;
        }
        int mid = root.l + (root.r - root.l) / 2;
        root.sum += offset;
        if(i <= mid) {
            update(root.left, i, offset);
        } else {
            update(root.right, i, offset);
        }
    }


}
