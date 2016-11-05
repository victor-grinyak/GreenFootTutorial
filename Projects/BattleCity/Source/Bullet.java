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
    private Tank.TankType _ownerType;
    private Tank _owner;
    
    public Bullet(Direction direction, int speed, Tank.TankType ownerType, Tank owner)
    {
        _speed = speed;
        _ownerType = ownerType;
        _owner = owner;
        
        _owner.setBulletFired(true);
        
        setRotation(direction.getAngle());
        Animation.scaleSprite(getImage(), BattleCity.SCALE);
    }
    
    public void act() 
    {
        if(isAtEdge()){
            //getWorld().removeObject(this);
            destroy();
            return;
        }
        else{
            move(_speed);
            try{
                hitWalls();
                hitTank();
                hitBullet();
            }catch(Exception e){
                //Что-то пошло не так
            }
        }
        
    } 
    
    public void hitWalls()
    {
        List<Wall> walls = (List<Wall>)getIntersectingObjects(Wall.class);
        Iterator it = walls.iterator();
        
        if(it.hasNext()){
            while(it.hasNext())
                {
                    Wall wall = (Wall)it.next();
                    wall.hit();
                }
                
            destroy();
        }
    }
    
    public void hitTank()
    {
        Tank tank = (Tank)getOneIntersectingObject(Tank.class);
        if(tank != null){
            if(tank.getType() != _ownerType && !tank.isPlayer()){
                tank.hit();
            }
            destroy();
        }
    }
    
    public void hitBullet()
    {
        Bullet bullet = (Bullet)getOneIntersectingObject(Bullet.class);
        if(bullet != null){
            if(bullet._ownerType != _ownerType){
                World wrld = getWorld();
            
                wrld.removeObject(bullet);
                wrld.removeObject(this);
            }
        }
    }
    
    private void makeExplosion()
    {
        int frameDelay = 3;
        String[] frames = {"explosion_bullet_anim1.png", "explosion_bullet_anim2.png", "explosion_bullet_anim3.png"};
        
        Explosion expl = new Explosion(frames, frameDelay);
        
        getWorld().addObject(expl, getX(), getY());
    }
    
    private void destroy()
    {
        makeExplosion();
        getWorld().removeObject(this);
        
        try{
            _owner.setBulletFired(false);
        }catch(Exception ex){
            //no obj
        }
        
    }
}
