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
     * Test hasWinner returns null when board has moves but no winner
     */
    @Test
    public void testHasWinnerNoWinnerYet() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[1][0] = "X";
        game.getBoard()[1][1] = "O";
        
        assertNull("Board with no winning combination should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects horizontal win for X
     */
    @Test
    public void testHasWinnerHorizontalX() {
        // X wins horizontally in first row
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        
        assertEquals("Should detect horizontal win for X", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects horizontal win for O
     */
    @Test
    public void testHasWinnerHorizontalO() {
        // O wins horizontally in second row
        game.getBoard()[1][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        
        assertEquals("Should detect horizontal win for O", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects vertical win for X
     */
    @Test
    public void testHasWinnerVerticalX() {
        // X wins vertically in first column
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        assertEquals("Should detect vertical win for X", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects vertical win for O
     */
    @Test
    public void testHasWinnerVerticalO() {
        // O wins vertically in third column
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][2] = "O";
        
        assertEquals("Should detect vertical win for O", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects diagonal win (top-left to bottom-right) for X
     */
    @Test
    public void testHasWinnerDiagonalForwardX() {
        // X wins diagonally from top-left to bottom-right
        game.getBoard()[0][0] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][2] = "X";
        
        assertEquals("Should detect forward diagonal win for X", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects diagonal win (top-left to bottom-right) for O
     */
    @Test
    public void testHasWinnerDiagonalForwardO() {
        // O wins diagonally from top-left to bottom-right
        game.getBoard()[0][0] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][2] = "O";
        
        assertEquals("Should detect forward diagonal win for O", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects diagonal win (top-right to bottom-left) for X
     */
    @Test
    public void testHasWinnerDiagonalReverseX() {
        // X wins diagonally from top-right to bottom-left
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][1] = "X";
        game.getBoard()[2][0] = "X";
        
        assertEquals("Should detect reverse diagonal win for X", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner detects diagonal win (top-right to bottom-left) for O
     */
    @Test
    public void testHasWinnerDiagonalReverseO() {
        // O wins diagonally from top-right to bottom-left
        game.getBoard()[0][2] = "O";
        game.getBoard()[1][1] = "O";
        game.getBoard()[2][0] = "O";
        
        assertEquals("Should detect reverse diagonal win for O", "O", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with larger board (4x4 with win config 3)
     */
    @Test
    public void testHasWinnerLargerBoard() {
        TicTacToe.GameConfig largeGame = new TicTacToe.GameConfig(4, 3);
        
        // X wins with 3 in a row horizontally
        largeGame.getBoard()[0][0] = "X";
        largeGame.getBoard()[0][1] = "X";
        largeGame.getBoard()[0][2] = "X";
        
        assertEquals("Should detect win on larger board", "X", TicTacToe.hasWinner(largeGame));
    }

    /**
     * Test hasWinner with board that has mixed marks but no winner
     */
    @Test
    public void testHasWinnerMixedBoardNoWinner() {
        // Classic tie game scenario
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "O";
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[1][1] = "O";
        game.getBoard()[1][2] = "O";
        game.getBoard()[2][0] = "O";
        game.getBoard()[2][1] = "X";
        game.getBoard()[2][2] = "X";
        
        assertNull("Tie game should have no winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner returns first winner found (horizontal takes precedence)
     */
    @Test
    public void testHasWinnerMultipleWinningConditions() {
        // Create a scenario where both horizontal and vertical could be winners
        // but horizontal is checked first
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        game.getBoard()[0][2] = "X";
        game.getBoard()[1][0] = "X";
        game.getBoard()[2][0] = "X";
        
        assertEquals("Should return winner when multiple winning conditions exist", "X", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with null values in board (empty cells)
     */
    @Test
    public void testHasWinnerWithEmptyCells() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        // [0][2] is null
        game.getBoard()[1][0] = "O";
        
        assertNull("Board with empty cells and no winner should return null", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with single mark on board
     */
    @Test
    public void testHasWinnerSingleMark() {
        game.getBoard()[0][0] = "X";
        
        assertNull("Single mark should not be a winner", TicTacToe.hasWinner(game));
    }

    /**
     * Test hasWinner with two consecutive marks (not enough for win)
     */
    @Test
    public void testHasWinnerTwoConsecutiveMarks() {
        game.getBoard()[0][0] = "X";
        game.getBoard()[0][1] = "X";
        
        assertNull("Two consecutive marks should not be a winner (need 3)", TicTacToe.hasWinner(game));
    }
}
