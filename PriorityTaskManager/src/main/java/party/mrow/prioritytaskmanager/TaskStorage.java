package party.mrow.prioritytaskmanager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskStorage implements Serializable {
    public PriorityQueue<Task> todo = new PriorityQueue<>(Comparator.reverseOrder());

    public Task[] todoToArraySorted() {
        Task[] tasks = todo.toArray(new Task[0]);
        Arrays.sort(tasks, Comparator.reverseOrder());
        return tasks;
    }
}
