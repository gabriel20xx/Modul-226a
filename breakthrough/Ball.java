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
        
        /*int initialX = getX();
        int initialY = getY();

        // Check line in sight
        int[] result = checkLineInSight(wholePixelsX, wholePixelsY);
        wholePixelsX = result[0];
        wholePixelsY = result[1];*/
        
        // Move object
        setLocation(getX() + wholePixelsX, getY() + wholePixelsY); 

        // Substract full pixels from fractional one
        fractionalDistanceX -= wholePixelsX;
        fractionalDistanceY -= wholePixelsY; 
    }
    
    /**
     * Check the collision of the ball with two other objects.
     */
    private void checkCollision() {
        // Check for collision with two classes
        if (((isTouching(Sideborder.class) && isTouching(Topborder.class)) || (isTouching(Sideborder.class) && isTouching(Paddle.class)) || (isTouching(Sideborder.class) && isTouching(Brick.class))) && blockedborderpaddle <= 0) {
            direction += 180;
            blockedborderpaddle = this.blockedborderpaddle;
        } else {
            blockedborderpaddle--;
            checkIntersecting();
            checkPaddleCollision();
            checkSideborderCollision();
            checkTopborderCollision();
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
                    // Your code for when the ball intersects with three instances
                    brick3X = brick.getX();
                    brick3Y = brick.getY();
                    break; // exit the loop once you've found three intersections
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
        getWorld().showText("Intersections: "+ intersections, 1100, 500);
        getWorld().showText("Brick 1: "+ brick1X + " / " + brick1Y, 1100, 600);
        getWorld().showText("Brick 2: "+ brick2X + " / " + brick2Y, 1100, 650);
        getWorld().showText("Brick 3: "+ brick3X + " / " + brick3Y, 1100, 700);
        if (intersections >= 1) {
            String edge = checkEdge(intersections, brick1X, brick1Y, brick2X, brick2Y, brick3X, brick3Y);
            redirectBall(edge);
        }
    }
    
    private String checkEdge(int objectCount, int brick1X, int brick1Y, int brick2X, int brick2Y, int brick3X, int brick3Y) {
        Brick brick = new Brick(1);
        int ballX = getX();
        int ballY = getY();
        int brickX = brick1X;
        int brickY = brick1Y;
        int brickWidth = brick.getImage().getWidth();
        int brickHeight = brick.getImage().getHeight();
        
        int topEdge = brickY - brickHeight/2;
        int bottomEdge = brickY + brickHeight/2;
        int leftEdge = brickX - brickWidth/2;
        int rightEdge = brickX + brickWidth/2;
        
        // Calculate the distance from the ball to the edges of the brick
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
            if (brick1X == brick2X) {
                // Vertically (left / right)
                if (distanceToLeft < distanceToRight) {
                    return "Left";
                } else {
                    return "Right";
                }
            } else if (brick1Y == brick2Y) {
                if (distanceToTop < distanceToBottom) {
                    return "Top";
                } else {
                    return "Bottom";
                }
                // Horizontally (top / bottom)
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
            getWorld().showText("Top", 1000, 550);
            double difference = 0;
            difference = 90 - direction;
            direction = 270 + difference;
        } else if (edge == "Bottom") {
            getWorld().showText("Bottom", 1000, 550);
            double difference = 0;
            difference = 270 - direction;
            direction = 90 + difference;
        } else if (edge == "Left") {
            getWorld().showText("Left", 1000, 550);
            double difference = 0;
            difference = 360 - direction;
            direction = 180 + difference;
        } else if (edge == "Right") {
            getWorld().showText("Right", 1000, 550);
            double difference = 0;
            difference = 180 - direction;
            direction = 360 + difference;    
        } else {
            getWorld().showText("Invert", 1000, 550);
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
