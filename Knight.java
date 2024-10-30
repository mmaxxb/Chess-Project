//Written by Evan Saksa009 and Max Bostan bosta022
package CSCI1933P2;

public class Knight extends Piece {

    public Knight(int row, int col, boolean isBlack) {

        super.row = row;

        super.col = col;


        super.isBlack = isBlack;


        if (isBlack) {

            super.representation = '\u265E';
        } else {

            super.representation = '\u2658';
        }
    }

    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDifference = Math.abs(endRow - this.row);
        int colDifference = Math.abs(endCol - this.col);

        // Case 1: Knight moves in an L-shape
        if (!((rowDifference == 2 && colDifference == 1) ||
                (rowDifference == 1 && colDifference == 2))) {
            return false;  // Not a valid knight move
        }

        // Case 2: Check if the destination square contains a piece
        Piece targetPiece = board.getPiece(endRow, endCol);
        if (targetPiece != null && targetPiece.getIsBlack() == this.isBlack) {
            return false;  // Cannot capture a piece
        }

        return true;  // Legal move
    }


    }





