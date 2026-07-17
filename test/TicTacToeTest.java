import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for TicTacToe.hasWinner
 */
public class TicTacToeTest {

    @Test
    public void testRowWin() {
        char[][] board = {
            {'X', 'X', 'X'},
            {'O', ' ', 'O'},
            {' ', ' ', ' '}
        };
        assertTrue("row fully occupied by 'X' should win", TicTacToe.hasWinner(board));
    }

    @Test
    public void testDiagonalWin() {
        char[][] board = {
            {'O', 'X', ' '},
            {' ', 'O', 'X'},
            {' ', ' ', 'O'}
        };
        assertTrue("top-left to bottom-right diagonal of 'O' should win", TicTacToe.hasWinner(board));
    }

    @Test
    public void testNoWinner() {
        char[][] board = {
            {'X', 'O', 'X'},
            {'X', 'O', 'O'},
            {'O', 'X', 'X'}
        };
        assertFalse("board with no complete line should not have a winner", TicTacToe.hasWinner(board));
    }
}
