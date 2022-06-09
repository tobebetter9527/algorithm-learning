package com.freedom.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    //  Scanner scanner = new Scanner(System.in);
    //  String next = scanner.next();
    String next = "2342345,236,234";
    List<String> list = Arrays.asList(next.split(","));
    System.out.println(list);
    Collections.sort(list, (o1, o2) -> {
          int max = Math.max(o1.length(), o2.length());
          int min = Math.min(o1.length(), o2.length());
          String maxStr;
          String minStr;
          if (o1.length() >= o2.length()) {
            maxStr = o1;
            minStr = o2;
          } else {
            maxStr = o2;
            minStr = o1;
          }
          int loopNum;
          if (max % min != 0) {
            loopNum = max / min + 1;
          } else {
            loopNum = max / min;
          }

          for (int i = 0; i < loopNum; i++) {
            int start = i * min;
            int end = Math.min((i + 1) * min, max);
            for (int j = start; j < end; j++) {
              char a = maxStr.charAt(j);
              char b = minStr.charAt(j % min);
              if (a > b ) {
                return 1;
              } else if (a < b) {
                return -1;
              } else {
                continue;
              }
            }
          }
          return 0;
        }
    );
    StringBuilder sb = new StringBuilder();
    for (String s : list) {
      sb.append(s);
    }
    System.out.println(list);
    System.out.println(sb);
  }


}
