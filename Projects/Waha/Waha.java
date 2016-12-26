import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Waha extends Actor
{
    private int jumpStrength = 10;
    private int speed = 5;
    private int vSpeed = 0;
    private int acceleration = 1;
    private GreenfootImage image1;
    private GreenfootImage image2;
    boolean touchingBadGuy = false;
    int delayAmount = 5;
    private boolean side;

    /**
     * Act - do whatever the waha wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Waha()
    {
        image1 = new GreenfootImage("WahaR.png");
        image2 = new GreenfootImage("WahaL.png");
        setImage(image1);
    }

    public void act() 
    {
        checkFall();
        move();
        eat();
        win();
        end();
    }    

    public void fall()
    {
        setLocation(getX(), getY() +vSpeed);
        vSpeed = (vSpeed + acceleration);

    }

    public boolean onGround()
    {
        int wahaHeight = getImage().getHeight();
        int lookForGround = (int)(wahaHeight/2) - 30;

        Actor ground = getOneObjectAtOffset (0, lookForGround, Platform.class);

        if (ground == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean onGround1()
    {
        int wahaHeight = getImage().getHeight();
        int lookForGround1 = (int)(wahaHeight/2) - 10;

        Actor ground1 = getOneObjectAtOffset (0, lookForGround1, Platform1.class);

        if (ground1 == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean onCroco()
    {
        int wahaHeight = getImage().getHeight();
        int lookForCroco = (int)(wahaHeight/2) - 10;

        Actor croco = getOneObjectAtOffset (0, lookForCroco, Croco.class);

        if (croco == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void checkFall()
    {
        if(onGround() || onGround1() || onCroco())
        {
            vSpeed = 0;
        }
        else
        {
            fall();
        }
    }

    public void setVertSpeed(int speed)
    {
        vSpeed = - speed;
    }

    public void jump()
    {
        setVertSpeed(jumpStrength);
        fall();
    }

    public void moveRight()
    {
        side = true;
        setLocation(getX() + speed, getY());
        setImage(image1);

    }

    public void moveLeft()
    {
        side = false;
        setLocation(getX() - speed, getY());
        setImage(image2);

    }

    public void move()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right"))
        {
            moveRight();
        }
        if (Greenfoot.isKeyDown("up"))
        {
            if (onGround() || onGround1() || onCroco())
            {
                jump();
            }
        }
    }

    public void eat()
    {
        Actor cookie = getOneIntersectingObject(Cookie.class);
        if (cookie != null)
        {
            World myWorld = getWorld();
            Level_1 myworld = (Level_1)myWorld;
            myWorld.removeObject(cookie);
        }
    }

    public void win()
    {
        Actor youwin = getOneObjectAtOffset(0, 0, YouWin.class);
                if (youwin != null)
        {
            Win win = new Win();
            getWorld().addObject(win, getWorld().getWidth()/2, getWorld().getHeight()/2);
            //Greenfoot.stop();
            Greenfoot.setWorld(new Level_2());
        }
    }
    
    public void end()
    {
        Actor gift = getOneObjectAtOffset(0, 0, Gift.class);
                if (gift != null)
        {
            End end = new End();
            getWorld().addObject(end, getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }
       
}
