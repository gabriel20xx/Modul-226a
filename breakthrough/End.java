import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

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
    private boolean typePlayerShowed;
    private List<PlayerScore> playerScores;
    
    public End(int score, int lives,String statusMessage) 
    {
        super(1280, 720, 1);
        this.score = score;
        this.lives = lives;
        this.statusMessage = statusMessage;
        setBackground();
        playSound();
        animationAlreadyShowed = false;
        typePlayerShowed = false;
    }
    
    public void act() {
        if (!animationAlreadyShowed) {
        for(int x = 0; x < statusMessage.length() + 1; x++) {
            showText(statusMessage.substring(0,x), this.getWidth()/2, this.getHeight()/10*2);
            Greenfoot.delay(5);
        }
        showText(String.format("Your Score: %d", score), this.getWidth()/2, this.getHeight()/10*3);

        showText("Press enter to choose a new game", this.getWidth()/2, this.getHeight()/10*8);
        animationAlreadyShowed = !animationAlreadyShowed;
        }
        Greenfoot.delay(20);
        if (animationAlreadyShowed && !typePlayerShowed) {
            showScoreboard();
            typePlayerShowed = true;
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
    
    /**
     * Plays game over sound.
     */
    public void playSound() {
        Greenfoot.playSound("Game-Over.mp3");
    }
    
    /**
     * Scoreboard (Top 3)
     */
    private void showScoreboard() {
        String username = Greenfoot.ask("What is your name?");
        
        PlayerScore playerScore = new PlayerScore(username, score);       
        
        String topPlayer1Name = PlayerScore.getName1();
        int topPlayer1Score = PlayerScore.getScore1();

        String topPlayer2Name = PlayerScore.getName2();
        int topPlayer2Score = PlayerScore.getScore2();

        String topPlayer3Name = PlayerScore.getName3();
        int topPlayer3Score = PlayerScore.getScore3();
        
        showText("1. Name: " + topPlayer1Name + " ; Score: " + topPlayer1Score, this.getWidth()/2, this.getHeight()/10*5);
        showText("2. Name: " + topPlayer2Name + " ; Score: " + topPlayer2Score, this.getWidth()/2, this.getHeight()/10*6);
        showText("3. Name: " + topPlayer3Name + " ; Score: " + topPlayer3Score, this.getWidth()/2, this.getHeight()/10*7);
    }
}
