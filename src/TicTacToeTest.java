import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for TicTacToe.hasWinner() method
 */
public class TicTacToeTest {

    private TicTacToe.GameConfig game;

    @Before
    public void setUp() {
        // Initialize a 3x3 board with win config of 3
        game = new TicTacToe.GameConfig(3, 3);
    }

    /**
     * Test hasWinner returns null when board is empty
     */
    @Test
    public void testHasWinnerEmptyBoard() {
        String winner = TicTacToe.hasWinner(game);
        assertNull("Empty board should have no winner", winner);
    }

    /**
     * Test hasWinner returns 'X' for horizontal win
     */
    @Test
    public void testHasWinnerHorizontalX() {
        // Set up horizontal win for X: X X X in first row
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("X should win horizontally", "X", winner);
    }

    /**
     * Test hasWinner returns 'O' for horizontal win
     */
    @Test
    public void testHasWinnerHorizontalO() {
        // Set up horizontal win for O: O O O in second row
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("O should win horizontally", "O", winner);
    }

    /**
     * Test hasWinner returns 'X' for vertical win
     */
    @Test
    public void testHasWinnerVerticalX() {
        // Set up vertical win for X: X in column 0, rows 0-2
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("X should win vertically", "X", winner);
    }

    /**
     * Test hasWinner returns 'O' for vertical win
     */
    @Test
    public void testHasWinnerVerticalO() {
        // Set up vertical win for O: O in column 2, rows 0-2
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][2] = "O";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("O should win vertically", "O", winner);
    }

    /**
     * Test hasWinner returns 'X' for diagonal win (top-left to bottom-right)
     */
    @Test
    public void testHasWinnerDiagonalX() {
        // Set up diagonal win for X: X at (0,0), (1,1), (2,2)
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("X should win diagonally", "X", winner);
    }

    /**
     * Test hasWinner returns 'O' for reverse diagonal win (top-right to bottom-left)
     */
    @Test
    public void testHasWinnerReverseDiagonalO() {
        // Set up reverse diagonal win for O: O at (0,2), (1,1), (2,0)
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][0] = "O";
        
        String winner = TicTacToe.hasWinner(game);
        assertEquals("O should win on reverse diagonal", "O", winner);
    }

    /**
     * Test hasWinner returns null when board is full but no winner
     */
    @Test
    public void testHasWinnerFullBoardNoWinner() {
        // Fill board with alternating X and O, no winner
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "X";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][0] = "O";
        game.getBoard()[2][1] = "X";
        game.getBoard()[2][2] = "O";
        
        String winner = TicTacToe.hasWinner(game);
        assertNull("Full board with no winner should return null", winner);
    }

    /**
     * Test hasWinner returns null when board is partially filled with no winner
     */
    @Test
    public void testHasWinnerPartialBoardNoWinner() {
        // Partially fill board with no winning combination
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][1] = "X";
        
        String winner = TicTacToe.hasWinner(game);
        assertNull("Partial board with no winner should return null", winner);
    }

    /**
     * Test hasWinner with 4x4 board and win config of 3
     */
    @Test
    public void testHasWinner4x4Board() {
        // Create a 4x4 board with win config of 3
        TicTacToe.GameConfig game4x4 = new TicTacToe.GameConfig(4, 3);
        
        // Set up horizontal win for X in first row
        game4x4.getBoard()[0][0] = "X";
        game4x4.getBoard()[0][1] = "X";
        game4x4.getBoard()[0][2] = "X";
        
        String winner = TicTacToe.hasWinner(game4x4);
        assertEquals("X should win on 4x4 board", "X", winner);
    }

    /**
     * Test hasWinner with incomplete sequence (should return null)
     */
    @Test
    public void testHasWinnerIncompleteSequence() {
        // Set up incomplete horizontal sequence
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        // Missing third X
        
        String winner = TicTacToe.hasWinner(game);
        assertNull("Incomplete sequence should return null", winner);
    }

    /**
     * Test hasWinner with mixed marks (should return null)
     */
    @Test
    public void testHasWinnerMixedMarks() {
        // Set up sequence with mixed marks
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[0][2] = "X";
        
        String winner = TicTacToe.hasWinner(game);
        assertNull("Mixed marks should return null", winner);
    }
}
