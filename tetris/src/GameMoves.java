
/**
 * The GameMoves class contains methods to control game moves such as pausing or resuming the game,
 * moving the current piece left, right, or down, and rotating the current piece.
 */

public class GameMoves {

    /**
     * Pause or resume the game (the game timer).
     * @param tetrisGame The TetrisGame instance.
     */
    public static void pauseOrResumeGame(TetrisGame tetrisGame) {
        if(TetrisGame.timer.isRunning()) { // Check if the game timer is running.
            TetrisGame.timer.stop(); // Pause the game timer.
            tetrisGame.repaint(); // Trigger repaint to update the display.
        } else if(!TetrisGame.timer.isRunning()) { // Check if the game timer isn't running.
            TetrisGame.timer.start(); // Resume the game timer.
            tetrisGame.repaint(); // Trigger repaint to update the display.
        }
    }

    /**
     * Move the current piece to the left.
     * If there is a collision, the piece moves to the left and then to the right (in order to avoid the piece from
     * moving further).
     */
    public static void moveLeft() {
        if(TetrisGame.timer.isRunning()) { // Check if the game timer is running.
            Pieces.currentPieceX--; // Move the current piece to the left.
            if (Board.isCollision()) {  // If there's a collision.
                Pieces.currentPieceX++; // Move the current piece back to the right.
            }
        }
    }

    /**
     * Move the current piece to the right.
     * If there is a collision, the piece moves to the right and then to the left (in order to avoid the piece from
     * moving further).
     */
    public static void moveRight() {
        if(TetrisGame.timer.isRunning()) { // Check if the game timer is running.
            Pieces.currentPieceX++; // Move the current piece to the right.
            if (Board.isCollision()) { // If there's a collision.
                Pieces.currentPieceX--; // Move the current piece back to the left.
            }
        }
    }

    /**
     * Move the current piece down.
     * If the game is on pause, or if the piece collides with the board, it stops moving.
     * @param tetrisGame The TetrisGame instance.
     */
    public static void moveDown(TetrisGame tetrisGame) {
        if (!TetrisGame.timer.isRunning()) { // Check if the game timer is not running.
            return; // If the game is paused, don't continue moving pieces down.
        }
        Pieces.currentPieceY++; // Move the current piece down.
        if (Board.isCollision()) { // If there's a collision.
            Pieces.currentPieceY--; // Move the current piece back up.
            Board.placePieceOnBoard(); // Place the current piece on the board.
            Board.removeFullRows(); // Remove any full rows on the board (if there are any to remove).
            Pieces.getNewPiece(); // Get a new random piece.
            if (Board.isCollision()) { // If there's a collision after getting a new piece.
                TetrisGame.gameOver = true; // set gameOver to true;
                TetrisGame.timer.stop(); // Stop the timer.
                tetrisGame.repaint(); // Call repaint function to show the end screen.
            }
        }
    }

    /**
     * Rotate the current piece clockwise.
     * If the game is paused or if the rotation causes a collision, it doesn't rotate the piece.
     */
    public static void rotate() {
        if (!TetrisGame.timer.isRunning()) { // If the game is paused.
            return; // Don't rotate the piece.
        }

        // Create a new array to hold the rotated piece.
        int[][] rotatedPiece = new int[Pieces.currentPiece[0].length][Pieces.currentPiece.length];

        // Perform rotation of the current piece.
        for (int i = 0; i < Pieces.currentPiece.length; i++) {
            for (int j = 0; j < Pieces.currentPiece[0].length; j++) {
                rotatedPiece[j][Pieces.currentPiece.length - 1 - i] = Pieces.currentPiece[i][j];
            }
        }

        // Check if the rotated piece does not collide with the board.
        if (!Board.isCollision(Pieces.currentPieceX, Pieces.currentPieceY, rotatedPiece)) {
            Pieces.currentPiece = rotatedPiece; // Rotate the piece if there's no collision.
        }
    }
}