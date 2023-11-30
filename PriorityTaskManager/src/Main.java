import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    private static String arrToString(Object[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Object o : arr) {
            sb.append(o);
            sb.append(", ");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    private static Task[] pqToArraySorted(PriorityQueue<Task> pq) {
        Task[] tasks = pq.toArray(new Task[0]);
        Arrays.sort(tasks, Comparator.reverseOrder());
        return tasks;
    }

    private static Task createTask() {
        System.out.println("Task info?");
        String info = input.nextLine();

        System.out.print("Priority? ");
        for (int i = 0; i < Priority.values().length; i++) {
            System.out.printf("%d for %s ", i, Priority.values()[i]);
        }
        System.out.println();
        Priority priority = Priority.values()[Integer.parseInt(input.nextLine())];

        return new Task(info, priority);
    }

    public static void main(String[] args) {
        while (true) {
            TaskStorage.todo.add(createTask());
            System.out.println(arrToString(pqToArraySorted(TaskStorage.todo)));
            if (TaskStorage.todo.size() >= 5) {
                while (!TaskStorage.todo.isEmpty()) {
                    System.out.println(TaskStorage.todo.poll());
                }
            }
        }
    }
}