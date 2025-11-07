
import java.util.List;

public abstract class PlaylistFactory {
    public abstract Playlist createPlaylist(String name);

    public Playlist createAndPopulate(String name, List<Song> songs) {
        Playlist playlist = createPlaylist(name);
        songs.forEach(playlist::addSong);
        System.out.println("✓ Created playlist: " + name);
        return playlist;
    }
}
