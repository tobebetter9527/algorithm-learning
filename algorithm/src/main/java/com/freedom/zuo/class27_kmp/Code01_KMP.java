package com.freedom.zuo.class27_kmp;

/**
 * KMP算法
 *
 * @author tobebetter9527
 * @create 2022/07/31 15:44
 */
public class Code01_KMP {

    /**
     * kmp算法
     *
     * @param str   主串
     * @param match 待匹配字符串
     * @return
     */
    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() == 0 || str.length() < match.length()) {
            return -1;
        }

        int[] nexts = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str.length() && y < match.length()) {
            // 匹配上，同时都++往前推
            if (str.charAt(x) == match.charAt(y)) {
                x++;
                y++;
                // 没有匹配，y的值要后退，-1，表明当前x位置和match的头都不匹配，x要往前走
            } else if (nexts[y] == -1) {
                x++;
            } else {
                y = nexts[y];
            }
        }

        // y == match.length() 匹配上了
        return y == match.length() ? x - y : -1;
    }

    /**
     * 每个位置的前缀和后缀最大的共同长度，比如123123d,d的前缀123，后缀123，则d位置值为3.
     * <p>
     * aaaaak, k位置的值为4，不能超过5.
     *
     * @param match
     */
    private static int[] getNextArray(String match) {
        if (match.length() < 2) {
            return new int[]{-1};
        }

        int[] nexts = new int[match.length()];
        nexts[0] = -1;
        nexts[1] = 0;

        int index = 2;
        int cn = 0;
        while (index < match.length()) {
            if (match.charAt(index - 1) == match.charAt(cn)) {
                nexts[index++] = ++cn;
            } else if (cn > 0) {
                cn = nexts[cn];
            } else {
                nexts[index++] = 0;
            }
        }

        return nexts;
    }

    // ------------------------------------------------------------ //


    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

//  public static void main(String[] args) {
//    int possibilities = 5;
//    int strSize = 20;
//    int matchSize = 5;
//    int testTimes = 5000000;
//    System.out.println("test begin");
//    for (int i = 0; i < testTimes; i++) {
//      String str = getRandomString(possibilities, strSize);
//      String match = getRandomString(possibilities, matchSize);
//      if (getIndexOf(str, match) != str.indexOf(match)) {
//        System.out.println("Oops!");
//      }
//    }
//    System.out.println("test finish");
//  }

    public static void main(String[] args) {
        String str = "aba";
        int[] nextArray = getNextArray(str);
        System.out.println(nextArray);
    }

}
