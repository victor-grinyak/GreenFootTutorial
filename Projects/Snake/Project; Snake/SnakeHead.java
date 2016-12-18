import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class SnakeHead  extends Actor
{
    private int bodyPos;
    private Point snakeCoords;
    private int temp = 0;

    /**
     * SnakeHead Constructor
     * Задает image для snakeHead
     */
    public SnakeHead(int bodyPosition )
    {
        GreenfootImage image = new GreenfootImage(10, 10);
        image.setColor(Color.GREEN);
        image.fillRect(0, 0, 10, 10);
        setImage(image);
    }

    /**
     * Act вызывается при нажатии кнопки Act или Run
     */
    public void act() 
    {
        moveHead();
        lookForFood();
        hitBody();
    }   

    /**
     * moveHead
     * сдвигает Head на следующую позицию на экране
     */
    public void moveHead()
    {
        SnakeWorld world = (SnakeWorld)getWorld();
        snakeCoords = world.getBodyPosition(0);
        setLocation(snakeCoords.getX(), snakeCoords.getY());

    }

    /**
     * growTail
     * Добавляет сегмент хвоста и новое яблоко на карту
     */
    public void growTail()
    {
        SnakeWorld world = (SnakeWorld)getWorld();
        world.addTail();
        world.placeFood(1);
    }

    /**
     * lookForFood
     *Если змейка находит яблоко - она съедает его
     */
    public void lookForFood()
    {
        if (canSee(Food.class))
        {
            eat(Food.class); 
            growTail();
        }
    }

    /**
     * hitBody - проверяет не врезалась ли змейка сама в себя
     */
    public void hitBody()
    {
        SnakeWorld world = (SnakeWorld)getWorld();
        if(canSee(SnakeBody.class))
        {
            world.die();
        }
    }

    /**
     * canSee
     * Проверяет не пересекаются ли другие объекты с телом змейки
     */
    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        return actor != null;        
    }

    /**
     * eat
     * съесть объект и удалить его с экрана
     */
    public void eat(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        if(actor != null)
        {
            getWorld().removeObject(actor);
        }
    }
}