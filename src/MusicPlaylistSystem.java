import java.util.*;
import java.util.stream.Collectors;

class Song {
    private final int id;
    private final String title;
    private final String artist;
    private final String genre;
    private final int duration;

    public Song(int id, String title, String artist, String genre, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s, %ds)", id, title, artist, genre, duration);
    }
}

class Playlist {
    private final String name;
    private final List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(int songId) {
        songs.removeIf(song -> song.getId() == songId);
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public String getName() {
        return name;
    }

    public int getTotalDuration() {
        return songs.stream().mapToInt(Song::getDuration).sum();
    }
}

class SongLibrary {
    private final List<Song> songs;

    public SongLibrary() {
        this.songs = new ArrayList<>();
        initializeLibrary();
    }

    private void initializeLibrary() {
        // RnB
        songs.add(new Song(1, "All Yours", "Normani", "RnB", 218));
        songs.add(new Song(2, "Saturn", "SZA", "RnB", 186));
        songs.add(new Song(3, "On My Mama", "Victoria Monet", "RnB", 156));
        
        // Pop
        songs.add(new Song(4, "Risk", "Gracie Abrams", "Pop", 209));
        songs.add(new Song(5, "Tonight", "PinkPantheress", "Pop", 144));
        songs.add(new Song(6, "365", "Charli XCX", "Pop", 213));
        songs.add(new Song(7, "This Song", "Conan Gray", "Pop", 195));
        
        // Kpop
        songs.add(new Song(8, "Loop", "Yves", "Kpop", 187));
        songs.add(new Song(9, "What is Love?", "TWICE", "Kpop", 208));
        songs.add(new Song(10, "Accendio", "IVE", "Kpop", 181));
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public Song getSongById(int id) {
        return songs.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}


interface SongFilter {
    List<Song> filter(List<Song> songs);
}

class GenreFilter implements SongFilter {
    private final String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public List<Song> filter(List<Song> songs) {
        return songs.stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}

class ArtistFilter implements SongFilter {
    private final String artist;

    public ArtistFilter(String artist) {
        this.artist = artist;
    }

    @Override
    public List<Song> filter(List<Song> songs) {
        return songs.stream()
                .filter(song -> song.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
    }
}

class DurationFilter implements SongFilter {
    private final int maxDuration;

    public DurationFilter(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Override
    public List<Song> filter(List<Song> songs) {
        return songs.stream()
                .filter(song -> song.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }
}

class NoFilter implements SongFilter {
    @Override
    public List<Song> filter(List<Song> songs) {
        return new ArrayList<>(songs);
    }
}

interface PlaylistDisplay {
    void display(Playlist playlist);
    void displaySongs(List<Song> songs, String title);
}

class SimplePlaylistDisplay implements PlaylistDisplay {
    @Override
    public void display(Playlist playlist) {
        System.out.println("\n" + playlist.getName());
        System.out.println("=".repeat(50));
        List<Song> songs = playlist.getSongs();
        
        if (songs.isEmpty()) {
            System.out.println("Empty playlist");
            return;
        }

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            System.out.printf("%d. %s - %s (%ds)\n", 
                i + 1, song.getTitle(), song.getArtist(), song.getDuration());
        }
        System.out.printf("\nTotal: %d songs | Duration: %d seconds\n", 
            songs.size(), playlist.getTotalDuration());
    }

    @Override
    public void displaySongs(List<Song> songs, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(50));
        
        if (songs.isEmpty()) {
            System.out.println("No songs found");
            return;
        }

        songs.forEach(System.out::println);
        System.out.println("\nTotal: " + songs.size() + " songs");
    }
}

class DetailedPlaylistDisplay implements PlaylistDisplay {
    @Override
    public void display(Playlist playlist) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.printf("║  %-52s  ║\n", centerText(playlist.getName(), 52));
        System.out.println("╠════════════════════════════════════════════════════════╣");
        
        List<Song> songs = playlist.getSongs();
        
        if (songs.isEmpty()) {
            System.out.println("║  Empty playlist                                        ║");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                Song song = songs.get(i);
                System.out.printf("║  %2d. %-48s  ║\n", i + 1, song.getTitle());
                System.out.printf("║      Artist: %-40s  ║\n", song.getArtist());
                System.out.printf("║      Genre: %-18s Duration: %4ds      ║\n", 
                    song.getGenre(), song.getDuration());
                if (i < songs.size() - 1) {
                    System.out.println("║      ────────────────────────────────────────────      ║");
                }
            }
        }
        
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total: %d songs | Duration: %d seconds%-15s║\n", 
            songs.size(), playlist.getTotalDuration(), "");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    @Override
    public void displaySongs(List<Song> songs, String title) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.printf("║  %-52s  ║\n", centerText(title, 52));
        System.out.println("╠════════════════════════════════════════════════════════╣");
        
        if (songs.isEmpty()) {
            System.out.println("║  No songs found                                        ║");
        } else {
            for (Song song : songs) {
                System.out.printf("║  [%2d] %-46s  ║\n", song.getId(), song.getTitle());
                System.out.printf("║       %s - %s (%ds)%-10s║\n", 
                    song.getArtist(), song.getGenre(), song.getDuration(), "");
            }
        }
        
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total: %d songs%-40s║\n", songs.size(), "");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private String centerText(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }
}
public class MusicPlaylistSystem {
    private final Scanner scanner;
    private final SongLibrary library;
    private final Playlist playlist;
    private PlaylistDisplay display;

    public MusicPlaylistSystem() {
        this.scanner = new Scanner(System.in);
        this.library = new SongLibrary();
        this.playlist = new Playlist("My Playlist");
        this.display = new SimplePlaylistDisplay();
    }

    public void run() {
        printHeader();
        
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> viewLibrary();
                case "2" -> filterSongs();
                case "3" -> addSongToPlaylist();
                case "4" -> removeSongFromPlaylist();
                case "5" -> viewPlaylist();
                case "6" -> changeDisplayMode();
                case "7" -> {
                    System.out.println("\nGoodbye!");
                    return;
                }
                default -> System.out.println("\nInvalid option. Try again.");
            }
        }
    }

    private void printHeader() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          MUSIC PLAYLIST SYSTEM (SOLID Demo)           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private void printMenu() {
        System.out.println("\n┌────────────────────────────────────────────────────────┐");
        System.out.println("│  1. View All Songs in Library                         │");
        System.out.println("│  2. Filter Songs (OCP Demo)                           │");
        System.out.println("│  3. Add Song to Playlist                               │");
        System.out.println("│  4. Remove Song from Playlist                          │");
        System.out.println("│  5. View My Playlist                                   │");
        System.out.println("│  6. Change Display Mode (DIP Demo)                     │");
        System.out.println("│  7. Exit                                               │");
        System.out.println("└────────────────────────────────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

    private void viewLibrary() {
        display.displaySongs(library.getAllSongs(), "Song Library");
    }

    private void filterSongs() {
        System.out.println("\n--- Filter Songs (Open/Closed Principle) ---");
        System.out.println("1. Filter by Genre");
        System.out.println("2. Filter by Artist");
        System.out.println("3. Filter by Max Duration");
        System.out.println("4. No Filter (Show All)");
        System.out.print("Choose filter type: ");
        
        String filterChoice = scanner.nextLine().trim();
        SongFilter filter;

        switch (filterChoice) {
            case "1" -> {
                System.out.print("Enter genre (RnB/Pop/Kpop): ");
                String genre = scanner.nextLine().trim();
                filter = new GenreFilter(genre);
            }
            case "2" -> {
                System.out.print("Enter artist name: ");
                String artist = scanner.nextLine().trim();
                filter = new ArtistFilter(artist);
            }
            case "3" -> {
                System.out.print("Enter max duration (seconds): ");
                int maxDuration = Integer.parseInt(scanner.nextLine().trim());
                filter = new DurationFilter(maxDuration);
            }
            case "4" -> filter = new NoFilter();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        List<Song> filtered = filter.filter(library.getAllSongs());
        display.displaySongs(filtered, "Filtered Results");
    }

    private void addSongToPlaylist() {
        display.displaySongs(library.getAllSongs(), "Available Songs");
        System.out.print("\nEnter song ID to add: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Song song = library.getSongById(id);
            
            if (song != null) {
                playlist.addSong(song);
                System.out.println("✓ Added: " + song.getTitle());
            } else {
                System.out.println("✗ Song not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid ID.");
        }
    }

    private void removeSongFromPlaylist() {
        if (playlist.getSongs().isEmpty()) {
            System.out.println("\nPlaylist is empty.");
            return;
        }

        display.display(playlist);
        System.out.print("\nEnter position to remove (1-" + playlist.getSongs().size() + "): ");
        
        try {
            int position = Integer.parseInt(scanner.nextLine().trim());
            
            if (position < 1 || position > playlist.getSongs().size()) {
                System.out.println("✗ Invalid position.");
                return;
            }
            
            Song songToRemove = playlist.getSongs().get(position - 1);
            playlist.removeSong(songToRemove.getId());
            System.out.println("✓ Removed: " + songToRemove.getTitle());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input.");
        }
    }

    private void viewPlaylist() {
        display.display(playlist);
    }

    private void changeDisplayMode() {
        System.out.println("\n--- Change Display Mode (Dependency Inversion Principle) ---");
        System.out.println("1. Simple Display");
        System.out.println("2. Detailed Display");
        System.out.print("Choose display mode: ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1" -> {
                display = new SimplePlaylistDisplay();
                System.out.println("✓ Switched to Simple Display");
            }
            case "2" -> {
                display = new DetailedPlaylistDisplay();
                System.out.println("✓ Switched to Detailed Display");
            }
            default -> System.out.println("✗ Invalid choice.");
        }
    }

    public static void main(String[] args) {
        MusicPlaylistSystem app = new MusicPlaylistSystem();
        app.run();
    }
}