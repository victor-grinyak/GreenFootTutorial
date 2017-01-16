import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class Score extends Actor
{

    private GreenfootImage score;
    private int scoreNum;    
    
    /**
     * Called when the object is added to the world
     */
    public void addedToWorld(World world)
    {
        scoreNum = 0;
        updateScore(0);
    }
    
    /**
     * Updates the score.
     * @param x The number of points to update the score by. Negative values of x take away points
     */
    public void updateScore(int x)
    {
        scoreNum += x;
        score = new GreenfootImage(60, 20);
        score.setColor(Color.WHITE);
        score.clear();
        score.drawString("Score: " + scoreNum, 1, 12);
        setImage(score);        
    }
    
    /**
     * Returns the numerical value of the score.
     * @return The score.
     */
    public int getScore()
    {
        return scoreNum;
    }
}
