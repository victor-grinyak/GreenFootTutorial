import java.awt.image.BufferedImage;
import greenfoot.GreenfootImage;

/**
 * Write a description of class Animation here.
 * 
 * @author Fedor Kuzin
 * @version 1
 */
public class Animation  
{
    private int _current_frame = 0;
    private int _scale = 1;
    
    private boolean _pause = false;
    private GreenfootImage[] _Frames = null;
    
    private Timer timer = new Timer();

    public Animation(String[] frames_imgs, int scale, long frameTime)
    {
        _scale = scale;
        setAnimationArray(frames_imgs);
        
        timer.setRange(frameTime);
        timer.reset();
    }
    
    public void setAnimationArray(String[] frames_imgs)
    {
        if(frames_imgs.length == 0) return;
        
        _Frames = new GreenfootImage[frames_imgs.length];
        
        for(int i = 0; i < frames_imgs.length; ++i){
            _Frames[i] = new GreenfootImage(frames_imgs[i]);
            scaleSprite(_Frames[i], _scale);
        }
    }
    
    public void setFrame(int frame)
    {
        if(frame >= 0 && frame < _Frames.length){
            _current_frame = frame;
        }
    }
    
    public void setFrameTime(long frameTime)
    {
        timer.setRange(frameTime);
    }
    
    public long getFrameTime()
    {
        return timer.getRange();
    }
    
    public void setPause(boolean pause)
    {
        if(_pause && !pause) timer.reset();
        _pause = pause;
    }
    
    public GreenfootImage getFrame()
    {
        if(_Frames == null) return null;
        return _Frames[_current_frame];
    }
    
    public int getFrameNum()
    {
        return _current_frame;
    }
    
    public boolean nextFrame()
    {
        if(_pause || _Frames == null) return false;
        
        if(timer.inRange()){
            return false;
        }else{
            timer.reset();
        }
        
        if(++_current_frame >= _Frames.length){
            _current_frame = 0;
        }
        
        return true;
    }
    
    public void mirrorVertically()
    {
        for(int i = 0; i < _Frames.length; ++i){
            _Frames[i].mirrorVertically();
        }
    }
    
    public void mirrorHorizontally()
    {
        for(int i = 0; i < _Frames.length; ++i){
            _Frames[i].mirrorHorizontally();
        }
    }
    
    public static void scaleSprite(GreenfootImage gfImg, int scale )
    {
        BufferedImage img = gfImg.getAwtImage();//get raw java image
        
        int height = img.getHeight();
        int width = img.getWidth();
        
        gfImg.scale(width * scale, height * scale );   
    }
}
