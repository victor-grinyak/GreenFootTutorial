import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Iterator;
import java.util.List;

/**
 * Write a description of class Tank here.
 *
 * @author Fedor Kuzin
 * @version 1
 */
public class Tank extends Actor
{   
    public static final int RELOAD_TIME_LVL1 = 80;
    public static final int RELOAD_TIME_LVL2 = 60;
    public static final int RELOAD_TIME_LVL3 = 40;
    
    private boolean _isPlayer = false;
    
    private int _speed = 2;
    private int _x = 0;
    private int _y = 0;
    private int _prev_x = 0;
    private int _prev_y = 0;
    
    private Direction _direction = Direction.RIGHT;
    
    private int _gunReloadTime = RELOAD_TIME_LVL1; // The minimum delay between firing the gun.
    private int _reloadDelayCount = _gunReloadTime; // How long ago we fired the gun the last time.   
    
    private boolean _mirrorV = false;
    private boolean _mirrorH = false;
    
    private static final String[] AnimList = {"player_tank_right_anim1.png", "player_tank_right_anim2.png"};
    private final Animation _animControl = new Animation(AnimList, BattleCity.SCALE, 3);
    
    /*
    public Tank()
    {
        setDirection(Direction.RIGHT);
        
        updateAnimation();
    }
    */
   
    public Tank(boolean isPlayer, int x, int y)
    {
        _isPlayer = isPlayer;
        _x = x;
        _y = y;
        
        setDirection(Direction.UP);
        
        updateAnimation();
    }
    
    public void act() 
    {  
        //updateAnimation();
        //int[][] mapObjects = {
        String[][] layout = { {"w", "s", "a", "d", "space"}, {"up", "down", "left", "right", "0"} };
        
        if(_isPlayer){
            checkKeys(layout[0]);
        }
        else{
            checkKeys(layout[1]);
        }
        
        _reloadDelayCount++;
    }    
    
    public void setReloadTime(int time)
    {
        if(time >= 0){
            _gunReloadTime = time;
        }
    }
    
    public void hit()
    {
        //spawn explosion first
        getWorld().removeObject(this);
    }
    
    private void updateAnimation(){
        _animControl.nextFrame();   
        setImage(_animControl.getFrame());
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys(String[] layout)
    {
        if(Greenfoot.isKeyDown(layout[0])){
            _direction = Direction.UP;
            setRotation(Direction.UP.getAngle());
            
            if(_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        else if(Greenfoot.isKeyDown(layout[1])){
            _direction = Direction.DOWN;
            setRotation(Direction.UP.getAngle());
            
            if(!_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        else if(Greenfoot.isKeyDown(layout[2])) {
            _direction = Direction.LEFT;
            setRotation(Direction.RIGHT.getAngle());
            
            if(!_mirrorH) mirrorHorizontally();
            
            moveForward();
        }        
        else if(Greenfoot.isKeyDown(layout[3])) {
            _direction = Direction.RIGHT;
            setRotation(Direction.RIGHT.getAngle());
            
            if(_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        
        if(Greenfoot.isKeyDown(layout[4])) {
            makeFire();
        }        
    }
    
    private boolean canMove(){
        int dist = (7) * BattleCity.SCALE + 12;
        List<Wall> walls = getNeighbours(dist, true, Wall.class);
        
        
        
        //Wall wall = (Wall) getOneIntersectingObject(Wall.class);
        //return wall == null;
        //List<Wall> walls = getObjectsInRange(radius, Wall.class);
        Iterator it = walls.iterator();
        
        return !it.hasNext();
    }
    
    private void mirrorHorizontally(){
        _animControl.mirrorHorizontally();
        _mirrorH = !_mirrorH;
    }
    
    private void mirrorVertically(){
        _animControl.mirrorVertically();
        _mirrorV = !_mirrorV;
    }
    
    private void setDirection(Direction direction)
    {
        setRotation(direction.getAngle());
        _direction = direction;
    }
    
    private void moveForward()
    {
        _prev_x = getX();
        _prev_y = getY();
            
        setLocation(getX() + _speed * _direction._x , getY() + _speed * _direction._y);
        
        if(!canMove()) {
            setLocation(_prev_x, _prev_y);
        }

        updateAnimation();
         //move(_speed);
    }
    
    private void makeFire()
    {
        if (_reloadDelayCount >= _gunReloadTime) {
            getWorld().addObject(new Bullet(_direction, Bullet.SPEED_LVL_1), getX() + _direction._x * 9*4, getY() + _direction._y * 9*4);
            
            _reloadDelayCount = 0;
        }
        
    }
}
