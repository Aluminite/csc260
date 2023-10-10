import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final LinkedList<Song> queue = new LinkedList<>();
    private static final Scanner input = new Scanner(System.in);

    private static Song createSong() {
        System.out.print("Song title? ");
        String title = input.nextLine();
        System.out.print("Artist name? ");
        String artist = input.nextLine();
        System.out.print("Song length (in seconds)? ");
        int length = Integer.parseInt(input.nextLine());

        return new Song(title, artist, length);
    }

    private static void addSong() {
        Song newSong = createSong();
        System.out.print("Position in queue to add song? (Leave blank for end): ");
        try {
            queue.add(Integer.parseInt(input.nextLine()) - 1, newSong);
        } catch (NumberFormatException unused) {
            queue.add(newSong);
        }
    }

    private static void removeSong() {
        System.out.println("Index of song to remove? ");
        int index = Integer.parseInt(input.nextLine()) - 1;
        try {
            queue.remove(index);
        } catch (IndexOutOfBoundsException unused) {
            System.out.println("Unable to remove that index.");
        }
    }

    private static void playSong() {
        try {
            System.out.println("Now playing: " + queue.remove());
        } catch (NoSuchElementException unused) {
            System.out.println("The queue is empty, there is nothing to play.");
        }
    }

    private static void listSongs() {
        if (queue.isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }

        int i = 1;
        for (Song song : queue) {
            System.out.printf("%d. %s%n", i, song);
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Media Player");
        while (true) {
            System.out.println("""
                                        
                    What would you like to do?
                    a to add a song
                    r to remove a song
                    p to play the next song
                    l to list songs in the queue
                    e to exit""");
            switch (input.nextLine().toLowerCase()) {
                case "a" -> addSong();
                case "r" -> removeSong();
                case "p" -> playSong();
                case "l" -> listSongs();
                case "e" -> System.exit(0);
            }
        }
    }
}