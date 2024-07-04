package com.freedom.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 139. Word Break
 *
 * @author tobebetter9527
 * @create 2022/12/07 19:59
 */
public class Problem139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        return recursive(s, wordDict, 0);
    }

    private static boolean recursive(String s, List<String> wordDict, int index) {
        if (index == s.length()) {
            return true;
        }

        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            if (index + word.length() <= s.length()) {
                boolean flag = isMatch(s, word, index) && recursive(s, wordDict, index + word.length());
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isMatch(String s, String word, int index) {
        for (int i = 0; i < word.length(); i++) {
            if (s.charAt(index + i) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;

        for (int index = n - 1; index >= 0; index--) {
            for (int i = 0; i < wordDict.size(); i++) {
                String word = wordDict.get(i);
                if (index + word.length() <= s.length() && isMatch(s, word, index) && dp[index + word.length()]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak2(s, wordDict));
    }

}
