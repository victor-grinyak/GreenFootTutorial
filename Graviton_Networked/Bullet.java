import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A bullet. This is fired by the rockets.
 * 
 * @author Poul Henriksen
 */
public class Bullet extends MovingThing
{
    /** A bullet loses one life each act, and will disappear when life = 0 */
    private int life = 30;
    /** The world the Bullet has been added to */
    private GravitonWorld world;
    /** The bullet's image */
    private static final GreenfootImage image = new GreenfootImage("images/bullet.png");
    
    /**
     * Creates a new Bullet
     */
    public Bullet(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        increaseSpeed(new Vector(rotation, 15));
    }
    
    /**
     * Called when the bullet is aded to the world
     */
    public void addedToWorld(World world)
    {
        this.world = (GravitonWorld)world;
        setImage(image);
    }
    
    /**
     * The Bullet's act method
     */
    public void act()
    {
        if(life <= 0 || getOneIntersectingObject(Block.class)!=null) {
            world.removeObject(this);
        }
        else {
            move();
            life--;
        }
    }    

}