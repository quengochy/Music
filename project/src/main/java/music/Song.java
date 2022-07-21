package music;

public class Song {
	// attributes of a song
	private String artist;
	private String title;
	private String year;
	private String genre;
	private String filePath;

	// getters and setters
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    public boolean isFavourite() {
        return false;
    }

    public void setFavourite(boolean b) {
    }

}
