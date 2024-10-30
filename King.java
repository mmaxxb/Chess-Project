//Written by Evan Saksa009 and Max Bostan bosta022
package CSCI1933P2;

public class King extends Piece {

    /**
     * Constructor.
     *
     * @param row     The current row of the pawn.
     * @param col     The current column of the pawn.
     * @param isBlack The color of the pawn.
     */
    public King(int row, int col, boolean isBlack) {

        super.row = row;

        super.col = col;


        super.isBlack = isBlack;


        if (isBlack) {
            // Black King
            super.representation = '\u265A';
        } else {
            // White King
            super.representation = '\u2654';
        }

    }

    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Case 1: King can move only one square in any direction
        if (Math.abs(this.row - endRow) > 1 || Math.abs(this.col - endCol) > 1) {
            return false;  // Invalid move for King
        }

        // Case 2: Check if the destination square is occupied
        Piece targetPiece = board.getPiece(endRow, endCol);
        if (targetPiece != null && targetPiece.getIsBlack() == this.isBlack) {
            return false;  // Cannot move to a square occupied
        }

        return true;  // Legal move
    }

}

