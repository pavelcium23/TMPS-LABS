public class PartyPlaylistFactory extends PlaylistFactory {
    @Override
    public Playlist createPlaylist(String name) {
        return new Playlist(name, "Upbeat playlist for parties and celebrations");
    }
}