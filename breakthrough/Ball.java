import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot
import java.util.*;

/**
 * A ball in space.
 *  
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Ball extends Actor {
    public double direction;
    public double speed;
    private double fractionalDistanceX = 0.0;
    private double fractionalDistanceY = 0.0;
    private int blockedborderpaddle = 5;
    private boolean blockedsideborder = false;
    private boolean blockedtopborder = false;
    private boolean blockedpaddle = false;
    private boolean blockedbrick = false;

    /**
     * Constructor to initialize the actor.
     */
    public Ball(double direction, double speed) {
        this.direction = direction;
        this.speed = speed;
    }

    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        normalizeDirection();
        moveInDirection();
        checkCollision();
        checkBottomBorder();
    }
    
    /**
     * Move the ball in a direction.
     */
    private void moveInDirection() {
         // Convert degrees to radians
        double radians = Math.toRadians(direction);
        
        // Convert to horizontal and vertical movement
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;

        // Save fractional distance in variable
        fractionalDistanceX += dx;
        fractionalDistanceY += dy;

        int wholePixelsX = (int) fractionalDistanceX;
        int wholePixelsY = (int) fractionalDistanceY;
        
        int initialX = getX();
        int initialY = getY();

        // Check line in sight
        int[] result = checkLineInSight(wholePixelsX, wholePixelsY);
        wholePixelsX = result[0];
        wholePixelsY = result[1];
        
        // Move object
        setLocation(initialX + wholePixelsX, initialY + wholePixelsY); 

        // Substract full pixels from fractional one
        fractionalDistanceX -= wholePixelsX;
        fractionalDistanceY -= wholePixelsY; 
    }
    
    /**
     * Checks if an object is in line of sight.
     */
    private int[] checkLineInSight(int wholePixelsX, int wholePixelsY) {
        // Check line in sight for touching
        int intSpeed = (int) Math.ceil(speed);
        //getWorld().showText("Speed: "+ intSpeed, 1000, 650);
        for (int i = 0; i < intSpeed; i++) {
            // Create new ball and delete it afterwards
            int newX = 0; 
            int newY = 0;

            if (wholePixelsX < 0) {
                newX = (wholePixelsX / intSpeed) - i;
            } else {
                newX = (wholePixelsX / intSpeed) + i;
            }
            if (wholePixelsY < 0) {
                newY = (wholePixelsY / intSpeed) - i;
            } else {
                newY = (wholePixelsY / intSpeed) + i;
            }
            
            // Spawn an invisible ball to check for touching
            BallInvisible invball = new BallInvisible();
            getWorld().addObject(invball, getX() + newX, getY() + newY);
            if (invball.brickTouching) {
                wholePixelsX = newX;
                wholePixelsY = newY;
                getWorld().removeObject(invball);
                break;
            } else {
                getWorld().removeObject(invball);
            }
            
            // Spawn an invisible ball to check for touching
            getWorld().addObject(invball, getX() + newX, getY());
            if (invball.brickTouching) {
                wholePixelsX = newX;
                getWorld().removeObject(invball);
                break;
            } else {
                getWorld().removeObject(invball);
            }
            
            // Spawn an invisible ball to check for touching 
            getWorld().addObject(invball, getX(), getY() + newY); 
            if (invball.brickTouching) {
                wholePixelsY = newY;
                getWorld().removeObject(invball);
                break;
            } else {
                getWorld().removeObject(invball);
            }
        }
        return new int[]{wholePixelsX, wholePixelsY};
    }
    
    /**
     * Check the collision of the ball with two other objects.
     */
    private void checkCollision() {
        // Check for collision with two objects
        if (((isTouching(Sideborder.class) && isTouching(Topborder.class)) || (isTouching(Sideborder.class) && isTouching(Paddle.class)) || (isTouching(Sideborder.class) && isTouching(Brick.class))) && blockedborderpaddle <= 0) {
            direction += 180;
            blockedborderpaddle = this.blockedborderpaddle;
        } else {
            blockedborderpaddle--;
            checkBrickCollision();
            checkPaddleCollision();
            checkSideborderCollision();
            checkTopborderCollision();
        }
    }
    
    /**
     * Check if the ball hits a brick.
     */
    private void checkBrickCollision() {
        if (isTouching(Brick.class) && blockedbrick == false) {
            // getWorld().showText("Touching: yes", 1000, 600);
            Greenfoot.playSound("Hit.mp3");
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
                    // Exact line
                    else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                        direction = direction + 180;
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
                    // Exact line
                    else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                        direction = direction + 180;
                    }
                } else {
                    direction += 180;
                }
                blockedbrick = true;
            }
        } else {
            blockedbrick = false;
            // getWorld().showText("Touching: no", 1000, 600);
        }
    }
    
    /**
     * Check if the ball hits a paddle.
     */
    private void checkPaddleCollision() {
        if (isTouching(Paddle.class) && blockedpaddle == false) {
            if (direction > 0 && direction < 180) {
                // Implement Callculation
                Paddle paddle = new Paddle(1);
                int width = paddle.getImage().getWidth();
                List<Paddle> paddles = getObjectsInRange(width, Paddle.class);
            
                if (!paddles.isEmpty()) {
                    Paddle closestPaddle = paddles.get(0); // Assume the first Leiste is the only one
                    int distance = getX() - closestPaddle.getX();
                    direction = 270 + (distance*(90/(width/1.5)));
                }
            }
            
            // Exact line
            else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                direction = direction + 180;
            }
            blockedpaddle = true;
        } else {
            blockedpaddle = false;
        }
    }
    
    /**
     * Check if the ball collides with the sideborder.
     */
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
            // Exact line
            else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                direction = direction + 180;
            }
            blockedsideborder = true;
        } else {
            blockedsideborder = false;
        }
    }
    
    /**
     * Check if the ball collides with the topborder.
     */
    private void checkTopborderCollision() {
        if (isTouching(Topborder.class) && blockedtopborder == false) {
            if (direction > 180 && direction < 360) {
                double difference = 0;
                difference = 270 - direction;
                direction = 90 + difference;
            }
            
            // Exact line
            else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                direction = direction + 180;
            }
            blockedtopborder = true;
        } else {
            blockedtopborder = false;
        }
    }
    
    /**
     * Create a degree between 0 and 360.
     */
    private void normalizeDirection(){
        if (direction >= 360) {
            direction -= 360;
        } else if (direction < 0) {
            direction += 360;
        }
    }
    
    /**
     * Check if the ball reached the bottom of screen border.
     */
    private void checkBottomBorder(){
        if (getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
}
