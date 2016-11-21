import greenfoot.*;

public class Block extends MovementController
{
    public void act() 
    {
                checkBumpWith(Player.class);
                stopIfCollideWith(Block.class);
                stopIfCollideWith(Wall.class);
    }
}