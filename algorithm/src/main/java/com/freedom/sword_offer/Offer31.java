package com.freedom.sword_offer;

import java.util.*;

import static com.freedom.zuo.class20_dynamic_programming3.Code03_Coffee.printArray;

public class Offer31 {

    private static List<int[]> list;
    private static int[] popped;


    public static void main(String[] args) {
        int lenth = 5;
        // 1. 进栈顺序
        int[] pushed = new int[lenth];
        for (int i = 1; i <= lenth; i++) {
            pushed[i - 1] = i;
        }

        // 2. 计算阶乘
        int num = calculate2(lenth);
        System.out.println(num);

        // 3. 初始化
        list = new ArrayList<>(num);
        popped = new int[lenth];

        // 4.可能的弹栈顺序
        handle(pushed, 0);
        System.out.println(list);

        // 5.验证对错
        for (int[] ints : list) {
            boolean flag = validateStackSequences(pushed, ints);
            boolean flag2 = validateStackSequences2(pushed, ints);
            if (!flag && flag == flag2) {
                printArray(ints);
            }
        }

    }

    private static void handle(int[] pushed, int idx) {
        if (idx == pushed.length) {
            int[] arr = copyArray(popped);
            list.add(arr);
        }

        for (int i = 0; i < pushed.length; i++) {
            if (pushed[i] != -1) {
                popped[idx] = pushed[i];
                pushed[i] = -1;
                handle(pushed, idx + 1);
                pushed[i] = popped[idx];
            }
        }
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private static int calculate2(int n) {
        int dp = 1;
        for (int i = 2; i <= n; i++) {
            dp = dp * i;
        }
        return dp;
    }

    /**
     * time complexity is O(n^2),space complexity is O(n)
     *
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length < 3) {
            return true;
        }
        int length = pushed.length;
        Map<Integer, Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            map.put(pushed[i], i);
        }

        for (int i = 1; i < length - 1; i++) {
            if (map.get(popped[i - 1]) > map.get(popped[i])) {
                int j = i + 1;
                boolean flag = false;
                while (j < length) {
                    if (map.get(popped[i - 1]) > map.get(popped[j]) && map.get(popped[i]) < map.get(popped[j])) {
                        flag = true;
                        break;
                    }
                    j++;
                }
                if (flag) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (popped.length < 3) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
