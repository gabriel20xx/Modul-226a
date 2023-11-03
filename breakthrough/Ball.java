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
    
    private boolean blockedsideborder = false;
    private boolean blockedtopborder = false;
    private boolean blockedpaddle = false;
    private boolean blockedbrick = false;

    public Ball(double direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public void act() {
        moveInDirection();
        checkBrickCollision();
        checkPaddleCollision();
        checkSideborderCollision();
        checkTopborderCollision();
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
    
    private void checkBrickCollision() {
        if (isTouching(Brick.class) && blockedbrick == false) {
            Brick brick = new Brick(1);
            
            int brickHeight = brick.getImage().getHeight();
            int brickWidth = brick.getImage().getWidth();
            int ballHeight = this.getImage().getHeight();
            int ballWidth = this.getImage().getWidth();
            
            int xOffset = brickWidth/2 + ballWidth/2;
            int yOffset = brickHeight/2 + ballHeight/2;
            
            List<Brick> bricksInRadius = getObjectsInRange(yOffset*2, Brick.class);
            if (bricksInRadius.isEmpty()) {
                bricksInRadius = getObjectsInRange(xOffset*2, Brick.class);
            }
            
            for (Brick bricks : bricksInRadius) {
                int ballPosX = this.getX();
                int ballPosY = this.getY();
                int brickPosX = bricks.getX();
                int brickPosY = bricks.getY();
                
                int DiffX = brickPosX - ballPosX;
                int DiffY = brickPosY - ballPosY;
                
                if (Math.abs(DiffY) <= yOffset) {
                    if (DiffX <= xOffset + 1 || DiffX >= -xOffset - 1) {
                        // Ball comming from right
                        if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                            double difference = 0;
                            difference = 360 - direction;
                            direction = 180 + difference;
                        }
                        // Ball comming from left
                        else if (direction > 90 && direction < 270) {
                            double difference = 0;
                            difference = 180 - direction;
                            direction = 360 + difference;
                        }
                    } 
                }
       
                if (Math.abs(DiffX) <= xOffset) {
                    if (Math.abs(DiffY) <= yOffset*2) {
                        // Ball comming from top
                        if (direction > 180 && direction < 360) {
                            double difference = 0;
                            difference = 270 - direction;
                            direction = 90 + difference;
                        }
                        // Ball comming from bottom
                        else if (direction > 0 && direction < 180){
                            double difference = 0;
                            difference = 90 - direction;
                            direction = 270 + difference;
                        }
                    } 
                }
            }
            // Update Score
            Space space = (Space) getWorld();
            space.updateScore(35);
            blockedbrick = true;
        } else {
            blockedbrick = false;
        }
    }
    
    private void checkPaddleCollision() {
        if (isTouching(Paddle.class) && blockedpaddle == false) {
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
            blockedpaddle = true;
        } else {
            blockedpaddle = false;
        }
    }
    
    private void checkSideborderCollision() {
        if (isTouching(Sideborder.class) && blockedsideborder == false) {
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
            blockedsideborder = true;
        } else {
            blockedsideborder = false;
        }
    }
    
    private void checkTopborderCollision() {
        if (isTouching(Topborder.class) && blockedtopborder == false) {
            if (direction > 180 && direction < 360) {
                double difference = 0;
                difference = 270 - direction;
                direction = 90 + difference;
            }
            blockedtopborder = true;
        } else {
            blockedtopborder = false;
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






