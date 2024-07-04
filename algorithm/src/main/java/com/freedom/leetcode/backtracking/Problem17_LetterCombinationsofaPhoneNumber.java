package com.freedom.leetcode.backtracking;

import java.util.*;

/**
 * 17. Letter Combinations of a Phone Number
 *
 * @author tobebetter9527
 * @create 2022/11/14 22:21
 */
public class Problem17_LetterCombinationsofaPhoneNumber {

    List<String> list = new LinkedList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] arr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        handle(digits, arr, 0);
        return list;
    }

    private void handle(String digits, String[] arr, int index) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String str = arr[digits.charAt(index) - '0'];
        for (int j = 0; j < str.length(); j++) {
            sb.append(str.charAt(j));
            handle(digits, arr, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // ----------------------------//

    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, List<Character>> map = new HashMap<>(8);
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        char[] chars = digits.toCharArray();
        List<String> list = new LinkedList<>();
        process(chars, map, 0, "", list);
        return list;
    }

    private void process(char[] chars, Map<Character, List<Character>> map, int index, String preStr, List<String> list) {
        if (index == chars.length) {
            list.add(preStr);
            return;
        }
        List<Character> characters = map.get(chars[index]);
        for (Character c : characters) {
            process(chars, map, index + 1, preStr + c, list);
        }
    }

}
