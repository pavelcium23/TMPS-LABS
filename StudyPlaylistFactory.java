public class StudyPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Focus-enhancing playlist for studying");
    }
}
