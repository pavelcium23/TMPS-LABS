
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlaylistSystem {
    private final Scanner scanner;
    private final MusicLibrary library;
    private final List<Playlist> userPlaylists;

    public MusicPlaylistSystem() {
        this.scanner = new Scanner(System.in);
        this.library = MusicLibrary.getInstance();
        this.userPlaylists = new ArrayList<>();
    }

    public void run() {
        printHeader();
        
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> viewLibrary();
                case "2" -> demonstrateBuilder();
                case "3" -> demonstrateFactoryMethod();
                case "4" -> demonstratePrototype();
                case "5" -> demonstrateSingleton();
                case "6" -> viewAllPlaylists();
                case "7" -> {
                    System.out.println("\n✓ Goodbye!");
                    return;
                }
                default -> System.out.println("\n✗ Invalid option. Try again.");
            }
        }
    }

    private void printHeader() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║     CREATIONAL DESIGN PATTERNS - MUSIC SYSTEM         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private void printMenu() {
        System.out.println("\n┌────────────────────────────────────────────────────────┐");
        System.out.println("│  1. View Music Library (Singleton)                     │");
        System.out.println("│  2. Add Custom Song (Builder Pattern)                  │");
        System.out.println("│  3. Create Playlist (Factory Method)                   │");
        System.out.println("│  4. Clone Song (Prototype Pattern)                     │");
        System.out.println("│  5. Test Singleton Pattern                             │");
        System.out.println("│  6. View All Created Playlists                         │");
        System.out.println("│  7. Exit                                               │");
        System.out.println("└────────────────────────────────────────────────────────┘");
        System.out.print("Choose an option: ");
    }

    private void viewLibrary() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MUSIC LIBRARY (Singleton Instance)");
        System.out.println("=".repeat(60));
        
        List<Song> songs = library.getAllSongs();
        songs.forEach(System.out::println);
        
        System.out.println("\n✓ Total songs: " + songs.size());
        System.out.println("✓ Library instance: " + library.hashCode());
    }

    private void demonstrateBuilder() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("BUILDER PATTERN - Create Custom Song");
        System.out.println("=".repeat(60));
        
        try {
            System.out.print("Enter song ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter song title: ");
            String title = scanner.nextLine().trim();
            
            System.out.print("Enter artist name: ");
            String artist = scanner.nextLine().trim();
            
            System.out.print("Enter genre (RnB/Pop/Kpop): ");
            String genre = scanner.nextLine().trim();
            
            System.out.print("Enter duration (seconds): ");
            int duration = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter album name: ");
            String album = scanner.nextLine().trim();

            Song newSong = new Song.SongBuilder(id, title, artist)
                    .genre(genre).duration(duration).year(year).album(album).build();

            library.addSong(newSong);
            System.out.println("\n✓ Song created using Builder Pattern:");
            System.out.println(newSong);
            
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input.");
        }
    }

    private void demonstrateFactoryMethod() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FACTORY METHOD PATTERN - Create Playlist");
        System.out.println("=".repeat(60));
        
        System.out.println("\nSelect playlist type:");
        System.out.println("1. Workout Playlist");
        System.out.println("2. Chill Playlist");
        System.out.println("3. Party Playlist");
        System.out.println("4. Study Playlist");
        System.out.print("Choice: ");
        
        String choice = scanner.nextLine().trim();
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine().trim();
        
        PlaylistFactory factory;
        switch (choice) {
            case "1" -> factory = new WorkoutPlaylistFactory();
            case "2" -> factory = new ChillPlaylistFactory();
            case "3" -> factory = new PartyPlaylistFactory();
            case "4" -> factory = new StudyPlaylistFactory();
            default -> { System.out.println("✗ Invalid choice"); return; }
        }
        
        System.out.println("\nAvailable songs:");
        List<Song> allSongs = library.getAllSongs();
        for (int i = 0; i < allSongs.size(); i++) {
            System.out.println((i + 1) + ". " + allSongs.get(i));
        }
        
        System.out.print("\nEnter song IDs (comma-separated): ");
        String input = scanner.nextLine().trim();
        
        List<Song> selectedSongs = new ArrayList<>();
        for (String idStr : input.split(",")) {
            try {
                int id = Integer.parseInt(idStr.trim());
                Song song = library.getSongById(id);
                if (song != null) selectedSongs.add(song);
            } catch (NumberFormatException e) {}
        }
        
        Playlist playlist = factory.createAndPopulate(name, selectedSongs);
        userPlaylists.add(playlist);
        System.out.println("✓ " + playlist.getDescription());
    }

    private void demonstratePrototype() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROTOTYPE PATTERN - Clone Song");
        System.out.println("=".repeat(60));
        
        System.out.println("\nAvailable songs:");
        library.getAllSongs().forEach(System.out::println);
        
        System.out.print("\nEnter song ID to clone: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Song original = library.getSongById(id);
            
            if (original == null) {
                System.out.println("✗ Song not found");
                return;
            }
            
            Song cloned = original.clone();
            System.out.println("\n✓ Original: " + original + " (Hash: " + original.hashCode() + ")");
            System.out.println("✓ Cloned:   " + cloned + " (Hash: " + cloned.hashCode() + ")");
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid ID");
        }
    }

    private void demonstrateSingleton() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SINGLETON PATTERN - Verify Single Instance");
        System.out.println("=".repeat(60));
        
        MusicLibrary lib1 = MusicLibrary.getInstance();
        MusicLibrary lib2 = MusicLibrary.getInstance();
        MusicLibrary lib3 = MusicLibrary.getInstance();
        
        System.out.println("\nInstance 1: " + lib1.hashCode());
        System.out.println("Instance 2: " + lib2.hashCode());
        System.out.println("Instance 3: " + lib3.hashCode());
        System.out.println("\n✓ All same: " + (lib1 == lib2 && lib2 == lib3));
    }

    private void viewAllPlaylists() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALL CREATED PLAYLISTS");
        System.out.println("=".repeat(60));
        
        if (userPlaylists.isEmpty()) {
            System.out.println("\nNo playlists created.");
            return;
        }
        
        for (int i = 0; i < userPlaylists.size(); i++) {
            Playlist p = userPlaylists.get(i);
            System.out.println("\n" + (i + 1) + ". " + p.getName());
            System.out.println("   " + p.getDescription());
            System.out.println("   Songs: " + p.getSongs().size() + " | Duration: " + p.getTotalDuration() + "s");
        }
    }

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║  4 Creational Design Patterns:                        ║");
        System.out.println("║  • Singleton  - MusicLibrary                          ║");
        System.out.println("║  • Builder    - Song creation                         ║");
        System.out.println("║  • Factory    - Playlist types                        ║");
        System.out.println("║  • Prototype  - Song cloning                          ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        MusicPlaylistSystem app = new MusicPlaylistSystem();
        app.run();
    }
}