import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a background full of stars.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stars extends World
{

    /**
     * Constructor for objects of class Stars.
     * 
     */
    public Stars()
    {    
        super(1280, 720, 1);
        setBackground();
    }
    
    /**
     * Set the background of the world.
     */
    private void setBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        Greenfoot.setSpeed(50);
        createStars(1000);
    }
    
    /**
     * Create random stars.
     * @param number The number of stars.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for (int i=0; i < number; i++) {            
             int x = Greenfoot.getRandomNumber(getWidth());
             int y = Greenfoot.getRandomNumber(getHeight());
             int color = 150 - Greenfoot.getRandomNumber(120);
             background.setColorAt(x, y, new Color(color,color,color));
        }
    }
}
