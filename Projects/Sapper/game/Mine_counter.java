import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mine_counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mine_counter extends Actor
{
    /**
     * Act - do whatever the Mine_counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int mine_counter;
    
    public Mine_counter(int mine_counter) {
        this.mine_counter = mine_counter;
        
        GreenfootImage image = new GreenfootImage("Мин размещено: " + this.mine_counter, 40, null, null);
        setImage(image);
    }
    
    public void act() 
    {
      
    }    
}
