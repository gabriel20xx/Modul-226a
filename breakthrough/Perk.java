import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Perk here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Perk extends Actor
{
    private int speed;
    /**
     * Constructor to initialize a Perk.
     */
    public Perk() {
        this.speed = Greenfoot.getRandomNumber(5) + 2;
    }
    
    /**
     * Fall down the game.
     */
    public void act()
    {
        setLocation(getX(), getY() + speed);
        checkToRemove();
    }
    
    /**
     * Check if perk should be removed.
     */
    private void checkToRemove() {
        if(isTouching(Ball.class) || isTouching(Paddle.class)) {
            updateWorldForPerk(1);
        }
        else if(getY() > 700) {
            updateWorldForPerk(0);
        }
    }
    
    /**
     * Update the world
     */
    private void updateWorldForPerk(int addingScore) {
        Space world = (Space) getWorld();
        world.updateUserLive(addingScore);
        world.removeObject(this);
    }
}
