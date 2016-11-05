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
    
    public static final int SCALE = 4;
    
    private int _player_spawn_x = 0;
    private int _player_spawn_y = 0;

    public BattleCity()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // 8 pixels per block, 13 blocks, 2x scale, 60 pixels for right panel.
        super(16*13*4, 16*13*4, 1); 
        
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
        Tank tankPlayer1 = new Tank(Tank.TankType.TANK_PLAYER_1, 100, 100);
        addObject(tankPlayer1,225,800);
        Tank tankPlayer2 = new Tank(Tank.TankType.TANK_PLAYER_2, 100, 100);
        addObject(tankPlayer2, 609, 800);
        Tank tankEnemy = new Tank(Tank.TankType.TANK_ENEMY, 100, 100);
        addObject(tankEnemy, 416, 700);
        
        Camp camp = new Camp();
        addObject(camp, 416, 800);
        
        Bonus bonus = new Bonus(); //тест бонуса лопата
        addObject(camp, 296, 800);

        drawMap(this);
        //tank.setLocation(388,492);
        //tank.setLocation(397,484);
        //tank2.setLocation(608,799);
        //tank2.setLocation(609,800);
    }
    
    private void drawMap(World BattleCity)
    {
        Map map = new Map(BattleCity);
    }
}