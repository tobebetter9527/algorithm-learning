package com.freedom.zuo.class17_graph_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * <p>
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 *
 * @author tobebetter9527
 * @create 2022/07/13 23:25
 */
public class Code04_PrintAllPermutations {


  public static List<String> permutation1(String str) {
    if (str == null || str.length() == 0) {
      return new ArrayList<>();
    }

    List<String> ans = new ArrayList<>();
    List<Character> rest = new ArrayList<>(str.length());
    for (char c : str.toCharArray()) {
      rest.add(c);
    }
    process(rest, "", ans);

    return ans;
  }

  /**
   * @param rest 剩余的字符list
   * @param path index-1之前的值
   * @param ans  答案
   */
  private static void process(List<Character> rest, String path, List<String> ans) {
    if (rest.isEmpty()) {
      ans.add(path);
      return;
    }
    int n = rest.size();
    for (int i = 0; i < n; i++) {
      Character character = rest.remove(i);
      process(rest, path + character, ans);
      rest.add(i, character);
    }
  }

  // ---------------------------------- //

  public static void main(String[] args) {
    String str = "abcd";

    List<String> list = permutation1(str);
    for (String s : list) {
      System.out.println(s);
    }
  }


}
