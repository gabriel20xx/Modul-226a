import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * Ein Weltraum mit Sternen
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Space extends World
{
    private int timePassed = 0;
    private double ballSpeed = 1;
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
        createPaddle();
        createBricks();
        createBall();
    }
    
    public void act() {
        checkBricks();
        checkBalls();
        updateTime();
        increaseSpeed();
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
            Sideborder border = new Sideborder();
            addObject(border, i*this.getWidth()/3, this.getHeight()/2);
        }
        Topborder border = new Topborder();
        addObject(border, this.getWidth()/2, 1);
    }
    
    /**
     * Erzeugt Leiste.
     */
    private void createPaddle()
    {
        Paddle paddle = new Paddle();
        addObject(paddle, this.getWidth()/2, this.getHeight() - this.getHeight()/5);
    }
    
    /**
     * Erzeugt eine Murmel.
     */
    private void createBall()
    {
        Ball ball = new Ball(340,ballSpeed); // Direction in Degrees and Speed
        addObject(ball,this.getWidth()/2, this.getHeight()/2);
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
    
    private void checkBricks() {
        List<Brick> bricks = getObjects(Brick.class);
        if (bricks.isEmpty()) {
            showText("You Won!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
    }
    
    private void checkBalls() {
        List<Ball> balls = getObjects(Ball.class);
        if (balls.isEmpty()) {
            showText("You Lost!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
    }
    
    private void updateTime() {
        timePassed++;
        showText("Time: " + timePassed/60, 100, 50);
    }
    
    private void increaseSpeed() {
        if (timePassed % 60 == 0) {
            List<Ball> balls = getObjects(Ball.class);
            ballSpeed+=0.01; // Faster every second
            for (Ball ball : balls) {
                ball.speed = ballSpeed;
            }
        }
    }
}
