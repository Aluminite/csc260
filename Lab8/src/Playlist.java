public class Playlist {
    private final DoubleLinkedList<Song> list = new DoubleLinkedList<>();
    private DoubleLinkedList.Node<Song> nowPlaying;

    public void addToEnd(String title, String artist) {
        list.add(new Song(title, artist));
    }

    public void insertAtPosition(String title, String artist, int position) {
        list.add(position, new Song(title, artist));
    }

    // Remove a song in the playlist by title. Returns true if the song is removed and false if it's not found.
    // If there are two songs with the same name, only the first will be removed.
    public boolean deleteByTitle(String title) {
        int index = 0;
        DoubleLinkedList.Node<Song> node = list.head;
        while (node != null) {
            if (node.data.getTitle().equals(title)) {
                list.remove(index);
                return true;
            } else {
                node = node.next;
                index++;
            }
        }

        return false;
    }

    public void deleteAtPosition(int position) {
        list.remove(position);
    }

    public String displayForward() {
        StringBuilder text = new StringBuilder();
        DoubleLinkedList.Node<Song> node = list.head;
        int index = 0;
        while (node != null) {
            text.append(String.format("%d. %s%n", index, node.data.toString()));
            node = node.next;
            index++;
        }
        return text.toString();
    }

    public String displayBackward() {
        StringBuilder text = new StringBuilder();
        DoubleLinkedList.Node<Song> node = list.head;
        int index = list.size() - 1;
        while (node != null) {
            text.append(String.format("%d. %s%n", index, node.data.toString()));
            node = node.prev;
            index--;
        }
        return text.toString();
    }

    public String playForward() {
        if (nowPlaying == null) {
            nowPlaying = list.head;
        } else {
            nowPlaying = nowPlaying.next;
        }

        return "Playing " + getCurrentSong();
    }

    public String playBackward() {
        if (nowPlaying == null) {
            nowPlaying = list.tail;
        } else {
            nowPlaying = nowPlaying.prev;
        }

        return "Playing " + getCurrentSong();
    }

    public int searchByTitle(String title) {
        int index = 0;
        DoubleLinkedList.Node<Song> node = list.head;
        while (node != null) {
            if (node.data.getTitle().equals(title)) {
                return index;
            } else {
                node = node.next;
                index++;
            }
        }

        return -1;
    }
    public Song returnSong(int indexed) {
        return list.get(indexed);
    }

    public String getCurrentSong() {
        if (nowPlaying == null) return "Nothing is playing right now.";
        return nowPlaying.data.toString();
    }

    public int size() {
        return list.size();
    }
}
