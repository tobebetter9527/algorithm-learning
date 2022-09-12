package com.freedom.zuo.class17_graph_recursive;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 子序列，不连续，比如abc，子序列有a,b,c,ab,ac,bc,abc,""
 *
 * 打印一个字符串的全部子序列
 * <p>
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 *
 * @author tobebetter9527
 * @create 2022/07/13 22:07
 */
public class Code03_PrintAllSubsquences {

  /**
   * 打印一个字符串的全部子序列
   */
  public static List<String> subs(String s) {
    if (s == null) {
      return null;
    }

    List<String> ans = new LinkedList<>();
    char[] chars = s.toCharArray();
    String path = "";
    process(chars, 0, path, ans);
    return ans;
  }

  /**
   * 每个位置的字符有要和不要两种选择
   *
   * @param chars 字符数组
   * @param index 来到当前index位置
   * @param path  index -1之前的取值
   * @param ans   答案
   */
  private static void process(char[] chars, int index, String path, List<String> ans) {
    // 终止条件，说明已经取完值了
    if (index == chars.length) {
      ans.add(path);
      return;
    }

    // index位置不要
    process(chars, index + 1, path, ans);

    // index位置要
    process(chars, index + 1, path + chars[index], ans);
  }

  // ------------------------------ //

  /**
   * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
   *
   * @param s
   * @return
   */
  public static Set<String> subsNoRepeat(String s) {
    if (s == null) {
      return null;
    }

    Set<String> ans = new HashSet<>();
    char[] chars = s.toCharArray();
    String path = "";
    process2(chars, 0, path, ans);
    return ans;
  }

  private static void process2(char[] chars, int index, String path, Set<String> ans) {
    // 终止条件，说明已经取完值了
    if (index == chars.length) {
      ans.add(path);
      return;
    }

    // index位置不要
    process2(chars, index + 1, path, ans);

    // index位置要
    process2(chars, index + 1, path + chars[index], ans);
  }

  public static void main(String[] args) {
    String s = "abc";
    List<String> subs = subs(s);
    for (String sub : subs) {
      System.out.println(sub);
    }
    System.out.println("=========");
    Set<String> strings = subsNoRepeat(s);
    for (String string : strings) {
      System.out.println(string);
    }
  }

}
