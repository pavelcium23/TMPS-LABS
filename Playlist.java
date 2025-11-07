

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final String name;
    private final List<Song> songs;
    private final String description;

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(int position) {
        if (position >= 0 && position < songs.size()) {
            songs.remove(position);
        }
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    public int getTotalDuration() {
        return songs.stream().mapToInt(Song::getDuration).sum();
    }
}
