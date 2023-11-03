import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Eine Rakete, die mit den Pfeiltasten gesteuert werden kann: hoch, runter, links, rechts. 
 * Durch Drücken der Leerzeichentaste wird ein Schuss abgefeuert. 
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Paddle extends Mover
{
    private int moveAmount = 6;
    /**
     * Initialisiert diese Leiste.
     */
    public Paddle()
    {
        // Code
    }

    /**
     * Tut, was eie Leiste so macht. (Das heißt: meistens herumfliegen und wenden,
     * beschleunigen und schießen, wenn die entsprechenden Tasten gedrückt werden.)
     */
    public void act()
    {
        checkKeys();
    }
    
    /**
     * Prüft, ob irgendeine Taste gedrückt wurde, und reagiert darauf.
     */
    private void checkKeys() 
    {   
        if(Greenfoot.isKeyDown("shift")) {
            moveAmount = 18;
        } else {
            moveAmount = 6;
        }
        if (Greenfoot.isKeyDown("left")) {
            if (isTouching(Sideborder.class)) {
                // Find the first 'Border' object this object is touching
                Sideborder border = (Sideborder) getOneIntersectingObject(Sideborder.class);
                if (border != null) {
                    int xCoordinateBorder = border.getX();
                    if (getX() < xCoordinateBorder) {
                        setLocation(getX() - moveAmount, getY());
                    }
                }
            } else {
                setLocation(getX() - moveAmount, getY());
            }
        }      
        if(Greenfoot.isKeyDown("right")) {
            if (isTouching(Sideborder.class)) {
                // Find the first 'Border' object this object is touching
                Sideborder border = (Sideborder) getOneIntersectingObject(Sideborder.class);
                if (border != null) {
                    int xCoordinateBorder = border.getX();
                    if (getX() > xCoordinateBorder) {
                        setLocation(getX() + moveAmount, getY());
                    }
                }
            } else {
                setLocation(getX() + moveAmount, getY());
            }
        }
    }  
}
