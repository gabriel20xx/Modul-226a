import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)


/**
 * Weltraum. Etwas, in dem Raketen fliegen ...
 * 
 * @author Michael Kölling
 * @version 2.0
 */
public class Space extends World
{
    /**
     * Erzeugt die Weltraum-Welt mit schwarzem Hintergrund und Sternen.
     */
    public Space() 
    {
        super(854, 480, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(300);
        createBorders();
        createLeiste();
        createBricks();
        createBall();
    }
    
    /**
     * Erzeugt einige zufällige Sterne in der Welt.
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
    
    private void createBorders()
    {
        for (int i = 1; i < 3; i++)
        {
            Border border = new Border();
            addObject(border, i*this.getWidth()/3, this.getHeight()/2);
        }
    }
    
    /**
     * Erzeugt Leiste.
     */
    private void createLeiste()
    {
        Leiste leiste = new Leiste();
        addObject(leiste, this.getWidth()/2, this.getHeight() - this.getHeight()/5);
    }
    
    private void createBall()
    {
        Ball ball = new Ball(90, 1); // Direction, Speed
        addObject(ball, this.getWidth()/2, this.getHeight()/2);
    }
    
    /**
     * Erzeugt Bricks.
     */
    private void createBricks()
    {
        int Rows = 3; // Row Count
        int Columns = 8; // Column Count
        for (int y = 0; y < Rows; y++)
        {
            for (int x = 0; x < Columns; x++)
            {
                Brick brick = new Brick();
                addObject(brick, (x*22)+(this.getWidth()/2)-(Columns*22/2)+(brick.getImage().getWidth()/2), (y*6)+(this.getHeight()/5));
            }
        }
    }
}
