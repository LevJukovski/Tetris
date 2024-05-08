// Import the necessary package.
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Music class handles the Tetris music in the game.
 * It provides methods to load and play the Tetris music.
 */

public class Music {
    private static Clip tetrisMusic; // The clip object to handle Tetris music.

    /**
     * Loads Tetris music from a file and sets up the audio stream.
     */
    public static void loadTetrisMusic() { // Load Tetris music from file.
        Path currentFilePath = Paths.get("");
        String filepath = currentFilePath.toAbsolutePath().toString() + "/src/Music/Tetris.wav";// Get the music's path.

        try {  // Try.
            // Load music file.
            File musicFile = new File(filepath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFile);

            // Creating a clip object.
            tetrisMusic = AudioSystem.getClip();

            // Open audio input stream.
            tetrisMusic.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Handling a few exceptions.
        }
    }

    /** Plays the Tetris music in a loop.
     * the music will play until the player closes the game's window, or up until the player presses on e when the game ends.
     */
    public static void playTetrisMusic() {
        // Play Tetris music in a loop.
        tetrisMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
