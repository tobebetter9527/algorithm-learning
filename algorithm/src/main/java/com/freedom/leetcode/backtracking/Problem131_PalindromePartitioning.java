package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 *
 * @author tobebetter9527
 * @create 2022/11/15 20:56
 */
public class Problem131_PalindromePartitioning {

  List<List<String>> ans = new LinkedList<>();
  List<String> path = new ArrayList<>();
  boolean[][] f;

  public List<List<String>> partition(String s) {
    int n = s.length();
    f = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      f[i][i] = true;
    }



    process(s, 0);
    return ans;
  }

  private void process(String s, int startIndex) {
    if (startIndex >= s.length()) {
      ans.add(new ArrayList<>(path));
      return;
    }
    for (int i = startIndex; i < s.length(); i++) {
      if (isPalindrome(s, startIndex, i)) {
        path.add(s.substring(startIndex, i + 1));
        process(s, i + 1);
        path.remove(path.size() - 1);
      }
    }
  }

  private boolean isPalindrome(String s, int start, int end) {
    while (start <= end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  public static void main(String[] args) {
    String str = "aab";
    Problem131_PalindromePartitioning problem = new Problem131_PalindromePartitioning();
    List<List<String>> partition = problem.partition(str);
    System.out.println(partition);
  }

}
