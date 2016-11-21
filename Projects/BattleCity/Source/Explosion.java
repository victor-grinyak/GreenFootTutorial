import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private Animation _animcontrol;
    private int _skipFirstFrameDelay = 0;
    private int _frameDelay;
    
    public Explosion(String[] animFrames, int frameDelay){
        _frameDelay = frameDelay;
        _animcontrol = new Animation(animFrames, BattleCity.SCALE, frameDelay);
        setImage(_animcontrol.getFrame());
    }
    
    public void act() 
    {
        if(_animcontrol.nextFrame()){
            setImage(_animcontrol.getFrame());
        }
        
        if(_skipFirstFrameDelay > _frameDelay){
            if(_animcontrol.getFrameNum() == 0){
                getWorld().removeObject(this);
            }
        }else{
            _skipFirstFrameDelay++;
        }
    }    
}
