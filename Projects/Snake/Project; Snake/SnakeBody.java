import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class SnakeBody  extends Actor
{
    private int x_speed=1;
    private int y_speed=0;
    private int bodyPos;
    private Point snakeCoords;
    
    /**
     * SnakeBody constructor
     */
    public SnakeBody(int bodyPosition)
    {
        GreenfootImage image = new GreenfootImage(10, 10);
        image.setColor(Color.GRAY);
        image.fillRect(0, 0, 10, 10);
        setImage(image);
        bodyPos = bodyPosition;
    }

    /**
     * Act вызывается при нажатии кнопки Act или Run
     */
    public void act() 
    {
        moveBody();
    }    

    /**
     * moveBody
     * Двигает змейку
     */
    public void moveBody()
    {
      SnakeWorld world = (SnakeWorld)getWorld();
      snakeCoords = world.getBodyPosition(bodyPos);
      setLocation(snakeCoords.getX(), snakeCoords.getY());
    }
}
