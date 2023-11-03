import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot
import java.util.*;

/**
 * Eine Murmel im Weltraum.
 *  
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Ball extends Actor {
    private double direction; // Direction in degrees
    public double speed; // Speed of the object
    private double fractionalDistanceX = 0.0; // Accumulate fractional distance for X
    private double fractionalDistanceY = 0.0; // Accumulate fractional distance for Y
    
    private int interval = 5;
    private int blockedsideborder = 0;
    private int blockedtopborder = 0;
    private int blockedpaddle = 0;
    private int blockedbrick = 0;

    public Ball(double direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public void act() {
        moveInDirection();
        checkCollisions();
        normalizeDirection();
        checkBottomBorder();
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
    
    private void checkCollisions() {
        if (isTouching(Brick.class)) {
            Space space = (Space) getWorld();
            space.updateScore(35);
            Brick brick = new Brick();
            int height = brick.getImage().getHeight();
            int width = brick.getImage().getWidth();
            
            int ballPosX = this.getX();
            int ballPosY = this.getY();
            
            int xOffset = brick.getImage().getWidth()/2 + this.getImage().getWidth()/2;
            int yOffset = brick.getImage().getHeight()/2 + this.getImage().getHeight()/2;
            
            List<Brick> bricksInRadius = getObjectsInRange(width, Brick.class);
            for (Brick bricks : bricksInRadius) {
                int objX = bricks.getX();
                int objY = bricks.getY();
                int DiffX = objX - ballPosX;
                int DiffY = objY - ballPosY;
       
                if (Math.abs(DiffX) <= width / 2) {
                    if (Math.abs(DiffY) <= yOffset + 1) {
                        // Top Edge
                        if (direction > 180 && direction < 360) {
                            double difference = 0;
                            difference = 270 - direction;
                            direction = 90 + difference;
                        }
                        // Bottom Edge
                        else if (direction > 0 && direction < 180){
                            double difference = 0;
                            difference = 90 - direction;
                            direction = 270 + difference;
                        }
                    } 
                }
                
                if (Math.abs(DiffY) <= height / 2) {
                    if (DiffX == xOffset || DiffX == -xOffset) {
                        // Left Edge
                        if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                            double difference = 0;
                            difference = 360 - direction;
                            direction = 180 + difference;
                        }
                        // Right Edge
                        else if (direction > 90 && direction < 270) {
                            double difference = 0;
                            difference = 180 - direction;
                            direction = 360 + difference;
                        }
                    } 
                }
            }
        }
        
        if (isTouching(Paddle.class) && blockedpaddle == 0) {
            if (direction > 0 && direction < 180) {
                // Implement Callculation
                Paddle paddle = new Paddle();
                int width = paddle.getImage().getWidth();
                List<Paddle> paddles = getObjectsInRange(width, Paddle.class);
            
                if (!paddles.isEmpty()) {
                    Paddle closestPaddle = paddles.get(0); // Assume the first Leiste is the only one
                    int distance = getX() - closestPaddle.getX();
                    direction = 270 + (distance*(90/(width/2)));
                }
            }
            blockedpaddle = interval;
        } else {
            if (blockedpaddle != 0) {
                blockedpaddle--;
            }
        }
        
        if (isTouching(Sideborder.class) && blockedsideborder == 0) {
            // Right Sideboarder
            if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                double difference = 0;
                difference = 360 - direction;
                direction = 180 + difference;
            }
            // Left Sideboarder
            else if (direction > 90 && direction < 270) {
                double difference = 0;
                difference = 180 - direction;
                direction = 360 + difference;
            }
            blockedsideborder = interval;
        } else {
            if (blockedsideborder != 0) {
                blockedsideborder--;
            }
        }
        
        if (isTouching(Topborder.class)) {
            if (direction > 180 && direction < 360) {
                double difference = 0;
                difference = 270 - direction;
                direction = 90 + difference;
            }
            blockedtopborder = interval;
        } else {
            if (blockedtopborder != 0) {
                blockedtopborder--;
            }
        }
    }
    
    private void normalizeDirection(){
        if (direction >= 360) {
            direction -= 360;
        } else if (direction < 0) {
            direction += 360;
        }
    }
    
    private void checkBottomBorder(){
        if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}






