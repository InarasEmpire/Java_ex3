package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {
    private List<Move> moves = new ArrayList<>();

    public List<Move> getMoves(){
        return this.moves;
    }

    public Move getLastMove(){
        int lastIndex = this.moves.size() - 1;

        return this.moves.get(lastIndex);
    }

    public void init(){
        this.moves.clear();
    }

    public void addMove(Move move){
        this.moves.add(move);
    }

    public void removeMove(Move move){
        int index = this.moves.indexOf(move);

        if (index >= 0){
            this.moves.remove(index);
        }
    }

    @Override
    public String toString(){
        StringBuilder movesString = new StringBuilder();
        for(Move move : this.moves){
            movesString.append(move.toString()).append("\n");
        }

        return movesString.toString();
    }

}
