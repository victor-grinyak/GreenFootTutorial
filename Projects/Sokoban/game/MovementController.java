import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MovementController extends Actor
{

    private final int SPACE = 4;

    int _x=0;
    int _y=0;

    public void set_x(int x){
        _x=x;
    }

    public int get_x(){
        return _x;
    }

    public void set_y(int y){
        _y=y;
    }

    public int get_y(){
        return _y;
    }

    public void moveWith(String RIGHT,String LEFT,String UP,String DOWN)
    {
        String key = Greenfoot.getKey();

        set_x(getX());
        set_y(getY());

        if ( key!=null && key.equals(RIGHT) ){moveIt("right");}
        if ( key!=null && key.equals(LEFT)  ) {moveIt("left");}
        if ( key!=null &&key.equals(UP)  ) {moveIt("up");}
        if ( key!=null && key.equals(DOWN)  ) {moveIt("down");}
    }

    public void moveIt(String direction)
    {       
        int dx = 4;
        int dy = 4;

        if(direction == "right" ){setLocation(getX()+dx, getY());}
        if(direction == "left" ) {setLocation(getX()-dx, getY());}
        if(direction == "up" )   {setLocation(getX(), getY()-dy);}
        if(direction == "down" ) {setLocation(getX(), getY()+dy);}

    }

    public void checkBumpWith(Class myClass){
        Actor actor1 = getOneIntersectingObject(myClass);
        if(actor1!=null && !blockBumpedWall()){
            this.moveIt(identifyCollisionSide(actor1));
        }
    }

    public String identifyCollisionSide(Actor actor) {     

        Object obj= getWorld().getObjects(Player.class).get(0);
        Player gB = (Player)obj;

        String result="";
        if (actor.getY() == this.getY()){
            if (actor.getX()> gB.get_x()) {
                result= "right";
            }else if (actor.getX()<gB.get_x() ){
                result= "left";
            }

        }

        if(actor.getX() == this.getX()){
            if (actor.getY() > gB.get_y())  {
                result= "down";
            } else if (actor.getY() < gB.get_y()) {
                result= "up";
            } 
        }
        return result;
    }

    public void stopIfCollideWith(Class myClass){

        Object obj= getWorld().getObjects(Player.class).get(0);
        Player gB = (Player)obj;

        Actor actor = getOneIntersectingObject(myClass);
        if(actor!=null){
            setLocation(gB.get_x(), gB.get_y());

        }

    }

    public boolean blockBumpedWall(){
        boolean result;

        Actor actor2 = getOneIntersectingObject(Block.class);
        if(actor2!=null){ result = true;}
        else { result = false;}

        return result;
    }
}

