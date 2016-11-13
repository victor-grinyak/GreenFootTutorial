import greenfoot.*; 

public class Player extends MovementController
{

    public void act(){
        moveWith("right", "left", "up", "down");
        stopIfCollideWith(Wall.class);
        
    }
  
}