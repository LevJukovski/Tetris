// Import the necessary package.
import java.util.Arrays;

/**
 * The Board class represents the game board for Tetris.
 * It includes methods for initializing the board, checking for collisions between tetris pieces, and
 * placing tetris pieces on the board. Additionally, there is a function for removing full rows.
 */

public class Board {
    public static int[][] board; // The game board.

    /**
     * Initializes the game board with empty cells.
     * The board has 20 rows and 10 columns.
     */
    public static void initBoard() {
        board = new int[20][10]; // Creating the board (20 rows, 10 columns).
    }

    /**
     * Checks if there is a collision between the current piece and the game board.
     * @return true if there is a collision, false otherwise.
     */
    public static boolean isCollision() {
        // Checks for collision between the current piece and the game board.
        return isCollision(Pieces.currentPieceX, Pieces.currentPieceY, Pieces.currentPiece);
    }

    /**
     * Checks if there is a collision at the specified position with the given piece.
     * @param x The x-coordinate of the piece.
     * @param y The y-coordinate of the piece.
     * @param piece The shape of the piece represented as a 2D array.
     * @return True if there is a collision, false otherwise.
     */
    public static boolean isCollision(int x, int y, int[][] piece) {
        for (int i = 0; i < piece.length; i++) { // Loop through each cell of the piece.
            for (int j = 0; j < piece[0].length; j++) {
                // Check if the current cell of the piece is filled and if it causes a collision with the game board.
                if (piece[i][j] == 1 &&
                        (x + j < 0 || x + j >= 10 || y + i >= 20 || board[y + i][x + j] == 1)) {
                    return true; // Collision detected, return true.
                }
            }
        }
        return false; // No collision found, return false.
    }

    /**
     * Places the current piece on the game board.
     */
    public static void placePieceOnBoard() {
        for (int i = 0; i < Pieces.currentPiece.length; i++) { // Loops through each cell of the current piece.
            for (int j = 0; j < Pieces.currentPiece[0].length; j++) { // Checks if the current cell of the piece is filled.
                if (Pieces.currentPiece[i][j] == 1) {
                    // Places the piece on the game board at the corresponding position.
                    board[Pieces.currentPieceY + i][Pieces.currentPieceX + j] = 1;
                }
            }
        }
    }


    /**
     * Removes full rows from the game board.
     * Increases the score by 1 for each cleared row.
     */
    public static void removeFullRows() {
        for (int i = 0; i < 20; i++) {// Loops through each row of the game board.
            boolean rowFull = true; // Assumes that the row is full initially.
            for (int j = 0; j < 10; j++) { // Checks if the current row is full.
                if (board[i][j] == 0) {
                    rowFull = false; // Set rowFull to false if any cell is empty.
                    break;
                }
            }
            if (rowFull) { // If the row is full, remove it and move the rows above down.
                ScoreAndRecord.increaseScore(); // Increase the score by 1 when a row is cleared.
                ScoreAndRecord.updateRecord(); // Update the record (if needed).
                for (int k = i; k > 0; k--) { // Move each row above the current row down by one.
                    System.arraycopy(board[k - 1], 0, board[k], 0, 10);
                }
                Arrays.fill(board[0], 0, 10, 0); // Clear the top row.
            }
        }
    }
}