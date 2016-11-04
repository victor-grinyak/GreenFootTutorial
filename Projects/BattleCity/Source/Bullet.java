import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

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
            
            hitWalls();
        }
        
    } 
    
    public void hitWalls(){
        List<Wall> walls = (List<Wall>)getIntersectingObjects(Wall.class);
        Iterator it = walls.iterator();
        
        if(it.hasNext()){
            while(it.hasNext())
                {
                    Wall wall = (Wall)it.next();
                    wall.hit();
                }
                
            //spawn bullet explosion
            getWorld().removeObject(this);
        }
    }
    
    public void hitTank(){
        Tank tank = (Tank)getOneIntersectingObject(Tank.class);
        tank.hit();
    }
}
