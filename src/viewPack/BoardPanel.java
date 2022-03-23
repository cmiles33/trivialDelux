/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewPack;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

import java.awt.Graphics;

import java.nio.file.Path;
import java.nio.file.Paths;
import viewPack.paintFigures.RedSquare;

import java.io.FileWriter;

/**
 * Graphical component used by the game board.
 * @author calebmiles
 */
public class BoardPanel extends JPanel {

    private Image img;
    String universalPath;
    private FileWriter myWriter;
    /**
     * sets the photo of the gameboard
     * upon creation
     */
    public BoardPanel()
    {
        
            Path newPath = Paths.get(System.getProperty("user.dir"),"trivialpersuitphoto.png");
            this.universalPath = newPath.toString();
            this.img = img = Toolkit.getDefaultToolkit().getImage(
            getClass().getResource("/viewPack/images/trivialpersuitphoto.png"));

            
    }
    /**
     * paints objects to board using the inherited graphics object
     * @param g graphics object for painting 
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        // Draw stuff
        
        
        g.drawImage(img, 0, 0, this);
        
        
        
        this.redSquare.paintSquare(g);
        //this.redSquare2.paintSquare(g);
        
    }
    /**
     * Paints the square in a specific place
     * and repaints it as well
     * @param x x pixel location
     * @param y y pixel location
     */
    public void moveSquare(int x, int y)
    {
        final int CURR_X = redSquare.getX();
        final int CURR_Y = redSquare.getY();
        final int CURR_W = redSquare.getWidth();
        final int CURR_H = redSquare.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background 
            // over the old square location. 
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            redSquare.setX(x);
            redSquare.setY(y);

            // Repaint the square at the new location.
            repaint(redSquare.getX(), redSquare.getY(), 
                    redSquare.getWidth()+OFFSET, 
                    redSquare.getHeight()+OFFSET);
        }
    }
    
    private RedSquare redSquare = new RedSquare(250,250);
    
}
