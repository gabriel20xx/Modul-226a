import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot
import java.util.*;

/**
 * Eine Murmel im Weltraum.
 *  
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Ball extends Actor {
    private int delayTimer = 100;
    private double direction;
    public double speed;
    private double fractionalDistanceX = 0.0;
    private double fractionalDistanceY = 0.0;
    
    private boolean blockedsideborder = false;
    private boolean blockedtopborder = false;
    private boolean blockedpaddle = false;
    private boolean blockedbrick = false;

    public Ball(double direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public void act() {
        if (delayTimer > 0) {
            initial();
            delayTimer--;
        } else {
            moveInDirection();
            checkCollision();
            normalizeDirection();
            checkBottomBorder();
        }
    }
    
    private void initial() {
        Class<Paddle> actorClass = Paddle.class;
        Paddle foundPaddle = null;
        
        for (Actor object : getWorld().getObjects(Paddle.class)) {
            if (object.getClass() == actorClass) {
                foundPaddle = (Paddle) object;
                break;
            }
        }
        
        if (foundPaddle != null) {
            int foundPaddleX = foundPaddle.getX();
            int foundPaddleY = foundPaddle.getY();
            setLocation(foundPaddleX + foundPaddle.getImage().getWidth()/4, foundPaddleY - foundPaddle.getImage().getHeight());
        }
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
    
    private void checkCollision() {
        // Check for collision with two objects
        if ((isTouching(Sideborder.class) && isTouching(Topborder.class)) || (isTouching(Sideborder.class) && isTouching(Paddle.class)) || (isTouching(Sideborder.class) && isTouching(Brick.class))) {
            direction += 180;
        } else {
            checkBrickCollision();
            checkPaddleCollision();
            checkSideborderCollision();
            checkTopborderCollision();
        }
    }
    
    private void checkBrickCollision() {
        if (isTouching(Brick.class) && blockedbrick == false) {
            Brick brickSize = new Brick(1);
            int brickHeight = brickSize.getImage().getHeight();
            int brickWidth = brickSize.getImage().getWidth();
            int brickPosX;
            int brickPosY;
            
            int ballHeight = this.getImage().getHeight();
            int ballWidth = this.getImage().getWidth();
            int ballPosX = this.getX();
            int ballPosY = this.getY();
            
            int xOffset = brickWidth/2 + ballWidth/2;
            int yOffset = brickHeight/2 + ballHeight/2;
            
            List<Brick> bricksInRadius = getObjectsInRange(yOffset + 2, Brick.class);
            if (bricksInRadius.isEmpty()) {
                bricksInRadius = getObjectsInRange(xOffset + 2, Brick.class);
            }
            
            Brick closestBrick = null;
            int minDiffX = Integer.MAX_VALUE;
            int minDiffY = Integer.MAX_VALUE;
            
            // Calculate closest brick
            for (Brick brick : bricksInRadius) {
                brickPosX = brick.getX();
                brickPosY = brick.getY();
            
                int DiffXx = brickPosX - ballPosX;
                int DiffYy = brickPosY - ballPosY;
            
                if (DiffXx < minDiffX) {
                    minDiffX = DiffXx;
                    closestBrick = brick;
                }
                
                if (DiffYy < minDiffY) {
                    minDiffY = DiffYy;
                    closestBrick = brick;
                }
            }
            
            if (closestBrick != null) {
                brickPosX = closestBrick.getX();
                brickPosY = closestBrick.getY();
                
                int DiffX = ballPosX - brickPosX;
                int DiffY = ballPosY - brickPosY;
                
                // Calculate Difference from DiffY to yOffset and DiffX to xOffset
                int xDiffOffset = Math.abs(DiffX - xOffset);
                int yDiffOffset = Math.abs(DiffY - yOffset);
                
                getWorld().showText("DiffX: "+xDiffOffset, getWorld().getWidth()/6*5, 500);
                getWorld().showText("DiffY: "+yDiffOffset, getWorld().getWidth()/6*5, 550);
                
                if (xDiffOffset < yDiffOffset) {
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
                } else if (yDiffOffset < xDiffOffset) {
                    // Ball comming from bottom
                    if (direction > 180 && direction < 360) {
                        double difference = 0;
                        difference = 270 - direction;
                        direction = 90 + difference;
                    }
                    // Ball comming from top
                    else if (direction > 0 && direction < 180){
                        double difference = 0;
                        difference = 90 - direction;
                        direction = 270 + difference;
                    }
                } else {
                    direction += 180;
                }
                blockedbrick = true;
            }
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






