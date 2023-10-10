public class Document {
    public final String title;
    public final int numPages;

    public Document(String title, int numPages) {
        this.title = title;
        this.numPages = numPages;
    }

    public String toString() {
        return String.format("Document \"%s\", %d pages long", title, numPages);
    }
}
