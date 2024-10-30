//Written by Evan Saksa009 and Max Bostan bosta022
package CSCI1933P2;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board board = new Board();

        // Load the standard chess board using the FEN notation
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Fen.load(fen, board);

        boolean WhitesTurn = true;  // Track whose turn it is
        boolean gameRunning = true; // Main game loop

        while (gameRunning) {
            // Display the board
            System.out.println(board.toString());

            // Announce whose turn it is
            String currentPlayer = WhitesTurn ? "White" : "Black";
            System.out.println(currentPlayer + "'s turn");

            // Get user input for the move
            System.out.print("Enter your move [startRow startCol endRow endCol]: ");
            int startRow = s.nextInt();
            int startCol = s.nextInt();
            int endRow = s.nextInt();
            int endCol = s.nextInt();

            // Validate input coordinates
            if (!isValidCoordinate(startRow, startCol, endRow, endCol)) {
                System.out.println("Invalid input. Coordinates must be between 0 and 7.");
                continue;
            }

            // Attempt to move the piece using `movePiece()`
            boolean moveSuccessful = board.movePiece(startRow, startCol, endRow, endCol);

            if (moveSuccessful) {
                // Check if the piece is a pawn that needs promotion
                Piece piece = board.getPiece(endRow, endCol);
                if (piece instanceof Pawn) {
                    ((Pawn) piece).promotePawn(board, endRow, endCol, piece.getIsBlack());
                }

                // Switch turns
                WhitesTurn = !WhitesTurn;

                // Check if the game is over
                if (board.isGameOver()) {
                    System.out.println("Game over! " + currentPlayer + " wins.");
                    break;  // Exit the game loop
                }
            } else {
                System.out.println("Invalid move, try again.");
            }
        }


        s.close(); // Close the scanner at the end of the game
    }

    private static boolean isValidCoordinate(int startRow, int startCol, int endRow, int endCol) {
        return (startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 &&
                endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8);
    }
}

