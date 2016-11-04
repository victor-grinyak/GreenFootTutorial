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
    private int _skip = 0;
    private int _skipCounter = 0;
    private boolean _pause = false;
    private GreenfootImage[] _Frames = null;

    public Animation(String[] frames_imgs, int scale, int skipTicks)
    {
        _scale = scale;
        if(skipTicks > 0) _skip = skipTicks;
        setAnimationArray(frames_imgs);
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
    
    public GreenfootImage getFrame(){
        if(_Frames == null) return null;
        return _Frames[_current_frame];
    }
    
    public void nextFrame()
    {
        if(_pause || _Frames == null) return;
        
        if(_skipCounter < _skip){
            ++_skipCounter;
            return;
        }else{
            _skipCounter = 0;
        }
        
        if(++_current_frame >= _Frames.length){
            _current_frame = 0;
        }
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
