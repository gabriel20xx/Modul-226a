import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The perk which adds lives to the user.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 * */
public class Perk extends Mover
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
        if(getY() > 700) {
            Space world = (Space) getWorld();
            world.removeObject(this);
        } else {
            checkPaddleTouch();
        }
    }
    
    /**
     * Update the world.
     */
    private void checkPaddleTouch() {
        if(isTouching(Paddle.class)) {
            Space world = (Space) getWorld();
            int perk = Greenfoot.getRandomNumber(2) + 1;
            switch (perk) {
                case 1:
                    // Add 1 Live
                    world.updateUserLive(1);
                case 2:
                    // Split ball
                    for (Object obj : getWorld().getObjects(null)) {
                    // Check if the object is an instance of the Ball class
                    if (obj instanceof Ball) {
                        // Cast the object to Ball to access its methods and properties
                        Ball ball = (Ball) obj;
        
                        // Get the x and y positions of the ball
                        int x = ball.getX();
                        int y = ball.getY();
                        double direction = ball.direction;
                        double speed = ball.speed;
                        
                        Ball new1 = new Ball(direction + 45, speed);
                        getWorld().addObject(new1, x, y);
                        Ball new2 = new Ball(direction - 45, speed);
                        getWorld().addObject(new2, x, y);
                        
                        getWorld().removeObject(ball);
                    }
                } 
                case 3:
                    // Fireball
                    
            }
            world.removeObject(this);
        }
    }
}
