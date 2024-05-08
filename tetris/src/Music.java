// Import the necessary package.
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Music class handles the Tetris music in the game.
 * It provides a method to load and play the Tetris music.
 */

public class Music {

    /**
     * Method to play the Tetris music.
     */

    public static void playTetrisMusic() {
        String filePath = "/Music/Tetris.wav"; // Relative path to the Tetris music file.
        try (InputStream audioFile = Music.class.getResourceAsStream(filePath)) { // Open the audio file input stream.
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(audioFile)); // Get AudioInputStream from the input stream.
            Clip clip = AudioSystem.getClip(); // Get a Clip to play the audio.

            clip.open(audioStream); // Open the audio clip with the audio stream.
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously.
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error: " + e.getMessage()); // Print error message if an exception occurs.
        }
    }
}