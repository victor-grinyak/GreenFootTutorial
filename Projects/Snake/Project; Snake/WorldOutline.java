import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class WorldOutline extends Actor
{
    /**
     * WorldOutline - создает игровое поле
     */
    public WorldOutline()
    {
        GreenfootImage image = new GreenfootImage(610, 410);
        image.setColor(Color.BLACK);
        int x = 610, y = 410;
        for(int i = 0; i < 10; i++)
        {
            image.drawRect(i, i, x, y); 
            x-= 2;
            y-=2;
        }
        setImage(image);
    }
}
