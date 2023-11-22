/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Levels
     */
    public Levels()
    {
        
    }
    
    /**
     * Level Predefinition. (Move to separate class)
     */
    public int[][] levelDefinition(int gameNumber) {
        /*
        The first value is the column number (between 1 and 10).
        The second value is the row number (between 1 and 16).
        The third value is the brick color (between 1 and 10) (1=White, 2=Green, 3=Yellow, 4=LightBlue, 5=Red, 6=Pink, 7=Orange, 8=DarkBlue, 9=Silver, 10=Gold).
         */
        int[][] level1 = {
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
            {10, 11, 1}
        };
        
        int[][] level2 = {
            {6, 2, 1},
            {6, 3, 2},
            {7, 3, 1},
            {3, 4, 1},
            {6, 4, 8},
            {7, 4, 2},
            {8, 4, 1},
            {2, 5, 1},
            {3, 5, 2},
            {4, 5, 1},
            {6, 5, 4},
            {7, 5, 8},
            {8, 5, 2},
            {9, 5, 1},
            {1, 6, 1},
            {2, 6, 2},
            {3, 6, 8},
            {4, 6, 2},
            {5, 6, 9},
            {6, 6, 9},
            {7, 6, 4},
            {8, 6, 8},
            {9, 6, 2},
            {10, 6, 1},
            {1, 7, 1},
            {10, 7, 1},
            {1, 8, 1},
            {2, 8, 5},
            {3, 8, 7},
            {4, 8, 3},
            {5, 8, 9},
            {6, 8, 9},
            {7, 8, 5},
            {8, 8, 7},
            {9, 8, 5},
            {10, 8, 1},
            {2, 9, 1},
            {3, 9, 5},
            {4, 9, 7},
            {5, 9, 3},
            {7, 9, 1},
            {8, 9, 5},
            {9, 9, 1},
            {3, 10, 1},
            {4, 10, 5},
            {5, 10, 7},
            {8, 10, 1},
            {4, 11, 1},
            {5, 11, 5},
            {5, 12, 1},
        };
        
        int[][] level3 = {
            {5, 3, 10},
            {6, 3, 10},
            {1, 4, 8},
            {2, 4, 8},
            {9, 4, 8},
            {10, 4, 8},
            {1, 5, 8},
            {2, 5, 8},
            {9, 5, 8},
            {10, 5, 8},
            {1, 6, 8},
            {2, 6, 8},
            {5, 6, 8},
            {6, 6, 8},
            {9, 6, 8},
            {10, 6, 8},
            {1, 7, 6},
            {2, 7, 6},
            {5, 7, 6},
            {6, 7, 6},
            {9, 7, 6},
            {10, 7, 6},
            {5, 8, 6},
            {6, 8, 6},
            {5, 11, 1},
            {6, 11, 1},
            {5, 12, 1},
            {6, 12, 1},
            {2, 13, 3},
            {5, 13, 3},
            {6, 13, 3},
            {9, 13, 3},
            {2, 14, 3},
            {9, 14, 3},
            {2, 15, 3},
            {9, 15, 3},
            {2, 16, 10},
            {9, 16, 10},
        };
        int[][] level4 = {
            {1, 3, 1},
            {2, 3, 1},
            {4, 3, 1},
            {7, 3, 1},
            {9, 3, 1},
            {10, 3, 1},
            {2, 4, 4},
            {4, 4, 1},
            {7, 4, 1},
            {9, 4, 1},
            {2, 5, 2},
            {4, 5, 4},
            {7, 5, 1},
            {9, 5, 1},
            {2, 6, 3},
            {4, 6, 2},
            {7, 6, 1},
            {9, 6, 1},
            {2, 7, 7},
            {4, 7, 3},
            {7, 7, 4},
            {9, 7, 1},
            {2, 8, 5},
            {4, 8, 7},
            {7, 8, 2},
            {9, 8, 4},
            {2, 9, 6},
            {4, 9, 5},
            {7, 9, 3},
            {9, 9, 2},
            {2, 10, 8},
            {4, 10, 6},
            {5, 10, 1},
            {6, 10, 1},
            {7, 10, 7},
            {9, 10, 3},
            {2, 11, 4},
            {4, 11, 8},
            {7, 11, 5},
            {9, 11, 7},
            {2, 12, 1},
            {4, 12, 4},
            {7, 12, 6},
            {9, 12, 5},
            {2, 13, 1},
            {4, 13, 1},
            {7, 13, 8},
            {9, 13, 6},
            {2, 14, 1},
            {4, 14, 1},
            {7, 14, 4},
            {9, 14, 8},
            {2, 15, 1},
            {4, 15, 1},
            {7, 15, 1},
            {9, 15, 4},
            {2, 16, 1},
            {4, 16, 1},
            {7, 16, 1},
            {9, 16, 1},
        };
        
        int[][] level5 = {};
        int[][] level6 = {};
        int[][] level7 = {};
        int[][] level8 = {};
        int[][] level9 = {};
        int[][] level10 = {};
        int[][] level11 = {};
        int[][] level12 = {};
        int[][] level13 = {};
        int[][] level14 = {};
        int[][] level15 = {};
        
        switch (gameNumber) {
            case 1:
                return level1;
            case 2:
                return level2;
            case 3:
                return level3;
            case 4:
                return level4;
            case 5:
                return level5;
            case 6:
                return level6;
            case 7:
                return level7;
            case 8:
                return level8;
            case 9:
                return level9;
            case 10:
                return level10;
            case 11:
                return level11;
            case 12:
                return level12;
            case 13:
                return level13;
            case 14:
                return level14;
            case 15:
                return level15;
            default:
                return level1;
        }
    }
}
