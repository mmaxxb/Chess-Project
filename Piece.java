
package CSCI1933P2;
public abstract class Piece {

    // Piece object's internal row position
    protected int row;

    // Piece object's internal col position
    protected int col;

    // Boolean representing Piece object's color (white/black)
    protected boolean isBlack;

    // Unicode character representing the piece
    protected char representation;



    public abstract boolean isMoveLegal(Board board, int endRow, int endCol);


    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;

    }



    /**
     * Return the position of the piece.
     * @return  The row of the piece.
     */
    public int getRow(){
        return this.row;

    }



    /**
     * Return the position of the piece.
     * @return  The col of the piece.
     */
    public int getCol(){
        return this.col;
    }



    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack(){
        return this.isBlack;
    }


    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return representation + "";
    }


}
