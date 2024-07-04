package com.freedom.zuo.class25_monotonic_stack;

import java.util.Stack;

/**
 * 85. Maximal Rectangle
 * <p>
 * https://leetcode.cn/problems/maximal-rectangle/
 *
 * @author tobebetter9527
 * @create 2022/07/29 9:54
 */
public class Code04_MaximalRectangle {


    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];

        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            maxArea = Math.max(largestRectangleArea(heights), maxArea);
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int length = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                int difx = stack.isEmpty() ? i - 1 + 1 : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, difx * heights[j]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int difx = stack.isEmpty() ? length - 1 + 1 : length - 1 - stack.peek();
            maxArea = Math.max(maxArea, difx * heights[j]);
        }

        return maxArea;
    }


}
