
package CSCI1933P2;
public class Queen extends Piece {

    /**
     * Constructor for Queen piece.
     * @param row The current row of the queen.
     * @param col The current column of the queen.
     * @param isBlack The color of the queen.
     */
    public Queen(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        // Set the representation based on the color
        if (isBlack) {
            super.representation = '\u265B'; // Black Queen
        } else {
            super.representation = '\u2655'; // White Queen
        }
    }

    /**
     * Checks if a move to a destination square is legal for the Queen.
     * The Queen can move any number of squares vertically, horizontally, or diagonally.
     * @param board The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move is legal, false otherwise.
     */


    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - row);
        int colDiff = Math.abs(endCol - col);

        // Ensure the piece is not moving to the same position
        if (row == endRow && col == endCol) {
            return false;  // Can't move to the same spot
        }

        // Check for horizontal movement
        if (row == endRow) {
            if (!board.verifyHorizontal(row, col, endRow, endCol)) {
                return false;  // Path is blocked
            }
        }
        // Check for vertical movement
        else if (col == endCol) {
            if (!board.verifyVertical(row, col, endRow, endCol)) {
                return false;  // Path is blocked
            }
        }
        // Check for diagonal movement
        else if (rowDiff == colDiff) {
            if (!board.verifyDiagonal(row, col, endRow, endCol)) {
                return false;  // Path is blocked
            }
        }
        // If not a valid queen move
        else {
            return false;
        }

        // Check if the destination contains a piece
        Piece targetPiece = board.getPiece(endRow, endCol);
        if (targetPiece != null && targetPiece.getIsBlack() == this.isBlack) {
            return false;  // Can't capture a piece
        }

        return true;  // Valid move
    }


}
