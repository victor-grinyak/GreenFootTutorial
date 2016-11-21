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
    
    public static final int blocks = 13;
    public static final int blockSize = 16;
    public static final int mapSize = blocks * blockSize * SCALE;
    
    private Map _map = new Map(blocks, blockSize);
    private GameState _state = GameState.GAME;
    
    
    public BattleCity()
    {
        super(mapSize, mapSize, 1, false); 
        
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        
        prepare();
    }

    public void act()
    {
        switch(_state)
        {
            case MENU:
            break;
            
            case GAME_STARTING:
            break;
            
            case PAUSE:
            break;
            
            case GAME:
            break;
            
            case GAME_OVER:
            break;
        }
    }
    
    public void setState(GameState state)
    {
        _state = state;
    }
    
    public GameState getState()
    {
        return _state;
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Camp camp = new Camp();
        addObject(camp, 416, 800);
        
        Tank tankPlayer1 = new Tank(Tank.Type.PLAYER_1);
        addObject(tankPlayer1,225,800);
        
        Tank tankPlayer2 = new Tank(Tank.Type.PLAYER_2);
        addObject(tankPlayer2, 609, 800);
        
        
        Tank tankEnemy = new Tank(Tank.Type.ENEMY);
        addObject(tankEnemy, 32, 32);
        
        Tank tankEnemy2 = new Tank(Tank.Type.ENEMY);
        addObject(tankEnemy2, 32*15, 32);
        
        Tank tankEnemy3 = new Tank(Tank.Type.ENEMY);
        addObject(tankEnemy3, 32*22, 32);        
        
        //Bonus bonus = new Bonus(); //тест бонуса лопата
        //addObject(bonus, 290, 736);

        buildMap();
    }
    
    public void started(){

    }
    
    private void buildMap()
    {
        _map.build(this);
    }
    
    public Map getMap()
    {
        return _map;
    }
}