public class ChillPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Relaxing playlist for unwinding");
    }
}