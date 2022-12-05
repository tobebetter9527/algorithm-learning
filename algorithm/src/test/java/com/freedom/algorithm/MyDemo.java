package com.freedom.algorithm;

public class MyDemo {

  public static void main(String[] args) {
    String sentence = "leetcode exercises sound delightful";
    System.out.println(isCircularSentence(sentence));
  }

  public static boolean isCircularSentence(String sentence) {
    String[] arr = sentence.split(" ");
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
      if (arr[i].charAt(arr[i].length() - 1) != arr[i + 1].charAt(0)) {
        return false;
      }
    }

    return  arr[0].charAt(0) == arr[n - 1].charAt(arr[n - 1].length() - 1);
  }



}

