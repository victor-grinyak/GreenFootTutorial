import greenfoot.*; 
import java.util.*;

public class ScenarioLoader extends java.lang.Object
{

    private final int OFFSET = 6;
    private final int SPACE = 4;

    private Player player;
    private Area area;
    private Wall wall;
    private Block block;
    
    private World world;
    private String level = new String();
    private ScenarioLevel sLevel = new ScenarioLevel();
    
        
    public ScenarioLoader(World world){
        this.world = world;
       
    }

    public void loadLevel(){
        int x = OFFSET;
        int y = OFFSET;
        
         level = sLevel.getScenarioLevel(24);
 

        for (int i = 0; i < level.length(); i++) {
            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                //if (this.w < x) {this.w = x;}

                x = OFFSET;

            } else if (item == '#') {

                world.addObject(new Wall(),x,y);
                x += SPACE;

            } else if (item == '$') {

                world.addObject(new Block(),x,y);
                x += SPACE;

            } else if (item == '.') {

                world.addObject(new Area(),x,y);
                x += SPACE;

            } else if (item == '@') {

                world.addObject(new Player(),x,y);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } 

        }
    }

}

