import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)
import java.util.*;

public class Brick extends Static
{
    public Brick() {
        // 
    }    
    
    public void act()
    { 
        // CheckCollision
        if (isTouching(Ball.class)) 
        {
            getWorld().removeObject(this);
        }
    }
}
