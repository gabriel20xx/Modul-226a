import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

public class Brick extends Static {
    private int actCounter = 0;

    public Brick() {
        // Constructor
    }

    public void act() {
        if (isTouching(Marble.class)) {
            actCounter++;
            if (actCounter >= 4) {
                // Remove the object after two act cycles
                getWorld().removeObject(this);
            }
        }
    }
}
