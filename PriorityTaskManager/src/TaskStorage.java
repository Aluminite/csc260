import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskStorage {
    public static PriorityQueue<Task> todo = new PriorityQueue<>(Comparator.reverseOrder());
    public static ArrayList<Task> done = new ArrayList<>();

}
