public class SingleLinkedList<T> {
    private static class Node<T> {
        public Node<T> next;
        public T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private Node<T> head = null;

    public void add(int index, T data) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index == 0) {
            Node<T> oldHead = head;
            head = new Node<>(oldHead, data);
        } else {
            Node<T> spliceNode = head; // The node after this will be replaced by a new node
            for (int i = 0; i < index - 1; i++) {
                spliceNode = spliceNode.next;
            }

            Node<T> oldNext = spliceNode.next;
            spliceNode.next = new Node<>(oldNext, data);
        }
    }

    public void add(T item) {
        add(size(), item);
    }

    private Node<T> getNode(int index) {
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void set(int index, T data) {
        getNode(index).data = data;
    }

    public void remove(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> spliceNode = head; // The node after this will be removed
            for (int i = 0; i < index - 1; i++) {
                spliceNode = spliceNode.next;
            }

            spliceNode.next = spliceNode.next.next;
        }
    }

    public int size() {
        if (head == null) return 0;
        int counter = 1;
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            counter++;
        }

        return counter;
    }
}
