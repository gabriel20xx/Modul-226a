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
    private int timePassed = 0;
    private double ballSpeed = 4;
    private int gameNumber, score, userLives;
    private int lastGame = 4;
    private int soundCount = 30*50;
    
    // Variables for the level creation.
    private static final int rows = 16;
    private static final int columns = 10;
    private static final int startWidth = 50;
    private static final int startHeight = 120;
    private static final int brickWidth = 80;
    private static final int brickHeight = 24;
    
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
        createPerk();
        increaseSpeed();
        updateTime();
        showTitle();
        showScore();
        showLevel(gameNumber);
        showLives();
        playSound();
    }
    
    /**
     * Create a Perk with a chance from 100 to 1.
     */
    private void createPerk() {
        if (Greenfoot.getRandomNumber(1000) < 1) {
            // Perk will only be created in the upper half.
            addObject(new Perk(), Greenfoot.getRandomNumber(10*80) + 50, Greenfoot.getRandomNumber(8*24) + 120);
        }
    }
    
    /**
     * Predefiniton for level six.
     */
    private int[][] levelSixPreDefinition() {
        /*
        The first value is the column number (between 1 and 10).
        The second value is the row number (between 1 and 16).
        The third value is the brick color (between 1 and 10) (1=White, 2=Green, 3=Yellow, 4=LightBlue, 5=Red, 6=Pink, 7=Orange, 8=DarkBlue, 9=Silver, 10=Gold).
         */
        int[][] level = {
                {1, 2, 1},
                {10, 2, 1},
                {2, 3, 1},
                {9, 3, 1},
                {3, 4, 1},
                {8, 4, 1},
                {4, 3, 1},
                {7, 3, 1},
                {4, 5, 5},
                {7, 5, 5},
                {5, 5, 6},
                {6, 5, 6},
                {2, 6, 2},
                {9, 6, 2},
                {3, 6, 3},
                {8, 6, 3},
                {2, 7, 2},
                {9, 7, 2},
                {5, 7, 7},
                {6, 7, 7},
                {2, 8, 2},
                {9, 8, 2},
                {3, 8, 3},
                {8, 8, 3},
                {4, 9, 5},
                {7, 9, 5},
                {5, 9, 6},
                {6, 9, 6},
                {3, 10, 4},
                {8, 10, 4},
                {5, 10, 4},
                {6, 10, 4},
                {1, 11, 1},
                {2, 11, 1},
                {3, 11, 1},
                {4, 11, 1},
                {7, 11, 1},
                {8, 11, 1},
                {9, 11, 1},
                {10, 11, 1},
        };
        return level;
    }
    
    /**
     * Creates all the bricks in the level
     */
    private void levelCreator(int level) {
        // Game crashed with switch case.
        if (level == 1) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < columns; x++) {
                    Brick brick = new Brick(1);
                    addObject(brick, x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
                }
            }
        }
        else if (level == 2) {
            for (int x = 0; x != 2; x++) {
                for (int y = 0; y < 15; y++) {
                Brick brick = new Brick(0);

                addObject(brick, x * 9 * brickWidth + startWidth + brickWidth / 2, y*brickHeight+startHeight+brickHeight/2);
                }
            }
        }
        else if (level == 3) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    Brick brick = new Brick(Greenfoot.getRandomNumber(9) + 1);
                    addObject(brick, x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
                }
            }
        }
        else if (level == 4) {
            for (int x = 0; x < columns; x += 3) {
                for (int y = 0; y < rows; y++) {
                    addObject(new Brick(Greenfoot.getRandomNumber(9) + 1), x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
                }
            }
        }
        else if (level == 5) {
            // Generating left half.
            for (int x = 1; x < columns/2; x += 3) {
                for (int y = 0; y < rows; y++) {
                    addObject(new Brick(Greenfoot.getRandomNumber(10) + 1), x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
                }
            }

            // Generating right half.
            for (int x = columns/2; x < columns; x += 3) {
                for (int y = 0; y < rows; y++) {
                    addObject(new Brick(Greenfoot.getRandomNumber(10) + 1), x*brickWidth+startWidth+brickWidth/2, y*brickHeight+startHeight+brickHeight/2);
                }
            }
        }
        else if (level == 6) {
            int[][] levelSix = levelSixPreDefinition();

            for (int i = 0; i < levelSix.length; i++) {
                int x = levelSix[i][0]-1;
                int y = levelSix[i][1]-1;
                int color = levelSix[i][2];

                int brickX = x * brickWidth + startWidth + brickWidth / 2;
                int brickY = y * brickHeight + startHeight + brickHeight / 2;

                Brick brick = new Brick(color);
                addObject(brick, brickX, brickY);
            }
        }
        else if (level == 7) {
            int yDifference, colorCode;
            for (int amount=0; amount < 6; amount++) {
                for (int x = 0; x != 10; x++) {
                    if ((amount + x - Greenfoot.getRandomNumber(2) + 1) % 2 == 0) {
                        colorCode = 10;
                    } else {
                        colorCode = Greenfoot.getRandomNumber(9) +1;
                    }
                    Brick brick = new Brick(colorCode);
                    if (amount == 0) {
                        yDifference = 0;
                    }
                    else {
                        yDifference = amount * 50;
                    }
                    addObject(brick, x * brickWidth + startWidth + brickWidth / 2, x*brickHeight+startHeight+brickHeight/2 + yDifference);
                }
            }
        }
        else {
            int counter = 0;
            while (counter < 34) {
                Brick brick = new Brick(Greenfoot.getRandomNumber(10)+1);
                addObject(brick, (Greenfoot.getRandomNumber(columns) * brickWidth) + startWidth + (brickWidth / 2), (Greenfoot.getRandomNumber(rows) * brickHeight) + startHeight + (brickHeight / 2));
                if(brick.isTouchingAnotherBrick() || brick.isTouchingBorder()) {
                    removeObject(brick);
                }
                else {
                    counter++;
                }
            }
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
     * Update the amount of the uses's live
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
        if (soundCount >= 30*50) {
            Greenfoot.playSound("Ingame.mp3");
            soundCount = 0;
        }
        soundCount++;
    }   
}
