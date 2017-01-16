import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A thing that can move around with a certain speed.
 * You do not need to modify this class
 * 
 * @author Poul Henriksen
 */
public abstract class MovingThing extends Actor
{
    private Vector speed = new Vector();
    
    private double x = 0;
    private double y = 0;
    
    public MovingThing()
    {
    }
    
    /**
     * Sets speed to 0
     */
    public void reset()
    {
        x = 0;
        y = 0;
        speed = new Vector();
    }
    
    /**
     * Create new thing initialised with given speed.
     */
    public MovingThing(Vector speed)
    {
        this.speed = speed;
    }
    
    /**
     * Move forward one step.
     */
    public void move() {
        x = x + speed.getX();
        y = y + speed.getY();
        if(x >= getWorld().getWidth()) {
            x = getWorld().getWidth() - 1;
        }
        if(x < 0) {
            x = 0;
        }
        if(y >= getWorld().getHeight()) {
            y = getWorld().getHeight() - 1;
        }
        if(y < 0) {
            y = 0;
        }
        setLocation(x, y);
    }
    
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
        super.setLocation((int) x, (int) y);
    }
    
    public void setLocation(int x, int y) {
        setLocation((double) x, (double) y);
    }

    /**
     * Increase the speed with the given vector.
     */
    public void increaseSpeed(Vector s) {
        speed.add(s);
    }
    /**
     * Return the current speed.
     */
    public Vector getSpeed() {
        return speed;
    }
}