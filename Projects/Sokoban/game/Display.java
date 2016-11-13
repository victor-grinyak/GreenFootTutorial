import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Display extends Actor
{

    public Display()
    {
        int imageWidth=100;
        int imageHeight=120;
        
        setImage(new GreenfootImage(imageWidth,imageHeight));
        
    }

    public void update(int x, int y)
    {
        GreenfootImage image = getImage();
        image.clear();
        image.setColor(Color.RED);
        image.drawString("X[ "+ x+" ]: Y[ "+ y +" ]", 4,20);
       
    }
    

    
}

