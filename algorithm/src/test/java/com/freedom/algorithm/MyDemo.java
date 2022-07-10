package com.freedom.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MyDemo {

  public static void main(String[] args) {
    NodeSet tim = new NodeSet(1, "tim");
    NodeSet tom = new NodeSet(1, "tom");

    Set<NodeSet> set = new HashSet<>();
    set.add(tim);
    set.add(tom);
    System.out.println(set);
  }


  static class NodeSet {

    int age;
    String name;

    public NodeSet(int age, String name) {
      this.age = age;
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      NodeSet nodeSet = (NodeSet) o;
      return age == nodeSet.age;
    }

    @Override
    public int hashCode() {
      return Objects.hash(age);
    }
  }
}

