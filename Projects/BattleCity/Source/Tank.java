import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private int _speed = 1;
    private int _x = 0;
    private int _y = 0;
    private Direction _direction = Direction.RIGHT;
    
    private int _gunReloadTime = RELOAD_TIME_LVL1; // The minimum delay between firing the gun.
    private int _reloadDelayCount = _gunReloadTime; // How long ago we fired the gun the last time.   
    
    private boolean _mirrorV = false;
    private boolean _mirrorH = false;
    
    private static final String[] AnimList = {"player_tank_right_anim1.png", "player_tank_right_anim2.png"};
    private static final Animation _animControl = new Animation(AnimList, BattleCity.SCALE, 7);
    
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
        
        if(isPlayer){
            setDirection(Direction.UP);
        }
        
        updateAnimation();
    }
    
    public void act() 
    {  
        updateAnimation();
        
        if(_isPlayer){
            checkKeys();
        }
        else{
           moveForward();
        }
        
        _reloadDelayCount++;
    }    
    
    public void setReloadTime(int time){
        if(time >= 0){
            _gunReloadTime = time;
        }
    }
    
    private void updateAnimation(){
        _animControl.nextFrame();   
        setImage(_animControl.getFrame());
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        if(Greenfoot.isKeyDown("up")){
            _direction = Direction.UP;
            setRotation(Direction.UP.getAngle());
            
            if(_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        else if(Greenfoot.isKeyDown("down")){
            _direction = Direction.DOWN;
            setRotation(Direction.UP.getAngle());
            
            if(!_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        else if(Greenfoot.isKeyDown("left")) {
            _direction = Direction.LEFT;
            setRotation(Direction.RIGHT.getAngle());
            
            if(!_mirrorH) mirrorHorizontally();
            
            moveForward();
        }        
        else if(Greenfoot.isKeyDown("right")) {
            _direction = Direction.RIGHT;
            setRotation(Direction.RIGHT.getAngle());
            
            if(_mirrorH) mirrorHorizontally();
            
            moveForward();
        }
        
        if(Greenfoot.isKeyDown("space")) {
            makeFire();
        }        
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
         setLocation(getX() + _speed * _direction._x , getY() + _speed * _direction._y);
         
         //move(_speed);
    }
    
    private void makeFire()
    {
        if (_reloadDelayCount >= _gunReloadTime) {
            getWorld().addObject( new Bullet(_direction, Bullet.SPEED_LVL_1), getX(), getY());
            
            _reloadDelayCount = 0;
        }
        
    }
}
