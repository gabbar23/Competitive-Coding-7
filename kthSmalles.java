/**
 * Time Complexity: O(n * log(range)), where n is the dimension of the matrix and range is the difference
 * between the maximum and minimum values in the matrix.
 * - The binary search runs in O(log(range)) iterations.
 * - Each iteration involves a linear scan through the matrix, which is O(n).

 * Space Complexity: O(1)
 * - The solution uses a constant amount of extra space beyond the input.
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        // Set the range of binary search
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left < right) {  // Notice the use of '<' instead of '<='
            int mid = left + (right - left) / 2;
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };
            int count = calculateLefts(matrix, mid, smallLargePair);

            if (count < k) {
                left = smallLargePair[1];  // Move the left pointer to the right of the current mid
            } else {
                right = smallLargePair[0]; // Move the right pointer to the left of the current mid
            }
        }

        return left;  // or return right since left == right
    }

    private int calculateLefts(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix.length) {
            if (matrix[row][col] > mid) {
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }
}
