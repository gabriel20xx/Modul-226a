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
    private boolean animationEnded = false;
    private boolean keyDown = false;
    
    /**
     * Constructor to initialize the world.
     */
    public Welcome() 
    {
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
        else {
            if (Greenfoot.isKeyDown("enter")) {
                keyDown = true;
            }
            if ((!Greenfoot.isKeyDown("enter")) && keyDown == true) {
                Greenfoot.setWorld(new Mode());
            }
        }
    }
    
    /**
     * Show the welcome text.
     */
    private void welcomeText() {
        String welcome = "Breakthrough";
        for(int x = 0; x < welcome.length() + 1; x++) {
            if (Greenfoot.isKeyDown("enter")) {
                keyDown = true;
            }
            if ((!Greenfoot.isKeyDown("enter")) && keyDown == true) {
                showText(welcome, this.getWidth()/2, 150);
                keyDown = false;
                break;
            } else {
                showText(welcome.substring(0,x), this.getWidth()/2, 150);
                Greenfoot.delay(20);
            }
        }
    
        showText("Press enter to play", this.getWidth()/2, this.getHeight()/16*9);
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

