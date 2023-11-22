import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BallInvisible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BallInvisible extends Actor
{
    /**
     * Act - do whatever the BallInvisible wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean brickTouching = false;
    public void act()
    {
        if (isTouching(Brick.class)) {
            brickTouching = true;
        }
    }
}
