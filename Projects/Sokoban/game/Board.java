import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Board extends World {
    private ScenarioLoader sL= new ScenarioLoader(this);
    private Player player;
    private Wall wall;
    
    public Board() {
        super(100,100, 5);
        setPaintOrder(Player.class,Block.class,Area.class,Wall.class);
        sL.loadLevel();

    }

}

