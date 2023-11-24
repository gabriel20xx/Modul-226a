import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Selection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selection extends Stars
{
    public static int mode;
    private boolean keyPressed = false;
    private boolean keyDown = false;
    /**
     * Constructor for objects of class Selection.
     */
    public Selection()
    {
    }
    
    public void showSelectors(int step, int rows, int columns, String text) {
        int horizontalspacing = this.getWidth()/columns;
        int verticalspacing = this.getHeight()/rows;
        int count = 0;
        addObject(new Selector(), (0 * horizontalspacing) + (horizontalspacing / 2), (0 * verticalspacing) + (verticalspacing / 2));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                count++;
                // Calculate the x and y positions for each actor
                int x = (col * horizontalspacing) + (horizontalspacing / 2);
                int y = (row * verticalspacing) + (verticalspacing / 2);

                // Create and add the actor at the calculated position
                addObject(new Square(), x, y);
                if (step == 1) {
                    if (count == 1) {
                       showText("Singleplayer", x, y); 
                    }
                    else if (count == 2) {
                        showText("Coop", x, y); 
                    }
                } else if (step == 2) {
                    showText("Level "+ count, x, y);
                }
            }
        }
        showText(text, this.getWidth()/2, this.getHeight()/16*1);
    }
    
    public void changeSelector(int step, int rows, int columns) {
        int horizontalspacing = this.getWidth()/columns;
        int verticalspacing = this.getHeight()/rows;
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
                keyDown = true;
            }
            if ((!Greenfoot.isKeyDown("enter")) && keyDown == true) {
                int count = 1;
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < columns; col++) {
                        // Calculate the x and y positions for each actor
                        int x = (col * horizontalspacing) + (horizontalspacing / 2);
                        int y = (row * verticalspacing) + (verticalspacing / 2);
                        
                        if (selectorX == x && selectorY == y) {
                            if (step == 1) {
                                mode = count;
                                Greenfoot.setWorld(new Level());
                            } else if (step == 2) {
                                Greenfoot.setWorld(new Space(count, 0, 3));
                            }
                        } else {
                            count++;
                        }
                    }   
                }
            }
        }
    }
}
