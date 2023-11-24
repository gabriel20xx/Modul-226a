import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level selector screen.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Level extends Selection
{
    private int step = 2;
    private int rows = 2;
    private int columns = 5;
    private String text = "Select your level";
    
    /**
     * Constructor to initialize the world.
     */
    public Level() 
    {
        super.showSelectors(step, rows, columns, text);
    }
    
    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        super.changeSelector(step, rows, columns);   
    }    
}
