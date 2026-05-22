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
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        assertEquals("Should return X for horizontal win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for horizontal win
     */
    @Test
    public void testHasWinnerHorizontalO() {
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        assertEquals("Should return O for horizontal win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' for vertical win
     */
    @Test
    public void testHasWinnerVerticalX() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        assertEquals("Should return X for vertical win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for vertical win
     */
    @Test
    public void testHasWinnerVerticalO() {
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][1] = "O";
        assertEquals("Should return O for vertical win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'X' for diagonal win (top-left to bottom-right)
     */
    @Test
    public void testHasWinnerDiagonalX() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        assertEquals("Should return X for diagonal win", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns 'O' for diagonal win (top-right to bottom-left)
     */
    @Test
    public void testHasWinnerDiagonalO() {
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][0] = "O";
        assertEquals("Should return O for diagonal win", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns null when no winning combination exists
     */
    @Test
    public void testHasWinnerNoWinner() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        assertNull("Board with no winning combination should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with partial line (not enough for win)
     */
    @Test
    public void testHasWinnerPartialLine() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        assertNull("Partial line should not be a winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with 4x4 board and win config of 3
     */
    @Test
    public void testHasWinner4x4Board() {
        TicTacToe.GameConfig game4x4 = new TicTacToe.GameConfig(4, 3);
        game4x4.getBoard()[0][0] = "X";
        game4x4.getBoard()[0][1] = "X";
        game4x4.getBoard()[0][2] = "X";
        assertEquals("Should return X for 4x4 board with 3 in a row", "X", TicTacToe.hasWinner(game4x4));
    }

    /**
     * Test hasWinner with interrupted line
     */
    @Test
    public void testHasWinnerInterruptedLine() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "O";
        assertNull("Interrupted line should not be a winner", TicTacToe.hasWinner(game));
    }
}
