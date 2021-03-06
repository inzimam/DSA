package com.inzy.ds.binarytree;


public class BinaryTree {
    private Node root;
    private Node targetLeaf;
    private int btHeight;
    private int maxSum;
    private int ind;

    public BinaryTree() {
        root = null;
        targetLeaf = null;
        btHeight = Integer.MIN_VALUE;
        maxSum = Integer.MIN_VALUE;
        ind = 0;
    }

    void add(int val) {
        root = addRecursive(val, root);
    }

    private Node addRecursive(int data, Node node) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = addRecursive(data, node.left);
        else node.right = addRecursive(data, node.right);
        return node;
    }

    void delete(int val) {
        if (root == null)
            return;
        deleteRecursive(root, val);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null)
            return current;
        if (value == current.data) {

        }
        if (value < current.data) {
            current.left = deleteRecursive(current.left, value);
            return current;
        } else {
            current.right = deleteRecursive(current.right, value);
            return current;
        }
    }

    void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print("[" + node.data + "] ");
            traverseInOrder(node.right);
        }
    }

    void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.println(node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    private boolean printBinaryPath(Node node, Node targetLeaf) {

        if (node == null)
            return false;

        if (node == targetLeaf || printBinaryPath(node.left, targetLeaf) || printBinaryPath(node.right, targetLeaf)) {
            System.out.print(node.data + " ");
            return true;
        }

        return false;
    }


    private void getMaxSumRec(Node node, int currSum) {
        if (node == null) return;
        currSum = currSum + node.data;
        if (node.left == null && node.right == null) {
            if (currSum > maxSum) {
                maxSum = currSum;
                targetLeaf = node;
            }
        }
        getMaxSumRec(node.left, currSum);
        getMaxSumRec(node.right, currSum);
    }

    int maxSum() {
        if (root == null)
            return 0;
        getMaxSumRec(root, 0);
        printBinaryPath(root, targetLeaf);
        return maxSum;
    }

    private void getHeight(Node node, int currHeight) {
        if (node == null) {
            return;
        }

        currHeight = currHeight + 1;
        if (node.left == null && node.right == null) {
            if (currHeight > btHeight) {
                btHeight = currHeight;
            }
        }
        getHeight(node.left, currHeight);
        getHeight(node.right, currHeight);
    }


    int height() {
        if (root == null) {
            return 0;
        }
        getHeight(root, 0);
        return btHeight;
    }

    private Node make(int[] pre, char[] preLN, int n) {
        if (n == ind) {
            return null;
        }
        Node node = new Node(pre[ind]);
        ind++;
        if (ind >= n)
            return node;
        if (preLN[ind - 1] == 'N') {
            node.left = make(pre, preLN, n);
            node.right = make(pre, preLN, n);
        }
        return node;
    }

    void constructTree(int n, int[] pre, char[] preLN) {
        root = make(pre, preLN, n);
    }

    void printNodes(Node node, int curr, int k) {
        if (node == null)
            return;
        curr++;
        if (curr == k) {
            System.out.print(node.data + " ");
        }
        printNodes(node.left, curr, k);
        printNodes(node.right, curr, k);
    }

    int countBstNodesThatLieInAGivenRange(int l, int h) {
        if (root == null)
            return 0;
        return countNodes(root, l, h);
    }


    private int countNodes(Node node, int m, int n) {
        if (node == null)
            return 0;
        int count = 0;
        if (node.data >= m && node.data <= n) {
            count++;
        }
        return count + countNodes(node.left, m, n) + countNodes(node.right, m, n);
    }

    void printNodesKDistanceFromRoot(int k) {
        System.out.print("printNodesKDistanceFromRoot: ");
        printNodes(root, 0, k);
        System.out.println("");
    }
}
