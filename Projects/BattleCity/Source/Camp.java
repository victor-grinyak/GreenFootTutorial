import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Camp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Camp extends Actor
{
    /**
     * Act - do whatever the Camp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private World _BattleCityWorld;
    private Wall[][] _Wall = new Wall[2][8];
    private boolean inited = false;
    private int x = 16 + 352;
    private int y = 16 + 736;
    
    public Camp()
    {
        Animation.scaleSprite(getImage(), BattleCity.SCALE);
    }
    
    public void act()
    {
        if(inited) return;
        
        _BattleCityWorld = getWorld();
        GreenfootImage background = _BattleCityWorld.getBackground();
        background.setColor(Color.WHITE);
        int size = background.getHeight();
       
        //background.drawLine(352, 832, 352, 736);
        //background.drawLine(480, 832, 480, 736);
        //background.drawLine(352, 736, 480, 736);
       
        drawBrick();
        //drawStone();
        
        inited = true;
    }
   
    public void drawBrick()
    {
        for (int k = 0; k < 8; k++)
            {
                if ( k < 3 )
                {
                    _Wall[0][k] = new Brick();
                    _BattleCityWorld.addObject(_Wall[0][k], x, y + k*32);
                }
                else
                {  
                    if ( k < 6)
                    {
                        _Wall[0][k] = new Brick();
                        _BattleCityWorld.addObject(_Wall[0][k], x + (k-2)*32, y);
                    }
                    else
                    {
                        _Wall[0][k] = new Brick();
                        _BattleCityWorld.addObject(_Wall[0][k], x + 3*32, y + (k - 5)*32);
                    }
                }
            }
    }
   
    public void drawStone()
    {
        for (int k = 0; k < 8; k++)
            {
                if ( k < 3 )
                {
                    _Wall[1][k] = new Stone();
                    _BattleCityWorld.addObject(_Wall[1][k], x, y + k*32);
                }
                else
                {  
                    if ( k < 6)
                    {
                        _Wall[1][k] = new Stone();
                        _BattleCityWorld.addObject(_Wall[1][k], x + (k-2)*32, y);
                    }
                    else
                    {
                        _Wall[1][k] = new Stone();
                        _BattleCityWorld.addObject(_Wall[1][k], x + 3*32, y + (k - 5)*32);
                    }
                }
            }
    }
    
    public void deleteStone()
    {
        for (int k = 0; k < 8; k++)
            {
                _BattleCityWorld.removeObject(_Wall[1][k]);
            }
    }
    
    public void hit()
    {
        getWorld().removeObject(this);
    }    
}