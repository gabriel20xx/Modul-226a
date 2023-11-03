import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    private final int score;
    private final String statusMessage;
    private boolean animationAlreadyShowed;
    public End(int score, String statusMessage) 
    {
        super(800, 600, 1); 
        this.score = score;
        this.statusMessage = statusMessage;
        animationAlreadyShowed = false;
    }
    
    public void act() {
        if (!animationAlreadyShowed) {
        for(int x = 0; x < statusMessage.length() + 1; x++) {
            showText(statusMessage.substring(0,x), 200, 150);
            Greenfoot.delay(5);
        }
        showText(String.format("Score: %d", score), 600, 150);
        showText("Press enter to choose a new game", 400, 100);
        animationAlreadyShowed = !animationAlreadyShowed;
        }
        if(Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Welcome());
        }
    }
}
