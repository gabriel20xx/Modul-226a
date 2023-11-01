import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Write a description of class Brick here.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Brick extends Static {
    public Brick() {
        // Constructor
    }

    public void act() {
        if (isTouching(Marble.class)) {
            getWorld().removeObject(this);
        }
        }
    }
