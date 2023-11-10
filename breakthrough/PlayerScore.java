import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public PlayerScore(String newname, int newscore) {
        if (newscore >= score1) {
            name3 = name2;
            score3 = score2;
            name2 = name1;
            score2 = score1;
            name1 = newname;
            score1 = newscore;
        } else if (newscore >= score2) {
            name3 = name2;
            score3 = score2;
            name2 = newname;
            score2 = newscore;
        } else if (newscore >= score3) {
            name3 = newname;
            score3 = newscore;
        }
    }
    
    public static String getName1() {
        return name1;
    }

    public static String getName2() {
        return name2;
    }

    public static String getName3() {
        return name3;
    }

    public static int getScore1() {
        return score1;
    }

    public static int getScore2() {
        return score2;
    }

    public static int getScore3() {
        return score3;
    }
}
