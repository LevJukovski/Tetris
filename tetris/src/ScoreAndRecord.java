
/**
 * The ScoreAndRecord class manages the score and record attributes for the Tetris game.
 * It provides methods to increase the score and update the record.
 */

public class ScoreAndRecord {
    public static int score; // Score attribute.
    public static int record; // Score record attribute;

    /**
     * Both of the functions below are used only in "removeFullRows()", located in the Board class.
     * they are used one after another: the score is increased by 1 when there is a full row, and the record is updated
     * when score > record.
     */
    public static void increaseScore() {
        score++; // Increase the score by 1.
    }

    public static void updateRecord() {
        if(score > record) { // if the score > the record.
            record = score; // update the record's value to the value of the score.
        }
    }
}