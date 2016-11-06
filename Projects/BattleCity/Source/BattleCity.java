import greenfoot.*;
import java.awt.Color;

/**
 * Клон игры BattleCity
 * 
 * @author Fedor Kuzin
 * @author Vitaliy Vitushkin
 * @version 1
 */
public class BattleCity extends World
{
    public enum GameState
    {
        MENU,
        GAME_STARTING,
        PAUSE,
        GAME,
        GAME_OVER
    }
    
    public static final int SCALE = 4;
    
    private static final int blocks = 13;
    private static final int blockSize = 16;
    private static final int mapSize = blocks * blockSize * SCALE;

    public BattleCity()
    {
        super(mapSize, mapSize, 1); 
        
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Tank tankPlayer1 = new Tank(Tank.Type.PLAYER_1);
        addObject(tankPlayer1,225,800);
        Tank tankPlayer2 = new Tank(Tank.Type.PLAYER_2);
        addObject(tankPlayer2, 609, 800);
        Tank tankEnemy = new Tank(Tank.Type.ENEMY);
        addObject(tankEnemy, 416, 700);
        
        Camp camp = new Camp();
        addObject(camp, 416, 800);
        
        //Bonus bonus = new Bonus(); //тест бонуса лопата
        //addObject(bonus, 290, 736);

        drawMap();
    }
    
    private void drawMap()
    {
        Map map = new Map(this);
    }
}