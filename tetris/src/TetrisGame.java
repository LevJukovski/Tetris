// Import necessary packages.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The TetrisGame class represents the main class for this Tetris game.
 * It handles the game initialization, rendering, and event handling.
 */

public class TetrisGame extends JFrame implements ActionListener, KeyListener { // TetrisGame class definition.
    public static Timer timer; // Timer for game loop.
    public static boolean gameOver; // This variable indicates if the game is over.

    /**
     * The main method starts the Tetris game.
     * It creates an instance of TetrisGame and makes it visible.
     */
    public static void main(String[] args) { // Main method to start the game.
        TetrisGame tetrisGame = new TetrisGame(); // Create an instance of TetrisGame and make it visible.
        tetrisGame.setVisible(true); // Set the visibility of the TetrisGame JFrame to true, making it visible on the screen.
    }

    /**
     * Constructor for the TetrisGame class.
     * This constructor initializes the Tetris game by setting up the game environment,
     * including the game board, JFrame properties, and the game timer.
     * It also handles the initialization of the first piece and the starting score (and also the starting record, both of
     * them 0).
     * Moreover, this TetrisGame function sets up the key listener for controlling game actions,
     *  and it loads and plays Tetris music in the background.
     */
    public TetrisGame() { // Constructor for TetrisGame class.
        ScoreAndRecord.score = 0; // Initialize score to 0 when the game starts.
        ScoreAndRecord.record = 0; // Initialize record to 0 when the game starts.

        // Set up JFrame properties.
        setTitle("Tetris");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize game board and create the game's first piece.
        Board.initBoard(); // 20 rows, 10 columns.
        Pieces.getNewPiece(); // Create a random piece.

        // Set up game timer and the key listener.
        timer = new Timer(500, this);
        addKeyListener(this);
        setFocusable(true);

        Music.playTetrisMusic(); // Play Tetris music.

        timer.start(); // Starts the game timer.
        Pieces.pieceColor = Pieces.getRandomPieceColor(); // Assign a random color to the first piece.
    }

    /**
     * ActionListener implementation for handling timer events.
     * It moves the piece down automatically and updates the display if the game is not over.
     */
    public void actionPerformed(ActionEvent e) { // ActionListener implementation for handling timer events.
        if (!gameOver) { // If the game is not over, move the piece down automatically and update the display.
            GameMoves.moveDown(this); // Move the piece down automatically.
            repaint(); // Update the display.
        }
    }

    /**
     * KeyListener implementation for handling key events.
     * It processes key events based on the pressed key.
     */
    public void keyPressed(KeyEvent e) { // KeyListener implementation for handling key events.
        if (gameOver) { // If the game is over:
            if (e.getKeyCode() == KeyEvent.VK_R) { // If R key is pressed.
                RestartGame.restartGame(); // Restart the game.
                return;
            } else if (e.getKeyCode() == KeyEvent.VK_E) { // If E key is pressed.
                System.exit(0); // Exit the game.
            }
            return; // If the game is over and the R / E keys are not pressed, return with no processing.
        }
        switch (e.getKeyCode()) { // Process key events based on the pressed key.
            case KeyEvent.VK_P:
                GameMoves.pauseOrResumeGame(this); // Pause the game if the "P" key is pressed.
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                GameMoves.moveLeft(); // Move the piece left if the left arrow key or A key is pressed.
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                GameMoves.moveRight(); // Move the piece left if the left arrow key or A key is pressed.
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                GameMoves.moveDown(this); // Move the piece down if the down arrow key or S key is pressed.
                break;
            case KeyEvent.VK_SPACE:
                GameMoves.rotate(); // Rotate the piece if the space key is pressed.
                break;
        }
        repaint(); // Update the display.
    }

    public void keyTyped(KeyEvent e) { // Do not delete!!!!
    }

    public void keyReleased(KeyEvent e) { // Do not delete!!!!
    }

    /**
     * Paint method to customize rendering.
     * It calls the paint method from the Paint class to render the Tetris game.
     */
    public void paint(Graphics g) { // paint method to customize rendering.
        Paint.paint(this, g); // Call the paint method from the Paint class.
    }
}