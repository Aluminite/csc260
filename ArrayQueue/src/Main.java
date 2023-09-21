import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Size of queue to create?");
        ArrayQueue queue = new ArrayQueue(Integer.parseInt(input.nextLine()));

        while (true) {
            System.out.println("e to enqueue, d to dequeue, s for size");
            switch (input.nextLine()) {
                case "e" -> {
                    System.out.println("Number to enqueue?");
                    System.out.println("Enqueued " + queue.enqueue(Integer.parseInt(input.nextLine())));
                }
                case "d" -> System.out.println("Dequeued item, got " + queue.dequeue());
                case "s" -> System.out.println("Size of queue is " + queue.size());
            }
        }
    }
}
