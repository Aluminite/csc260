import java.util.Objects;

public class Task implements Comparable<Task> {
    // Human-readable information about the task.
    private String info;

    // The task's priority. Always LOW, MEDIUM, or HIGH.
    private Priority priority;

    // Used to determine which tasks were created first. Starts at 0 and counts up.
    public final int sequenceID;

    private static int sequenceNum = 0;

    public Task(String info, Priority priority) {
        this.info = Objects.requireNonNull(info, "Info cannot be null");
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null");

        this.sequenceID = sequenceNum;
        sequenceNum++;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = Objects.requireNonNull(info, "Info cannot be null");
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = Objects.requireNonNull(priority, "Priority cannot be null");
    }

    public int compareTo(Task other) {
        if (this.priority == other.priority) {
            return this.sequenceID - other.sequenceID;
        }
        return this.priority.compareTo(other.getPriority());
    }

    public String toString() {
        return String.format("Task \"%s\" with priority %s", getInfo(), getPriority());
    }
}
