package com.freedom.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 1207. Unique Number of Occurrences
 *
 * @author tobebetter9527
 * @create 2022/12/16 21:19
 */
public class Problem1207_UniqueNumberofOccurrences {

  public boolean uniqueOccurrences(int[] arr) {
    int n = arr.length;
    Map<Integer, Integer> map = new HashMap<>(n);
    for (int i : arr) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    Set<Integer> set = map.values().stream().collect(Collectors.toSet());

    return set.size() == map.size();
  }
}
