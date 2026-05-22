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
        assertNull("Empty board should have no winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' for horizontal win
     */
    @Test
    public void testHasWinnerHorizontalX() {
        // Set up a horizontal win for X: X X X in first row
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        
        assertEquals("Should detect horizontal X win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for horizontal win
     */
    @Test
    public void testHasWinnerHorizontalO() {
        // Set up a horizontal win for O: O O O in second row
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        
        assertEquals("Should detect horizontal O win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' for vertical win
     */
    @Test
    public void testHasWinnerVerticalX() {
        // Set up a vertical win for X: X in column 0, rows 0-2
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        assertEquals("Should detect vertical X win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for vertical win
     */
    @Test
    public void testHasWinnerVerticalO() {
        // Set up a vertical win for O: O in column 2, rows 0-2
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][2] = "O";
        
        assertEquals("Should detect vertical O win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' for forward diagonal win
     */
    @Test
    public void testHasWinnerDiagonalForwardX() {
        // Set up a forward diagonal win for X: (0,0), (1,1), (2,2)
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        
        assertEquals("Should detect forward diagonal X win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for reverse diagonal win
     */
    @Test
    public void testHasWinnerDiagonalReverseO() {
        // Set up a reverse diagonal win for O: (0,2), (1,1), (2,0)
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][0] = "O";
        
        assertEquals("Should detect reverse diagonal O win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns null when board is full but no winner
     */
    @Test
    public void testHasWinnerFullBoardNoWinner() {
        // Fill board with alternating X and O (tie game)
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "X";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][0] = "O";
        game.getBoard()[2][1] = "X";
        game.getBoard()[2][2] = "O";
        
        assertNull("Full board with no winner should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with partial board (no winner yet)
     */
    @Test
    public void testHasWinnerPartialBoard() {
        // Set up a partial board with no winner
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][1] = "X";
        
        assertNull("Partial board with no winner should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with 4x4 board and win config of 3
     */
    @Test
    public void testHasWinner4x4Board() {
        TicTacToe.GameConfig game4x4 = new TicTacToe.GameConfig(4, 3);
        
        // Set up a horizontal win for X in 4x4 board
        game4x4.getBoard()[0][0] = "X";
        game4x4.getBoard()[0][1] = "X";
        game4x4.getBoard()[0][2] = "X";
        
        assertEquals("Should detect win in 4x4 board", "X", TicTacToe.hasWinner(game4x4));
    }

    /**
     * Test hasWinner with 4x4 board and no winner
     */
    @Test
    public void testHasWinner4x4NoWinner() {
        TicTacToe.GameConfig game4x4 = new TicTacToe.GameConfig(4, 3);
        
        // Set up a board with only 2 in a row (not enough to win)
        game4x4.getBoard()[0][0] = "X";
        game4x4.getBoard()[0][1] = "X";
        game4x4.getBoard()[0][3] = "O";
        
        assertNull("Should return null when no winner in 4x4 board", TicTacToe.hasWinner(game4x4));
    }

    /**
     * Test hasWinner returns first winner found (horizontal takes precedence)
     */
    @Test
    public void testHasWinnerMultipleWinConditions() {
        // Set up both horizontal and vertical win for X
        // Horizontal: X X X in row 0
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        // Vertical: X X X in column 0
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        // Should return X (either horizontal or vertical is fine)
        assertEquals("Should detect winner when multiple win conditions exist", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with only 2 consecutive marks (not enough to win)
     */
    @Test
    public void testHasWinnerInsufficientMarks() {
        // Set up only 2 X's in a row
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "O";
        
        assertNull("Two consecutive marks should not be a winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with marks separated by empty cells
     */
    @Test
    public void testHasWinnerSeparatedMarks() {
        // Set up X's separated by empty cells
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][2] = "X";
        
        assertNull("Separated marks should not be a winner", TicTacToe.hasWinner(game));
    }
}
