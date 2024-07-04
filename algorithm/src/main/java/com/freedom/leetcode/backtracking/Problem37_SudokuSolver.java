package com.freedom.leetcode.backtracking;

/**
 * 37. Sudoku Solver
 *
 * @author tobebetter9527
 * @create 2022/11/19 16:17
 */
public class Problem37_SudokuSolver {


    public void solveSudoku(char[][] board) {
        backTracking(board, board.length, board[0].length);
    }

    private boolean backTracking(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(i, j, board, k)) {
                            board[i][j] = k;
                            if (backTracking(board, row, col)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board, char k) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == k) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == k) {
                return false;
            }
        }

        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }


}
