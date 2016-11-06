public class Timer  
{ 
    public static long getTime(){
        return System.currentTimeMillis();
    }

    private long _range = 0;
    private long _startTime = 0;
    private long _finishTime = 0;

    public void reset()
    {
        _startTime = getTime();
        updateFinishTime();
    }
    
    public void setRange(long range){
        _range = range;
        updateFinishTime();
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
    
    private void updateFinishTime()
    {
        _finishTime = _startTime + _range;
    }
}
