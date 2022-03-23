/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPack;
import controllerPack.BoardTrack;
import modelPack.Subject;
/**
 *This is the track class.
 * <p>
 * This class contains information about each piece in the track
 * Each piece contains the color which corresponds to a subject that is
 * predefined by the user.
 * A track can also contain an edge which is a link to another part of the board.
 * 
 * @author calebmiles
 */
public class Track {
    
   /**
    * A track may or may not be connecting another 
    * part of the track
    * This method sets a node to another part of the track
    * if it is.
    * @param newEdge board track
    */
    public void setEdge(BoardTrack newEdge)
    {
        
        this.edge = newEdge;
        
    }
    /**
     * gets edge if the track has one
     * @return board track edge
     */
    public BoardTrack getEdge( )
    {
        
        return this.edge;
        
    }
    /**
     * sets the pixel location of the piece
     * @param xLoc x location
     * @param yLoc y location
     */
    public void setCord(int xLoc, int yLoc)
    {
        this.xLocation = xLoc;
        this.yLocation = yLoc;
    }
    /** 
     * sets the color of the piece
     * @param color color
     */
    public void setColor(String color)
    {
        this.color = color;
    }
    /**
     * gets the color of the piece
     * @return color
     */
    public String getColor()
    {
        return this.color;
    }
    /**
     * gets the x pixel location
     * @return x location
     */
    public int getX()
    {
        return this.xLocation;
    }
    /**
     * gets the y pixel location 
     * @return y location
     */
    public int getY()
    {
        return this.yLocation;
    }

    // This class contains the X and Y locations for each piece
    // as well.
    
    private int xLocation;
    private int yLocation;
    
    private BoardTrack edge = null;
    private Subject trackSub;
    
    private String color;
    
    private String testSub;
    
    
}
