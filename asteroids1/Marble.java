import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot
import java.util.*;

/**
 * Ein Gesteinsbrocken im Weltraum.
 *  
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.0
 */
public class Marble extends Actor {
    private double direction; // Direction in degrees
    private int speed; // Speed of the object
    private double fractionalDistanceX = 0.0; // Accumulate fractional distance for X
    private double fractionalDistanceY = 0.0; // Accumulate fractional distance for Y
    
    private int interval = 5;
    private int blockedsideborder = 0;
    private int blockedtopborder = 0;
    private int blockedleiste = 0;

    public Marble(double direction, int speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public void act() {
        moveInDirection();
        checkCollisions();
        getWorld().showText("Direction: " + direction, 100, 200);
    }

    private void moveInDirection() {
        double radians = Math.toRadians(direction); // Convert degrees to radians
        double dx = Math.cos(radians) * speed; // Calculate horizontal movement
        double dy = Math.sin(radians) * speed; // Calculate vertical movement

        fractionalDistanceX += dx; // Accumulate the fractional distance for X
        fractionalDistanceY += dy; // Accumulate the fractional distance for Y

        int wholePixelsX = (int) fractionalDistanceX;
        int wholePixelsY = (int) fractionalDistanceY;

        setLocation(getX() + wholePixelsX, getY() + wholePixelsY); // Update the position

        fractionalDistanceX -= wholePixelsX; // Subtract the whole pixels from X
        fractionalDistanceY -= wholePixelsY; // Subtract the whole pixels from Y
    }

    /*public void setDirection(double newDirection) {
        direction = newDirection;
    }*/
    
    private void checkCollisions() {
        if (isTouching(Brick.class)) {
            getWorld().showText("Touching", 100, 150);
            if (direction > 0 && direction < 90) {
                
            }
            
            else if (direction > 90 && direction < 180) {
                
            }
            
            else if (direction > 180 && direction < 270) {
                
            }
            
            else if (direction > 270 && direction < 360) {
                
            }

            // Get Edge of the object it is touching and then calculate the new Direction of the Marble
        }
        
        if (isTouching(Leiste.class)) {
            getWorld().showText("Touching", 100, 100);
            if (direction > 0 && direction < 180) {
                getWorld().showText("Leiste", 100, 150);
                double difference = 0;
    
                difference = 90 - direction;
                direction = 270 + difference;
            }
            blockedleiste = interval;
        } else {
            if (blockedleiste != 0) {
                blockedleiste--;
            }
            getWorld().showText("NotTouching", 100, 100);
        }
        
        if (isTouching(Sideborder.class) && blockedsideborder == 0) {
            getWorld().showText("Touching", 100, 100);
            // Right Sideboarder
            if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                getWorld().showText("Rightborder", 100, 150);
                double difference = 0;
                
                difference = 360 - direction;
                direction = 180 + difference;
            }
            // Left Sideboarder
            else if (direction > 90 && direction < 270) {
                getWorld().showText("Leftborder", 100, 150);
                double difference = 0;
    
                difference = 180 - direction;
                direction = 360 + difference;
            }
            blockedsideborder = interval;
        } else {
            if (blockedsideborder != 0) {
                blockedsideborder--;
            }
            getWorld().showText("NotTouching", 100, 100);
        }
        
        if (isTouching(Topborder.class)) {
            getWorld().showText("Touching", 100, 100);
            if (direction > 180 && direction < 360) {
                getWorld().showText("Topborder", 100, 150);
                double difference = 0;
    
                difference = 270 - direction;
                direction = 90 + difference;
            }
            blockedtopborder = interval;
        } else {
            if (blockedtopborder != 0) {
                blockedtopborder--;
            }
            getWorld().showText("NotTouching", 100, 100);
        }
        
        if (direction >= 360) {
            direction -= 360;
        }
    }
    
    private void calculateDirection(Actor collisionObject) {
        // Implement Realistic Physics
    }
}






