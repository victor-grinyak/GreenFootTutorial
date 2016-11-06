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
    public enum Type {
        PLAYER_1, 
        PLAYER_2, 
        ENEMY
    }
    
    public static final int RELOAD_TIME_LVL1 = 500;
    //public static final int RELOAD_TIME_LVL2 = 60;
    //public static final int RELOAD_TIME_LVL3 = 40;
    
    private static final String[] AnimListYellow = {"player_tank_right_anim1.png", "player_tank_right_anim2.png"};
    private static final String[] AnimListGreen  = {"player2_tank_anim1.png", "player2_tank_anim2.png"};
    private static final String[] AnimListGrey   = {"enemy_tank_anim1.png", "enemy_tank_anim1.png"};
    
    private Type _type = Type.ENEMY;
    
    private int _speed = 2;
    private int _x = 0;
    private int _y = 0;
    private int _prev_x = 0;
    private int _prev_y = 0;
    
    private Direction _direction = Direction.RIGHT;
    private Timer _reloadTimer = new Timer();
    
    private boolean _mirrorV = false;
    private boolean _mirrorH = false;
    
    private boolean _bulletFired = false;
    private boolean _btnPressedPrev = false;
    
    private final GreenfootSound _fireSound = new GreenfootSound("fire.mp3");
    
    private Animation _animControl;

    public Tank(Type type)
    {
        _type = type;
        
        setDirection(Direction.UP);
        
        _reloadTimer.setRange(RELOAD_TIME_LVL1);
        //_reloadTimer.reset();
        
        final int animDelay = 50;
        
        switch(_type){
            case PLAYER_1:
                 _animControl = new Animation(AnimListYellow, BattleCity.SCALE, animDelay);
            break;
            
            case PLAYER_2:
                 _animControl = new Animation(AnimListGreen, BattleCity.SCALE, animDelay);
            break;
            
            case ENEMY:
                _animControl = new Animation(AnimListGrey, BattleCity.SCALE, animDelay);
            break;
        }
        
        updateAnimation();
    }
    
    public void act() 
    {  
        String[][] layout = { {"w", "s", "a", "d", "space"}, 
                              {"up", "down", "left", "right", "shift"} 
                            };
        
        switch(_type){
            case PLAYER_1:
                checkKeys(layout[0]);
                
            break;
            
            case PLAYER_2:
                checkKeys(layout[1]);
            break;
            
            case ENEMY:
            break;
        }
    }    
    
    public void setReloadTime(int time)
    {
        if(time >= 0){
            _reloadTimer.setRange(time);
        }
    }
    
    public Type getType()
    {
        return _type;
    }
    
    public void setBulletFired(boolean fired)
    {
        _bulletFired = fired;
    }
    
    public boolean isPlayer()
    {
        return _type == Type.PLAYER_1 || _type == Type.PLAYER_2;
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
        boolean btnPressed = false;
        
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
            if(!_btnPressedPrev){
               makeFire();
            }

            btnPressed = true;
        }       
        
        _btnPressedPrev = btnPressed;
    }
    
    private boolean canMove(){
        Wall wall = (Wall)getOneIntersectingObject(Wall.class); 
        Camp camp = (Camp)getOneIntersectingObject(Camp.class); 
        Tank tank = (Tank)getOneIntersectingObject(Tank.class); 

        return wall == null && tank == null && camp == null && !atBorder(); 
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
        if (_reloadTimer.outRange() && !_bulletFired) {
            _reloadTimer.reset();
            
            getWorld().addObject(new Bullet(_direction, Bullet.SPEED_LVL_1, _type, this), getX() + _direction._x * 9*4, getY() + _direction._y * 9*4);
                       
            if(isPlayer()) _fireSound.play();
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
