import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>>  {
    public Node<T> root;

    public static class Node<T> {
        public Node<T> left;
        public Node<T> right;
        public T data;

        public Node(Node<T> left, Node<T> right, T data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        public Node(T data) {
            this.data = data;
        }
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> arrayList = new ArrayList<>();
        addInOrder(root, arrayList);
        return arrayList;
    }

    private static <T> void addInOrder(Node<T> node, ArrayList<T> arrayList) {
        if (node.left != null) {
            addInOrder(node.left, arrayList);
        }
        arrayList.add(node.data);
        if (node.right != null) {
            addInOrder(node.right, arrayList);
        }
    }

    public ArrayList<T> preOrder() {
        ArrayList<T> arrayList = new ArrayList<>();
        addPreOrder(root, arrayList);
        return arrayList;
    }

    private static <T> void addPreOrder(Node<T> node, ArrayList<T> arrayList) {
        arrayList.add(node.data);
        if (node.left != null) {
            addPreOrder(node.left, arrayList);
        }
        if (node.right != null) {
            addPreOrder(node.right, arrayList);
        }
    }

    public ArrayList<T> postOrder() {
        ArrayList<T> arrayList = new ArrayList<>();
        addPostOrder(root, arrayList);
        return arrayList;
    }

    private static <T> void addPostOrder(Node<T> node, ArrayList<T> arrayList) {
        if (node.left != null) {
            addPostOrder(node.left, arrayList);
        }
        if (node.right != null) {
            addPostOrder(node.right, arrayList);
        }
        arrayList.add(node.data);
    }

    public Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            node = new Node<>(data);
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) >= 0) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public Node<T> insert(T data) {
        return insert(root, data);
    }

    public Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return node;
        }
        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) >= 0) {
            node.right = delete(node.right, data);
        }

    }
}
