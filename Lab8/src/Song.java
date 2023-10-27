public class Song {
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    private final String title;
    private final String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String toString() {
        return String.format("%s - %s", artist, title);
    }
}
