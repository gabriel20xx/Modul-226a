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
    private int score;
    private int amountLives;
    /**
     * Erzeugt die Weltraum-Welt mit schwarzem Hintergrund und Sternen.
     */
    public Space() 
    {
        super(854, 480, 1);
        setBackground();
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        Greenfoot.setSpeed(50);
        //createStars(300);
        createBorders();
        createPaddle();
        // createBricks();
        createBall();
    }
    
    /**
     * Erzeugt die Weltraum-Welt mit schwarzem Hintergrund und Sternen.
     */
    public Space(int gameNumber) 
    {
        super(854, 480, 1);
        setBackground();
        initializeGame();
        if (gameNumber == 1) {
            playGameOne();
        }
        else if (gameNumber == 2) {
            playGameTwo();
        }
        else  {
            playGameThree();
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
     * Initialize the actors which are always the same
     */
    private void initializeGame() {
        createBorders();
        createPaddle();
        createBall();
    }
    
    public void act() {
        checkBricks();
        checkBalls();
        increaseSpeed();
        updateTime();
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
    
    private void playGameOne() {
        int rowCount = 4;
        int columnCount = 8; 
        for (int y = 0; y < rowCount; y++)
        {
            for (int x = 0; x < columnCount; x++)
            {
                Brick brick = new Brick();
                addObject(brick, (x*22)+(this.getWidth()/2)-(columnCount*22/2)+(brick.getImage().getWidth()/2), (y*6)+(this.getHeight()/8));
            }
        }
    }
    
    private void playGameTwo() {
        int counter = 0;
        int brickRange = getWidth() / 3;
        int height = getHeight();
        int minusHeight = getHeight() / 7;
        while (counter < 20) {
            Brick brick = new Brick();
            addObject(brick, Greenfoot.getRandomNumber(brickRange) + brickRange, Greenfoot.getRandomNumber(height) - minusHeight);
            if(brick.isTouchingAnotherBrick() || brick.isTouchingBorder()) {
                removeObject(brick);
            }
            else {
                counter++;
            }
        }
    }
    
    private void playGameThree() {
        int xPostionLeft = getWidth() / 3 + 50;
        int xPositionRight = getWidth() / 3 * 2 - 50;
        int middlePosition = (xPostionLeft + xPositionRight) / 2;
        for (int x=0; x < 8; x++) {
            addObject(new Brick(), xPostionLeft, 50 + 15 * (x + 1));
        }
        
        for (int x=0; x < 8; x++) {
            addObject(new Brick(), xPositionRight, 50 + 15 * (x + 1));
        }
        
        for (int x=0; x < 8; x++) {
            addObject(new Brick(), middlePosition, 50 + 15 * (x + 1));
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
        addObject(paddle, this.getWidth()/2, this.getHeight() - this.getHeight()/ 9);
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
    
    private void increaseSpeed() {
        if (timePassed % 60 == 0) {
            List<Ball> balls = getObjects(Ball.class);
            ballSpeed+=0.01; // Faster every second
            for (Ball ball : balls) {
                ball.speed = ballSpeed;
            }
        }
    }
    
    private void updateTime() {
        timePassed++;
        showText("Time: " + timePassed/50, 100, 50);
    }
}
