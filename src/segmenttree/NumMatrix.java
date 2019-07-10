package segmenttree;

class NumMatrix {

    class SegmentTreeNode {
        int row1, row2, col1, col2;
        int sum;
        SegmentTreeNode c1, c2, c3, c4;
        SegmentTreeNode(int row1, int col1, int row2, int col2, int sum) {
            this.row1 = row1;
            this.row2 = row2;
            this.col1 = col1;
            this.col2 = col2;
            this.sum = sum;
        }
    }
    SegmentTreeNode root;
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        root = buildTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    private SegmentTreeNode buildTree(int[][] matrix, int row1, int col1, int row2, int col2) {
        if(row1 > row2 || col1 > col2) return null;
        if(row1 == row2 && col1 == col2) {
            return new SegmentTreeNode(row1, col1, row2, col2, matrix[row1][col1]);
        }
        SegmentTreeNode root = new SegmentTreeNode(row1, col1, row2, col2, 0);
        int r_m = row1 + (row2 - row1) / 2;
        int c_m = col1 + (col2 - col1) / 2;
        root.c1 = buildTree(matrix, row1, col1, r_m, c_m);
        root.c2 = buildTree(matrix, r_m + 1, col1, row2, c_m);
        root.c3 = buildTree(matrix, row1, c_m + 1, r_m, col2);
        root.c4 = buildTree(matrix, r_m + 1, c_m + 1, row2, col2);
        root.sum = (root.c1 == null ? 0 : root.c1.sum)
                + (root.c2 == null ? 0 : root.c2.sum)
                + (root.c3 == null ? 0 : root.c3.sum)
                + (root.c4 == null ? 0 : root.c4.sum);
        return root;
    }

    public void update(int row, int col, int val) {
        updateVal(root, row, col, val);
    }

    private void updateVal(SegmentTreeNode root, int row, int col, int val) {
        if(root == null) return;
        if(row < root.row1 || row > root.row2 || col < root.col1 || col > root.col2) {
            return;
        }
        if(root.row1 == row && root.row2 == row && root.col1 == col && root.col2 == col) {
            root.sum = val;
            return;
        }

        updateVal(root.c1, row, col, val);
        updateVal(root.c2, row, col, val);
        updateVal(root.c3, row, col, val);
        updateVal(root.c4, row, col, val);

        root.sum = (root.c1 == null ? 0 : root.c1.sum)
                + (root.c2 == null ? 0 : root.c2.sum)
                + (root.c3 == null ? 0 : root.c3.sum)
                + (root.c4 == null ? 0 : root.c4.sum);
    }


    public int sumRegion(int row1, int col1, int row2, int col2) {
        return query(root, row1, col1, row2, col2);
    }

    private int query(SegmentTreeNode root, int row1, int col1, int row2, int col2) {
        if(root == null) return 0;
        if(row1 > root.row2 && row2 < root.row1 && col1 > root.col2 && col2 < root.col1) {
            return 0;
        }
        if(row1 <= root.row1 && row2 >= root.row2 && col1 <= root.col1 && col2 >= root.col2) {
            return root.sum;
        }
        return query(root.c1, row1, col1, row2, col2) +
                query(root.c2, row1, col1, row2, col2) +
                query(root.c3, row1, col1, row2, col2) +
                query(root.c4, row1, col1, row2, col2);
    }
}