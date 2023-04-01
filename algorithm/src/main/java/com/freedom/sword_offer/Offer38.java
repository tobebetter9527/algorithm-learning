package com.freedom.sword_offer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tobebetter9527
 * @create 2023/04/01 18:12
 */
public class Offer38 {

  List<String> list = new LinkedList<>();
  char[] path;

  public String[] permutation(String s) {
    char[] arr = s.toCharArray();
    boolean[] visited = new boolean[arr.length];
    path = new char[arr.length];
    Arrays.sort(arr);
    backTrack(arr, visited, 0);
    return list.toArray(new String[list.size()]);
  }

  private void backTrack(char[] arr, boolean[] visited, int idx) {
    if (idx == arr.length) {
      list.add(String.valueOf(path));
      return;
    }

    for (int i = 0; i < arr.length; i++) {
      if (visited[i] || (i > 0 && !visited[i - 1] && arr[i - 1] == arr[i])) {
        continue;
      }
      path[idx] = arr[i];
      visited[i] = true;
      backTrack(arr, visited, idx + 1);
      visited[i] = false;
    }
  }

  public static void main(String[] args) {
    String s = "aab";
    Offer38 offer38 = new Offer38();
    String[] strings = offer38.permutation(s);
    for (String string : strings) {
      System.out.println(string);
    }
  }
}
