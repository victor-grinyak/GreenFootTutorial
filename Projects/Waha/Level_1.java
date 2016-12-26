import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Level_1 extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public Level_1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1100, 600, 1);
        setBackground(new GreenfootImage("level_1.png"));
        prepare();
    }
    
     private void prepare()
    {
        Platform platform0 = new Platform();
        addObject(platform0,124,535);       
        Platform platform1 = new Platform();
        addObject(platform1,270,492);
        Platform platform2 = new Platform();
        addObject(platform2,433,479);
        Platform platform3 = new Platform();
        addObject(platform3,599,522);
        Platform platform4 = new Platform();
        addObject(platform4,771,524);
        Platform platform5 = new Platform();
        addObject(platform5,937,504);
        Platform platform6 = new Platform();
        addObject(platform6,1051,422);
        Platform platform7 = new Platform();
        addObject(platform7,900,358);
        Platform platform8 = new Platform();
        addObject(platform8,733,344);
        Platform platform9 = new Platform();
        addObject(platform9,551,323);
        Platform platform10 = new Platform();
        addObject(platform10,382,290);
        Platform platform11 = new Platform();
        addObject(platform11,202,276);
        Platform platform12 = new Platform();
        addObject(platform12,61,205);
        Platform platform13 = new Platform();
        addObject(platform13,224,127);
        Platform platform14 = new Platform();
        addObject(platform14,435,134);
        Platform platform15 = new Platform();
        addObject(platform15,641,148);
        Platform platform16 = new Platform();
        addObject(platform16,834,161);
        Platform platform17 = new Platform();
        addObject(platform17,1023,164);        
              
        Cookie cookie1 = new Cookie();
        addObject(cookie1,276,442);
        Cookie cookie2 = new Cookie();
        addObject(cookie2,439,429);
        Cookie cookie3 = new Cookie();
        addObject(cookie3,605,472);
        Cookie cookie4 = new Cookie();
        addObject(cookie4,777,474);
        Cookie cookie5 = new Cookie();
        addObject(cookie5,943,454);
        Cookie cookie6 = new Cookie();
        addObject(cookie6,1057,372);
        Cookie cookie7 = new Cookie();
        addObject(cookie7,906,308);
        Cookie cookie8 = new Cookie();
        addObject(cookie8,739,294);
        Cookie cookie9 = new Cookie();
        addObject(cookie9,557,273);
        Cookie cookie10 = new Cookie();
        addObject(cookie10,388,240);
        Cookie cookie11 = new Cookie();
        addObject(cookie11,208,226);
        Cookie cookie12 = new Cookie();
        addObject(cookie12,67,155);
        Cookie cookie13 = new Cookie();
        addObject(cookie13,230,77);
        Cookie cookie14 = new Cookie();
        addObject(cookie14,441,84);
        Cookie cookie15 = new Cookie();
        addObject(cookie15,647,98);
        Cookie cookie16 = new Cookie();
        addObject(cookie16,840,111);
        
        
        Waha waha = new Waha();
        addObject(waha,135,478);

        YouWin youwin = new YouWin();
        addObject(youwin,1028,93);
    }
}
