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
    private int _speed;
    
    public Bullet(Direction direction, int speed)
    {
        _speed = speed;
        setRotation(direction.getAngle());
        Animation.scaleSprite(getImage(), 4);
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
