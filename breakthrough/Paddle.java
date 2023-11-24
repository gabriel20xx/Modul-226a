import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * The paddle to steer the balls direction.
 *
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Paddle extends Mover {
    private int movingSpeed = 8;
    private int type;
    private int mode;

    /**
     * Constructor to initialize the actor.
     */
    public Paddle(int type) {
        this.type = type;
        mode = Mode.mode;
    }

    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        checkKeys();
        checkBallTouching();
    }

    /**
     * Check if keys are pressed.
     */
    private void checkKeys() {
        /*
        Greenfoot.isKeyDown and Arrays/ ArrayLists don't work well together.
        It works only for the first element in the array.
        --> Solution was to have a bit of "doubled" code.
         */
        if (mode == 1) {
            updatePaddleSpeed(Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("space"));
            moveToLeft(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"));
            moveToRight(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"));
        } else if (mode == 2) {
            if (type == 1) {
                updatePaddleSpeed(Greenfoot.isKeyDown("shift"));
                moveToLeft(Greenfoot.isKeyDown("a"));
                moveToRight(Greenfoot.isKeyDown("d"));
            } else if (type == 2) {
                updatePaddleSpeed(Greenfoot.isKeyDown("space"));
                moveToLeft(Greenfoot.isKeyDown("left"));
                moveToRight(Greenfoot.isKeyDown("right"));
                setImage("Paddle2.png");
            }
        }
    }

    /**
     * Update the moving speed of the paddle
     *
     * @param higherSpeed True if moving speed should be higher.
     */
    private void updatePaddleSpeed(boolean higherSpeed) {
        if (higherSpeed) {
            movingSpeed = 15;
        } else {
            movingSpeed = 8;
        }
    }

    /**
     * Move the paddle in a given direction.
     *
     * @param moving True if paddle should move.
     * @param direction The direction in which to move (1 for right, -1 for left).
     */
    private void movePaddle(boolean moving, int direction) {
        if (moving) {
            if (isTouching(Sideborder.class)) {
                Sideborder border = (Sideborder) getOneIntersectingObject(Sideborder.class);
                if (border != null) {
                    int xCoordinateBorder = border.getX();
                    if ((direction == 1 && getX() > xCoordinateBorder) ||
                            (direction == -1 && getX() < xCoordinateBorder)) {
                        setLocation(getX() + direction * movingSpeed, getY());
                    }
                }
            } else {
                setLocation(getX() + direction * movingSpeed, getY());
            }
        }
    }

    /**
     * Move the paddle to the left.
     *
     * @param movingLeft True if paddle should move to the left.
     */
    private void moveToLeft(boolean movingLeft) {
        movePaddle(movingLeft, -1);
    }

    /**
     * Move the paddle to the right.
     *
     * @param movingRight True if paddle should move to the right.
     */
    private void moveToRight(boolean movingRight) {
        movePaddle(movingRight, 1);
    }


    /**
     * Check if a ball is touching the perk.
     */
    private void checkBallTouching() {
        if (isTouching(Ball.class)) {
            Space space = (Space) getWorld();
            space.updateScore(10);
        }
    }
}
