import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Kugel, die Asteroide zerstören kann.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 2.0
 */
public class Bullet extends Mover
{
    /** Eine Kugel verliert bei jedem Drücken des Act-Buttons ein Leben und verschwindet, wenn life = 0 */
    private int life = 30;
    
    /** Der Schaden, den diese Kugel anrichtet */
    private int damage = 16;
    
    public Bullet()
    {
    }
    
    public Bullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        increaseSpeed(new Vector(rotation, 15));
        Greenfoot.playSound("EnergyGun.wav");
    }
    
    /**
     * Die Kugel zerlegt bzw. zerstört Asteroide, wenn sie diese trifft.
     */
    public void act()
    {
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            move();
            Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
            if (asteroid != null) {
                getWorld().removeObject(this);
                asteroid.hit(damage);
            }
            else {
                life--;
            }
        }
    }
}
