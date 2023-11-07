import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    private final int score, lives;
    private final String statusMessage;
    private boolean animationAlreadyShowed;
    public End(int score, int lives,String statusMessage) 
    {
        super(1280, 720, 1);
        this.score = score;
        this.lives = lives;
        this.statusMessage = statusMessage;
        setBackground();
        animationAlreadyShowed = false;
    }
    
    public void act() {
        if (!animationAlreadyShowed) {
        for(int x = 0; x < statusMessage.length() + 1; x++) {
            showText(statusMessage.substring(0,x), this.getWidth()/2, this.getHeight()/5*1);
            Greenfoot.delay(5);
        }
        showText(String.format("Your Score: %d", score), this.getWidth()/2, this.getHeight()/5*2);

        showText("Press enter to choose a new game", this.getWidth()/2, this.getHeight()/5*4);
        animationAlreadyShowed = !animationAlreadyShowed;
        }
        if(Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Welcome());
        }
    }
    
    /**
     * Set the background of the world.
     */
    private void setBackground() {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        Greenfoot.setSpeed(50);
        createStars(300);
    }
    
    /**
     * Create random stars in the world.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for (int i=0; i < number; i++) {            
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 150 - Greenfoot.getRandomNumber(120);
             background.setColorAt(x, y, new Color(color,color,color));
        }
    }
}
