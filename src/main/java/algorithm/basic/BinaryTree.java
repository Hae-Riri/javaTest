package algorithm.basic;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    // 전위순회 (루트 - 좌 - 우)
    public void preorderTraversal(Node node) {
        if(node != null) {
            System.out.println(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // 중위순회 (좌 - 루트 - 우)
    public void inorderTraversal(Node node) {
        if(node != null) {
            inorderTraversal(node.left);
            System.out.println(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // 후위순회 (좌 - 우 - 루트)
    public void postorderTraversal(Node node) {
        if(node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.println(node.data);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("전위순회");
        tree.preorderTraversal(tree.root);

        System.out.println("중위순회");
        tree.inorderTraversal(tree.root);

        System.out.println("후위순회");
        tree.postorderTraversal(tree.root);
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
