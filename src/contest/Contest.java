package contest;

public class Contest {
    public static void main(String[] args) {
        System.out.println(minHeightShelves(new int[][]{{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}}, 4));
    }
    private static int[][] memo;
    public static int minHeightShelves(int[][] books, int shelf_width) {
        memo = new int[books.length + 1][shelf_width + 1];
        return minHeightShelves(books, 0, 0, shelf_width, shelf_width);
    }

    private static int minHeightShelves(int[][] books, int i, int cur_height, int remain_width, int shelf_width) {
        if(i == books.length) {
            return cur_height;
        }
        if(memo[i][remain_width] != 0) return memo[i][remain_width];

        memo[i][remain_width] = cur_height + minHeightShelves(books, i + 1, books[i][1],
                shelf_width - books[i][0], shelf_width);

        if(remain_width - books[i][0] >= 0) {
            memo[i][remain_width] = Math.min(memo[i][remain_width], minHeightShelves(books, i + 1, Math.max(cur_height, books[i][1]), remain_width - books[i][0], shelf_width));
        }
        return memo[i][remain_width];
    }

}
