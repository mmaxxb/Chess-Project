package CSCI1933P2;

public class Rook extends Piece {

    public Rook(int row, int col, boolean isBlack) {
        // Set  row instance variable
        super.row = row;

        // Set col instance variable
        super.col = col;

        // (white/black)
        super.isBlack = isBlack;


        if (isBlack) {
            // Black Rook
            super.representation = '\u265C';
        } else {
            // White Rook
            super.representation = '\u2656';
        }
    }


    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (this.row == endRow) {  // Horizontal move
            if (!board.verifyHorizontal(row, col, endRow, endCol)) {
                return false;  // Path is blocked
            }
        } else if (this.col == endCol) {  // Vertical move
            if (!board.verifyVertical(row, col, endRow, endCol)) {
                return false;  // Path is blocked
            }
        } else {
            return false;  // Not a valid rook move
        }

        Piece targetPiece = board.getPiece(endRow, endCol);
        if (targetPiece != null && targetPiece.getIsBlack() == this.isBlack) {
            return false;  // Cannot capture own piece
        }

        return true;
    }


}
