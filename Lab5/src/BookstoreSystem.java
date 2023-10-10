import java.util.Scanner;

public class BookstoreSystem {
    private static final SingleLinkedList<Book> books = new SingleLinkedList<>();
    private static final Scanner input = new Scanner(System.in);

    private static Book createBook() {
        System.out.print("Book title? ");
        String title = input.nextLine();
        System.out.print("Author name? ");
        String author = input.nextLine();
        System.out.print("ISBN of the book? ");
        String isbn = input.nextLine();

        return new Book(title, author, isbn);
    }

    private static void removeBook() {
        printBooks();
        if (books.size() == 0) return;

        System.out.print("Number of the book to remove? ");
        int index = Integer.parseInt(input.nextLine()) - 1;
        books.remove(index);
    }

    private static void printBooks() {
        if (books.size() == 0) {
            System.out.println("There are no books.");
            return;
        }

        System.out.println("Current books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d: %s%n", i + 1, books.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Bookstore Management System");
        while (true) {
            System.out.println("""
                    
                    Choose an option:
                    a to add a book
                    r to remove a book
                    d to display all books
                    e to exit""");
            switch (input.nextLine().toLowerCase()) {
                case "a" -> books.add(createBook());
                case "r" -> removeBook();
                case "d" -> printBooks();
                case "e" -> System.exit(0);
            }
        }
    }
}