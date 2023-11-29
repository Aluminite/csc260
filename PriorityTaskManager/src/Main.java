import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);

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
            System.out.println(TaskStorage.todo);
            if (TaskStorage.todo.size() >= 5) {
                while (!TaskStorage.todo.isEmpty()) {
                    System.out.println(TaskStorage.todo.poll());
                }
            }
        }
    }
}