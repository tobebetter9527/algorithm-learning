package com.freedom.leetcode.string;

/**
 * 151. Reverse Words in a String
 *
 * @author tobebetter9527
 * @create 2022/10/23 10:43
 */
public class Problem151_ReverseWordsInaString {

    /**
     * time complexity is O(n), space complexity is O(n).
     * <p>
     * 双指针做法
     */
    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] oldArr = s.toCharArray();
        char[] newArr = new char[oldArr.length + 1];
        int size = 0;

        // start,end表示单词的起始和终点索引
        int end;
        // 从后面往前遍历
        for (int start = oldArr.length - 1; start >= 0; start--) {
            // 跳过空格
            if (oldArr[start] == ' ') {
                continue;
            } else {
                // 单词的终点索引
                end = start;
                // 找到下个空格的起点
                while (start >= 0 && oldArr[start] != ' ') {
                    start--;
                }
                // 单词的索引start + 1， end。
                // 这里start要加1，因为上面的start位置是空格
                for (int i = start + 1; i <= end; i++) {
                    newArr[size++] = oldArr[i];
                }
                // 单词之间加空格
                newArr[size++] = ' ';
            }
        }
        // 去掉最后一个空格
        return String.valueOf(newArr, 0, size - 1);
    }

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }

}
