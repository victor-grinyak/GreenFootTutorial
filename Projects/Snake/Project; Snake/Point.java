/**
 * Write a description of class Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point 
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    /**
     * Constructor for objects of class Point
     */
    public Point(int xCoord, int yCoord)
    {
        x = xCoord;
        y = yCoord;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setX(int xCoord)
    {
        x=xCoord;
    }
    
    public void setY(int yCoord)
    {
        y=yCoord;
    }
}
