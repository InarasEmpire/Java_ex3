package Logic;

import java.io.Serializable;

public class Disc implements Serializable {
    private static int discCounter = 0;
    private int discId;
    private Player player;
    private boolean isConnected = false;

    public Disc(Player player){
        discCounter++;
        this.discId = discCounter;
        this.player = player;
    }

    public static void init(){
        discCounter = 0;
    }

    public int getDiscId(){
        return this.discId;
    }

    public Player getPlayer(){
        return this.player;
    }

    protected void setConnected(boolean isConnected){ this.isConnected = isConnected;}

    public boolean isConnected(){return this.isConnected;}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this){
            return true;
        }

        if (!(obj instanceof Disc)) {
            return false;
        }

        final Disc other = (Disc) obj;
        return this.player.equals(other.player);
    }
}
