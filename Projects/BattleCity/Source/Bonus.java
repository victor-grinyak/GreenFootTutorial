import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bonus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bonus extends Actor
{
    public enum BonusType {
        TANK, 
        STAR, 
        BOMB, 
        SHOVEL
    }
    
    /**
     * Act - do whatever the Bonus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Bonus()
    {
        Animation.scaleSprite(getImage(), BattleCity.SCALE);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
