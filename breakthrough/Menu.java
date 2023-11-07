import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    private int rows = 3;
    private int columns = 5;
    private int count = 1;
    private int horizontalspacing = this.getWidth()/columns;
    private int verticalspacing = this.getHeight()/rows;
    private boolean keyPressed = false;
    /**
     * Constructor to create a world.
     */
    public Menu() 
    {
        super(1280, 720, 1);
        setBackground();
        showLevels();
    }
    
    public void act() {
        changeSelector();   
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
    
    private void showLevels() {
        addObject(new Selector(), (0 * horizontalspacing) + (horizontalspacing / 2), (0 * verticalspacing) + (verticalspacing / 2));
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                // Calculate the x and y positions for each actor
                int x = (col * horizontalspacing) + (horizontalspacing / 2);
                int y = (row * verticalspacing) + (verticalspacing / 2);

                // Create and add the actor at the calculated position
                addObject(new Level(), x, y);
                showText("Level "+ count, x, y);
                count++;
            }
        }
    }
    
    private void changeSelector() {
        Class<Selector> selectorClass = Selector.class;
        Selector selector = null;
        
        for (Actor object : getObjects(Selector.class)) {
            if (object.getClass() == selectorClass) {
                selector = (Selector) object;
                break;
            }
        }
        
        if (selector != null) {
            int selectorX = selector.getX();
            int selectorY = selector.getY();
            
            boolean noleft = false;
            boolean noright = false;
            boolean nodown = false;
            boolean noup = false;
            
            if (selectorX <= horizontalspacing) {
                noleft = true;
            }
            if (selectorX >= getWidth() - horizontalspacing) {
                noright = true;
            }            
            if (selectorY <= verticalspacing) {
                noup = true;
            }
            if (selectorY >= getHeight() - verticalspacing) {
                nodown = true;
            }
            
            if (!keyPressed && !noright && Greenfoot.isKeyDown("right")) {
                selector.setLocation(selectorX+horizontalspacing,selectorY);
                keyPressed = true;
            }
            if (!keyPressed && !noleft && Greenfoot.isKeyDown("left")) {
                selector.setLocation(selectorX-horizontalspacing,selectorY);
                keyPressed = true;
            }
            if (!keyPressed && !nodown && Greenfoot.isKeyDown("down")) {
                selector.setLocation(selectorX,selectorY+verticalspacing);
                keyPressed = true;
            }
            if (!keyPressed && !noup && Greenfoot.isKeyDown("up")) {
                selector.setLocation(selectorX,selectorY-verticalspacing);
                keyPressed = true;
            }
            
            if (!Greenfoot.isKeyDown("right")&& !Greenfoot.isKeyDown("left")&&!Greenfoot.isKeyDown("down")&&!Greenfoot.isKeyDown("up")) {
                // Reset the flag when the key is released
                keyPressed = false;
            }
            
            if (Greenfoot.isKeyDown("enter")) {
                // Get the location
                int level = 1;
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < columns; col++) {
                        // Calculate the x and y positions for each actor
                        int x = (col * horizontalspacing) + (horizontalspacing / 2);
                        int y = (row * verticalspacing) + (verticalspacing / 2);
                        
                        
                        if (selectorX == x && selectorY == y) {
                        Greenfoot.setWorld(new Space(level, 0, 3));
                        } else {
                            level++;
                        }
                    }   
                }
            }
        }
    }
}
