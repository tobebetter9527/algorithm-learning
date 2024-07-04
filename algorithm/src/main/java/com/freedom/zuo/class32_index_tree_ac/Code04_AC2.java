package com.freedom.zuo.class32_index_tree_ac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 *
 * @author tobebetter9527
 * @create 2022/08/08 21:01
 */
public class Code04_AC2 {

    public static void main(String[] args) {
        AcAutomation ac = new AcAutomation();
        ac.insert("abcd");
        ac.insert("bc");
        ac.insert("c");
        ac.insert("d");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcxbcdabcdabcd");
        for (String word : contains) {
            System.out.println(word);
        }
    }

    static class Node {

        /**
         * end为空，不是结尾，不为空，当前的字符串就保存在end
         */
        public String end;
        /**
         * end不为空，这个才有意义；表示字符串之前有没有加入过答案
         */
        public boolean endUse;
        /**
         * fail指针
         */
        public Node fail;
        /**
         * 当前节点的子节点
         */
        public Node[] nexts;

        public Node() {
            end = null;
            endUse = false;
            fail = null;
            nexts = new Node[26];
        }
    }

    static class AcAutomation {

        /**
         * 根节点，其fail指针为null
         */
        public Node root;

        public AcAutomation() {
            this.root = new Node();
        }

        /**
         * 插入字符串，构建trie树
         *
         * @param str
         */
        public void insert(String str) {
            if (str == null || str.length() == 0) {
                return;
            }

            int index;
            Node cur = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = str;
        }

        /**
         * 构建trie树的节点的fail指针
         * <p>
         * 广度优先遍历，需要用到队列
         */
        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            // 当前节点
            Node cur;
            // 当前节点的fail指针
            Node curFail;
            while (!queue.isEmpty()) {
                // 弹出当前节点
                cur = queue.poll();
                // 当前节点的子节点
                for (int i = 0; i < 26; i++) {
                    // 如果子节点不为空
                    if (cur.nexts[i] != null) {
                        // 子节点fail先设置为root
                        cur.nexts[i].fail = root;
                        // 当前节点的fail指针
                        curFail = cur.fail;
                        // 如果fail指针不为空
                        while (curFail != null) {
                            // 如果fail指针有子节点，就设置当前节点的子节点的fail为fail指针的子节点，并且break
                            if (curFail.nexts[i] != null) {
                                cur.nexts[i].fail = curFail.nexts[i];
                                break;
                            }
                            // 如果fail指针没有子节点，就继续往上找
                            curFail = curFail.fail;
                        }
                        // 广度遍历优先
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        /**
         * 大文章是否有敏感字
         *
         * @param content 大文章
         * @return
         */
        public List<String> containWords(String content) {
            List<String> ans = new ArrayList<>();

            Node cur = root;
            Node follow;
            int index;
            char[] chars = content.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';

                // 如果当前字符在这条路上没配出来，就随着fail方向走向下条路径
                // 减少匹配的工作量
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }

                // 1) 现在来到的路径，是可以继续匹配的
                // 2) 现在来到的节点，就是前缀树的根节点
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }
                    // 不同的需求，在这一段之间修改
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    // 不同的需求，在这一段之间修改
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

}
