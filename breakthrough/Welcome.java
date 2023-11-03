import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Ein Weltraum mit Sternen
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Welcome extends World
{
    private boolean animationEnded;
    /**
     * Constructor to create a world.
     */
    public Welcome() 
    {
        super(800, 600, 1);
        setBackground();
        animationEnded = false;
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
        // Initialize the animation and games only once.
        if (!animationEnded) {
            welcomeText();
            showGames();
        }
        if (Greenfoot.isKeyDown("1")) {
            Greenfoot.setWorld(new Space(1));
        }
        if (Greenfoot.isKeyDown("2")) {
            Greenfoot.setWorld(new Space(2));
        }
        if (Greenfoot.isKeyDown("3")) {
            Greenfoot.setWorld(new Space(3));
        }
    }
    
    /**
     * Show the welcome text.
     */
    private void welcomeText() {
        String welcome = "Breakthrough";
        for(int x = 0; x < welcome.length() + 1; x++) {
            showText(welcome.substring(0,x), 400, 150);
            Greenfoot.delay(4);
        }
        showText("Press the number of the game you want to play", 400, 200);
        animationEnded = true;
    }
    
    /**
     * Display the different games.
     */
    private void showGames() {
        showText("1: THE CLASSIC", 400, 250);
        showText("2: RANDOMIZE", 400, 270);
        showText("3: OLD FASHION ", 400, 290);
    }
}
