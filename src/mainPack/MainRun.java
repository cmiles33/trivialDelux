

package mainPack;
import viewPack.MainMenu;

/**
 * This is the mainTest function 
 * <p>
 * This acts as the main function to call the main menu
 * @author calebmiles
 */
public class MainRun
{
    /**
     * Main function
     * @param args Not used.
     */
    public static void main(String[] args)
    {
        MainMenu mainPanel = new MainMenu();
        mainPanel.setVisible(true);
    }
}