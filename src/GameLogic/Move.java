package Logic;

import java.io.Serializable;

public class Move implements Serializable {
    private final int moveNumber;
    private final Player player;
    private final String type;
    private final int column;

    public Move(int moveNum, Player player, String type, int col){
        this.moveNumber = moveNum;
        this.player = player;
        this.type = type;
        this.column = col;
    }

    public int getMoveNumber(){
        return this.moveNumber;
    }

    public Player getPlayer(){
        return this.player;
    }

    public String getType(){
        return this.type;
    }

    public int getColumn(){
        return this.column;
    }

    @Override
    public String toString(){
        return this.moveNumber+") "+player.getName()+" "+this.type+"ed disc in column "+(this.column+1);
    }
}
