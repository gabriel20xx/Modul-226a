import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Write a description of class Brick here.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Brick extends Actor {
    public Brick() {
        // Constructor
    }

    public void act() {
        if (isTouching(Ball.class)) {
            getWorld().removeObject(this);
        }
        }
    
    /**
     * Check if brick is touching another brick-
     */    
    public boolean isTouchingAnotherBrick() {
        if (isTouching(Brick.class)) {
            return true;
        }
        return false;
    }    
    
    /**
     * Check if brick is touching another brick-
     */    
    public boolean isTouchingBorder() {
        if (isTouching(Border.class)) {
            return true;
        }
        return false;
    }     
}
