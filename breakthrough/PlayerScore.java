import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Saves the user scores..
 *
 * @author Gabriel Franz
 * @author Cornel Forster
 */
public class PlayerScore {
    private static String name1;
    private static String name2;
    private static String name3;
    private static String newname;
    
    private static int score1 = 0;
    private static int score2 = 0;
    private static int score3 = 0;
    private static int newscore;

    /**
     * Constructor to initialize the score.
     */
    public PlayerScore(String newname, int newscore) {
        if (newscore >= score1 || score1 == 0) {
            name3 = name2;
            score3 = score2;
            name2 = name1;
            score2 = score1;
            name1 = newname;
            score1 = newscore;
        } else if (newscore >= score2 || score2 == 0) {
            name3 = name2;
            score3 = score2;
            name2 = newname;
            score2 = newscore;
        } else if (newscore >= score3 || score3 == 0) {
            name3 = newname;
            score3 = newscore;
        }
    }

    /**
     * Get the name of the player.
     *
     * @param playerNumber the number of the player.
     * @return the name of player.
     */
    public static String getNameOfPlayer(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return name1;
            case 2:
                return name2;
            default:
                return name3;
        }
    }

    /**
     * Get the score of the player.
     *
     * @param playerNumber the number of the player.
     * @return the score of player.
     */
    public static int getScoreOfPlayer(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return score1;
            case 2:
                return score2;
            default:
                return score3;
        }
    }
}
