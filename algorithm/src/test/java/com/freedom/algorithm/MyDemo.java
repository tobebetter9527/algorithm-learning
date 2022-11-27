package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    String customers = "9999900000";
    MyDemo demo = new MyDemo();
    int i = demo.countPalindromes(customers);
    System.out.println(i);
  }

  int count = 0;
  char[] str = new char[5];
  int num = 1000 * 1000 * 1000 + 7;

  public int countPalindromes(String s) {
    if (s.length() < 5) {
      return 0;
    }
    char[] chars = s.toCharArray();
    process(chars, 0, 0);
    return count % num;
  }

  private  void process(char[] chars, int index, int deep) {
    if (deep == 5) {
      if (isPalindrome(str)) {
        count++;
        count = count % num;
      }
      return;
    }

    for (int i = index; i + (5 - deep) <= chars.length; i++) {
      str[deep] = chars[i];
      process(chars, i + 1, deep + 1);
    }
  }

  private boolean isPalindrome(char[] str) {
    int start = 0;
    int end = str.length - 1;
    while (start <= end) {
      if (str[start] != str[end]) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }



}

