
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author navneetprabhakar
 */
public class TicTacToe {

    /**
     * @author navneetprabhakar
     */
    public static class GameConfig{
        private Integer boardSize; // e.g. boardSize=3 means 3X3 board
        private Integer winConfig; // e.g. winConfig=3 means 3 consecutive 'X' or 'O' in horizontal, vertical or diagonal direction
        private String[][] board; // actual game board

        public Integer getBoardSize() {
            return boardSize;
        }
        public Integer getWinConfig() {
            return winConfig;
        }
        public String[][] getBoard() {
            return board;
        }

        private GameConfig(Integer boardSize, Integer winConfig){
            this.boardSize=boardSize;
            this.winConfig=winConfig;
            this.board=new String[boardSize][boardSize];
        }
    }

    /**
     * Game is over when:
     * # Board is full
     * # Any player has reached the winning config
     * @param @GameConfig(boardSize, winConfig, board[][])
     * @return
     */
    public static boolean isGameOver(GameConfig game){
        return  checkWinningConfig(game) || checkIfBoardFull(game);
    }

    /**
     * Check if character is valid and location is already occupied or not
     */
    private static boolean isValidMove(GameConfig game, char ch, int row, int column){
        return (row<game.getBoardSize() && column<game.getBoardSize()) && (ch =='X' || ch=='O' ) && null==game.getBoard()[row][column];
    }

    /**
     * Update Board with received character
     */
    private static void makeMove(GameConfig game, char ch, int row, int column){
        game.getBoard()[row][column]=String.valueOf(ch);
    }

    /**
     * Check all position in the board if any value is null return false
     * @param @GameConfig(boardSize, winConfig, board[][])
     * @return boolean
     */
    private static boolean checkIfBoardFull(GameConfig game){
        for(int i=0;i<game.getBoardSize();i++){
            for(int j=0;j< game.getBoardSize();j++){
                if(game.getBoard()[i][j]==null){
                    return false;
                }
            }
        }
        System.out.println("Board full, no move possible. Game tied.");
        return true;
    }

    /**
     * Check board for winning combination
     * @param @GameConfig(boardSize, winConfig, board[][])
     * @return boolean
     */
    private static boolean checkWinningConfig(GameConfig game){
        return horizontalCheck(game) || verticalCheck(game) || diagonalCheck(game);
    }

    /**
     * Check horizontally in board whether there is continuous sequence of same character and count is >= winning conf
     * @param @GameConfig(boardSize, winConfig, board[][])
     * @return boolean
     */
    private static boolean horizontalCheck(GameConfig game){
        for(int i=0;i<game.getBoardSize();i++){
            // New Row
            int count=0;
            String curr="";
            for(int j=0;j< game.getBoardSize();j++){
                if(count>=game.getWinConfig()){
                    System.out.println(curr+" wins");
                    // Game finished horizontal match
                    return true;
                }
                if(game.getBoard()[i][j]==null){
                    // empty cell
                    curr="";
                    count=0;
                }else{
                    if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                        // character match increase counter
                        count++;
                    }else{
                        // reset counter different character
                        curr=game.getBoard()[i][j];
                        count=1;
                    }
                }
            }
            if(count>=game.getWinConfig()){
                System.out.println(curr+" wins");
                // Game finished horizontal match
                return true;
            }
        }
        return false;
    }

    /**
     * Check vertically in board whether there is continuous sequence of same character and count is >= winning conf
     * @param @GameConfig(boardSize, winConfig, board[][])
     * @return boolean
     */
    private static boolean verticalCheck(GameConfig game){
        for(int j=0;j<game.getBoardSize();j++){
            // New Column
            int count=0;
            String curr="";
            for(int i=0;i< game.getBoardSize();i++){
                if(count>=game.getWinConfig()){
                    System.out.println(curr+" wins");
                    // Game finished vertical match
                    return true;
                }
                if(game.getBoard()[i][j]==null){
                    // empty cell
                    curr="";
                    count=0;
                }else{
                    if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                        // character match increase counter
                        count++;
                    }else{
                        // reset counter different character
                        curr=game.getBoard()[i][j];
                        count=1;
                    }
                }
            }
            if(count>=game.getWinConfig()){
                System.out.println(curr+" wins");
                // Game finished vertical match
                return true;
            }
        }
        return false;
    }

    /**
     * Check diagonally in board whether there is continuous sequence of same character and count is >= winning conf
     * @param game
     * @return
     */
    private static boolean diagonalCheck(GameConfig game){
        int count=0;
        String curr="";
        // Forward diagonal
        for(int i=0,j=0;i< game.getBoardSize() && j< game.getBoardSize(); i++,j++){
            if(count>=game.getWinConfig()){
                System.out.println(curr+" wins");
                // Game finished diagonal match
                return true;
            }
            if(game.getBoard()[i][j]==null){
                // empty cell
                curr="";
                count=0;
            }else{
                if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                    // character match increase counter
                    count++;
                }else{
                    // reset counter different character
                    curr=game.getBoard()[i][j];
                    count=1;
                }
            }
        }
        if(count>=game.getWinConfig()){
            System.out.println(curr+" wins");
            return true;
        }
        // Reverse diagonal
        count=0;
        curr="";
        for(int i=0,j=game.getBoardSize()-1;i< game.getBoardSize() && j>= 0; i++,j--){
            if(count>=game.getWinConfig()){
                System.out.println(curr+" wins");
                // Game finished diagonal match
                return true;
            }
            if(game.getBoard()[i][j]==null){
                // empty cell
                curr="";
                count=0;
            }else{
                if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                    // character match increase counter
                    count++;
                }else{
                    // reset counter different character
                    curr=game.getBoard()[i][j];
                    count=1;
                }
            }
        }
        if(count>=game.getWinConfig()){
            System.out.println(curr+" wins");
            return true;
        }
        return false;
    }

    /**
     * Print Board
     * @param game
     */
    private static void printBoard(GameConfig game){
        for(int i=0;i<game.getBoardSize();i++){
            for(int j=0;j< game.getBoardSize();j++){
                if(null==game.getBoard()[i][j]){
                    System.out.print(" ");
                }else{
                    System.out.print(game.getBoard()[i][j]);
                }
                if(j!=game.getBoardSize()-1){
                    System.out.print(" |");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Computer calculates all possible moves and then finds the best one out of them.
     * Works efficiently till we have 3x3 board as for 4x4 and above the number of possible moves increase exponentially
     */
    private static void computerMove(GameConfig game, char ch, char other){
        System.out.println("Computer calculating all possible moves.");
        AIPlayer.PossibleMove move= AIPlayer.findBestMove(game, ch, other);
        System.out.println("Computer moves row:"+move.getRow()+" column:"+move.getColumn());
        game.getBoard()[move.getRow()][move.getColumn()]=String.valueOf(ch);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter board size in n X n:");
        Integer boardSize=Integer.valueOf(reader.readLine());
        System.out.println("Enter the continuous number of same character required for winning:");
        Integer winConfig=Integer.valueOf(reader.readLine());
        while(winConfig>boardSize){
            System.out.println("Wrong winning config.");
            System.out.println("Enter the continuous number of same character required for winning:");
            winConfig=Integer.valueOf(reader.readLine());
        }
        GameConfig game=new GameConfig(boardSize,winConfig);
        System.out.println("Player 1:X, Player 2:O");
        System.out.println("Please select Player 1. Enter 1 for Computer, Enter any key for Human");
        String chance=reader.readLine();
        Integer row=-1;
        Integer column=-1;
        char comp='X';
        char player='O';
        switch (chance){
            case"1":
                System.out.println("Computer will make first move.");
                computerMove(game,comp,player);
                break;
            default:
                player='X';
                comp='O';
                System.out.println("Enter row (starts with 0):");
                row=Integer.valueOf(reader.readLine());
                System.out.println("Enter column (starts with 0):");
                column=Integer.valueOf(reader.readLine());
                makeMove(game,player,row,column);
                printBoard(game);
                computerMove(game,comp,player);
                break;
        }
        printBoard(game);
        while(!isGameOver(game)){
            System.out.println("Enter row (starts with 0):");
            row=Integer.valueOf(reader.readLine());
            System.out.println("Enter column (starts with 0):");
            column=Integer.valueOf(reader.readLine());
            if(isValidMove(game,comp,row,column)){
                makeMove(game,player,row,column);
                if(!isGameOver(game)){
                    computerMove(game,comp,player);
                    printBoard(game);
                }else{
                    break;
                }
            }else{
                System.out.println("Wrong move. Please try again.");
                printBoard(game);
            }
        }

    }
}
