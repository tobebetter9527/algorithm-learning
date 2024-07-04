package com.freedom.zuo.class38;

/**
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * <p>
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * <p>
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * <p>
 * 1，4，16，64…(4的某次方)
 * <p>
 * 谁最先把草吃完，谁获胜
 * <p>
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * <p>
 * 根据唯一的参数N，返回谁会赢
 *
 * @author tobebetter9527
 * @create 2022/08/16 20:21
 */
public class Code02_EatGrass {

    /**
     * 如果n份草，最终先手赢，返回"先手"
     * <p>
     * 如果n份草，最终后手赢，返回"后手"
     */
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }

        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            if (want * 4 <= n) {
                want *= 4;
            } else {
                break;
            }
        }

        return "后手";
    }

    public static String whoWin1(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        }
        return "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //System.out.println(i + "; " + whoWin(i));
            if (!whoWin(i).equals(whoWin1(i))) {
                System.out.println("wrong");
            }
        }
    }

}
