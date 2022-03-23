/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewPack.paintFigures;

import java.awt.Color;
import java.awt.Graphics;

/**
 *This is a graphical class
 * 
 * @author calebmiles
 */
public class RedSquare
{
    /**
     * Sets the location of the square when made
     * @param xPos pixel location x
     * @param yPos pixel location y
     */
    public RedSquare(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    /**
     * Set x
     * @param xPos pixel location
     */
    public void setX(int xPos){ 
        this.xPos = xPos;
    }
    /**
     * gets x
     * @return integer x
     */
    public int getX(){
        return xPos;
    }
    /**
     * sets the Y cord
     * @param yPos y location
     */
    public void setY(int yPos){
        this.yPos = yPos;
    }
    /**
     * gets the y location
     * @return y location
     */
    public int getY(){
        return yPos;
    }
    /**
     * gets the width of the object
     * @return width
     */
    public int getWidth(){
        return width;
    } 
    /**
     * returns height of the object
     * @return return int hight
     */
    public int getHeight(){
        return height;
    }
    /**
     * Paints the square using the graphics
     * object passed in by the board panel class
     * @param g graphics object
     */
    public void paintSquare(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(xPos,yPos,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,width,height);  
    }
    
    private int xPos = 50;
    private int yPos = 50;
    private int width = 30;
    private int height = 30;
    
}