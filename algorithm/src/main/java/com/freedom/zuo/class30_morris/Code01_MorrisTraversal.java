package com.freedom.zuo.class30_morris;

/**
 * morris traversal
 *
 * @author tobebetter9527
 * @create 2022/08/06 8:28
 */
public class Code01_MorrisTraversal {

  /**
   * 遍历的形式
   *
   * @param root
   */
  public static void traversal(Node root) {
    if (root == null) {
      return;
    }
    // 1 前序遍历
    // System.out.print(root.value + " ");
    traversal(root.left);
    // 2 中序遍历
    // System.out.print(root.value + " ");
    traversal(root.right);
    // 3 后序遍历
    System.out.print(root.value + " ");
  }

  // ------------------------------------------------------------------------ //

  /**
   * morris遍历
   *
   * @param head
   */
  public static void morris(Node head) {
    if (head == null) {
      return;
    }

    Node cur = head;
    Node mostRight;
    while (cur != null) {
      // morris遍历
      // System.out.print(cur.value + " ");
      // 先找左子树最右节点
      mostRight = cur.left;
      if (mostRight != null) {
        // mostRight.right != cur 说明是第一次遍历
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        // 最右节点关联cur，目的是利用其节省空间，以及回头可以遍历回来cur
        if (mostRight.right == null) {
          // 关联
          mostRight.right = cur;
          // 移动到左子树
          cur = cur.left;
          continue;
        } else {
          // 第二次遍历，恢复原状
          mostRight.right = null;
        }
      }

      cur = cur.right;
    }
  }

  private static Node generateBinaryTree(int maxDepth, int maxValue) {
    if (Math.random() > 0.7D || maxDepth < 0) {
      return null;
    }
    Node head = new Node(generateValue(maxValue));
    head.left = generateBinaryTree(maxDepth - 1, maxValue);
    head.right = generateBinaryTree(maxDepth - 1, maxValue);
    return head;
  }

  private static int generateValue(int maxValue) {
    return (int) (Math.random() * maxValue);
  }
  // ------------------------------------------------------------------------ //

  /**
   * 前序遍历,第二次回到节点时不打印
   *
   * @param head
   */
  public static void morrisPre(Node head) {
    if (head == null) {
      return;
    }

    Node cur = head;
    Node mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          System.out.print(cur.value + " ");
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }
      } else {
        System.out.print(cur.value + " ");
      }

      cur = cur.right;
    }
  }

  // ------------------------------------------------------------------------ //

  /**
   * 中序遍历,第一次出现的不打印
   *
   * @param head
   */
  public static void morrisIn(Node head) {
    if (head == null) {
      return;
    }

    Node cur = head;
    Node mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          // 第一种
          // System.out.print(cur.value + " ");
          mostRight.right = null;
        }
      }
      // 第一种
      //      else {
      //        System.out.print(cur.value + " ");
      //      }

      // 第二种
      System.out.print(cur.value + " ");

      cur = cur.right;
    }
  }

  // ------------------------------------------------------------------------ //


  /**
   * 后序遍历,第二次右子树逆序打印
   *
   * @param head
   */
  public static void morrisPost(Node head) {
    if (head == null) {
      return;
    }

    Node cur = head;
    Node mostRight;
    while (cur != null) {
      // System.out.print(cur.value + " ");
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
          printEdge(cur.left);
        }
      }
      cur = cur.right;
    }
    printEdge(head);
  }

  private static void printEdge(Node head) {
    Node tail = reverse(head);

    Node cur = tail;
    while (cur != null) {
      System.out.print(cur.value + " ");
      cur = cur.right;
    }

    reverse(tail);
  }

  private static Node reverse(Node head) {
    Node pre = null;
    Node temp;
    Node cur = head;
    while (cur != null) {
      temp = cur.right;
      cur.right = pre;
      pre = cur;
      cur = temp;
    }
    return pre;
  }

  // ------------------------------------------------------------------------ //

  /**
   * 判断是否位二叉搜索树
   *
   * @param head
   * @return
   */
  public static boolean isBST(Node head) {
    if (head == null) {
      return true;
    }
    Integer preValue = null;
    boolean ans = true;
    Node cur = head;
    Node mostRight;
    while (cur != null) {
      mostRight = cur.left;
      if (mostRight != null) {
        while (mostRight.right != null && mostRight.right != cur) {
          mostRight = mostRight.right;
        }

        if (mostRight.right == null) {
          mostRight.right = cur;
          cur = cur.left;
          continue;
        } else {
          mostRight.right = null;
        }
      }

      if (preValue != null && preValue >= cur.value) {
        ans = false;
      }
      preValue = cur.value;

      cur = cur.right;
    }

    return ans;
  }

  // ------------------------------------------------------------------------ //

  public static void main(String[] args) {
    //    Node head = generateBinaryTree(3, 50);
    //    morris(head);

    Node head = new Node(10);
    head.left = new Node(11);
    head.right = new Node(21);
    head.left.left = new Node(12);
    head.left.right = new Node(13);
    head.right.left = new Node(22);
    head.right.right = new Node(23);

    traversal(head);
    System.out.println("");
    morrisPost(head);
    System.out.println("");
  }

  // ------------------------------------------------------------------------ //

  static class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }

    public Node(Node right) {
      this.right = right;
    }
  }
}
