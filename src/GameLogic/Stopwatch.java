package Logic;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Stopwatch implements Serializable{
    private long startTime = 0;
    private long gameDuration = 0;
    private long savedTime = 0;

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public void restart(){
        reset();
        start();
    }

    public void reset(){
        this.startTime = 0;
        this.gameDuration = 0;
        this.savedTime = 0;
    }

    public void save(){
        this.savedTime = getElapsedTime();
    }

    public void load(){
        this.startTime = System.currentTimeMillis();
        this.gameDuration = this.savedTime;
    }

    public long getElapsedTime(){
        return System.currentTimeMillis() - this.startTime + this.gameDuration;
    }

    @Override
    public String toString(){
        long seconds = TimeUnit.MILLISECONDS.toSeconds(getElapsedTime());
        return String.format("%02d:%02d", seconds/60, seconds%60);
    }
}
