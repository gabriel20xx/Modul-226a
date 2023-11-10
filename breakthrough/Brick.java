import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Write a description of class Brick here.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Brick extends Actor {
    private int color;
    private int count = 0;
    private boolean blockedball = false;
    
    // Color codes: 1=White, 2=Green, 3=Yellow, 4=LightBlue, 5=Red, 6=Pink, 7=Orange, 8=DarkBlue, 9=Silver, 10=Gold
    public Brick(int colour) {
        this.color = colour;
        setColor();
    }

    public void act() {
        if (isTouching(Ball.class) && blockedball == false) {
            if (color == 9) {
                count++;
                blockedball = true;
                if (count >= 2) {
                    Space space = (Space) getWorld();
                    space.updateScore(50);
                    getWorld().removeObject(this);
                }
            } 
            else if (color != 10) {
                Space space = (Space) getWorld();
                space.updateScore(50);
                getWorld().removeObject(this);
            }
        } else {
            blockedball = false;
        }
    }
    
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
     * Check if brick is touching another brick-
     */    
    public boolean isTouchingAnotherBrick() {
        if (isTouching(Brick.class)) {
            return true;
        }
        return false;
    }    
    
    /**
     * Check if brick is touching another brick-
     */    
    public boolean isTouchingBorder() {
        if (isTouching(Border.class)) {
            return true;
        }
        return false;
    }     
}
