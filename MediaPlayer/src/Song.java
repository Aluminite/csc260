public class Song {
    public final String title;
    public final String artist;
    public final int length;

    public Song(String title, String artist, int length) {
        this.title = title;
        this.artist = artist;
        this.length = length;
    }

    public String lengthString() {
        return String.format("%d:%02d", length / 60, length % 60);
    }

    public String toString() {
        return String.format("%s - %s (%s)", artist, title, lengthString());
    }
}
