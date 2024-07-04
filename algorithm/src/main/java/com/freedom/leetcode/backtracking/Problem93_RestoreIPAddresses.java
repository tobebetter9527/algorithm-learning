package com.freedom.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. Restore IP Addresses
 */
public class Problem93_RestoreIPAddresses {

    List<String> ans = new LinkedList<>();
    List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String str = "101023";
        Problem93_RestoreIPAddresses problem = new Problem93_RestoreIPAddresses();
        List<String> strings = problem.restoreIpAddresses(str);
        System.out.println(strings);
    }

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() > 12 || s.length() < 4) {
            return ans;
        }
        process(s, s.length(), 0, 0);
        return ans;
    }

    private void process(String s, int n, int startIndex, int deep) {
        // 优化点，实际递归到第三个小点时候，直接判断后面的字符串是否符合要求即可
        if (deep == 3) {
            String substring = s.substring(startIndex);
            if (isValid(substring)) {
                StringBuilder sb = new StringBuilder();
                list.forEach(x -> sb.append(x).append("."));
                sb.append(substring);
                ans.add(sb.toString());
            }
            return;
        }
        for (int i = startIndex; i < startIndex + 3 && i < n; i++) {
            String substring = s.substring(startIndex, i + 1);
            if (isValid(substring)) {
                list.add(substring);
                process(s, n, i + 1, deep + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isValid(String substring) {
        if (substring.length() == 0) {
            return false;
        }
        if (substring.length() > 1 && substring.charAt(0) == '0') {
            return false;
        }

        return Integer.valueOf(substring) <= 255;
    }

}
