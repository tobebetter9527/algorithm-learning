package com.freedom.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1905. Count Sub Islands
 *
 * @author tobebetter9527
 * @create 2023/01/08 11:10
 */
public class Problem1905_CountSubIslands {


    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid2.length, n = grid2[0].length;
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n, dx, dy)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean dfs(int[][] grid1, int[][] grid2, int i, int j, int m, int n, int[] dx, int[] dy) {
        boolean isSubIsland = grid1[i][j] == 1;
        grid2[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j + dy[k];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                isSubIsland &= dfs(grid1, grid2, x, y, m, n, dx, dy);
            }
        }
        return isSubIsland;
    }

    // ----//

    /**
     * 主要思路是: 在grid2搜索岛屿的过程中，判断grid2的每个格子在grid1中是否为陆地，如果是，则答案加1
     *
     * @param grid1
     * @param grid2
     * @return
     */
    public static int countSubIslands2(int[][] grid1, int[][] grid2) {
        int m = grid2.length, n = grid2[0].length;
        // 用来模拟方格的上下左右方向
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1 && bfs(grid1, grid2, i, j, m, n, dx, dy)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean bfs(int[][] grid1, int[][] grid2, int i, int j, int m, int n, int[] dx, int[] dy) {
        boolean isSubIsland = grid1[i][j] == 1;
        // 保存行和列坐标
        Queue<Integer> queueRow = new LinkedList<>();
        Queue<Integer> queueCol = new LinkedList<>();
        queueRow.add(i);
        queueCol.add(j);
        grid2[i][j] = 0;
        while (!queueRow.isEmpty()) {
            int row = queueRow.poll();
            int col = queueCol.poll();
            for (int k = 0; k < 4; k++) {
                int x = row + dx[k], y = col + dy[k];
                // 判断是否为连接的陆地
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                    grid2[x][y] = 0;
                    queueRow.add(x);
                    queueCol.add(y);
                    // 判断当前各自在grid1是否为陆地
                    isSubIsland &= grid1[x][y] == 1;
                }
            }
        }
        return isSubIsland;
    }


    public static void main(String[] args) {
        int[][] grid1 = {{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}};
        int[][] grid2 = {{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        System.out.println(countSubIslands2(grid1, grid2));

    }

}
