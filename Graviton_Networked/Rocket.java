import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Rocket extends MovingThing
{
    private static GreenfootImage rocket = new GreenfootImage("images/rocket.png");    
    private static GreenfootImage rocketWithThrust = new GreenfootImage("images/rocketWithThrust.png");
 
    /** How fast the rocket is */  
    private Vector acceleration = new Vector(0,0.6);
    /** Gravity */
    private Vector gravity = new Vector(0,0.4);
    /** The minimum delay between firing the gun. */
    private final int minGunFireDelay = 5;    
    /** How long ago we fired the gun the last time. */
    private int gunFireDelay = 0;
    /** The world the rocket is in */
    private GravitonWorld world;
    /** The speed which the rocket rotates at */
    private static final int ROTATESPEED = 4;
    /** Whether the rocket is remote */
    private boolean remote;
    /** The network connection object */
    private Connection connection;
    /** Initial positions */
    private int x, y;
    
    /**
     * Sets up gravity
     */
    public Rocket(boolean remote, int x, int y, Connection connection)
    {
        setImage(rocket);
        gravity.setDirection(90);
        this.remote = remote;
        this.connection = connection;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Called when the rocket is added to the world.
     */
    public void addedToWorld(World world)
    {
        this.world = (GravitonWorld) world;
    }     
    
    /**
     * Do what the rocket wants.
     */
    public void act()
    {
        if(!remote) {
            if((Goal)getOneIntersectingObject(Goal.class) != null) { //If the rocket has made it to the goal
                world.end(true, this);
                connection.sendInts(getX(), getY(), getRotation(), 2, Greenfoot.isKeyDown("up") ? 1 : 0, 0);
            }
            else if(getOneIntersectingObject(Block.class) != null || outsideWorld()) { //If the rocket has crashed
                world.end(false, this);
                connection.sendInts(getX(), getY(), getRotation(), 1, Greenfoot.isKeyDown("up") ? 1 : 0, 0);
            }
            else {
                if(Greenfoot.isKeyDown("up")) {
                    ignite(true);
                }
                else {
                    ignite(false);
                }
                if(Greenfoot.isKeyDown("down")) {
                    fire();
                }
                if(Greenfoot.isKeyDown("left")) {
                    setRotation(getRotation() - ROTATESPEED);
                }
                if(Greenfoot.isKeyDown("right")) {
                    setRotation(getRotation() + ROTATESPEED);
                }        
                move();
                
                connection.sendInts(getX(), getY(), getRotation(), 0, Greenfoot.isKeyDown("up") ? 1 : 0, Greenfoot.isKeyDown("down") ? 1 : 0);
            }
            
        }
        else {
            int[] vals = connection.receiveInts();
            int x = vals[0];
            int y = vals[1];
            int rot = vals[2];
            int stop = vals[3];
            int boost = vals[4];
            int fire = vals[5];
            
            setLocation(x, y);
            setRotation(rot);
            if(boost==1) setImage(rocketWithThrust);
            else setImage(rocket);
            if(stop==1) {
                world.end(false, this);
            }
            if(stop==2) {
                world.end(true, this);
            }
            if(fire==1) {
                fire();
            }
        }
        gunFireDelay++;        
    }
    
    /**
     * Resets the rocket
     */
    public void reset()
    {
        super.reset();
        
        setLocation(x, y);
        setRotation(270);
        acceleration = new Vector(0,0.6);
        gravity = new Vector(0,0.4);
        gravity.setDirection(90);
        setImage(rocket);
        
    }
    
    /**
     * Controls the rockets movement based on whether the rocket is ignited or not.
     * @param ignited True if the rocket is ignited
     */
    protected void ignite(boolean ignited)
    {
        if(ignited) {
            setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            increaseSpeed(acceleration);
        }
        else {
            setImage(rocket);
            increaseSpeed(gravity);
        }
    }
    
    /**
     * Moves the rocket after calculations on its speed and direction are complete.
     */
    public void move()
    {
        super.move();
    }
    
    /**
     * Is the rocket outside the world?
     * @return true if the rocket is outside the world.
     */
    protected boolean outsideWorld()
    {
        return getX()<=5 || getY()<=5 || getX()>=getWorld().getWidth()-5 || getY()>=getWorld().getHeight()-5;
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    protected void fire() {
        if(gunFireDelay >= minGunFireDelay) {
            Bullet b = new Bullet(getSpeed().copy(), getRotation());
            getWorld().addObject(b, getX(), getY());
            b.move();
            gunFireDelay = 0;
        }
    }
}