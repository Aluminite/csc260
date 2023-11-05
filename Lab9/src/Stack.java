import java.util.EmptyStackException;

public class Stack<T> {
    private Object[] data = new Object[50];
    private int pointer = 0;

    private void grow() {
        Object[] dataNew = new Object[data.length * 2];
        System.arraycopy(data, 0, dataNew, 0, data.length);
        data = dataNew;
    }

    public T push(T item) {
        if (pointer == data.length) {
            grow();
        }

        data[pointer] = item;
        pointer++;
        return item;
    }

    public T pop() {
        if (pointer == 0) throw new EmptyStackException();
        T item = peek();
        pointer--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) data[pointer - 1];
    }

    public boolean empty() {
        return pointer == 0;
    }
}
