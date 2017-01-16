import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sits there and does absolutely nothing. Causes a player to win if their rocket hits it.
 * 
 * @author Michael Berry
 * @version 22.02.2008
 */
public class Goal extends Actor
{
    private static GreenfootImage image = new GreenfootImage("images/house-05.png");
    
    public void addedToWorld(World world)
    {
        setImage(image);
    }
}
