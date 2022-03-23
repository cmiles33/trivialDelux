package modelPack;

import controllerPack.*;
import controllerPack.BoardTrack;
/**
 * This is the player class.
 * <p>
 * This class contains important information stored about each player.
 * it contains there whereabouts on the board, as well as what pieces they
 * have and what moves they have left.
 * 
 * 
 * @author calebmiles
 */

public class Player 
{
    /**
    * Constructor
    * @param aName takes a string and sets it to member variable name
    * @param aColor takes a string of the user's desired color for their piece
    */
    public Player(String aName, String aColor)
    {
        name = aName;
        color = aColor;
        this.pieceCount[0] =0;
        this.pieceCount[1] =0;
        this.pieceCount[2] =0;
        this.pieceCount[3] =0;
        this.pieceCount[4] =0;
        this.pieceCount[5] =0;
        
    }    
    /**
     * The way I have the piece collection is an array of integers for example [1,0,0,1,0,1] this would mean they have the piece
     * for subject 1, 4, and 5. If the array is all 1s then it will end the game
     * @return the number of pieces that a user has. Does not specify which category pieces they have
     */
    public int getPieceCount()
    {
        int j = 0;
        for (int i = 0; i < 6; i++)
        {
            if(pieceCount[i] == 1)
                j++;
        }
        return j;
    }
    /**
     * @return the member variable name
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * adds the piece to the player
     * @param subjectNumber index for piece
     */
    public void addPiece(int subjectNumber)
    {
            this.pieceCount[subjectNumber] = 1;
    }
    /**
     * Sets the current track that the player is on
     * @param index track number
     */
    public void setTrackNum(int index)
    {
        this.currentTrackNum = index;
    }
    /**
     * Retrieves players track number
     * @return track number
     */
    public int getTrackNum()
    {
        return this.currentTrackNum;
    }
    /**
     * returns the part of the board the player
     * is currently on
     * @return boardTrack object
     */
    public BoardTrack getCurrentBoard()
    {
        return this.currentBoard;
    }
    /**
     * Sets the players next destination
     * @param next boardtrack
     */
    public void setBoardTrack(BoardTrack next)
    {
        this.currentBoard = next;
    }
    /**
     * returns whether or not the player is going in 
     * a positive or negative direction
     * @return returns true or false
     */
    public boolean getNegative()
    {
        return this.negative;
    }
    /**
     * This function sets whether or not 
     * the player has any more moves left
     * @param extra number of moves
     */
    public void setExtraMoves(int extra)
    {
        this.movementsleft = extra;
    }
    /**
     * gets the number of moves that are left
     * @return returns the number
     */
    public int getExtra()
    {
        return this.movementsleft;
    }
    /**
     * This function sets whether or not the player
     * is going in in the negative direction
     * @param setter true or false
     */
    public void setNegitive(boolean setter)
    {
        this.negative = setter;
    }
    /**
     * returns the list of pieces that the user has
     * @return integer list
     */
    public int[] getPieces()
    {
        return this.pieceCount;
    }
    private String name;
    private String color;
    private int[] pieceCount = new int[6] ;
    private boolean negative = false;
    private int movementsleft = 0;
    private BoardTrack currentBoard = null;
    private int currentTrackNum = 0;
    
    
}
