package com.freedom.zuo.class15_union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode 200. Number of Islands
 *
 * @author tobebetter9527
 * @create 2022/07/09 16:58
 */
public class Code02_NumberOfIslands {

  /**
   * 感染方法，把遇到的1改成0
   */
  public static int numIslands3(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int island = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          island++;
          infect(grid, m, n, i, j);
        }
      }
    }
    return island;
  }

  private static void infect(char[][] grid, int m, int n, int i, int j) {
    if (i < 0 || i == m || j < 0 || j == n || grid[i][j] != '1') {
      return;
    }
    grid[i][j] = '0';
    infect(grid, m, n, i + 1, j);
    infect(grid, m, n, i - 1, j);
    infect(grid, m, n, i, j + 1);
    infect(grid, m, n, i, j - 1);
  }

  // -----------------------------------------------//

  public static int numIslands1(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    Dot[][] dots = new Dot[row][col];
    List<Dot> list = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1') {
          dots[i][j] = new Dot();
          list.add(dots[i][j]);
        }
      }
    }

    UnionFind1<Dot> unionFind1 = new UnionFind1<>(list);
    // 第一行
    for (int i = 1; i < col; i++) {
      if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
        unionFind1.union(dots[0][i - 1], dots[0][i]);
      }
    }

    // 第一列
    for (int i = 1; i < row; i++) {
      if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
        unionFind1.union(dots[i - 1][0], dots[i][0]);
      }
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (grid[i][j] == '1') {
          if (grid[i - 1][j] == '1') {
            unionFind1.union(dots[i - 1][j], dots[i][j]);
          }
          if (grid[i][j - 1] == '1') {
            unionFind1.union(dots[i][j - 1], dots[i][j]);
          }
        }
      }
    }

    return unionFind1.sets();
  }


  static class UnionFind1<T> {

    private Map<T, Node<T>> nodes;
    private Map<Node<T>, Node<T>> parents;
    private Map<Node<T>, Integer> sizeMap;

    public UnionFind1(List<T> list) {
      nodes = new HashMap<>();
      parents = new HashMap<>();
      sizeMap = new HashMap<>();
      for (T t : list) {
        Node<T> node = new Node<>(t);
        nodes.put(t, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    public Node<T> findFather(Node<T> cur) {
      Stack<Node<T>> stack = new Stack<>();
      while (cur != parents.get(cur)) {
        stack.push(cur);
        cur = parents.get(cur);
      }

      while (!stack.isEmpty()) {
        parents.put(stack.pop(), cur);
      }

      return cur;
    }

    public void union(T a, T b) {
      Node<T> aNode = findFather(nodes.get(a));
      Node<T> bNode = findFather(nodes.get(b));
      if (aNode != bNode) {
        int aSize = sizeMap.get(aNode);
        int bSize = sizeMap.get(bNode);
        if (aSize >= bSize) {
          parents.put(bNode, aNode);
          sizeMap.put(aNode, aSize + bSize);
          sizeMap.remove(bNode);
        } else {
          parents.put(aNode, bNode);
          sizeMap.put(bNode, aSize + bSize);
          sizeMap.remove(aNode);
        }
      }
    }

    public int sets() {
      return sizeMap.size();
    }
  }

  static class Dot {

  }

  static class Node<T> {

    T value;

    public Node(T value) {
      this.value = value;
    }
  }

  // -----------------------------------------------//

  public static int numIslands2(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;

    UnionFind2 uf = new UnionFind2(grid, row, col);
    // 第一行
    for (int i = 1; i < col; i++) {
      if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
        uf.union(0, i - 1, 0, i);
      }
    }

    // 第一列
    for (int i = 1; i < row; i++) {
      if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
        uf.union(i - 1, 0, i, 0);
      }
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (grid[i][j] == '1') {
          if (grid[i - 1][j] == '1') {
            uf.union(i - 1, j, i, j);
          }
          if (grid[i][j - 1] == '1') {
            uf.union(i, j - 1, i, j);
          }
        }
      }
    }

    return uf.sets;
  }

  static class UnionFind2 {

    int[] parents;
    int[] size;
    int[] stack;
    int sets;
    int col;
    int row;

    public UnionFind2(char[][] grid, int row, int col) {
      this.row = row;
      this.col = col;
      int total = row * col;
      this.parents = new int[total];
      this.size = new int[total];
      this.stack = new int[total];

      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (grid[i][j] == '1') {
            int index = handleIndex(i, j);
            parents[index] = index;
            size[index] = 1;
            sets++;
          }
        }
      }
    }


    public void union(int row1, int col1, int row2, int col2) {
      int index1 = findFather(row1, col1);
      int index2 = findFather(row2, col2);
      if (index1 != index2) {
        int aSize = size[index1];
        int bSize = size[index2];
        if (aSize >= bSize) {
          parents[index2] = index1;
          size[index1] = aSize + bSize;
          size[index2] = 0;
        } else {
          parents[index1] = index2;
          size[index2] = aSize + bSize;
          size[index1] = 0;
        }
        sets--;
      }
    }

    private int findFather(int row1, int col1) {
      int index = handleIndex(row1, col1);
      int hi = 0;
      while (index != parents[index]) {
        stack[hi++] = index;
        index = parents[index];
      }

      while (hi > 0) {
        parents[stack[--hi]] = index;
      }

      return index;
    }


    private int handleIndex(int row, int col) {
      return this.col * row + col;
    }
  }


  public static char[][] generateRandomMatrix(int row, int col) {
    char[][] board = new char[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        board[i][j] = Math.random() < 0.5 ? '1' : '0';
      }
    }
    return board;
  }

  // 为了测试
  public static char[][] copy(char[][] board) {
    int row = board.length;
    int col = board[0].length;
    char[][] ans = new char[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        ans[i][j] = board[i][j];
      }
    }
    return ans;
  }

  // 为了测试
  public static void main(String[] args) {
    int row = 0;
    int col = 0;
    char[][] board1 = null;
    char[][] board2 = null;
    char[][] board3 = null;
    long start = 0;
    long end = 0;

    row = 1000;
    col = 1000;
    board1 = generateRandomMatrix(row, col);
    board2 = copy(board1);
    board3 = copy(board1);

    System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
    System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

    start = System.currentTimeMillis();
    System.out.println("感染方法的运行结果: " + numIslands3(board1));
    end = System.currentTimeMillis();
    System.out.println("感染方法的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行结果: " + numIslands1(board2));
    end = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
    end = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

    System.out.println();

    row = 10000;
    col = 10000;
    board1 = generateRandomMatrix(row, col);
    board3 = copy(board1);
    System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
    System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

    start = System.currentTimeMillis();
    System.out.println("感染方法的运行结果: " + numIslands3(board1));
    end = System.currentTimeMillis();
    System.out.println("感染方法的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
    end = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

  }

}
