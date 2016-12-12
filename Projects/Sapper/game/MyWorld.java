import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private final int CELLS_COUNT_X = 9;
    private final int CELLS_COUNT_Y = 9;
    public int[][] counts = new int[CELLS_COUNT_X][CELLS_COUNT_Y];
    public Cell[][] map = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];
    public int mine_counter = 0;
    public int flags_counter = 0;
    
    public Flags_counter flags_counter_text = new  Flags_counter();
    public Game_info game_info_text = new  Game_info();
    
    public int getRandomNumber(int start,int end)
    {
           int normal = Greenfoot.getRandomNumber(end-start+1);
           return normal+start;
    }

    public MyWorld()
    {    
        super(810, 900, 1);
        
         for (int x = 0; x < CELLS_COUNT_X; x++){
                for(int y = 0; y < CELLS_COUNT_Y; y++){
                     
                    boolean isMine = getRandomNumber(0, 100) < 15;
                        
                    if(isMine){
                        map[x][y] = new Cell(x, y, -1, false);
                        addObject(map[x][y], 45 + 90*x, 45 + 90*y);  
                        counts[x][y] = -1;
                        
                        mine_counter++;
                    }
                    else {
                        map[x][y] = new Cell(x, y, 1, false);
                        addObject(map[x][y], 45 + 90*x, 45 + 90*y);  
                        counts[x][y] = 1;
                    }
                        
                } 
            }

        flags_counter = mine_counter;
        addObject(flags_counter_text, 220, 840);
        addObject(new Mine_counter(mine_counter), 220, 870);    
        addObject(game_info_text, 620, 840);  

    }
    
    public int[][] getCounts()
    {
        return counts;
    }
    
     public Cell[][] getMap()
    {
        return map;
    }
    
    public int getFlagsCount()
    {
        return flags_counter;
    }

}

    