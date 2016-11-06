public class Timer  
{ 
    public static long getTime(){
        return System.currentTimeMillis() / 1000L;
    }

    private long _range = 0;
    private long _startTime = 0;
    private long _finishTime = 0;

    public void reset()
    {
        _startTime = getTime();
    }
    
    public void setRange(long range){
        _range = range;
    }
    
    public long getRange()
    {
        return _range;
    }
    
    public boolean inRange()
    {
        return getTime() < _finishTime;
    }
    
    public boolean outRange()
    {
        return getTime() >= _finishTime;
    }
}
