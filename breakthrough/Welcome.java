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
    public static int mode;
    /**
     * Constructor to create a world.
     */
    public Welcome() 
    {
        super(1280, 720, 1);
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
            playSound();
            welcomeText();
            showGames();
        }
        if (Greenfoot.isKeyDown("1")) {
            mode = 1;
            Greenfoot.setWorld(new Menu());
        }
        if (Greenfoot.isKeyDown("2")) {
            mode = 2;
            Greenfoot.setWorld(new Menu());
        }
        if (Greenfoot.isKeyDown("3")) {
            mode = 3;
            Greenfoot.setWorld(new Menu());
        }
    }
    
    /**
     * Show the welcome text.
     */
    private void welcomeText() {
        String welcome = "Breakthrough";
        for(int x = 0; x < welcome.length() + 1; x++) {
            showText(welcome.substring(0,x), this.getWidth()/2, 150);
            Greenfoot.delay(18);
        }
        showText("Press the number of the mode you want to play", this.getWidth()/2, this.getHeight()/8*3);
        showText("Made by Gabriel Franz and Cornel Forster", this.getWidth()/2, this.getHeight()/16*15);
        animationEnded = true;
    }
    
    /**
     * Display the different games.
     */
    private void showGames() {
        showText("1: SINGLEPLAYER", this.getWidth()/2, this.getHeight()/8*4);
        showText("2: COOP", this.getWidth()/2, this.getHeight()/8*5);
        showText("3: PVP (Work in progress)", this.getWidth()/2, this.getHeight()/8*6);
    }
    
    private void playSound() {
        Greenfoot.playSound("Intro.mp3");
    }
}
