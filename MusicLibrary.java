
import java.util.ArrayList;
import java.util.List;

public class MusicLibrary {
    private static final MusicLibrary INSTANCE = new MusicLibrary();
    private final List<Song> songs;

    private MusicLibrary() {
        this.songs = new ArrayList<>();
        initializeLibrary();
        System.out.println("✓ MusicLibrary Singleton initialized");
    }

    public static MusicLibrary getInstance() {
        return INSTANCE;
    }

    private void initializeLibrary() {
        songs.add(new Song.SongBuilder(1, "All Yours", "Normani")
                .genre("RnB").duration(218).year(2023).album("Dopamine").build());

        songs.add(new Song.SongBuilder(2, "Saturn", "SZA")
                .genre("RnB").duration(186).year(2024).album("SOS").build());

        songs.add(new Song.SongBuilder(3, "On My Mama", "Victoria Monet")
                .genre("RnB").duration(156).year(2023).album("Jaguar II").build());

        songs.add(new Song.SongBuilder(4, "Risk", "Gracie Abrams")
                .genre("Pop").duration(209).year(2024).album("The Secret of Us").build());

        songs.add(new Song.SongBuilder(5, "Tonight", "PinkPantheress")
                .genre("Pop").duration(144).year(2023).album("Heaven Knows").build());

        songs.add(new Song.SongBuilder(6, "365", "Charli XCX")
                .genre("Pop").duration(213).year(2024).album("BRAT").build());

        songs.add(new Song.SongBuilder(7, "This Song", "Conan Gray")
                .genre("Pop").duration(195).year(2024).album("Found Heaven").build());

        songs.add(new Song.SongBuilder(8, "Loop", "Yves")
                .genre("Kpop").duration(187).year(2023).album("Loop").build());

        songs.add(new Song.SongBuilder(9, "What is Love?", "TWICE")
                .genre("Kpop").duration(208).year(2018).album("What is Love?").build());

        songs.add(new Song.SongBuilder(10, "Accendio", "IVE")
                .genre("Kpop").duration(181).year(2024).album("IVE SWITCH").build());
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public Song getSongById(int id) {
        return songs.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
