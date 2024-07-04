package com.freedom.zuo.class19_dynamic_programming2;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/stickers-to-spell-word/
 *
 * @author tobebetter9527
 * @create 2022/07/16 16:25
 */
public class Code03_StickersToSpellWord {

    /**
     * 暴力递归
     *
     * @param stickers
     * @param target
     * @return
     */
    public int minStickers(String[] stickers, String target) {
        int min = process1(stickers, target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int process1(String[] stickers, String restTarget) {
        if (restTarget.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            // 剪掉出现的字符后，还剩下的字符
            String rest = minus(first, restTarget);
            if (rest.length() != restTarget.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        // min == Integer.MAX_VALUE ? 0 : 1 表示有没有匹配上字符
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private String minus(String first, String restTarget) {
        int[] arr = new int[26];

        for (char c : restTarget.toCharArray()) {
            arr[c - 'a']++;
        }

        for (char c : first.toCharArray()) {
            arr[c - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }

        return sb.toString();
    }

    // ----------------------------------------------------------- //

    public int minStickers2(String[] stickers, String target) {
        int length = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[length][26];
        for (int i = 0; i < length; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c - 'a']++;
            }
        }

        int min = process2(counts, target);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int process2(int[][] stickers, String restTarget) {
        if (restTarget.length() == 0) {
            return 0;
        }

        char[] target = restTarget.toCharArray();
        int[] targetSticker = new int[26];
        for (char c : target) {
            targetSticker[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    int num = targetSticker[i] - sticker[i];
                    for (int j = 0; j < num; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
                min = Math.min(min, process2(stickers, sb.toString()));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // ----------------------------------------------------------- //

    public int minStickers3(String[] stickers, String target) {
        int length = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[length][26];
        for (int i = 0; i < length; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c - 'a']++;
            }
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("", 0);

        int min = process3(counts, target, map);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int process3(int[][] stickers, String restTarget, Map<String, Integer> map) {
        if (map.get(restTarget) != null) {
            return map.get(restTarget);
        }

        char[] target = restTarget.toCharArray();
        int[] targetSticker = new int[26];
        for (char c : target) {
            targetSticker[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    int num = targetSticker[i] - sticker[i];
                    for (int j = 0; j < num; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
                min = Math.min(min, process3(stickers, sb.toString(), map));
            }
        }

        min = min + (min == Integer.MAX_VALUE ? 0 : 1);
        map.put(restTarget, min);

        return min;
    }

}
