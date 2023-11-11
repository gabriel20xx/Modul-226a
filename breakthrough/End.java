import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The end to show the different scores.
 * 
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class End extends Stars
{
    private final int score, lives;
    private final String statusMessage;
    private boolean animationAlreadyShowed;
    private boolean typePlayerShowed;
    
    /**
     * Constructor to initialize the world.
     */
    public End(int score, int lives,String statusMessage) 
    {
        this.score = score;
        this.lives = lives;
        this.statusMessage = statusMessage;
        playSound();
        animationAlreadyShowed = false;
        typePlayerShowed = false;
    }
    
    /**
     * Act method which runs in endless loop.
     */
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
        
        String topPlayer1Name = PlayerScore.getNameOfPlayer(1);
        int topPlayer1Score = PlayerScore.getScoreOfPlayer(1);

        String topPlayer2Name = PlayerScore.getNameOfPlayer(1);
        int topPlayer2Score = PlayerScore.getScoreOfPlayer(2);

        String topPlayer3Name = PlayerScore.getNameOfPlayer(3);
        int topPlayer3Score = PlayerScore.getScoreOfPlayer(3);
        
        showText("1. " + topPlayer1Name + " (Score: " + topPlayer1Score + ")", this.getWidth()/2, this.getHeight()/10*5);
        showText("2. " + topPlayer2Name + " (Score: " + topPlayer2Score + ")", this.getWidth()/2, this.getHeight()/10*6);
        showText("3. " + topPlayer3Name + " (Score: " + topPlayer3Score + ")", this.getWidth()/2, this.getHeight()/10*7);
    }
}
