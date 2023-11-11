import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Mode selector screen.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Mode extends Stars
{
    public static int mode;
    
    /**
     * Constructor to initialize the world.
     */
    public Mode() 
    {
        welcomeText();
        showGames();
    }
    
    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        if (Greenfoot.isKeyDown("1")) {
            mode = 1;
            Greenfoot.setWorld(new Level());
        }
        if (Greenfoot.isKeyDown("2")) {
            mode = 2;
            Greenfoot.setWorld(new Level());
        }
    }
    
    /**
     * Show the welcome text.
     */
    private void welcomeText() {
        showText("Breakthrough", this.getWidth()/2, 150);
        showText("Press the number of the mode you want to play", this.getWidth()/2, this.getHeight()/8*3);
    }
    
    /**
     * Display the different games.
     */
    private void showGames() 
    {
        showText("1: SINGLEPLAYER", this.getWidth()/2, this.getHeight()/8*5);
        showText("2: CO-OP", this.getWidth()/2, this.getHeight()/8*6);
    }
}
