package com.freedom.sword_offer;

public class Offer04 {

    /**
     * time complexity is O(n + m), space complexity is (1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) {
            return false;
        }
        int m = matrix[0].length;
        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            int num = matrix[i][j];
            if (num > target) {
                j--;
            } else if (num < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
