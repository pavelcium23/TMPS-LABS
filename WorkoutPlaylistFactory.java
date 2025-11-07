public class WorkoutPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "High-energy playlist for workout sessions");
    }
}
