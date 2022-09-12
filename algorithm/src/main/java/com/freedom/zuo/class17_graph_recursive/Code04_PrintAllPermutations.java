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

  /**
   * 打印一个字符串的全部排列
   *
   * @param str
   * @return
   */
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

  /**
   * 打印一个字符串的全部排列
   *
   * @param str
   * @return
   */
  public static List<String> permutation2(String str) {
    if (str == null || str.length() == 0) {
      return new ArrayList<>();
    }

    List<String> ans = new ArrayList<>();
    process2(str.toCharArray(), 0, ans);

    return ans;
  }

  /**
   * @param chars 字符数组
   * @param index 当前索引
   * @param ans   答案
   */
  private static void process2(char[] chars, int index, List<String> ans) {
    // 终止条件
    if (index == chars.length) {
      ans.add(String.valueOf(chars));
      return;
    }
    for (int i = index; i < chars.length; i++) {
      swap(chars, i, index);
      process2(chars, index + 1, ans);
      swap(chars, i, index);
    }
  }

  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  // ---------------------------------- //

  /**
   * 打印一个字符串的全部排列，要求不要出现重复的排列
   *
   * @param str
   * @return
   */
  public static List<String> permutation3(String str) {
    if (str == null || str.length() == 0) {
      return new ArrayList<>();
    }

    List<String> ans = new ArrayList<>();

    process3(str.toCharArray(), 0, ans);

    return ans;
  }

  /**
   * @param str 字符数组
   * @param index 当前索引
   * @param ans   答案
   */
  private static void process3(char[] str, int index, List<String> ans) {
    // 终止条件
    if (index == str.length) {
      ans.add(String.valueOf(str));
    } else {
      boolean[] visited = new boolean[256];
      for (int i = index; i < str.length; i++) {
        // 访问过的字符就不在
        if (!visited[str[i]]) {
          visited[str[i]] = true;
          swap(str, i, index);
          process3(str, index + 1, ans);
          swap(str, i, index);
        }
      }
    }
  }


  public static void main(String[] args) {
    String str = "acc";

    List<String> list = permutation1(str);
    for (String s : list) {
      System.out.println(s);
    }

    System.out.println("=======================");

    List<String> list2 = permutation2(str);
    for (String s : list2) {
      System.out.println(s);
    }

    System.out.println("=======================");

    List<String> list3 = permutation3(str);
    for (String s : list3) {
      System.out.println(s);
    }
  }


}
