package music;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/*
    To compile: javac SpotifyLikeApp.java
    To run: java SpotifyLikeApp
 */

// declares a class for the app
public class SpotifyLikeApp {

	// global variables for the app
	String status;
	Long position;
	static Clip audioClip;
	static HashMap<String, Song> songs = new HashMap<>();
	static List<Song> favouriteSongs = new ArrayList<Song>();

	// "main" makes this class a java app that can be executed
	public static void main(final String[] args) throws LineUnavailableException {

		// create a scanner for user input
		Scanner input = new Scanner(System.in);

		String userInput = "";
		while (!userInput.equals("q")) {

			menu();

			// get input
			userInput = input.nextLine();

			// accept upper or lower case commands
			userInput.toLowerCase();

			// do something
			handleMenu(userInput);

		}

		// close the scanner
		input.close();

	}

	/*
	 * displays the menu for the app
	 */
	public static void menu() {

		System.out.println("---- SpotifyLikeApp ----");
		System.out.println("[H]ome");
		System.out.println("[S]earch by title");
		System.out.println("[L]ibrary");
		System.out.println("[P]lay");
		System.out.println("[Q]uit");
		System.out.println("");

		System.out.println("");
		System.out.print("Press the first letter of the menu screen:");

	}

	/*
	 * handles the user input for the app
	 */
	public static void handleMenu(String userInput) throws LineUnavailableException {

		switch (userInput) {

		case "h":
			System.out.println("-->Home<--");
			break;

		case "s":
			System.out.println("-->Search by title<--");
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter the title:");
			String title = input.nextLine();

			Song song = findSong(title, initializeLibrary());

			if (song != null) {
				System.out.println("Your current song is playing");
				audioClip = play(song.getFilePath());
				displaySongDetails(song);
				if (song.isFavourite() == false) {
					System.out.println("Add song to favourite list? press ( Y / N )");
					String response = input.nextLine();
					if (response.equalsIgnoreCase("y")) {
						song.setFavourite(true);
						favouriteSongs.add(song);
					}

				}

				String choice = "";
				if (choice.equalsIgnoreCase("x")) {
					choiceAction(choice.toLowerCase(), audioClip);
				}

				while (!choice.equalsIgnoreCase("x")) {
					System.out.println("Press D to Pause");
					System.out.println("Press P to Play");
					System.out.println("Press X to Stop");
					System.out.println("Press R to Rewind");
					System.out.println("Press N to Foward");

					choice = input.nextLine();

					choiceAction(choice.toLowerCase(), audioClip);
				}

			} else {
				System.out.println("Sorry, please try again.");
			}
			break;

		case "l":
			List<Song> songList = initializeLibrary();
			System.out.println("-->Library<--");
			for (int i = 0; i < songList.size(); i++) {

				Song songObj = songList.get(i);
				System.out.println(i + "- Title: " + songObj.getTitle() + "  Artist: " + songObj.getArtist() + " Genre: " + songObj.getGenre()
						+ "  Year: " + songObj.getYear());

			}
			break;

			case "f":
			System.out.println("-->Favourite<--");
			if (favouriteSongs.isEmpty() == true) {
				System.out.println("Favourite List is Empty , Search and Play song to Add");
			} else {
				for (int i = 0; i < favouriteSongs.size(); i++) {

					Song songObj = favouriteSongs.get(i);
					System.out.println(
							i + "- Title: " + songObj.getTitle() + "  Artist: " + songObj.getArtist() + " Genre: " + songObj.getGenre()
									+ "  Year: " + songObj.getYear() + " Favourite: " + songObj.isFavourite());

				}
			}

			break;

		case "p":
			System.out.println("-->Play<--");
			break;

		case "q":
			System.out.println("-->Quit<--");
			break;

		default:
			break;
		}

	}

		// handling the play pause stop choices
		public static void choiceAction(String choice, Clip audioClip) throws LineUnavailableException {

			switch (choice) {
			case "d":
				audioClip.stop();
				break;
			case "p":
				audioClip.start();
				break;
			case "x":
				audioClip.stop();
				break;
			case "r":
				long currentTime = audioClip.getMicrosecondPosition();
				long newTime = currentTime - 5000000;
				if (newTime < 0) {
					audioClip.setMicrosecondPosition(0);
				} else {
					audioClip.setMicrosecondPosition(newTime);
				}
	
				break;
			case "n":
				long currentTimeN = audioClip.getMicrosecondPosition();
				long newTimeN = currentTimeN + 5000000;
				if (newTimeN > audioClip.getMicrosecondLength()) {
					audioClip.setMicrosecondPosition(audioClip.getMicrosecondLength());
				} else {
					audioClip.setMicrosecondPosition(newTimeN);
				}
				break;
	
			}
	
		}


	// initializes the song to the library
	public static List<Song> initializeLibrary() {

		List<Song> songList = new ArrayList<Song>();

		Song s1 = new Song();
		s1.setArtist("Hoang Thuy Linh");
		s1.setTitle("See Tinh");
		s1.setYear("2022");
		s1.setGenre("Pop");
		s1.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/See Tinh.wav");
		songList.add(s1);
		// songs.put(s.getTitle(), s);
		Song s2 = new Song();
		s2 = new Song();
		s2.setArtist("Seachains");
		s2.setTitle("Cam Nhan");
		s2.setYear("2021");
		s2.setGenre("Rap");
		s2.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Cam Nhan.wav");
		songList.add(s2);
		// songs.put(s.getTitle(), s);

		Song s3 = new Song();
		s3 = new Song();
		s3.setArtist("Chang feat. LeWiuy");
		s3.setTitle("Sai Gon Dau Co Lanh");
		s3.setYear("2020");
		s3.setGenre("Pop");
		s3.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Sai-Gon-Dau-Co-Lanh.wav");
		songList.add(s3);
		// songs.put(s.getTitle(), s);
		Song s4 = new Song();
		s4 = new Song();
		s4.setArtist("OSAD");
		s4.setTitle("Lung Lay");
		s4.setYear("2022");
		s4.setGenre("Hip Hop/Rap");
		s4.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Lung Lay.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s4);

		Song s5 = new Song();
		s5 = new Song();
		s5.setArtist("Obito");
		s5.setTitle("Soju Love");
		s5.setYear("2022");
		s5.setGenre("R&B/Soul");
		s5.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Soju Love.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s5);

		Song s6 = new Song();
		s6 = new Song();
		s6.setArtist("Den & JustaTee");
		s6.setTitle("Di Ve Nha");
		s6.setYear("2020");
		s6.setGenre("Hip-Hop/Rap");
		s6.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Di Ve Nha.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s6);

		Song s7 = new Song();
		s7 = new Song();
		s7.setArtist("Miu Le & Karik");
		s7.setTitle("Vi Me Anh Bat Chia Tay");
		s7.setYear("2022");
		s7.setGenre("Pop");
		s7.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Vi-Me-Anh-Bat-Chia-Tay.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s7);

		Song s8 = new Song();
		s8 = new Song();
		s8.setArtist("B.Ray");
		s8.setTitle("Yeu Nhu Tre Con");
		s8.setYear("2019");
		s8.setGenre("Rap");
		s8.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Yeu Nhu Tre Con.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s8);

		Song s9 = new Song();
		s9 = new Song();
		s9.setArtist("Truc Nhan");
		s9.setTitle("Co Khong Giu Mat Dung Tim");
		s9.setYear("2022");
		s9.setGenre("Pop");
		s9.setFilePath(
				"/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Co Khong Giu Mat Dung Tim.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s9);

		Song s10 = new Song();
		s10 = new Song();
		s10.setArtist("Duc Phuc");
		s10.setTitle("Ngay Dau Tien");
		s10.setYear("2022");
		s10.setGenre("Pop");
		s10.setFilePath("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/Ngay Dau Tien.wav");
		// songs.put(s.getTitle(), s);
		songList.add(s10);

		return songList;
	}

	// search song
	static Song findSong(String title, List<Song> songList) {
		for (Song song : songList) {
			if (song.getTitle().equals(title)) {
				return song;
			}

		}
		return null;

	}

	public static void displaySongDetails(Song song) {
		System.out.println("--------------------------------");
		System.out.println("Artist: " + song.getArtist());
		System.out.println("Title: " + song.getTitle());
		System.out.println("Year: " + song.getYear());
		System.out.println("Genre: " + song.getGenre());
		System.out.println("IsFavourite: " + song.isFavourite());
		System.out.println("FilePath: " + song.getFilePath());
		System.out.println("--------------------------------");
	
	}

	/*
	 * plays an audio file
	 */
	public static Clip play(String filePath) {

		// open the audio file
		// src\library\example
		// audio\cropped_wav\Checkie_Brown_-_11_-_Wirklich_Wichtig_CB_27.wav
		final File file = new File(filePath);

		try {

			// create clip
			audioClip = AudioSystem.getClip();

			// get input stream
			final AudioInputStream in = getAudioInputStream(file);

			audioClip.open(in);
			audioClip.setMicrosecondPosition(0);
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return audioClip;

	}

}
