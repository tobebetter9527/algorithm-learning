package com.freedom.zuo.class25_monotonic_stack;

/**
 * https://leetcode.cn/problems/count-submatrices-with-all-ones/
 */
public class Code05_CountSubmatricesWithAllOnes {

    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        // 子矩阵数量
        int numSubmat = 0;

        int row = mat.length;
        int col = mat[0].length;
        int[] heights = new int[col];

        for (int i = 0; i < row; i++) {
            // 以当前行为底行，求子矩阵数量
            for (int j = 0; j < col; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            numSubmat += countFromBottom(heights);
        }

        return numSubmat;
    }


    // 比如
    //              1
    //              1
    //              1         1
    //    1         1         1
    //    1         1         1
    //    1         1         1
    //
    //    2  ....   6   ....  9
    // 如上图，假设在6位置，1的高度为6
    // 在6位置的左边，离6位置最近、且小于高度6的位置是2，2位置的高度是3
    // 在6位置的右边，离6位置最近、且小于高度6的位置是9，9位置的高度是4
    // 此时我们求什么？
    // 1) 求在3~8范围上，必须以高度6作为高的矩形，有几个？
    // 2) 求在3~8范围上，必须以高度5作为高的矩形，有几个？
    // 也就是说，<=4的高度，一律不求
    // 那么，1) 求必须以位置6的高度6作为高的矩形，有几个？等差数列求解公式
    // 3..3  3..4  3..5  3..6  3..7  3..8
    // 4..4  4..5  4..6  4..7  4..8
    // 5..5  5..6  5..7  5..8
    // 6..6  6..7  6..8
    // 7..7  7..8
    // 8..8
    // 这么多！= 21 = (9 - 2 - 1) * (9 - 2) / 2
    // 这就是任何一个数字从栈里弹出的时候，计算矩形数量的方式
    private int countFromBottom(int[] heights) {
        int numSubMat = 0;

        // 模拟栈
        int[] stack = new int[heights.length];
        int si = -1;

        for (int i = 0; i < heights.length; i++) {
            while (si != -1 && heights[stack[si]] >= heights[i]) {
                // 从栈里弹出
                int cur = stack[si--];
                // 相等的值最后一次一起算，类似算直方图
                if (heights[cur] > heights[i]) {
                    // 计算最左边的索引
                    int leftIndex = si == -1 ? -1 : stack[si];
                    // 计算总长度
                    int difL = (i - 1) - leftIndex;
                    // 这里特别注意leftIndex等于-1的情况，
                    int down = Math.max(leftIndex == -1 ? 0 : heights[leftIndex], heights[i]);
                    numSubMat += (heights[cur] - down) * nums(difL);
                }
            }
            stack[++si] = i;
        }

        while (si != -1) {
            // 从栈里弹出
            int cur = stack[si--];
            // 计算最左边的索引
            int leftIndex = si == -1 ? -1 : stack[si];
            // 计算总长度
            int difL = (heights.length - 1) - leftIndex;
            // 这里特别注意leftIndex等于-1的情况，
            int down = leftIndex == -1 ? 0 : heights[leftIndex];
            numSubMat += (heights[cur] - down) * nums(difL);
        }

        return numSubMat;
    }

    private int nums(int n) {
        return ((n * (1 + n)) >> 1);
    }

}
