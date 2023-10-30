import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Ein Gesteinsbrocken im Weltraum.
 *  
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.0
 */
public class Ball extends Mover {
    private Vector speed;
    private int currentAngle;
    /**
     * Erzeugt einen Asteroiden mit einer gegebenen Größe, Richtung und Geschwindigkeit.
     */
    public Ball(int angle, int speed) {
        super(new Vector(angle, speed));
        this.speed = new Vector(angle, speed);
        int currentAngle = angle;
    }
    
    /**
     * Lässt den Asteroiden agieren; d.h. herumfliegen.
     */
    public void act()
    {         
        move();
        turnOnTouch();
    }
    
    public void turnOnTouch()
    {
        int negativeOrPositive = Greenfoot.getRandomNumber(2); // Generates 0 or 1
        int randomDirection;
    
        if (negativeOrPositive == 0) {
            randomDirection = Greenfoot.getRandomNumber(45);
        } else {
            randomDirection = Greenfoot.getRandomNumber(45);
        }
        
        if ( isTouching(Leiste.class) || isTouching(Brick.class) || isTouching(Border.class))
        {
            getWorld().addObject(new Ball(currentAngle-90-randomDirection, (int) speed.getLength()), this.getX(), this.getY());
            getWorld().removeObject(this);
        }
        
        else if ( isAtEdge())
        {
            getWorld().addObject(new Ball(currentAngle+90+randomDirection, (int) speed.getLength()), this.getX(), this.getY());
            getWorld().removeObject(this);
        }
    }
}
