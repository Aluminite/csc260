public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new BinaryTree.Node<>(50);
        tree.root.left = new BinaryTree.Node<>(30);
        tree.root.left.left = new BinaryTree.Node<>(10);
        tree.root.left.right = new BinaryTree.Node<>(15);
        tree.root.right = new BinaryTree.Node<>(20);
        tree.root.right.left = new BinaryTree.Node<>(3);
        tree.root.right.right = new BinaryTree.Node<>(2);

        System.out.println(tree.inOrder());
        System.out.println(tree.preOrder());
        System.out.println(tree.postOrder());
    }
}