import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Welcome screen
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Welcome extends Stars
{
    private boolean animationEnded;
    
    /**
     * Constructor to initialize the world.
     */
    public Welcome() 
    {
        animationEnded = false;
    }
    
    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        // Initialize the animation and games only once.
        if (!animationEnded) {
            playSound();
            welcomeText();
        }
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Mode());
        }
    }
    
    /**
     * Show the welcome text.
     */
    private void welcomeText() {
        String welcome = "Breakthrough";
        for(int x = 0; x < welcome.length() + 1; x++) {
            if (!Greenfoot.isKeyDown("Enter")) {
                showText(welcome.substring(0,x), this.getWidth()/2, 150);
                Greenfoot.delay(18);
            } else {
                showText(welcome, this.getWidth()/2, 150);
                break;
            }
        }
    
        showText("Press enter to play", this.getWidth()/2, this.getHeight()/8*4);
        showText("Made by Gabriel Franz and Cornel Forster", this.getWidth()/2, this.getHeight()/16*15);
        animationEnded = true;
    }
    
    /**
     * Plays the intro sound.
     */
    private void playSound() {
        Greenfoot.playSound("Intro.mp3");
    }
}

