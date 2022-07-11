package music;
import java.io.File; 
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

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

    // "main" makes this class a java app that can be executed
    public static void main(final String[] args) {
        
        Song s = new Song(); 
             s.setArtist("Hoang Thuy Linh"); 
             s.setTitle("See Tinh");
             s.setYear("2022"); 
             s.setGenre("Pop"); 
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Seachains");
             s.setTitle("Cam Nhan");
             s.setYear("2021"); 
             s.setGenre("Rap");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Chang feat. LeWiuy");
             s.setTitle("Sai Gon Dau Co Lanh Dau");
             s.setYear("2020"); 
             s.setGenre("Pop");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("OSAD");
             s.setTitle("Lung Lay");
             s.setYear("2022"); 
             s.setGenre("Hip Hop/Rap");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Obito");
             s.setTitle("Soju Love");
             s.setYear("2022"); 
             s.setGenre("R&B/Soul");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Den & JustaTee");
             s.setTitle("Di Ve Nha");
             s.setYear("2020"); 
             s.setGenre("Hip-Hop/Rap");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Miu Le & Karik");
             s.setTitle("Vi Me Anh Bat Chia Tay");
             s.setYear("2022"); 
             s.setGenre("Pop");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Hien Ho");
             s.setTitle("Gap Nhung Khong O Lai");
             s.setYear("2020"); 
             s.setGenre("Pop");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Truc Nhan");
             s.setTitle("Co Khong Giu Mat Dung Tim");
             s.setYear("2022"); 
             s.setGenre("Pop");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

             s = new Song(); 
             s.setArtist("Duc Phuc");
             s.setTitle("Ngay Dau Tien");
             s.setYear("2022"); 
             s.setGenre("Pop");
             s.setFilePath("");
             songs.put(s.getTitle(), s);

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
        System.out.print("Enter q to Quit:");

    }

    /*
     * handles the user input for the app
     */
    public static void handleMenu(String userInput) {

        switch (userInput) {

            case "h":
                System.out.println("-->Home<--");
                break;

            case "s":
                System.out.println("-->Search by title<--");
                break;

            case "l":
                System.out.println("-->Library<--");
                break;
                
            case "p":
                System.out.println("-->Play<--");
                play();
                break;

            case "q":
                System.out.println("-->Quit<--");
                break;

            default:
                break;
        }

    }

    /*
     * plays an audio file
     */
    public static void play() {

        // open the audio file
        // src\library\example audio\cropped_wav\Checkie_Brown_-_11_-_Wirklich_Wichtig_CB_27.wav
        final File file = new File("/Users/quengochy/Documents/GitHub/Music/project/src/main/java/music/wav/See Tinh.wav");

        try {
        
            // create clip 
            audioClip = AudioSystem.getClip();

            // get input stream
            final AudioInputStream in = getAudioInputStream(file);

            audioClip.open(in);
            audioClip.setMicrosecondPosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace(); 
        }

    }


}

