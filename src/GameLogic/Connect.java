package Logic;

import java.util.ArrayList;

public class Connect extends ArrayList<Disc> {
    private final Player player;
    private boolean isReachedTarget = false;

    public Connect(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }

    protected void setReachedTarget(boolean isReachedTarget){
        this.isReachedTarget = isReachedTarget;
        for(Disc disc : this)
            disc.setConnected(true);
    }

    public boolean isReachedTarget(){return this.isReachedTarget;}
}
