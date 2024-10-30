package CSCI1933P2;

public class Bishop extends Piece {

    public Bishop(int row, int col, boolean isBlack) {
        super.row = row;

        super.col = col;

        super.isBlack = isBlack;

        if (isBlack) {

            super.representation = '\u265D';
        } else {

            super.representation = '\u2657';
        }
    }

    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (Math.abs(row - endRow) != Math.abs(col - endCol)) {
            return false;  // Not a valid diagonal move
        }

        if (!board.verifyDiagonal(row, col, endRow, endCol)) {
            return false;  // Path is blocked
        }

        Piece targetPiece = board.getPiece(endRow, endCol);
        if (targetPiece != null && targetPiece.getIsBlack() == this.isBlack) {
            return false;  // Cannot capture
        }

        return true;
    }


}
