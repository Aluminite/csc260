public class DoubleLinkedList<T> {
    public static class Node<T> {
        public Node<T> next;
        public Node<T> prev;
        public T data;

        public Node(Node<T> next, Node<T> prev, T data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }

    public Node<T> head = null;
    public Node<T> tail = null;
    private int size = 0;

    public void add(int index, T data) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index > size()) throw new IndexOutOfBoundsException("Index cannot be greater than size of list");

        if (index == 0) { // Adding at beginning
            if (head == null && tail == null) { // List is empty
                head = new Node<>(null, null, data);
                tail = head;
            } else {
                head = new Node<>(head, null, data);
                if (head.next != null) head.next.prev = head;
            }
        } else if (index == size()) { // Adding to end
            tail = new Node<>(null, tail, data);
            if (tail.prev != null) tail.prev.next = tail;
        } else { // Adding in middle
            Node<T> spliceNode = getNode(index - 1); // Will be added to the node after this

            Node<T> newNode = new Node<>(spliceNode.next.next, spliceNode.next, data);
            spliceNode.next.next.prev = newNode;
            spliceNode.next.next = newNode;
        }

        size++;
    }

    public void add(T item) {
        add(size(), item);
    }

    private Node<T> getNode(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index >= size()) throw new IndexOutOfBoundsException("Index must be less than the size of the list");

        Node<T> node;
        if (index < size() / 2) { // Start from head if it's in the first half
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else { // Otherwise, start from tail (minor speed improvement)
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void set(int index, T data) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index >= size()) throw new IndexOutOfBoundsException("Index must be less than the size of the list");

        getNode(index).data = data;
    }

    public void remove(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index cannot be negative");
        if (index >= size()) throw new IndexOutOfBoundsException("Index must be less than the size of the list");

        if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size() - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> spliceNode = getNode(index - 1); // The node after this will be removed

            spliceNode.next = spliceNode.next.next;
            spliceNode.next.prev = spliceNode;
        }

        size--;
    }

    public int size() {
        return size;
    }
}
