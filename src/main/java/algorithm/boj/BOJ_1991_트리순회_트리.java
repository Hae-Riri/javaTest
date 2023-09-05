package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1991_트리순회_트리 {
    public static void main(String[] args) throws IOException {
        int n;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        Tree tree = new Tree();
        tree.root = new Node("A");

        for(int i = 0; i < n; i++) {
            String[] splits = br.readLine().split(" ");
            String data = splits[0];
            String leftData = splits[1];
            String rightData = splits[2];

            tree.insertNode(tree.root, data, leftData, rightData);
        }

        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }
}

class Tree {
    Node root;

    public void insertNode(Node currentNode, String data, String leftData, String rightData) {
        if(currentNode == null) { // 대신 null 처리는 앞에서 먼저 해버리기. 리프노드인데 여기까지 안 찾아졌으면 그냥 return 할 수 있게.
            return;
        }
        if(currentNode.data.equals(data)) {
            currentNode.left = leftData.equals(".") ? null : new Node(leftData);
            currentNode.right = rightData.equals(".") ? null : new Node(rightData);
        } else { // 이번 노드 아니야? 그럼 왼쪽 오른쪽 탐색해서 찾아질 때 넣어.
            insertNode(currentNode.left, data, leftData, rightData);
            insertNode(currentNode.right, data, leftData, rightData);
        }
    }

    public void preOrder(Node node) {
        if(node != null) {
            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(Node node) {
        if(node != null) {
            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if(node!=null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data);
        }
    }
}

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
    }
}
