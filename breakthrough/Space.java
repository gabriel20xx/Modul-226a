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
    private int score, userLives;
    
    /**
     * Constructor for the Space world.
     */
    public Space(int gameNumber) 
    {
        super(800, 600, 1);
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
        showText("Game "+gameNumber, this.getWidth()/6*5, this.getHeight()/6*1);
        userLives = 3;
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
    
    /**
     * The worlds act.
     */
    public void act() {
        checkBricks();
        checkBalls();
        increaseSpeed();
        updateTime();
        showScore();
        showLives();
    }
    
    /**
     * Create random stars.
     * @param number The number of stars.
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
    
    /**
     * Brick places of game one.
     */
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
    
    /**
     * Brick places of game two.
     */
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
    
    /**
     * Brick places of game three.
     */
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
    
    /**
     * Create the borders for the game.
     */
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
     * Create a paddle.
     */
    private void createPaddle()
    {
        Paddle paddle = new Paddle();
        addObject(paddle, this.getWidth()/2, this.getHeight() - this.getHeight()/ 9);
    }
    
    /**
     * Create a ball.
     */
    private void createBall()
    {
        Ball ball = new Ball(340,ballSpeed);
        addObject(ball,this.getWidth()/2, this.getHeight()/2);
    }
    
    /**
     * Check if there are bricks left to hit.
     */
    private void checkBricks() {
        List<Brick> bricks = getObjects(Brick.class);
        if (bricks.isEmpty()) {
            Greenfoot.delay(25);
            Greenfoot.setWorld(new End(score, userLives, "You lost"));
        }
    }
    
    /**
     * Check if the ball still exists.
     */
    private void checkBalls() {
        List<Ball> balls = getObjects(Ball.class);
        if (balls.isEmpty()) {
            updateUserLive(-1);
            updateScore(-500);
            if(userLives == 0) {
                Greenfoot.delay(25);
                Greenfoot.setWorld(new End(score, userLives, "You lost"));
            }
            else {
                createBall();
            }

        }
    }
    
    /**
     * Increse the movement speed of the ball.
     */
    private void increaseSpeed() {
        if (timePassed % 60 == 0) {
            List<Ball> balls = getObjects(Ball.class);
            ballSpeed+=0.01;
            for (Ball ball : balls) {
                ball.speed = ballSpeed;
            }
        }
    }
    
    /**
     * Update the time of the game.
     */
    private void updateTime() {
        timePassed++;
        updateScore(1); // Why increase score with one on every act? Shouldn't it only increase by touching Brick and decrease on live lost?
        showText("Time: " + timePassed / 50, 100, 50);
    }
    
    /**
     * Display the scoreboard.
     */
    private void showScore() {
        showText("Score: " + score, 100, 80);
    }
    
    /**
     * Display the amount of lives the user has left.
     */
    private void showLives() {
        showText("Lives: " + userLives, 100, 110);
    }

    /**
     * Update the score of the game.
     * @param amount The amount which should be added.
     */
    public void updateScore(int amount) {
        score = score + amount;
        showScore();
    }
    
    /**
     * Update the amount of the uses's live
     * @param amount The amount which should be added.
     */
    public void updateUserLive(int amount) {
        userLives = userLives + amount;
        showLives();
    }
}
