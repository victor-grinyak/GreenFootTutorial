import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cell extends Actor
{
    /**
     * Act - do whatever the Cell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int state;
    private int x;
    private int y;
    private boolean flag;
    public GreenfootImage image;
    public boolean game_status = true;
    
    public Cell(int x, int y, int state, boolean flag) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.flag = flag;
        this.game_status = game_status;
        
        this.image = getImage();
        image.scale(90, 90);
        setImage(image);
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            
            MouseInfo mouse_info = Greenfoot.getMouseInfo();
            
            if (this.flag != true) {
                if(mouse_info.getButton() == 1) {
                    //this.setImage("res/0.png");
                    
                    if (this.state == -1) {
                        GreenfootImage image = new GreenfootImage("Статус : проиграли", 40, null, null);
                        this.game_status = false;
                        ((MyWorld) getWorld()).game_info_text.setImage(image);
                        this.setImage("res/explosion.png");
                    }
                    else {
                        recieve_click(this);                    
                    }
                }
            }
                
            if(mouse_info.getButton() == 3) {
                
                if (!this.flag) {
                    this.flag = true;
                    this.setImage("res/flag.png");
                    ((MyWorld) getWorld()).flags_counter--;
                    
                    if (this.state == -1)
                        ((MyWorld) getWorld()).mine_counter--;
                }
                else {
                    this.flag = false;
                    this.setImage(this.image);
                    ((MyWorld) getWorld()).flags_counter++;
                    
                     if (this.state == -1)
                        ((MyWorld) getWorld()).mine_counter++;
                }
                    
                
                if (((MyWorld) getWorld()).mine_counter == 0) {
                        GreenfootImage image = new GreenfootImage("Статус : выиграли", 40, null, null);
                    ((MyWorld) getWorld()).game_info_text.setImage(image);;
                    }
                
                int flags_counter = ((MyWorld) getWorld()).getFlagsCount();
                
                 GreenfootImage image = new GreenfootImage("Флагов осталось: " + flags_counter, 40, null, null);
                ((MyWorld) getWorld()).flags_counter_text.setImage(image);
            }
        }
        
    }    
    
    public int recieve_click(Cell cell) {
        int around_mines = 0;
        
        int[][] counts = ((MyWorld) getWorld()).getCounts();
        Cell[][] map = ((MyWorld) getWorld()).getMap();
        
        
        for(int x = cell.x - 1; x <= cell.x + 1; x++) {
            if (x < 0 || x > 8)
                continue;
            
            for (int y = cell.y - 1; y <= cell.y + 1; y++) {
                if (y < 0 || y > 8)
                    continue;
                    
               //System.out.println(x + " " + y + " state: " + cell.state);
               if (counts[x][y] == -1) {
                   around_mines++;
               }
            }
        }
       
        //System.out.println(cell.x + " " + cell.y + " mine: " + around_mines);
        cell.setImage("res/"+around_mines+".png"); 
        
        if (cell.state != -1)
            cell.setImage("res/"+around_mines+".png"); 
        else return 0;
        
        if (around_mines == 0) {
            try{
                recieve_click(map[cell.x+1][cell.y]);
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e){
            }
 
            
            try{
                recieve_click(map[cell.x][cell.y-1]);
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e){
            }
            
        }
        
        
        return 0;
    }
} 




//123 1,1 1,2 1,3
//456 2,1 2,2 2,3
//789 3,1 3,2 3,3