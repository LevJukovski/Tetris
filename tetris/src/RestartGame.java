// Import necessary package.
import java.util.Arrays;

/**
 * This class contains only one function: The restartGame function
 */

public class RestartGame {

    /**
     * The restartGame() function restarts the game. The function can be invoked when pressing the e button at
     * the end screen.
     */
    public static void restartGame() {
        // Reset the game board (with 0)
        for (int i = 0; i < 20; i++) {
            Arrays.fill(Board.board[i], 0);
        }

        ScoreAndRecord.score = 0; // Set the score to 0.

        Pieces.getNewPiece(); // Get a new piece, the very first piece of the game.

        TetrisGame.gameOver = false; // Reset game over status (assign to false).

        TetrisGame.timer.restart(); // Restart the game timer.
    }
}
