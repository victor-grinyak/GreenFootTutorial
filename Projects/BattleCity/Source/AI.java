import greenfoot.*;
import java.util.Vector;
import java.util.Iterator;
import java.util.List;

/**
 * Write a description of class AI here.
 * 
 * @author Fedor Kuzin
 * @version 1
 */
public class AI  
{
    private int _sumWeights = 24;
    private enum CaseWeight{
        MAP_BORDER(0),
        STONE_WALL(0),
        BRICK_WALL(1),
        ROAD(8),
        CAMP(15);
        
        public int weight;
        
        CaseWeight(int wght)
        {
            weight = wght;
        }
    }
    
    private int _maxCases;
    private Camp _camp;
    private Tank _tank;
    
    public AI(Tank tank)
    {
        _maxCases = 4;
        
        _tank = tank;
        _camp = Camp.getCamp();
    }
    
    private List<Wall> getWalls(World wrld, int x, int y){
        return (List<Wall>)wrld.getObjectsAt(x, y, Wall.class);
    }
        
    private int getCaseWeight(Direction direction){
        BattleCity wrld = (BattleCity)_tank.getWorld();
        Map map = wrld.getMap();
        
        int x = _tank.getX();
        int y = _tank.getY();
        
        int point_x1 = x + direction._x * 40 + direction._y * 7;
        int point_y1 = y + direction._y * 40 + direction._x * 7;
        
        int point_x2 = point_x1 - direction._y * 14;
        int point_y2 = point_y1 - direction._x * 14;
        
        //wall
        List<Wall> walls1 = getWalls(wrld, point_x1, point_y1);
        List<Wall> walls2 = getWalls(wrld, point_x2, point_y2);
        walls1.addAll(walls2); 
        
        if(!walls1.isEmpty()){

            for (Wall wall : walls1) {
                if(wall instanceof Stone){
                   return CaseWeight.STONE_WALL.weight;
                }
            }
            
            return CaseWeight.BRICK_WALL.weight;
        }
        
        //camp
        List<Camp> camps1 = (List<Camp>)wrld.getObjectsAt(point_x1, point_y1, Camp.class);
        List<Camp> camps2 = (List<Camp>)wrld.getObjectsAt(point_x2, point_y2, Camp.class);
        if(!camps1.isEmpty() || !camps2.isEmpty()){
            return CaseWeight.CAMP.weight;
        }
        
        if(map.isInside(point_x1, point_y1)){
            if(direction == Direction.DOWN){
                return CaseWeight.CAMP.weight;
            }
            else if(direction == getCampSide() && getSqrDistanceToCamp() <= 128){
                return CaseWeight.CAMP.weight;
            }
            else{
                return CaseWeight.ROAD.weight;
            }
        }
        //map border
        else{
            return CaseWeight.MAP_BORDER.weight;
        }    
    }
    
    private void appendDirection(Vector<Direction> vec, Direction direction, int repeat)
    {
        for(int i = 0; i < repeat; i++){
            vec.addElement(direction);
        }
   }
    
    private Vector<Direction> getDirections()
    {
        Direction campSide = getCampSide();
        Direction tankDirection = _tank.getDirection();
        
        int[] ourCases = {getCaseWeight(Direction.UP) - 3,
                          getCaseWeight(Direction.DOWN),
                          getCaseWeight(Direction.LEFT),
                          getCaseWeight(Direction.RIGHT),
                          };
                                       //size, sum of weights
        Vector<Direction> directions = new Vector(0, _sumWeights);
        
        appendDirection(directions, Direction.UP,    ourCases[0]);
        appendDirection(directions, Direction.DOWN,  ourCases[1]);
        appendDirection(directions, Direction.LEFT,  ourCases[2]);
        appendDirection(directions, Direction.RIGHT, ourCases[3]);
        
        return directions;
    }
    
    private Direction getCampSide()
    {
        if(_camp.getX() > 
        _tank.getX()){
            return Direction.RIGHT;
        }else{
            return Direction.LEFT;
        }
    }
    
    private int getSqrDistanceToCamp(){
        int x = _tank.getX() - _camp.getX();
        int y = _tank.getY() - _camp.getY();
        
        return x*x + y*y;
    }
    
    protected Direction makeDecision()
    {
        Vector<Direction> direction = getDirections();
        
        int dirSize = direction.size();
        
        return direction.get(Greenfoot.getRandomNumber(dirSize));
    }
}
