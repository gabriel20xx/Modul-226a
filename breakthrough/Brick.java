import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * The brick actor which gets destroyed when a bill is touching..
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Brick extends Static {
    private int color;
    private int count = 0;
    private boolean blockedball = false;

    /**
     * Constructor to initialize the actor.
     */
    public Brick(int colour) {
        // Color codes: 1=White, 2=Green, 3=Yellow, 4=LightBlue, 5=Red, 6=Pink, 7=Orange, 8=DarkBlue, 9=Silver, 10=Gold
        this.color = colour;
        setColor();
    }

    /**
     * Act method which runs in endless loop.
     */
    public void act() {
        checkForBall();
    }

    /**
     * Check if ball hits the brick.
     */
    private void checkForBall() {
        if (isTouching(Ball.class) && blockedball == false) {
            if (color == 9) {
                // Bricks with color 9 must be hitted twice.
                count++;
                blockedball = true;
                if (count >= 2) {
                    Space space = (Space) getWorld();
                    space.updateScore(Config.silverBrickScore);
                    createPerk();
                    getWorld().removeObject(this);
                }
            }
            // The brick has neither color 9 nor 10.
            else if (color != 10) {
                Space space = (Space) getWorld();
                space.updateScore(Config.normalBrickScore);
                createPerk();
                getWorld().removeObject(this);
            }
            // The brick has the color 10 and can't be destroyed.
        } else {
            blockedball = false;
        }
    }

    /**
     * Return the color code.
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the color of the brick.
     */
    private void setColor() {
        GreenfootImage Image;
        switch (color) {
            case 1:
                Image = new GreenfootImage("Brick_White.png");
                break;
            case 2:
                Image = new GreenfootImage("Brick_Green.png");
                break;
            case 3:
                Image = new GreenfootImage("Brick_Yellow.png");
                break;
            case 4:
                Image = new GreenfootImage("Brick_LightBlue.png");
                break;
            case 5:
                Image = new GreenfootImage("Brick_Red.png");
                break;
            case 6:
                Image = new GreenfootImage("Brick_Pink.png");
                break;
            case 7:
                Image = new GreenfootImage("Brick_Orange.png");
                break;
            case 8:
                Image = new GreenfootImage("Brick_DarkBlue.png");
                break;
            case 9:
                Image = new GreenfootImage("Brick_Silver.png");
                break;
            case 10:
                Image = new GreenfootImage("Brick_Gold.png");
                break;
            default:
                Image = new GreenfootImage("Brick_White.png");
        }
        setImage(Image);
    }
    
    /**
     * Check if the brick is touching another brick.
     */    
    public boolean isTouchingAnotherBrick() {
        if (isTouching(Brick.class)) {
            return true;
        }
        return false;
    }    
    
    /**
     * Check if the brick is touching a border.
     */    
    public boolean isTouchingBorder() {
        if (isTouching(Border.class)) {
            return true;
        }
        return false;
    }     
    
    /**
     * Create a Perk on the brick position with a chance from 10 to 1.
     */
    private void createPerk() {
        if (Greenfoot.getRandomNumber(10) < 1) {
            getWorld().addObject(new Perk(), this.getX(), this.getY());
        }
    }
}
