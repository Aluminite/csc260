import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueStuff {
    public static <T> Queue<T> reverseQueue(Queue<T> inputQueue, int numReversed) {
        if (numReversed > inputQueue.size()) throw new RuntimeException("numReversed is greater than the queue length");
        int originalSize = inputQueue.size();
        Queue<T> tempQueue = new LinkedList<>();
        Stack<T> reverseStack = new Stack<>();

        // put the items of inputQueue into a temporary queue and stack
        for (int i = 0; i < originalSize - numReversed; i++) {
            reverseStack.push(inputQueue.remove());
        }
        while (!inputQueue.isEmpty()) {
            tempQueue.add(inputQueue.remove());
        }

        // move the items back into the queue in reversed order
        while (!reverseStack.isEmpty()) {
            inputQueue.add(reverseStack.pop());
        }
        while (!tempQueue.isEmpty()) {
            inputQueue.add(tempQueue.remove());
        }

        return inputQueue;
    }

    public static void printBinary(int max) {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        for (int i = 0; i < max; i++) {
            String current = queue.remove();
            System.out.println(current);
            queue.add(current + "0");
            queue.add(current + "1");
        }
    }

    public static <T> Queue<T> interleaveQueue(Queue<T> inputQueue) {
        if (inputQueue.size() % 2 != 0) throw new RuntimeException("Queue length must be even");
        int originalSize = inputQueue.size();
        Queue<T> firstHalf = new LinkedList<>();
        Queue<T> secondHalf = new LinkedList<>();
        for (int i = 0; i < originalSize / 2; i++) {
            secondHalf.add(inputQueue.remove());
        }
        for (int i = 0; i < originalSize / 2; i++) {
            firstHalf.add(inputQueue.remove());
        }

        while (!firstHalf.isEmpty() && !secondHalf.isEmpty()) {
            inputQueue.add(secondHalf.remove());
            inputQueue.add(firstHalf.remove());
        }
        return inputQueue;
    }

}
