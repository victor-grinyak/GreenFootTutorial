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
        Stone stone = new Stone();
        addObject(stone,357,440);
        stone.setLocation(349,434);
        Stone stone2 = new Stone();
        addObject(stone2,409,415);
        stone2.setLocation(381,434);
        stone2.setLocation(381,434);
        Stone stone3 = new Stone();
        addObject(stone3,437,442);
        stone3.setLocation(412,434);
        stone3.setLocation(414,434);
        stone3.setLocation(413,434);
        Stone stone4 = new Stone();
        addObject(stone4,525,450);
        stone4.setLocation(445,434);
        stone3.setLocation(445,401);
        stone3.setLocation(445,403);
        stone2.setLocation(349,403);
        stone2.setLocation(349,402);
        Brick brick = new Brick();
        addObject(brick,301,322);
        brick.setLocation(382,401);
        brick.setLocation(381,401);
        brick.setLocation(380,434);
        brick.setLocation(381,433);
        brick.setLocation(381,434);
        Brick brick2 = new Brick();
        addObject(brick2,418,439);
        brick2.setLocation(413,434);
        Brick brick3 = new Brick();
        addObject(brick3,397,396);
        brick3.setLocation(381,402);
        Brick brick4 = new Brick();
        addObject(brick4,408,404);
        brick4.setLocation(413,402);
        Stone stone5 = new Stone();
        addObject(stone5,394,360);
        stone5.setLocation(382,369);
        stone5.setLocation(381,370);
        Stone stone6 = new Stone();
        addObject(stone6,563,384);
        stone6.setLocation(412,370);
        stone6.setLocation(412,370);
        stone6.setLocation(413,370);
        tank.setLocation(388,492);
        tank.setLocation(397,484);
    }
}