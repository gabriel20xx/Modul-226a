import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Eine Rakete, die mit den Pfeiltasten gesteuert werden kann: hoch, runter, links, rechts. 
 * Durch Drücken der Leerzeichentaste wird ein Schuss abgefeuert. 
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * @author KEL
 * 
 * @version 2.0
 * @version 2.1
 * Parameter verändern
 */
public class Leiste extends Mover
{
    private int moveAmount = 2;
    /**
     * Initialisiert diese Leiste.
     */
    public Leiste()
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
            moveAmount = 8;
        } else {
            moveAmount = 2;
        }
        if(Greenfoot.isKeyDown("left")) {
            setLocation(getX() - moveAmount, getY());
        }        
        if(Greenfoot.isKeyDown("right")) {
            setLocation(getX() + moveAmount, getY());
        }
    }  
}
