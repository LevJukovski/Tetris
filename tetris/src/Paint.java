// Import necessary packages.
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Paint class handles the rendering of the Tetris game.
 */

public class Paint {

    /**
     * The paint function renders the Tetris game.
     * @param tetrisGame the TetrisGame instance.
     * @param g the Graphics object to draw on.
     */
    public static void paint(TetrisGame tetrisGame, Graphics g) {
        // Create an off-screen buffer for smooth rendering.
        BufferedImage offScreenBuffer = new BufferedImage(tetrisGame.getWidth(), tetrisGame.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics object from the off-screen buffer.
        Graphics offScreenGraphics = offScreenBuffer.getGraphics();

        // Clear the off-screen buffer with white color.
        offScreenGraphics.setColor(Color.WHITE);
        offScreenGraphics.fillRect(0, 0, tetrisGame.getWidth(), tetrisGame.getHeight());

        drawGridLines(offScreenGraphics); // Draw the grid lines.

        if (tetrisGame.gameOver) { // If the game is over:
            drawEndScreen(tetrisGame, offScreenGraphics); // Draw the end screen.
        } else { // Otherwise, if the game is not over:
            drawTetrisGame(offScreenGraphics);  // Draw the Tetris game elements (board, current piece, score, message).
        }

        g.drawImage(offScreenBuffer, 0, 0, null); // Transfer the off-screen buffer to the screen.
    }

    /**
     * Draws the grid lines of the game board.
     * @param offScreenGraphics is the Graphics object to draw on.
     */
    private static void drawGridLines(Graphics offScreenGraphics) {
        // Draw the horizontal grid lines.
        offScreenGraphics.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= 20; i++) {
            offScreenGraphics.drawLine(0, i * 30, 10 * 30, i * 30);
        }

        // Draw the vertical grid lines.
        for (int j = 0; j <= 10; j++) {
            offScreenGraphics.drawLine(j * 30, 0, j * 30, 20 * 30);
        }
    }

    /**
     * Draws the end screen when the game is over.
     * @param tetrisGame is the TetrisGame instance.
     * @param offScreenGraphics is the Graphics object to draw on.
     */
    private static void drawEndScreen(TetrisGame tetrisGame, Graphics offScreenGraphics) {
        // Fill the background with blue color.
        offScreenGraphics.setColor(Color.BLUE);
        offScreenGraphics.fillRect(0, 0, tetrisGame.getWidth(), tetrisGame.getHeight());

        // Draw "Game Over!" text.
        // Set the font for drawing the "Game Over!" text.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font for the text.
        FontMetrics fm = offScreenGraphics.getFontMetrics(); // Get font metrics to measure the dimensions of the text.
        int x = (tetrisGame.getWidth() - fm.stringWidth("Game Over!")) / 2; // Calculation for x-coordinate to center the text.
        int y = tetrisGame.getHeight() / 4; // Calculation for y-coordinate to position the text vertically (1/4th of the height).
        offScreenGraphics.setColor(Color.WHITE); // Set the color for drawing the text (white).
        offScreenGraphics.drawString("Game Over!", x, y); // Draw the "Game Over!" text at the calculated position (x, y).

        // Draw "Thank you for playing Tetris!" text.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 20));
        fm = offScreenGraphics.getFontMetrics();
        x = (tetrisGame.getWidth() - fm.stringWidth("Thank you for playing Tetris!")) / 2;
        y += 40; // Move down from the "Game Over!" text
        offScreenGraphics.drawString("Thank you for playing Tetris!", x, y);

        // Draw "Press R to Replay" message.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 20));
        fm = offScreenGraphics.getFontMetrics();
        x = (tetrisGame.getWidth() - fm.stringWidth("Press R to Replay")) / 2;
        y = tetrisGame.getHeight() / 4 * 3; // Move down from the "Thank you for playing Tetris!" text.
        offScreenGraphics.drawString("Press R to Replay", x, y);

        // Draw "Press E to Replay" message.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 20));
        fm = offScreenGraphics.getFontMetrics();
        x = (tetrisGame.getWidth() - fm.stringWidth("Press E to Replay")) / 2;
        y += 40; // Move down from the "Thank you for playing Tetris!" text.
        offScreenGraphics.drawString("Press E to Replay", x, y);

        // Display the final score.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 30));
        fm = offScreenGraphics.getFontMetrics();
        x = tetrisGame.getWidth() / 2 - fm.stringWidth("Score: " + ScoreAndRecord.score) / 2;
        y = tetrisGame.getHeight() / 2;
        offScreenGraphics.drawString("Score: " + ScoreAndRecord.score, x, y);

        // Display the record.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 30));
        fm = offScreenGraphics.getFontMetrics();
        x = tetrisGame.getWidth() / 2 - fm.stringWidth("Record: " + ScoreAndRecord.record) / 2;
        y += 50;
        offScreenGraphics.drawString("Record: " + ScoreAndRecord.record, x, y);
    }

    /**
     * Drawing the Tetris game elements (board, current piece, score, message).
     * @param offScreenGraphics the Graphics object to draw on. */
    private static void drawTetrisGame(Graphics offScreenGraphics) {
        // Draw the board.
        for (int i = 0; i < 20; i++) { // For all the rows.
            for (int j = 0; j < 10; j++) { // For all the columns.
                if (Board.board[i][j] == 1) { // Check if there's a block at the current position.
                    offScreenGraphics.setColor(Color.GRAY); // Set color for filled block.
                    offScreenGraphics.fillRect(j * 30, i * 30, 30, 30);
                    offScreenGraphics.setColor(Color.BLACK); // // Set color for block outline.
                    // Draw a rectangle outline around the block with black color.
                    offScreenGraphics.drawRect(j * 30, i * 30, 30, 30);
                }
            }
        }

        // Draw the current piece with its assigned color.
        for (int i = 0; i < Pieces.currentPiece.length; i++) { // For all the rows.
            for (int j = 0; j < Pieces.currentPiece[0].length; j++) { // For all the columns.
                if (Pieces.currentPiece[i][j] == 1) { // Check if there's a block at the current position.
                    offScreenGraphics.setColor(Pieces.pieceColor); // Set color for a filled block (random color).
                    offScreenGraphics.fillRect((Pieces.currentPieceX + j) * 30, (Pieces.currentPieceY + i) * 30, 30, 30);
                    offScreenGraphics.setColor(Color.BLACK); // // Set color for block outline.
                    // Draw a rectangle outline around the block with black color.
                    offScreenGraphics.drawRect((Pieces.currentPieceX + j) * 30, (Pieces.currentPieceY + i) * 30, 30, 30);
                }
            }
        }

        // Display the (live) score of the player.
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font for the text.
        FontMetrics fm = offScreenGraphics.getFontMetrics(); // Get font metrics to measure the dimensions of the text.
        int x = 10; // X-coordinate.
        int y = 47; // Y-coordinate.
        offScreenGraphics.setColor(Color.BLACK); // Set the color for drawing the text (black).

        // Draw the score of the player at the calculated position (x, y).
        offScreenGraphics.drawString("Score: " + ScoreAndRecord.score, x, y);

        // Display the message "p = pause".
        offScreenGraphics.setFont(new Font("Arial", Font.BOLD, 17));
        fm = offScreenGraphics.getFontMetrics();
        y += 23;
        offScreenGraphics.drawString("p = pause", x, y);
    }
}