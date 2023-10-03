import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class DocumentQueue {
    private final Queue<Document> docQueue;

    public DocumentQueue() {
        docQueue = new LinkedList<>();
    }

    public void addDocument(Document doc) {
        docQueue.add(doc);
    }

    public Document viewNext() {
        return docQueue.peek();
    }

    public int size() {
        return docQueue.size();
    }

    public String print() {
        try {
            return "Successfully printed " + docQueue.remove();
        } catch (NoSuchElementException unused) {
            return "The print queue is empty";
        }
    }
}
