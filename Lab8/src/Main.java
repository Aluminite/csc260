import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Playlist playlist = new Playlist();

    public static void main(String[] args) {
        System.out.println("Music Player");
        while (true) {
            System.out.println("""
                    Choose an option:
                    a to add a song
                    d to delete a song
                    l to display the playlist
                    s to search for a song
                    p to play a song
                    c to get the current song
                    e to exit""");
            switch (input.nextLine().toLowerCase()) {
                case "a" -> addSong();
                case "d" -> deleteSong();
                case "l" -> displayPlaylist();
                case "s" -> searchForSong();
                case "p" -> playSong();
                case "c" -> System.out.println("Currently playing: " + playlist.getCurrentSong());
                case "e" -> System.exit(0);
            }
        }
    }

    private static void addSong() {
        System.out.print("Title of the song? ");
        String title = input.nextLine();
        System.out.print("Name of the artist? ");
        String artist = input.nextLine();
        System.out.println("""
                Where do you want to add it?
                s to add a song at a specific point of the playlist
                e to add a song to the end of the playlist""");
        switch (input.nextLine().toLowerCase()) {
            case "s" -> {
                System.out.println("Please insert where you want your song to be added (size:" + playlist.size() + ")");
                playlist.insertAtPosition(title, artist, Integer.parseInt(input.nextLine()));
            }
            case "e" -> playlist.addToEnd(title, artist);
        }
    }

    private static void deleteSong() {
        System.out.println("""
                How do you want to remove a song?
                t to remove a song via title
                p to remove a song via position
                """);
        switch (input.nextLine().toLowerCase()) {
            case "t" -> {
                System.out.println("Please insert the title of the song. -> ");
                playlist.deleteByTitle(input.nextLine());
            }
            case "p" -> {
                System.out.println("Please insert the position of the song. -> ");
                playlist.deleteAtPosition(Integer.parseInt(input.nextLine()));
            }

        }
    }

    private static void searchForSong() {
        int songPos = -1;
        System.out.println("""
                How do you want to search for a song?
                t to search via title
                p to search via position
                """);
        switch (input.nextLine()) {
            case "t" -> {
                System.out.println("Please insert the title of the song. -> ");
                songPos = playlist.searchByTitle(input.nextLine());

            }
            case "p" -> {
                System.out.println("Please insert the position of the song. -> ");
                songPos = Integer.parseInt(input.nextLine());

            }
        }
        if (songPos == -1) {
            System.out.println("That song does not appear to exist.");
        } else {
            System.out.printf("Found song %s at position %d%n", playlist.returnSong(songPos).toString(), songPos);
        }

    }

    private static void displayPlaylist() {
        System.out.print("Type f for forwards, b for backwards ");
        switch (input.nextLine().toLowerCase()) {
            case "f" -> System.out.println(playlist.displayForward());
            case "b" -> System.out.println(playlist.displayBackward());
        }
    }

    private static void playSong() {
        System.out.print("Type f for forwards, b for backwards ");
        switch (input.nextLine().toLowerCase()) {
            case "f" -> System.out.println(playlist.playForward());
            case "b" -> System.out.println(playlist.playBackward());
        }
    }
}