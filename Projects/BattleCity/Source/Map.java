import greenfoot.*;
import java.awt.Color;
/**
 * Write a description of class Map here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Map
{
    // instance variables - replace the example below with your own
    private int[][] mapObjects = { {0,0,0,0,0,0,0,0,0,0,0,0,0},
                                   {0,1,0,1,0,1,0,1,0,1,0,1,0},
                                   {0,1,0,1,0,1,0,1,0,1,0,1,0},
                                   {0,1,0,1,0,1,2,1,0,1,0,1,0},
                                   {0,1,0,1,0,1,0,1,0,1,0,1,0},
                                   {0,1,0,1,0,0,0,0,0,1,0,1,0},
                                   {0,0,0,0,0,1,1,1,0,0,0,0,0},
                                   {2,1,1,1,0,0,0,0,0,1,1,1,2},
                                   {0,0,0,0,0,1,0,1,0,0,0,0,0},
                                   {0,1,0,1,0,1,1,1,0,1,0,1,0},
                                   {0,1,0,1,3,1,0,1,0,1,0,1,0},
                                   {0,1,0,1,3,0,0,0,0,1,0,1,0},
                                   {0,0,0,0,0,0,0,0,0,0,0,0,0}
                                 };
 
    public Map(World BattleCity)
    {
        /*
        
        GreenfootImage background = BattleCity.getBackground();
        background.setColor(Color.WHITE);
        int size = background.getHeight();
       
        for (int i = 1; i < 14; i++) {
            background.drawLine(64*i, 0, 64*i, size);//832 - размер поля боя
            background.drawLine(0, 64*i, size, 64*i);
        }
       */ 
        Wall[][][] wall = new Wall[13][13][4];
        Bonus[][] bonuses = new Bonus[13][13];
       
        int x = 16;
        int y = 16;
       
        for (int i = 0; i < 13; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                if ( mapObjects[i][j] == 3) {bonuses[i][j] = new Bonus();}//Бонусы
                
                for ( int k = 0; k < 4; k++ )
                {
                    if ( mapObjects[i][j] == 1) {wall[i][j][k] = new Brick();}
                    if ( mapObjects[i][j] == 2) {wall[i][j][k] = new Stone();}
                }
            }
        }
       
       
        for (int i = 0; i < 13; i++)
        {
            for (int j = 0; j < 13; j++)
            {
                int _x = x + 64*j;
                int _y = y + 64*i;
                if ( mapObjects[i][j] == 3 ) {BattleCity.addObject(bonuses[i][j], _x + 16, _y + 16);}
                
                if ( mapObjects[i][j] == 1 || mapObjects[i][j] == 2 )
                {
                    for (int k = 0; k < 4; k++)
                    {
                        if ( k < 2 )
                        {
                            BattleCity.addObject(wall[i][j][k], _x, _y + k*32);
                        }
                        else
                        {  
                            if ( k == 2)
                            {
                                BattleCity.addObject(wall[i][j][k], _x + 32, _y + (k - 2)*32);
                            }
                            else
                            {
                                BattleCity.addObject(wall[i][j][k], _x + 32, _y + (k - 2)*32);
                            }
                        }
                    }
                }
            }
        }
    }
 
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
   
}