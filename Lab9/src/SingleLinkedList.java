public class SingleLinkedList<T> {
    public static class Node<T> {
        public Node<T> next;
        public T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    public Node<T> head = null;

    public void add(int index, T data) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
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
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
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

    public void reverse() {
        if (head == null) return;

        Stack<SingleLinkedList.Node<T>> stack = new Stack<>();
        SingleLinkedList.Node<T> node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        head = stack.pop();
        node = head;
        while (!stack.empty()) {
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;
    }

    public String toString() {
        if (head == null) return "";

        StringBuilder output = new StringBuilder();
        Node<T> node = head;
        while (node != null) {
            output.append(node.data);
            output.append(' ');
            node = node.next;
        }

        return output.substring(0, output.length() - 1); // removes the extra space at the end
    }
}
