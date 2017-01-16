import greenfoot.*;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GravitonWorld extends World
{
    private Rocket rocket1, rocket2;
    private ArrayList<Actor> actors;
    private int level;
    private Score score1, score2;
    /** The IP address of the server. If this game is the server, simply leave the address blank. */
    private static String SERVERIP;
    /** The port being used for the connection. */
    private static final int PORT = 9000;
    
    /**
     * Create the world and add objects.
     */
    public GravitonWorld()
    {
        super(600, 400, 1);
        SERVERIP = JOptionPane.showInputDialog("Enter the IP address of the server (or leave blank if this is the server)");
        Connection connection = new Connection(SERVERIP, PORT);
        
        Greenfoot.setSpeed(44);
        actors = new ArrayList<Actor>();
        level = 0;
        
        getBackground().setColor(Color.BLACK);
        getBackground().fill();
        
        //Add rocket 1 to the world
        rocket1 = new Rocket(SERVERIP.equals("") ? true : false, 30, 50, connection);
        addObject(rocket1 , 30,50);
        rocket1.setRotation(270);
        score1 = new Score();
        addObject(score1 , 200, 30);
        
        //Add rocket 2 to the world
        rocket2 = new Rocket(SERVERIP.equals("") ? false : true, getWidth()-30,50, connection);
        addObject(rocket2 , getWidth()-30,50);
        rocket2.setRotation(270);
        score2 = new Score();
        addObject(score2 , getWidth()-200,30);        
                
        nextLevel();

    }
    
    /**
     * Sets up the next level
     */
    public void nextLevel()
    {
        level++;
        removeAll();
        makeLevel(level);
    }
    
    /**
     * This method is called whenever a player wins or loses.
     */
    public void end(boolean won, Rocket rocket)
    {
        
        if((won && rocket==rocket1) || (!won && rocket==rocket2)) {
            score1.updateScore(1);
        }
        if((won && rocket==rocket2) || (!won && rocket==rocket1)) {
            score2.updateScore(1);
        }
        
        rocket1.reset();
        rocket2.reset();  

        if(!won) level--;
        
        nextLevel();
        
        Greenfoot.stop();

    }
    
    /**
     * Overrides the addObject method in world.
     * Used to store a reference to any blocks and goals that are being added to the world, so they can easily be removed later.
     */
    public void addObject(Actor object, int x, int y)
    {
        if (!(object instanceof Rocket) && !(object instanceof Score)) actors.add(object);
        super.addObject(object, x, y);
    }
    
    /**
     * Remove all actors from the world
     */
    private void removeAll()
    {
        for(Actor actor : actors) {
            removeObject(actor);
        }
        actors = new ArrayList<Actor>();
    }        
    
    /**
     * Make a certain level
     */
    private void makeLevel(int level)
    {
        switch(level) {
            case 1 :
            
                for(int i=20 ; i<300 ; i+=40) {
                    addObject(new Block(), 100, i);
                }
                for(int i=20 ; i<300 ; i+=40) {
                    addObject(new Block(), 500, i);
                }
                for(int i=300 ; i<400 ; i+=40) {
                    addObject(new Block(), 300, i);
                }
                addObject(new Goal(), 300, 40);
                
                break;
                
            case 2 :
            
                for(int i=20 ; i<300 ; i+=40) {
                    addObject(new Block(), 100, i);
                }
                for(int i=20 ; i<300 ; i+=40) {
                    addObject(new Block(), 500, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 350, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 300, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 250, i);
                }
                addObject(new Goal(), 300, 40);
                
                break;
                
            case 3 :
            
                for(int i=20 ; i<350 ; i+=40) {
                    addObject(new Block(), 100, i);
                }
                for(int i=20 ; i<350 ; i+=40) {
                    addObject(new Block(), 500, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 350, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 300, i);
                }
                for(int i=150 ; i<400 ; i+=40) {
                    addObject(new Block(), 250, i);
                }
                addObject(new Goal(), 300, 40);
                
                break;
                
                
            default :
            
                this.level = 0;
                nextLevel();
                Greenfoot.stop();
                break;
        }
    }    
}
