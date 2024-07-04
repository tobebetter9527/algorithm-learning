package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 拼接的字符串字典序最小
 *
 * @author tobebetter9527
 * @create 2022/07/06 20:15
 */
public class Code05_LowestLexicography {


    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }


    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> process = process(strs);
        return process.first();
    }

    private static TreeSet<String> process(String[] strs) {
        TreeSet<String> set = new TreeSet<>();
        if (strs.length == 0) {
            set.add("");
            return set;
        }

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String[] nexts = removeIndex(strs, i);
            TreeSet<String> treeSet = process(nexts);
            for (String s : treeSet) {
                set.add(str + s);
            }
        }
        return set;
    }

    private static String[] removeIndex(String[] strs, int j) {
        if (strs == null) {
            return null;
        }
        String[] nexts = new String[strs.length - 1];
        int index = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i != j) {
                nexts[index++] = strs[i];
            }
        }
        return nexts;
    }

    public static void main(String[] args) {
        int maxLength = 8;
        int maxStrLength = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            String[] strs = generateRandomStringArray(maxLength, maxStrLength);
            String[] copyStrs = Arrays.copyOf(strs, strs.length);
            if (!lowestString1(strs).equals(lowestString2(copyStrs))) {
                System.out.println("wrong!");
            }
        }
        System.out.println("done!");
    }

    private static String[] generateRandomStringArray(int maxLength, int maxStrLength) {
        int length = (int) (Math.random() * maxLength);
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = generateRandomString(maxStrLength);
        }
        return strs;
    }

    private static String generateRandomString(int maxStrLength) {
        int length = (int) (Math.random() * maxStrLength) + 1;
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] =
                    (Math.random() > 0.5D) ? (char) (97 + (int) (Math.random() * 26)) : (char) (65 + (int) (Math.random() * 26));
        }
        return String.valueOf(chars);
    }

    static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

}
