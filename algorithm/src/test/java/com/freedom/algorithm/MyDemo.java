package com.freedom.algorithm;

import java.util.LinkedList;

public class MyDemo {

  public static void main(String[] args) {
    System.out.println(5 >> 1);

    int[] nums = new int[1];
    nums[0] = -2;
    nums[0] = nums[0] ^ 2;
    System.out.println(nums[0]);

    LinkedList<String> list = new LinkedList<>();
    list.add(1, "");
    list.add("");
    list.remove(1);
    list.get(2);
    list.getFirst();

  }


}

