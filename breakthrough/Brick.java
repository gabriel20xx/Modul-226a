import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Write a description of class Brick here.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Brick extends Actor {
    public Brick(int color) {
        // Constructor
        // Color codes: 1=White, 2=Green, 3=Yellow, 4=LightBlue, 5=Red, 6=Pink, 7=Orange, 8=DarkBlue, 9=Silver, 10=Gold
        setColor(color);
    }

    public void act() {
        if (isTouching(Ball.class)) {
            getWorld().removeObject(this);
        }
    }
    
    private void setColor(int color) {
        
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
