package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.Map;

public class Offer67 {

  public int strToInt(String str) {
    if (str == null) {
      return 0;
    }
    str = str.trim();
    if (str.length() == 0) {
      return 0;
    }
    // 返回结果，默认为0
    int res = 0;
    // 数字的符号标准
    int sign = 1;
    // 数字的起始索引位置
    int idx = 1;
    // 边界，用于判断数字是否越界
    int boundary = Integer.MAX_VALUE / 10;

    char[] chars = str.toCharArray();
    // 判断首个字符, 如果是-，那符号是负号，如果既不是-也不是+，那idx=0开始计算
    if (chars[0] == '-') {
      sign = -1;
    } else if (chars[0] != '+') {
      idx = 0;
    }

    for (int j = idx; j < chars.length; j++) {
      // 非数字，直接返回
      if (chars[j] < '0' || chars[j] > '9') {
        break;
      }
      // 数字的范围-2147483648 ~ 2147483647
      // 如果res大于boundary，再来一个无论什么数字，都已经越界，比如res=214748365,再来一个1，2147483651显然大于2147483648
      // 如果res等于boundary，再来一个大于7的数字，越界，或者刚好是-2147483648不越界
      if (res > boundary || (res == boundary && chars[j] > '7')) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      res = res * 10 + (chars[j] - '0');
    }

    return res * sign;
  }


  public int strToInt2(String str) {
    if (str == null) {
      return 0;
    }
    str = str.trim();
    if (str.length() == 0) {
      return 0;
    }

    Automation automation = new Automation();
    for (char c : str.toCharArray()) {
      if (!automation.get(c)) {
        break;
      }
    }
    return automation.sign * (int) automation.res;
  }

  static class Automation {
    public int sign = 1;
    public long res = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
      put("start", new String[]{"start", "signed", "in_number", "end"});
      put("signed", new String[]{"end", "end", "in_number", "end"});
      put("in_number", new String[]{"end", "end", "in_number", "end"});
      put("end", new String[]{"end", "end", "end", "end"});
    }};

    public boolean get(char c) {
      state = table.get(state)[getIndex(c)];
      // 如果是end状态，可以提前结束循环
      if ("end".equals(state)) {
        return false;
      }

      if ("in_number".equals(state)) {
        res = res * 10 + (c - '0');
        res = sign == 1 ? Math.min(res, (long) Integer.MAX_VALUE) : Math.min(res, -(long) Integer.MIN_VALUE);
      }

      if ("signed".equals(state)) {
        sign = c == '+' ? 1 : -1;
      }

      return true;
    }

    private int getIndex(char c) {
      if (' ' == c) {
        return 0;
      } else if ('+' == c || '-' == c) {
        return 1;
      } else if (Character.isDigit(c)) {
        return 2;
      } else {
        return 3;
      }
    }
  }

  public static void main(String[] args) {
    Offer67 offer67 = new Offer67();
    int res = offer67.strToInt2(" h  43454534534534534534534sa");
    System.out.println(res);

  }
}
