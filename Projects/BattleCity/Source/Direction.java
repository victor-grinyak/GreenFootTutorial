public enum Direction{
    UP    (0, -1),
    DOWN  (0,  1),
    LEFT  (-1, 0),
    RIGHT (1,  0);
    
    public final int _x;
    public final int _y;
    
    Direction(int x, int y) {
        _x = x;
        _y = y;
    }
    
    int getAngle()
    {
        if(_x == 0  && _y  == -1) return 270; // UP
        if(_x == 0  && _y  ==  1) return 90;  // DOWN
        if(_x == -1 && _y  ==  0) return 180; // LEFT
        if(_x ==  1 && _y  ==  0) return 0;   // RIGHT
        return 0;  
    }
}
