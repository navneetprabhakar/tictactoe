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
        // Create a 3x3 board with win config of 3
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
     * Test hasWinner returns 'X' when X wins horizontally
     */
    @Test
    public void testHasWinnerHorizontalX() {
        // Set up a winning horizontal line for X
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        
        assertEquals("X should win horizontally", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' when O wins horizontally
     */
    @Test
    public void testHasWinnerHorizontalO() {
        // Set up a winning horizontal line for O
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        
        assertEquals("O should win horizontally", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' when X wins vertically
     */
    @Test
    public void testHasWinnerVerticalX() {
        // Set up a winning vertical line for X
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        assertEquals("X should win vertically", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' when O wins vertically
     */
    @Test
    public void testHasWinnerVerticalO() {
        // Set up a winning vertical line for O
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][1] = "O";
        
        assertEquals("O should win vertically", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' when X wins diagonally (forward)
     */
    @Test
    public void testHasWinnerDiagonalForwardX() {
        // Set up a winning diagonal line for X (top-left to bottom-right)
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        
        assertEquals("X should win diagonally (forward)", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' when O wins diagonally (reverse)
     */
    @Test
    public void testHasWinnerDiagonalReverseO() {
        // Set up a winning diagonal line for O (top-right to bottom-left)
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][0] = "O";
        
        assertEquals("O should win diagonally (reverse)", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns null when board is full but no winner
     */
    @Test
    public void testHasWinnerFullBoardNoWinner() {
        // Fill the board with no winner (draw scenario)
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][0] = "O";
        game.getBoard()[2][1] = "X";
        game.getBoard()[2][2] = "X";
        
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
        game.getBoard()[1][0] = "X";
        
        assertNull("Partial board with no winner should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with 4x4 board and win config of 3
     */
    @Test
    public void testHasWinner4x4Board() {
        // Create a 4x4 board with win config of 3
        TicTacToe.GameConfig game4x4 = new TicTacToe.GameConfig(4, 3);
        
        // Set up a winning horizontal line for X
        game4x4.getBoard()[0][0] = "X";
        game4x4.getBoard()[0][1] = "X";
        game4x4.getBoard()[0][2] = "X";
        
        assertEquals("X should win on 4x4 board", "X", TicTacToe.hasWinner(game4x4));
    }

    /**
     * Test hasWinner with 2x2 board and win config of 2
     */
    @Test
    public void testHasWinner2x2Board() {
        // Create a 2x2 board with win config of 2
        TicTacToe.GameConfig game2x2 = new TicTacToe.GameConfig(2, 2);
        
        // Set up a winning diagonal line for O
        game2x2.getBoard()[0][0] = "O";
        game2x2.getBoard()[1][1] = "O";
        
        assertEquals("O should win on 2x2 board", "O", TicTacToe.hasWinner(game2x2));
    }

    /**
     * Test hasWinner returns null when only one mark on board
     */
    @Test
    public void testHasWinnerSingleMark() {
        game.getBoard()[0][0] = "X";
        
        assertNull("Single mark should not produce a winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with two consecutive marks (no winner)
     */
    @Test
    public void testHasWinnerTwoConsecutiveMarks() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        
        assertNull("Two consecutive marks should not produce a winner", TicTacToe.hasWinner(game));
    }
}
