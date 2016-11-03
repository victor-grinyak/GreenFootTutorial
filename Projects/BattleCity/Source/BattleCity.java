import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class MyWorld here.
 * 
 * @author Fedor Kuzin
 * @version 1
 */
public class BattleCity extends World
{
    public enum BonusType {
        BONUS_TANK, 
        BONUNS_STAR, 
        BONUS_BOMB, 
        BONUS_SHOVEL
    }

    private int _player_spawn_x = 0;
    private int _player_spawn_y = 0;

    public BattleCity()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // 8 pixels per block, 13 blocks, 2x scale, 60 pixels for right panel.
        super(16*13*4, 16*13*4 + 60, 1); 
        
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        prepare();
    }

    public void savePlayerSpawnLocation(int x, int y)
    {
        _player_spawn_x = x;
        _player_spawn_y = y;
    }

    public void spawnPlayerAt(int x, int y)
    {

    }

    public void spawnTank(Tank tank, int x, int y)
    {

    }

    public void spawnBonus(BonusType bonus)
    {

    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Tank tank = new Tank(true, 100, 100);
        addObject(tank,352,514);
    }
}