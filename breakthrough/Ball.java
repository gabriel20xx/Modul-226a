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
        checkBottomBorder();
        //getWorld().showText("Direction: " + direction, 100, 200);
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
            //getWorld().showText("Touching", 100, 150);
            Brick brick = new Brick();
            int height = brick.getImage().getHeight();
            int width = brick.getImage().getWidth();
            
            //getWorld().showText("Height: "+height, 100, 350);
            //getWorld().showText("Width: "+width, 100, 400);
            
            int ballPosX = this.getX();
            int ballPosY = this.getY();
            
            int xOffset = brick.getImage().getWidth()/2 + this.getImage().getWidth()/2;
            int yOffset = brick.getImage().getHeight()/2 + this.getImage().getHeight()/2;
            
            //getWorld().showText("yOffset: "+yOffset, 700, 380);
            
            List<Brick> bricksInRadius = getObjectsInRange(width, Brick.class);
            //getWorld().showText("Objects Nearby: "+ bricksInRadius.size(), 700, 300);
            for (Brick bricks : bricksInRadius) {
                int objX = bricks.getX();
                int objY = bricks.getY();
                int DiffX = objX - ballPosX;
                int DiffY = objY - ballPosY;
       
                if (Math.abs(DiffX) <= width / 2) {
                    //getWorld().showText("Reach 1: "+DiffY, 700, 400);
                    // Change direction of Brick like top- and bottomborders
                    if (Math.abs(DiffY) <= yOffset + 1) {
                        //getWorld().showText("Reach 2", 700, 420);
                        //getWorld().showText("Found Vertical Object", 700, 200);
                        // Top Edge
                        if (direction > 180 && direction < 360) {
                            //getWorld().showText("Reach 3", 700, 440);
                            //getWorld().showText("Topbrick", 100, 150);
                            double difference = 0;
                
                            difference = 270 - direction;
                            direction = 90 + difference;
                        }
                        
                        // Bottom Edge
                        else if (direction > 0 && direction < 180){
                            //getWorld().showText("Bottombrick", 100, 150);
                            double difference = 0;
            
                            difference = 90 - direction;
                            direction = 270 + difference;
                        }
                    } 
                }
                
                if (Math.abs(DiffY) <= height / 2) {
                    if (DiffX == xOffset || DiffX == -xOffset) {
                        //getWorld().showText("Found Horizontal Object", 700, 100);
                        // Change direction of ball like sideborders
                        // Left Edge
                        if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                            //getWorld().showText("Brick", 100, 150);
                            double difference = 0;
                            
                            difference = 360 - direction;
                            direction = 180 + difference;
                        }
                        // Right Edge
                        else if (direction > 90 && direction < 270) {
                            //getWorld().showText("Brick", 100, 150);
                            double difference = 0;
                
                            difference = 180 - direction;
                            direction = 360 + difference;
                        }
                    } 
                }
            }
            //getWorld().showText("NotTouching", 100, 100);
        }
        
        if (isTouching(Paddle.class) && blockedpaddle == 0) {
            //getWorld().showText("Touching", 100, 100);
            if (direction > 0 && direction < 180) {
                // Implement Callculation
                Paddle paddle = new Paddle();
                int width = paddle.getImage().getWidth();
                List<Paddle> paddles = getObjectsInRange(width, Paddle.class);
            
                if (!paddles.isEmpty()) {
                    Paddle closestPaddle = paddles.get(0); // Assume the first Leiste is the only one
                    int distance = getX() - closestPaddle.getX();
                    
                    // left edge = -12
                    // right edge = 12
                    direction = 270 + (distance*(90/(width/2)));
                }
            }
            blockedpaddle = interval;
        } else {
            if (blockedpaddle != 0) {
                blockedpaddle--;
            }
            //getWorld().showText("NotTouching", 100, 100);
        }
        
        if (isTouching(Sideborder.class) && blockedsideborder == 0) {
            //getWorld().showText("Touching", 100, 100);
            // Right Sideboarder
            if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
                //getWorld().showText("Rightborder", 100, 150);
                double difference = 0;
                
                difference = 360 - direction;
                direction = 180 + difference;
            }
            // Left Sideboarder
            else if (direction > 90 && direction < 270) {
                //getWorld().showText("Leftborder", 100, 150);
                double difference = 0;
    
                difference = 180 - direction;
                direction = 360 + difference;
            }
            blockedsideborder = interval;
        } else {
            if (blockedsideborder != 0) {
                blockedsideborder--;
            }
            //getWorld().showText("NotTouching", 100, 100);
        }
        
        if (isTouching(Topborder.class)) {
            //getWorld().showText("Touching", 100, 100);
            if (direction > 180 && direction < 360) {
                //getWorld().showText("Topborder", 100, 150);
                double difference = 0;
    
                difference = 270 - direction;
                direction = 90 + difference;
            }
            blockedtopborder = interval;
        } else {
            if (blockedtopborder != 0) {
                blockedtopborder--;
            }
            //getWorld().showText("NotTouching", 100, 100);
        }
        
        if (direction >= 360) {
            direction -= 360;
        }
    }
    
    private void checkBottomBorder(){
        if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}






