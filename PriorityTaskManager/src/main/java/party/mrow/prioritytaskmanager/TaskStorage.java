package party.mrow.prioritytaskmanager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// The main priority queue needs to be encapsulated in another object
// due to some limitations in Java with deserializing an instance of a generic class.
public class TaskStorage implements Serializable {
    public PriorityQueue<Task> todo = new PriorityQueue<>(Comparator.reverseOrder());

    private int currentSequenceID = 0;

    // Returns the next sequence ID for when a Task is being created.
    // This method should only be used when a new Task is made, and only once.
    public int nextSequenceID() {
        return currentSequenceID++;
    }

    public Task[] todoToArraySorted() {
        Task[] tasks = todo.toArray(new Task[0]);
        Arrays.sort(tasks, Comparator.reverseOrder());
        return tasks;
    }
}
