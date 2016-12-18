import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

public class Score extends Actor
{
    Font font = new Font("Dialog", Font.BOLD, 20);
    GreenfootImage image = new GreenfootImage(100, 20);
    private int score = 0;

    /**
     * Score - создает объект "счет"
     */
    public Score()
    {
        image.setFont(font);
        setText();
        setImage(image);
    }

    /**
     * setText - задает текст для объекта Score
     */
    private void setText()
    {
        image.clear();
        image.drawString("Score: " + score, ShiftCoord(1, 2), ShiftCoord(15, 2));
    }

    /**
     * updateScore - увеличивает счет и запускает setText
     */
    public void updateScore()
    {
        score++;
        setText();
        setImage(image);
    }

    /**
     * ShiftCoord - сдвигает координаты вниз на дистанцию distance
     */
    public int ShiftCoord(int p, int distance) {
        return (p + distance);
    }
}
