import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Mode selector screen.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Mode extends Selection
{
    private int step = 1;
    private int rows = 1;
    private int columns = 2;
    private String text = "Select your play mode";
    
    /**
     * Constructor to initialize the world.
     */
    public Mode() 
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
