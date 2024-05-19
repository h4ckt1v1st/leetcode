// First get the target row by comparing first and last value in it with the target.
// Use binary search to do this. => O(log(m))
// Then in a row apply a simple binary search. => O(log(n))
// Time complexity = O(log(m) + log(n)), Space complexity = O(1)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        final int ROWS = matrix.length;
        final int COLS = matrix[0].length; // Matrix will be non empty.

        int top = 0;
        int bot = ROWS - 1;
        while (top <= bot) {
            int row = top + (bot - top) / 2;

            if (target > matrix[row][COLS - 1]) {
                // Target is bigger than the biggest item in this row.
                top = row + 1;
            } else if (target < matrix[row][0]) {
                // Target is smaller than the smallest item in this row.
                bot = row - 1;
            } else {
                // Target is not bigger than the biggest item in this row.
                // Target is not smaller than the smallest item in this row.
                // That means, if target exists, it must be in this row.
                break;
            }
        }

        // At this point, either we found our target row, or we have exhausted all rows.
        if (top > bot) {
            return false;
        }

        int row = top + (bot - top) / 2;
        int left = 0;
        int right = COLS - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target > matrix[row][mid]) {
                left = mid + 1;
            } else if (target < matrix[row][mid]) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}