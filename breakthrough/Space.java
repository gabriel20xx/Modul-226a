import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

/**
 * The space. Here happens the main game.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class Space extends Stars
{
    private int delayTimer = 100;
    private int timePassed = 0;
    private double ballSpeed = 4;
    private int gameNumber, score, userLives;
    private int lastGame = 10;
    private int soundCount = 30*50;
    
    /**
     * Constructor to initialize the world.
     */
    public Space(int gameNumber, int score, int userLives) 
    {
        initializeGame();
        this.gameNumber = gameNumber;
        this.score = score;
        this.userLives = userLives;
        levelCreator(gameNumber);
        showText("Game " + gameNumber, this.getWidth()/6*5, this.getHeight()/6*1);
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
     * Act method which runs in endless loop.
     */
    public void act() {
        checkBricks();
        checkBalls();
        increaseSpeed();
        updateTime();
        showTitle();
        showScore();
        showLevel(gameNumber);
        showLives();
        playSound();
        if (delayTimer > 0) {
            initial();
            delayTimer--;
        }
    }
    
    /**
     * Runs on the first few seconds of the game
     */
    private void initial() {
        Class<Paddle> actorClass = Paddle.class;
        Paddle foundPaddle = null;
        
        for (Actor object : getObjects(Paddle.class)) {
            if (object.getClass() == actorClass) {
                foundPaddle = (Paddle) object;
                break;
            }
        }
        
        if (foundPaddle != null) {
            int foundPaddleX = foundPaddle.getX();
            int foundPaddleY = foundPaddle.getY();
            for (Object obj : getObjects(null)) {
                // Check if the object is an instance of the Ball class
                if (obj instanceof Ball) {
                    // Cast the object to Ball to access its methods and properties
                    Ball ball = (Ball) obj;
                    ball.setLocation(foundPaddleX + foundPaddle.getImage().getWidth()/4, foundPaddleY - foundPaddle.getImage().getHeight());
                }
            }
        }
    }
    
    /**
     * Creates all the bricks in the level
     */
    private void levelCreator(int level) {
        final int rows = 16;
        final int columns = 10;
        final int startWidth = 50;
        final int startHeight = 120;
        final int brickWidth = 80;
        final int brickHeight = 24;
        
        Levels levels = new Levels();
        int[][] levelDef = levels.levelDefinition(level);
        for (int i = 0; i < levelDef.length; i++) {
            int x = levelDef[i][0]-1;
            int y = levelDef[i][1]-1;
            int color = levelDef[i][2];

            int brickX = x * brickWidth + startWidth + brickWidth / 2;
            int brickY = y * brickHeight + startHeight + brickHeight / 2;

            Brick brick = new Brick(color);
            addObject(brick, brickX, brickY);    
        }
    }

    /**
     * Create the borders for the game.
     */
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
     * Create a paddle.
     */
    private void createPaddle()
    {
        Paddle paddle1 = new Paddle(1);
        addObject(paddle1, 450, this.getHeight() - this.getHeight()/ 12);
        
        int mode = Mode.mode;
        if (mode == 2) {
            Paddle paddle2 = new Paddle(2);
            addObject(paddle2, 450, this.getHeight() - this.getHeight()/ 24);
        }
    }
    
    /**
     * Create a ball.
     */
    private void createBall()
    {
        // 315 = Direction in Degrees, ballSpeed = Speed
        Ball ball = new Ball(315,ballSpeed);
        addObject(ball, 450, this.getHeight()/4*3);
    }
    
    /**
     * Check if there are bricks left to hit.
     */
    private void checkBricks() {
        List<Brick> bricks = getObjects(Brick.class);
        // Check if only the not destroyable bricks are left.
        boolean allBricksAreColor10 = bricks.stream().allMatch(brick -> brick.getColor() == 10);
        if (bricks.isEmpty() || allBricksAreColor10) {
            if (gameNumber <= lastGame) {
                Greenfoot.delay(25);
                Greenfoot.setWorld(new Space(gameNumber+1, score, userLives));
            } else {
                Greenfoot.setWorld(new End(score, userLives, "You Won"));
            }
        }
    }
    
    /**
     * Check if the ball still exists.
     */
    private void checkBalls() {
        List<Ball> balls = getObjects(Ball.class);
        if (balls.isEmpty()) {
            updateScore(-500);
            updateUserLive(-1);
            ballSpeed = 4;
            Greenfoot.playSound("Death.mp3");
            Greenfoot.delay(100);
            if(userLives == 0) {
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
            ballSpeed+=0.1;
            for (Ball ball : balls) {
                ball.speed = ballSpeed;
            }
        }
    }
    
    /**
     * Display the scoreboard.
     */
    private void showScore() {
        showText("Score: " + score, this.getWidth()/6*5, this.getHeight()/12*4);
    }
    
    /**
     * Display the level.
     */
    private void showLevel(int gameNumber) {
        showText("Level "+ gameNumber, this.getWidth()/6*5, this.getHeight()/12*2);
    }
    
    /**
     * Display the title.
     */
    private void showTitle() {
        showText("Breakthrough", this.getWidth()/12, this.getHeight()/12*1);
    }
    
    /**
     * Display the amount of lives the user has left.
     */
    private void showLives() {
        showText("Lives: " + userLives, this.getWidth()/6*5, this.getHeight()/12*5);
    }
    
    /**
     * Update the time of the game.
     */
    private void updateTime() {
        timePassed++;
        showText("Time: " + timePassed / 50, this.getWidth()/6*5, this.getHeight()/12*3);
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
     * Update the amount of the user's live.
     * @param amount The amount which should be added.
     */
    public void updateUserLive(int amount) {
        userLives = userLives + amount;
        showLives();
    }
    
    /**
     * Play ingame sound.
     */
    private void playSound() {
        if (soundCount >= 40*50) {
            Greenfoot.playSound("Ingame.mp3");
            soundCount = 0;
        }
        soundCount++;
    }   
}
