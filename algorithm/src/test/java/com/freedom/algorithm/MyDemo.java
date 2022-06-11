package com.freedom.algorithm;

import java.util.Deque;
import java.util.LinkedList;
import javax.sound.midi.Soundbank;

public class MyDemo {

  public static void main(String[] args) {
    for (int i = 0; i < 1000000000; i++) {
      int random = (int) (Math.random() * 10);
      if (random == 0) {
        System.out.println("0");
      }
    }
  }


}

