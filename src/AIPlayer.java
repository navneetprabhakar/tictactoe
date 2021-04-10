import java.util.HashMap;
import java.util.Map;

/**
 * @author navneetprabhakar
 */
public class AIPlayer {

    /**
     * @author navneetprabhakar
     */
    public static class PossibleMove {
        private Integer row;
        private Integer column;

        public PossibleMove(Integer row, Integer column){
            this.row=row;
            this.column=column;
        }

        public Integer getRow() {
            return row;
        }
        public void setRow(Integer row) {
            this.row = row;
        }
        public Integer getColumn() {
            return column;
        }
        public void setColumn(Integer column) {
            this.column = column;
        }
    }

    /**
     * Calculate Best Possible move by calculating all possible moves and assigning them a score
     */
    public static PossibleMove findBestMove(TicTacToe.GameConfig game, char ch, char other){
        Integer best=Integer.MIN_VALUE;
        PossibleMove move=new PossibleMove(-1,-1);
        for (int i=0; i<game.getBoardSize(); i++){
            for (int j=0; j<game.getBoardSize(); j++) {
                // If cell is empty
                if (game.getBoard()[i][j] == null) {
                    game.getBoard()[i][j] = String.valueOf(ch);
                    // Calculate score by Minimax algo.
                    int moveScore = minimax(game, 0, false, ch, other,new HashMap<>());
                    // Undo the move
                    game.getBoard()[i][j] = null;
                    // Check max and update Possible move
                    if (moveScore > best) {
                        move.setRow(i);
                        move.setColumn(j);
                        best = moveScore;

                    }
                }
            }
        }
        return move;
    }

    /**
     * Generate unique cache string for possible move
     */
    private static String generateCacheString(TicTacToe.GameConfig game, char ch){
        StringBuilder sb=new StringBuilder(String.valueOf(ch)+"_");
        for(int i=0;i<game.getBoardSize();i++){
            for(int j=0;j< game.getBoardSize();j++){
                sb.append(game.getBoard()[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * Calculate possible score of each possible combination move by placing either computer character or player character
     * Dynamic Programming (Memoization) used to improve execution time
     */
    public static int minimax(TicTacToe.GameConfig game, int depth, Boolean isMax, char ch, char other, Map<String, Integer> CACHE){
        String key=generateCacheString(game, ch);
        if(CACHE.containsKey(key)){
            return CACHE.get(key);
        }
        Integer score=evaluate(game,ch);
        if(score!=0){
            return score;
        }
        if(!isMoveLeft(game)){
            return 0;
        }

        int best;
        if (isMax) {
            // Maximizer's move (Computer character move)
            best = Integer.MIN_VALUE;
            // Traverse all cells
            for (int i=0; i<game.getBoardSize(); i++) {
                for (int j=0; j<game.getBoardSize(); j++) {
                    if (game.getBoard()[i][j]==null) {
                        game.getBoard()[i][j] = String.valueOf(ch);
                        // Call minimax recursively and choose the maximum value
                        best = Math.max(best, minimax(game, depth + 1, !isMax,ch, other,CACHE));
                        // Undo the move
                        game.getBoard()[i][j] = null;
                    }
                }
            }
        } else {
            // Minimizer's move (Other player move)
            best = Integer.MAX_VALUE;
            // Traverse all cells
            for (int i=0; i<game.getBoardSize(); i++) {
                for (int j=0; j<game.getBoardSize(); j++) {
                    if (game.getBoard()[i][j]==null) {
                        game.getBoard()[i][j] = String.valueOf(other);
                        // Call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(game, depth + 1, !isMax, ch, other,CACHE));
                        // Undo the move
                        game.getBoard()[i][j] = null;
                    }
                }
            }
        }
        CACHE.put(key,best);
        return best;
    }

    /**
     * Check if any valid moves are left for the computer
     * @param game
     * @return
     */
    public static boolean isMoveLeft(TicTacToe.GameConfig game){
        for(int i=0;i<game.getBoardSize();i++){
            for(int j=0;j<game.getBoardSize();j++){
                if(game.getBoard()[i][j]==null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if computer wins or loses by making horizontal,vertical or diagonal check else return 0 as score
     */
    public static int evaluate(TicTacToe.GameConfig game, char ch) {
        Integer horizontal=horizontalCheck(game, ch);
        if(horizontal!=0)
            return horizontal;
        Integer vertical=verticalCheck(game, ch);
        if(vertical!=0)
            return vertical;
        Integer diagonal=diagonalCheck(game, ch);
        if(diagonal!=0)
            return diagonal;
        return 0;
    }

    /**
     * Check horizontally in board whether there is winning option
     * return 10 if yes, return -10 if opponent wins return 0 if no one wins
     * @param @GameConfig(boardSize, winConfig, board[][])
     */
    private static int horizontalCheck(TicTacToe.GameConfig game, char ch){
        for(int i=0;i<game.getBoardSize();i++){
            int count=0;
            String curr="";
            for(int j=0;j< game.getBoardSize();j++){
                if(count>=game.getWinConfig()){
                    if(String.valueOf(ch).equals(curr)){
                        // Victory
                        return 10;
                    }else{
                        // Loss
                        return -10;
                    }
                }
                if(game.getBoard()[i][j]==null){
                    curr="";
                    count=0;
                }else{
                    if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                        count++;
                    }else{
                        // reset counter different character
                        curr=game.getBoard()[i][j];
                        count=1;
                    }
                }
            }
            if(count>=game.getWinConfig()){
                if(String.valueOf(ch).equals(curr)){
                    // Victory
                    return 10;
                }else{
                    // Loss
                    return -10;
                }
            }
        }
        return 0;
    }

    /**
     * Check vertically in board whether there is winning option
     * return 10 if yes, return -10 if opponent wins return 0 if no one wins
     * @param @GameConfig(boardSize, winConfig, board[][])
     */
    private static int verticalCheck(TicTacToe.GameConfig game, char ch){
        for(int j=0;j<game.getBoardSize();j++){
            int count=0;
            String curr="";
            for(int i=0;i< game.getBoardSize();i++){
                if(count>=game.getWinConfig()){
                    if(String.valueOf(ch).equals(curr)){
                        // Victory
                        return 10;
                    }else{
                        // Loss
                        return -10;
                    }
                }
                if(game.getBoard()[i][j]==null){
                    curr="";
                    count=0;
                }else{
                    if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                        count++;
                    }else{
                        curr=game.getBoard()[i][j];
                        count=1;
                    }
                }
            }
            if(count>=game.getWinConfig()){
                if(String.valueOf(ch).equals(curr)){
                    // Victory
                    return 10;
                }else{
                    // Loss
                    return -10;
                }
            }
        }
        return 0;
    }

    /**
     * Check diagonally in board whether there is winning option
     * return 10 if yes, return -10 if opponent wins return 0 if no one wins
     * @param @GameConfig(boardSize, winConfig, board[][])
     */
    private static int diagonalCheck(TicTacToe.GameConfig game, char ch){
        int count=0;
        String curr="";
        // Forward diagonal
        for(int i=0,j=0;i< game.getBoardSize() && j< game.getBoardSize(); i++,j++){
            if(count>=game.getWinConfig()){
                if(String.valueOf(ch).equals(curr)){
                    // Victory
                    return 10;
                }else{
                    // Loss
                    return -10;
                }
            }
            if(game.getBoard()[i][j]==null){
                curr="";
                count=0;
            }else{
                if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                    count++;
                }else{
                    curr=game.getBoard()[i][j];
                    count=1;
                }
            }
        }
        if(count>=game.getWinConfig()){
            if(String.valueOf(ch).equals(curr)){
                // Victory
                return 10;
            }else{
                // Loss
                return -10;
            }
        }
        // Reverse diagonal
        count=0;
        curr="";
        for(int i=0,j=game.getBoardSize()-1;i< game.getBoardSize() && j>= 0; i++,j--){
            if(count>=game.getWinConfig()){
                if(String.valueOf(ch).equals(curr)){
                    // Victory
                    return 10;
                }else{
                    // Loss
                    return -10;
                }
            }
            if(game.getBoard()[i][j]==null){
                curr="";
                count=0;
            }else{
                if(!curr.isEmpty() && curr.equals(game.getBoard()[i][j])){
                    count++;
                }else{
                    curr=game.getBoard()[i][j];
                    count=1;
                }
            }
        }
        if(count>=game.getWinConfig()){
            if(String.valueOf(ch).equals(curr)){
                // Victory
                return 10;
            }else{
                // Loss
                return -10;
            }
        }
        return 0;
    }
}