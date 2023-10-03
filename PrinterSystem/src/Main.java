import java.util.Scanner;

public class Main {
    private static final DocumentQueue queue = new DocumentQueue();
    private static final Scanner input = new Scanner(System.in);

    private static Document createDocument() {
        System.out.print("Name of the document? ");
        String name = input.nextLine();
        System.out.print("Number of pages? ");
        int numPages = Integer.parseInt(input.nextLine());

        return new Document(name, numPages);
    }

    public static void main(String[] args) {
        System.out.println("Print Queue System");
        while (true) {
            System.out.println("""
                    
                    What would you like to do?
                    a to add a document
                    v to view the document at the front of the queue
                    p to print the next document
                    s to view the queue's size
                    e to exit""");
            switch (input.nextLine().toLowerCase()) {
                case "a" -> queue.addDocument(createDocument());
                case "v" -> System.out.println(queue.viewNext());
                case "p" -> System.out.println(queue.print());
                case "s" -> System.out.printf("There are %d document(s) in the queue.%n", queue.size());
                case "e" -> System.exit(0);
            }
        }
    }
}