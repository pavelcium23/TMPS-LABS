

public class Song {
    private final int id;
    private final String title;
    private final String artist;
    private final String genre;
    private final int duration;
    private final int year;
    private final String album;

    private Song(SongBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.artist = builder.artist;
        this.genre = builder.genre;
        this.duration = builder.duration;
        this.year = builder.year;
        this.album = builder.album;
    }

    public Song(Song song) {
        this.id = song.id;
        this.title = song.title;
        this.artist = song.artist;
        this.genre = song.genre;
        this.duration = song.duration;
        this.year = song.year;
        this.album = song.album;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public int getYear() { return year; }
    public String getAlbum() { return album; }

    public Song clone() {
        return new Song(this);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s, %d, %ds)", 
            id, title, artist, genre, year, duration);
    }

    public static class SongBuilder {
        private final int id;
        private final String title;
        private final String artist;
        private String genre = "Unknown";
        private int duration = 0;
        private int year = 2024;
        private String album = "Single";

        public SongBuilder(int id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }

        public SongBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public SongBuilder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public SongBuilder year(int year) {
            this.year = year;
            return this;
        }

        public SongBuilder album(String album) {
            this.album = album;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }
}