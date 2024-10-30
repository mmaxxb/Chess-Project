package CSCI1933P2;
public class Board {
    // Instance variables (add more if you need)
    private final Piece[][] board;


    /**
     * Default Constructor
     */
    public Board() {
        // initialize the board to chessboard dimensions.
        board = new Piece[8][8];
    }

    // Accessor Methods

    /**
     * Gets the piece at a particular row and column of the board.
     * @param row       The row of the piece to be accessed.
     * @param col       The column of the piece to be accessed.
     * @return          The piece at the specified row and column of the board.
     */
    public Piece getPiece(int row, int col)  {
        return board[row][col];
    }


    /**
     * Sets the piece at a particular row and column of the board.
     * @param row       The row to place the piece at.
     * @param col       The column to place the piece at.
     * @param piece     The piece to place at the specified row and column.
     */
    public void setPiece(int row, int col, Piece piece) {

        board[row][col]= piece;
    }

    // Movement helper functions

    /**
     * Verifies that the source and destination of a move are valid by performing the following checks:
     *  1. ALL rows and columns provided must be >= 0.
     *  2. ALL rows and columns provided must be < 8.
     *  3. The start position of the move must contain a piece.
     *  4. The piece at the starting position must be the correct color.
     *  5. The destination must be empty OR must contain a piece of the opposite color.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @param isBlack   The expected color of the starting piece.
     * @return True if the above conditions are met, false otherwise.
     */


    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0
                || startRow >= 8 || startCol >= 8 || endRow >= 8 || endCol >= 8) {
            return false;
        }
        if (board[startRow][startCol] == null) {
            return false;
        }
        Piece startPiece;
        startPiece = board[startRow][startCol];
        if (startPiece.getIsBlack() != isBlack) {
            return false;
        }
        Piece endPiece;
        endPiece = board[endRow][endCol];
        if (endPiece != null) {
            if (endPiece.getIsBlack() == isBlack) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that the source and destination of a move are adjacent squares (within 1 square of each other)
     * Example, Piece P is adjacent to the spots marked X:
     * OOOOO
     * OXXXO
     * OXPXO
     * OXXXO
     * OOOOO
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if the source and destination squares are adjacent, false otherwise.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        //calc difference  start and end rows and columns
        int rowDifference = endRow - startRow;
        int colDifference = endCol - startCol;

        if ((rowDifference >= -1 && rowDifference <= 1) && (colDifference >= -1 && colDifference <= 1)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Verifies that a source and destination are in the same row and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same row with no pieces between them, false otherwise.
     */


    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
       int minColumn = Math.min(startCol, endCol);
       int maxColumn = Math.max(startCol, endCol);
        if (startRow != endRow){
            return false;
        }
        for(int col = minColumn + 1; col < maxColumn; col++){
            if (board[startRow][col] != null){
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that a source and destination are in the same column and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same column with no pieces between them, false otherwise.
     */

    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // Case 1: No movement
        if (startRow == endRow && startCol == endCol) {
            return true;  // No movement is valid
        }

        // Case 2: Check if it's a valid vertical move
        if (startCol != endCol) {
            return false;  // Not a valid vertical move
        }

        // Case 3: Check if the path is clear
        int rowStep = (endRow > startRow) ? 1 : -1;
        int currentRow = startRow + rowStep;

        while (currentRow != endRow) {
            if (board[currentRow][startCol] != null) {
                return false;  // Path is blocked
            }
            currentRow += rowStep;
        }

        return true;  // Path is clear
    }


    /**
     * Verifies that a source and destination are on the same diagonal and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are on the same diagonal with no pieces between them, false otherwise.
     */

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // Case 1: No movement
        if (startRow == endRow && startCol == endCol) {
            return true;  // No movement is valid
        }

        // Case 2: Check if it's a valid diagonal move
        if (Math.abs(endRow - startRow) != Math.abs(endCol - startCol)) {
            return false;  // Not a valid diagonal move
        }

        // Case 3: Check if the path is clear
        int rowStep = (endRow > startRow) ? 1 : -1;
        int colStep = (endCol > startCol) ? 1 : -1;
        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;

        while (currentRow != endRow && currentCol != endCol) {
            if (board[currentRow][currentCol] != null) {
                return false;  // Path is blocked
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true;  // Path is clear
    }






    // Game functionality methods

    /**
     * Moves the piece from startRow, startCol to endRow, endCol if it is legal to do so.
     * IMPORTANT: Make sure to update the internal position of the piece, and the starting position of the piece to null!
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return Whether the move was successfully completed or not. (Moves are not completed if they are not legal.)
     */



    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = getPiece(startRow, startCol);

        // Makes sure of starting position
        if (piece == null) {
            System.out.println("No piece at the starting position.");
            return false;
        }

        Piece destinationPiece = getPiece(endRow, endCol);

        // Check if move contains a piece (invalid capture)
        if (destinationPiece != null && destinationPiece.getIsBlack() == piece.getIsBlack()) {
            System.out.println("Cannot capture your own piece.");
            return false;
        }

        // Check if the move is legal
        if (!piece.isMoveLegal(this, endRow, endCol)) {
            System.out.println("Invalid move.");
            return false;
        }

        // Perform the move/capture
        setPiece(endRow, endCol, piece);  // Place the piece at the destination
        setPiece(startRow, startCol, null);  // Remove the piece from the original location

        // Update piece position
        piece.setPosition(endRow, endCol);

        return true;  // Move was successful
    }



    /**
     * Returns true if there are fewer than TWO kings on the board.
     * @return If the game is in a game over state.
     */
    public boolean isGameOver() {
        int kingCount = 0;

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                Piece piece = board[row][col];
                if(piece != null && piece instanceof King){
                    kingCount ++;
                    if(kingCount >= 2){
                        return false;
                    }

                }
            }

        }
        return true;
    }

    /**
     * Sets all indexes in the board to null
     */
    public void clear() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;  // Clear each position on the board
            }
        }

    }


    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

}


