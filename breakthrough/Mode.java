import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Mode selector screen
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Mode extends World
{
    public static int mode;
    /**
     * Constructor to create a world.
     */
    public Mode() 
    {
        super(1280, 720, 1);
        setBackground();
        welcomeText();
        showGames();
    }
    
    /**
     * Set the background of the world.
     */
    private void setBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        Greenfoot.setSpeed(50);
        createStars(300);
    }
    
    /**
     * Create random stars in the world.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for (int i=0; i < number; i++) {            
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 150 - Greenfoot.getRandomNumber(120);
             background.setColorAt(x, y, new Color(color,color,color));
        }
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
