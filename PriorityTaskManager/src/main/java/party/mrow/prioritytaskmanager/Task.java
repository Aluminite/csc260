package party.mrow.prioritytaskmanager;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Comparable<Task>, Serializable {

    // Human-readable information about the task.
    private String info;

    // The task's priority. Always LOW, MEDIUM, or HIGH.
    private TaskPriority priority;

    // Used to determine which tasks were created first.
    // Should start at 0 for the first task and count up.
    public final int sequenceID;

    public Task(String info, TaskPriority priority, int sequenceID) {
        this.info = Objects.requireNonNull(info, "Info cannot be null");
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null");
        this.sequenceID = sequenceID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = Objects.requireNonNull(info, "Info cannot be null");
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority taskPriority) {
        this.priority = Objects.requireNonNull(taskPriority, "Priority cannot be null");
    }

    // Compares this Task to another Task object.
    // NONE < LOW < MEDIUM < HIGH < MAXIMUM.
    // When the Priority is the same, Tasks with a lower sequenceID go first.
    public int compareTo(Task other) {
        if (this.priority == other.priority) {
            return other.sequenceID - this.sequenceID;
        }
        return this.priority.compareTo(other.priority);
    }

    public String toString() {
        return String.format("Task \"%s\" with priority %s", getInfo(), getPriority());
    }
}
