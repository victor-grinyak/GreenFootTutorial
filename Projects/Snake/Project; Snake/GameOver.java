import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class GameOver extends Actor
{
    Font font = new Font("Dialog", Font.BOLD, 40);
    
    public GameOver()
    {
        GreenfootImage image = new GreenfootImage(250, 100);
        image.setFont(font);
        image.drawString("Game Over", ShiftCoord(1, 2), ShiftCoord(50, 2));
        setImage(image);
    }

    /**
     * ShiftCoord - сдвигает координаты вниз на дистанцию distance
     */
    public int ShiftCoord(int p, int distance) {
        return (p + distance);
    }
}
