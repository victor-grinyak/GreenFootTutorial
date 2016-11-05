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
    public enum TankType {
        TANK_PLAYER_1, 
        TANK_PLAYER_2, 
        TANK_ENEMY
    }
    
    public static final int RELOAD_TIME_LVL1 = 80;
    public static final int RELOAD_TIME_LVL2 = 60;
    public static final int RELOAD_TIME_LVL3 = 40;
    
    //private boolean _isPlayer = false;
    private TankType _type = TankType.TANK_ENEMY;
    
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
    
    private static final String[] AnimListYellow = {"player_tank_right_anim1.png", "player_tank_right_anim2.png"};
    private static final String[] AnimListGreen  = {"player2_tank_anim1.png", "player2_tank_anim2.png"};
    private static final String[] AnimListGrey   = {"enemy_tank_anim1.png", "enemy_tank_anim1.png"};
    
    private Animation _animControl;

    public Tank(TankType type, int x, int y)
    {
        _x = x;
        _y = y;
        _type = type;
        
        setDirection(Direction.UP);
        
        switch(_type){
            case TANK_PLAYER_1:
                 _animControl = new Animation(AnimListYellow, BattleCity.SCALE, 3);
            break;
            
            case TANK_PLAYER_2:
                 _animControl = new Animation(AnimListGreen, BattleCity.SCALE, 3);
            break;
            
            case TANK_ENEMY:
                _animControl = new Animation(AnimListGrey, BattleCity.SCALE, 3);
            break;
        }
        
        updateAnimation();
    }
    
    public void act() 
    {  
        String[][] layout = { {"w", "s", "a", "d", "space"}, 
                              {"up", "down", "left", "right", "0"} 
                            };
        
        switch(_type){
            case TANK_PLAYER_1:
                checkKeys(layout[0]);
            break;
            
            case TANK_PLAYER_2:
                checkKeys(layout[1]);
            break;
            
            case TANK_ENEMY:
            break;
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
        destroy();
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
        int dist = (7) * BattleCity.SCALE + 13;
        
        List<Wall> walls = getNeighbours(dist, true, Wall.class);
        Iterator it = walls.iterator();
        
        return !it.hasNext() && !atBorder();
    }
    
    private boolean atBorder(){
        int dist = (7) * BattleCity.SCALE;
        
        int map_w = getWorld().getWidth();
        int map_h = getWorld().getHeight();
        
        int map_x = 0;
        int map_y = 0;
        
        switch(_direction){
            case UP:            
            case DOWN:
                dist *= _direction._y;
                
                int y = getY() + dist;
                
                if(map_y > y || map_y + map_h < y){
                    return true;
                }
            break;
            
            case RIGHT:
            case LEFT:
                dist *= _direction._x;
                
                int x = getX() + dist;
                
                if(map_x > x || map_x + map_w < x){
                    return true;
                }
            break;
        }
        
        return false;
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
    
    private void makeExplosion()
    {
        int frameDelay = 3;
        String[] frames = {"explosion_tank_anim1.png", "explosion_tank_anim2.png"};
        
        Explosion expl = new Explosion(frames, frameDelay);
        
        getWorld().addObject(expl, getX(), getY());
    }
    
    private void destroy()
    {
        makeExplosion();
        getWorld().removeObject(this);
    }
}
