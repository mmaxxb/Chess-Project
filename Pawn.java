//Written by Evam Saksa009 and Max Bostan bosta022
package CSCI1933P2;
import java.util.Scanner;
public class Pawn extends Piece {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Pawn(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Pawn object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack){
            // Black Pawn
            super.representation = '\u265F';
        }
        else{
            // White Pawn
            super.representation = '\u2659';
        }

    }

    /**
     * Handle promotion of a pawn.
     * @param board Board instance
     * @param row Current row of the pawn
     * @param col Current col of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(Board board, int row, int col, boolean isBlack) {
        if ((isBlack && row == 7) || (!isBlack && row == 0)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pawn Promotion. select Q: Queen, R: Rook, K: Knight, B: Bishop ");
            String choice = scanner.nextLine().toUpperCase();

            Piece newPiece = null;
            switch (choice) {
                case "Q":
                    newPiece = new Queen(row, col, isBlack);
                    break;
                case "R":
                    newPiece = new Rook(row, col, isBlack);

                case "B":
                    newPiece = new Bishop(row, col, isBlack);
                    break;
                case "K":
                    newPiece = new Knight(row, col, isBlack);
                    break;

                default:
                    System.out.println("Not a valid choice. Defaulting to Queen");
                    newPiece = new Queen(row, col, isBlack);
                    break;
            }
            board.setPiece(row, col, newPiece);
        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Case 1: Forward movement to empty square.
            // Determine if the distance being moved is valid.
            if (this.isBlack) {
                return (endRow == this.row + 1) || ((endRow == this.row + 2) && (this.row == 1));
            } else {
                return (endRow == this.row - 1) || ((endRow == this.row - 2) && (this.row == 6));
            }
        }

        else if (this.col == endCol+1 || this.col == endCol-1) {
            // Case 2: Capturing a piece.
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // There is a piece of the opposite color to be captured.
                if (this.isBlack) {
                    return (endRow == this.row + 1);
                } else {
                    return (endRow == this.row - 1);
                }
            } else {
                return false;
            }
        }

        else {
            // Case 3: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }



}

