import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void printIntQueue(Queue<Integer> queue) {
        for (int item : queue) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Generate binary numbers up to what value?");
        QueueStuff.printBinary(Integer.parseInt(input.nextLine()));

        System.out.println("Creating a queue, type one int per line then any non-int when done");
        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            try {
                queue.add(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException unused) {
                break;
            }
        }

        System.out.print("Queue interleaved: ");
        printIntQueue(QueueStuff.interleaveQueue(new LinkedList<>(queue)));

        System.out.println("Reverse up to what index in the queue?");
        printIntQueue(QueueStuff.reverseQueue(queue, Integer.parseInt(input.nextLine())));
    }
}
