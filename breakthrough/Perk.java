import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The perk which adds lives to the user.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 * */
public class Perk extends Actor
{
    private int speed;

    /**
     * Constructor to initialize the actor.
     */
    public Perk() {
        this.speed = Greenfoot.getRandomNumber(5) + 2;
    }

    /**
     * Act method which runs in endless loop.
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
     * Update the world.
     */
    private void updateWorldForPerk(int addingScore) {
        Space world = (Space) getWorld();
        world.updateUserLive(addingScore);
        world.removeObject(this);
    }
}
