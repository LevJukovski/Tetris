// Import necessary packages.
import java.awt.*;
import java.util.Random;

/**
 * The Pieces class has all the logic for managing the Tetris pieces.
 * Additionally, it provides two methods:
 * 1) a method to get a new random tetris piece. the new piece will be placed on the game screen.
 * 2) a method for getting a random color for a tetris piece.
 */

public class Pieces {
    public static int[][] currentPiece; // Current piece's shape.
    public static int currentPieceX, currentPieceY; // Current piece's position.
    public static Color pieceColor; // Current piece's color.
    private static final int[][][] pieces = { // Array to store different tetris pieces.

            // Piece 1: Straight line.
            {{1, 1, 1, 1}},

            // Piece 2: L shape.
            {{1, 0, 0},
                    {1, 1, 1}},

            // Piece 3: Reverse L shape.
            {{0, 0, 1},
                    {1, 1, 1}},

            // Piece 4: T shape.
            {{1, 1, 1},
                    {0, 1, 0}},

            // Piece 5: Square.
            {{1, 1},
                    {1, 1}},

            // Piece 6: Z shape.
            {{1, 1, 0},
                    {0, 1, 1}},

            // Piece 7: Reverse Z shape.
            {{0, 1, 1},
                    {1, 1, 0}}
    };

    private static final Color[] pieceColors = { // Colors for different tetris shapes.
            Color.CYAN,     // Color 1.
            Color.ORANGE,   // Color 2.
            Color.BLUE,     // Color 3.
            Color.MAGENTA,  // Color 4.
            Color.YELLOW,   // Color 5.
            Color.GREEN,    // Color 6.
            Color.RED       // Color 7.
    };

    /**
     * Creates a new random tetris pieces with a random color.
     * Additionally, the piece's initial position is set.
     */
    public static void getNewPiece() {
        Random random = new Random(); // Initialize a new random object.
        int index = random.nextInt(pieces.length); // Gets a random number from 0 (included) to pieces.length (excluded).
        currentPiece = pieces[index]; //  Chooses a piece from the pieces (int[][][] list) according to the value in index.
        currentPieceX = 3; // Setting the x coordinate.
        currentPieceY = 0; // Setting the y coordinate.
        pieceColor = getRandomPieceColor(); // Assign random color to the new piece.
    }

    /** Get a random color for a tetris piece.
     * @return a random color object, which represents the color of a tetris piece.
    */
    public static Color getRandomPieceColor() {
        Random random = new Random(); // Initialize a new random object.
        return pieceColors[random.nextInt(pieceColors.length)]; // Gets a random number from 0 (included) to
        // pieceColors.length (excluded), and then chooses a color from the pieceColors (Color list) accordingly.
    }
}