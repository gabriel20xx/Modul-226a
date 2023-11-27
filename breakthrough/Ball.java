import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot
import java.util.*;

/**
 * A ball in space.
 *  
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Ball extends Mover {
    public double direction;
    public double speed;
    private double fractionalDistanceX = 0.0;
    private double fractionalDistanceY = 0.0;
    private boolean brickTouched = false;
    private boolean sideborderTouched = false;
    private boolean topborderTouched = false;
    private boolean paddleTouched = false;

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
        checkCollisions();
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
        
        // Move object
        setLocation(getX() + wholePixelsX, getY() + wholePixelsY); 

        // Substract full pixels from fractional one
        fractionalDistanceX -= wholePixelsX;
        fractionalDistanceY -= wholePixelsY; 
    }
    
    /**
     * Check the collision of the ball with two other objects.
     */
    private void checkCollisions() {
        // Check for collision with two classes
        if ((isTouching(Sideborder.class) && isTouching(Topborder.class)) || 
            (isTouching(Sideborder.class) && isTouching(Paddle.class)) || 
            (isTouching(Sideborder.class) && isTouching(Brick.class))) {
            redirectBall("Invert");
            
            if (isTouching(Brick.class)) {
                brickTouched = true;
            } else {
                sideborderTouched = false;
            }
            if (isTouching(Sideborder.class)) {
                sideborderTouched = true;
            } else {
                sideborderTouched = false;
            }
            if (isTouching(Topborder.class)) {
                topborderTouched = true;
            } else {
                sideborderTouched = false;
            }
            if (isTouching(Paddle.class)) {
                paddleTouched = true;
            } else {
                sideborderTouched = false;
            }
        } else {
            // Check for collision with only one object
            if (isTouching(Brick.class)) {
                if (!brickTouched) {
                    checkIntersecting();
                    brickTouched = true;
                }
            } else {
                brickTouched = false;
            }
    
            if (isTouching(Sideborder.class)) {
                if (!sideborderTouched) {
                    checkSideborderCollision();
                    sideborderTouched = true;
                }
            } else {
                sideborderTouched = false;
            }
    
            if (isTouching(Topborder.class)) {
                if (!topborderTouched) {
                    checkTopborderCollision();
                    topborderTouched = true;
                }
            } else {
                topborderTouched = false;
            }
    
            if (isTouching(Paddle.class)) {
                if (!paddleTouched) {
                    checkPaddleCollision();
                    paddleTouched = true;
                }
            } else {
                paddleTouched = false;
            }
        }
    }
    
    /**
     * Check if the ball hits a paddle.
     */
    private void checkPaddleCollision() {
        if (direction > 0 && direction < 180) {
            // Implement Callculation
            Paddle paddle = new Paddle(1);
            int width = paddle.getImage().getWidth();
            List<Paddle> paddles = getObjectsInRange(width, Paddle.class);
        
            if (!paddles.isEmpty()) {
                for (Paddle closestpaddle : paddles) {
                    if (this.intersects(closestpaddle)) {
                        int distance = getX() - closestpaddle.getX();
                        direction = 270 + (distance*(90/(width/1.5)));
                        break;
                    }
                }
            }
        
            // Exact line
            else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
                redirectBall("Invert");
            }
        }
    }
    
    /**
     * Check if the ball collides with the sideborder.
     */
    private void checkSideborderCollision() {
        // Right Sideboarder
        if ((direction > 270 && direction < 360) || (direction < 90 && direction > 0)) {
            redirectBall("Left");
        }
        // Left Sideboarder
        else if (direction > 90 && direction < 270) {
            redirectBall("Right");
        } 
        // Exact line
        else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
            redirectBall("Invert");
        }
    }
    
    /**
     * Check if the ball collides with the topborder.
     */
    private void checkTopborderCollision() {
        if (direction > 180 && direction < 360) {
            redirectBall("Bottom");
        }
        // Exact line
        else if (direction == 0 || direction == 90 || direction == 180 || direction == 270 || direction == 360) {
            redirectBall("Invert");
        }
    }
    
    /**
     * Create a degree between 0 and 360.
     */
    private void normalizeDirection() {
        if (direction >= 360) {
            direction -= 360;
        } else if (direction < 0) {
            direction += 360;
        }
    }
    
    private void checkIntersecting() {
        List<Brick> bricks = getObjectsInRange(100, Brick.class);

        // Counter for the number of intersections
        int intersections = 0;
        int brick1X = 0;
        int brick1Y = 0;
        int brick2X = 0;
        int brick2Y = 0;
        int brick3X = 0;
        int brick3Y = 0;
        
        // Loop through each instance to check for intersections
        for (Brick brick : bricks) {
            if (this.intersects(brick)) {
                intersections++;

                // Your code for when the ball touches this instance of "Target"
                if (intersections == 3) {
                    // Exit the loop once you've found three intersections
                    break; 
                }
        
                if (intersections == 2) {
                    // Your code for when the ball intersects with three instances
                    brick2X = brick.getX();
                    brick2Y = brick.getY();
                }
                
                if (intersections == 1) {
                    // Your code for when the ball intersects with three instances
                    brick1X = brick.getX();
                    brick1Y = brick.getY();
                }
            }
        }
        String edge = checkEdge(intersections, brick1X, brick1Y, brick2X, brick2Y);
        redirectBall(edge);
    }
    
    private String checkEdge(int objectCount, int brickX, int brickY, int brick2X, int brick2Y) {
        Brick brick = new Brick(1);
        int ballX = getX();
        int ballY = getY();        int brickWidth = brick.getImage().getWidth();
        int brickHeight = brick.getImage().getHeight();
        
        int topEdge = brickY - brickHeight/2;
        int bottomEdge = brickY + brickHeight/2;
        int leftEdge = brickX - brickWidth/2;
        int rightEdge = brickX + brickWidth/2;
        
        int xDistanceToMiddle = Math.abs(brickX - ballX);
        int yDistanceToMiddle = Math.abs(brickY - ballY);
        int distanceToTop = Math.abs(topEdge - ballY);
        int distanceToBottom = Math.abs(bottomEdge - ballY);
        int distanceToLeft = Math.abs(leftEdge - ballX);
        int distanceToRight = Math.abs(rightEdge - ballX);
        
        if (objectCount == 1) {            
            // Determine the closest edge and print the corresponding statement
            if (distanceToTop < distanceToBottom && distanceToTop < distanceToLeft && distanceToTop < distanceToRight) {
                if (xDistanceToMiddle < brickWidth/2) {
                    // Top Edge
                    return "Top";
                } else {
                    if (distanceToLeft < distanceToRight) {
                        return "Left";
                        // Left Edge
                    } else {
                        return "Right";
                        // Right Edge
                    }
                }
            }
            else if (distanceToBottom < distanceToTop && distanceToBottom < distanceToLeft && distanceToBottom < distanceToRight) {
                if (xDistanceToMiddle < brickWidth/2) {
                    // Bottom Edge
                    return "Bottom";
                } else {
                    if (distanceToLeft < distanceToRight) {
                        return "Left";
                        // Left Edge
                    } else {
                        return "Right";
                        // Right Edge
                    }
                }
            } else if (distanceToLeft < distanceToTop && distanceToLeft < distanceToBottom && distanceToLeft < distanceToRight) {
                return "Left";
                // Left Edge
            } else {
                return "Right";
                // Right Edge
            }
        } else if (objectCount == 2) {
            // 3 Variants
            // 1. Horizontaly Next to each other (top or bottom)
            // 2. Verticaly Next to each other (like left or right)
            // 3. Diagonally next to each other (invert)
            if (brickX == brick2X) {
                // Vertically (left / right)
                if (distanceToLeft < distanceToRight) {
                    return "Left";
                } else {
                    return "Right";
                }
            } else if (brickY == brick2Y) {
                // Horizontally (top / bottom)
                if (distanceToTop < distanceToBottom) {
                    return "Top";
                } else {
                    return "Bottom";
                }
            } else {
                return "Invert";
            }
        } else {
            // Invert direction
            return "Invert";
        }
    }
    
    private void redirectBall(String edge) {
        if (edge == "Top") {
            if (direction >= 0 && direction <= 180) {
                double difference = 0;
                difference = 90 - direction;
                direction = 270 + difference;
            }
        } else if (edge == "Bottom") {
            if (direction >= 180 && direction <= 360) {
                double difference = 0;
                difference = 270 - direction;
                direction = 90 + difference;
            }
        } else if (edge == "Right") {
            if (direction >= 90 && direction <= 270) {
                double difference = 0;
                difference = 180 - direction;
                direction = 360 + difference;   
            }
        } else if (edge == "Left") {
            if (direction >= 270 || direction <= 90) {
                double difference = 0;
                difference = 360 - direction;
                direction = 180 + difference;
            }
        } else {
            double difference = 0;
            direction = direction + 180;
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
