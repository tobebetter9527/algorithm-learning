package com.freedom.wangzheng;

/**
 * KMP
 *
 * @author tobebetter9527
 * @create 2022/09/27 22:37
 */
public class Class34_KMP {

    /**
     * @param str     主串
     * @param pattern 模式串
     * @return 第一个匹配的下标，如果没有匹配返回-1
     */
    public static int kmp(String str, String pattern) {
        if (str == null || pattern == null) {
            return -1;
        }
        return kmp(str.toCharArray(), str.length(), pattern.toCharArray(), pattern.length());
    }


    private static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNext(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }

            if (a[i] == b[j]) {
                j++;
            }

            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    private static int[] getNext(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;

        int k = -1;
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }

            if (b[k + 1] == b[i]) {
                k++;
            }

            next[i] = k;
        }

        return next;
    }


    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

//  public static void main(String[] args) {
//    int possibilities = 5;
//    int strSize = 10;
//    int matchSize = 5;
//    int testTimes = 10000000;
//    System.out.println("test begin");
//    for (int i = 0; i < testTimes; i++) {
//      String str = getRandomString(possibilities, strSize);
//      String match = getRandomString(possibilities, matchSize);
//      if (kmp(str, match) != str.indexOf(match)) {
//        System.out.println("Oops!" + str + " " + match);
//      }
//    }
//    System.out.println("test finish");
//  }

    public static void main(String[] args) {
        int kmp = kmp("", "");
        System.out.println(kmp);
    }

}
