package controllerPack;
import java.util.*;

/**
 * This is the dice Roll class
 * <p> 
 * This class is used to generate random numbers for
 * the board.
 * @author calebmiles
 */

public class DiceRoll 
{
    /**
     * Constructor
     */
    public DiceRoll()
    {
        
    }
    
    
    /**
     * Simulates a dice roll of any size
     * @param sizeOfDice int size number
     * @return a random number between 1-n
     */
    public int rollDice(int sizeOfDice)
    {
        try // The try catch is a hot fix to input issues. 
        {
            Random number = new Random();
            int finalRandom = number.nextInt((sizeOfDice - 1) + 1) + 1;
            //System.out.println("You rolled a " +finalRandom);
            return finalRandom;
        }
        catch(Exception e)
        {
            return 1;
        }

    }
}
