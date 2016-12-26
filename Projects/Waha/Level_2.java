import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level_2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level_2 extends World
{

    /**
     * Constructor for objects of class Level_2.
     * 
     */
    public Level_2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 600, 1);
        setBackground(new GreenfootImage("level_2.png"));
        prepare(); 
    }
    
     private void prepare()
    {
        Platform1 platform0 = new Platform1();
        addObject(platform0,105,529);       
        Platform1 platform1 = new Platform1();
        addObject(platform1,973,338);
        
        Croco croco0 = new Croco();
        addObject(croco0,347,538);       
        Croco croco1 = new Croco();
        addObject(croco1,607,471);
        Croco croco2 = new Croco();
        addObject(croco2,818,403);
        
        Waha waha1 = new Waha();
        addObject(waha1,106,459);

        Gift gift = new Gift();
        addObject(gift,974,293);
    }
}
