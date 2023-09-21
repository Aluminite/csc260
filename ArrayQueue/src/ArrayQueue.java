public class ArrayQueue {
    private final int[] data;
    private int front = 0;
    private int back = 0;
    private int size = 0;

    public ArrayQueue(int size) {
        data = new int[size];
    }

    public int enqueue(int item) {
        if (isFull()) {
            throw new IllegalArgumentException("Queue is full");
        }

        data[front] = item;
        front = (front + 1) % data.length;
        size++;
        return item;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int index = back;

        back = (back + 1) % data.length;
        size--;
        return data[index];
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
