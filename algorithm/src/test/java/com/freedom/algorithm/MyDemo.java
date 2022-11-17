package com.freedom.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDemo {

  public static void main(String[] args) {
    double celsius = 122.11;
    double[] doubles = convertTemperature(celsius);
    for (double aDouble : doubles) {
      System.out.println(aDouble);
    }

  }

  public static double[] convertTemperature(double celsius) {
    double[] ans = new double[2];
    ans[0] = celsius + 273.15;
    ans[1] = celsius * 1.80 + 32.00;
    return ans;
  }

  


}

