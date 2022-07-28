import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static class Node {
        private String name;
        private Node left;
        private Node right;
        
        public Node(String name, Main.Node left, Main.Node right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
        
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
    }
    
    public static void preorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.name);
        preorder(node.left);
        preorder(node.right);
    }
    
    public static void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.name);
        inorder(node.right);
    }
    
    public static void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.name);
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<String, Node> tree = new HashMap<>();
        // 트리 구성
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            if (!tree.containsKey(name))
                tree.put(name, new Node(name, null, null));
            Node node = tree.get(name);
            String lname = sc.next();
            String rname = sc.next();
            if (!lname.equals(".")) {
                Node leftNode = new Node(lname, null, null);
                tree.put(lname, leftNode);
                node.setLeft(leftNode);
            }
            if (!rname.equals(".")) {
                Node rightNode = new Node(rname, null, null);
                tree.put(rname, rightNode);
                node.setRight(rightNode);
            }
        }
//        System.out.println(Arrays.toString(tree));
        // 전위 순회
        preorder(tree.get("A"));
        System.out.println();
        inorder(tree.get("A"));
        System.out.println();
        postorder(tree.get("A"));
    }
}
