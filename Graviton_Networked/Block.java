import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sits there and does absolutely nothing. Causes a player to die if their rocket hits it, and disappears if hit by a bullet.
 * 
 * @author Michael Berry
 * @version 22.02.2008
 */
public class Block extends Actor
{

    private GravitonWorld world;
    private static GreenfootImage block = new GreenfootImage("images/brick.png");
    
    /**
     * Called when the Block is added to the world
     */
    public void addedToWorld(World world)
    {
        this.world = (GravitonWorld) world;
        setImage(block);
    }
    
    /**
     * If the block is shot, it will disappear.
     */
    public void act()
    {
        if(getOneIntersectingObject(Bullet.class) != null) {
            world.removeObject(this);
        }
    }
}
