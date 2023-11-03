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
    private int gameNumber;
    /**
     * Erzeugt die Weltraum-Welt mit schwarzem Hintergrund und Sternen.
     */
    public Space() 
    {
        super(1280, 720, 1);
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
        super(1280, 720, 1);
        setBackground();
        initializeGame();
        this.gameNumber = gameNumber;
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
        showTitle();
        showScore();
        showLevel(gameNumber);
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
        int rows = 16;
        int columns = 10;
        int startWidth = 50;
        int startHeight = 120;
        int brickWidth = 80;
        int brickHeight = 24;
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                Brick brick = new Brick(1);
                addObject(brick, x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
            }
        }
    }
    
    private void playGameTwo() {
        int counter = 0;
        int brickRange = getWidth() / 3;
        int height = getHeight();
        int minusHeight = getHeight() / 7;
        while (counter < 20) {
            Brick brick = new Brick(1);
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
            addObject(new Brick(1), xPostionLeft, 50 + 15 * (x + 1));
        }
        
        for (int x=0; x < 8; x++) {
            addObject(new Brick(1), xPositionRight, 50 + 15 * (x + 1));
        }
        
        for (int x=0; x < 8; x++) {
            addObject(new Brick(1), middlePosition, 50 + 15 * (x + 1));
        }
    }
    
    private void createBorders()
    {
        Sideborder leftborder = new Sideborder();
        addObject(leftborder, 40, 410);
        Sideborder rightborder = new Sideborder();
        addObject(rightborder, 860, 410);
        Topborder topborder = new Topborder();
        addObject(topborder, 450, 110);
    }
    
    /**
     * Erzeugt Leiste.
     */
    private void createPaddle()
    {
        Paddle paddle = new Paddle();
        addObject(paddle, 450, this.getHeight() - this.getHeight()/ 9);
    }
    
    /**
     * Erzeugt eine Murmel.
     */
    private void createBall()
    {
        // 340 = Direction in Degrees, ballSpeed = Speed
        Ball ball = new Ball(340,ballSpeed);
        addObject(ball, 450, this.getHeight()/4*3);
    }
    
    /**
     * Erzeugt Bricks.
     */
    private void createBricks()
    {
        int rows = 16;
        int columns = 10;
        int startWidth = 50;
        int startHeight = 120;
        int brickWidth = 80;
        int brickHeight = 24;
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                Brick brick = new Brick(1);
                addObject(brick, x*brickWidth+startWidth, y*brickHeight+startHeight);
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
        updateScore(1); // Why increase score with one on every act? Shouldn't it only increase by touching Brick and decrease on live lost?
        showText("Time: " + timePassed / 50, this.getWidth()/6*5, this.getHeight()/12*3);
    }
    
    private void showScore() {
        showText("Score: " + score, this.getWidth()/6*5, this.getHeight()/12*4);
    }
    
    private void showLevel(int gameNumber) {
        showText("Level "+ gameNumber, this.getWidth()/6*5, this.getHeight()/12*2);
    }
    
    private void showTitle() {
        showText("Breakthrough", this.getWidth()/12, this.getHeight()/12*1);
    }
    
    public void updateScore(int amount) {
        score = score + amount;
        showScore();
    }
}
