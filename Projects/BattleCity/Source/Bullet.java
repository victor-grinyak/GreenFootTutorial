import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;

/**
 * Write a description of class Bullet here.
 * 
 * @author Fedor Kuzin 
 * @version 1
 */
public class Bullet extends Actor
{    
    public static final int SPEED_LVL_1 = 3;
    public static final int SPEED_LVL_2 = 5;
    
    private int _speed;
    
    public Bullet(Direction direction, int speed)
    {
        _speed = speed;
        
        setRotation(direction.getAngle());
        Animation.scaleSprite(getImage(), BattleCity.SCALE);
    }
    
    public void act() 
    {
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        else{
            move(_speed);
        }
        
    }    
}
