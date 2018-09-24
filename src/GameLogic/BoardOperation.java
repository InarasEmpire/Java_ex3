package Logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class BoardOperation {
    protected Board board;
    private Random random = new Random();

    public BoardOperation(Board board){
        this.board = board;
    }

    abstract public boolean isAllowed(Player player, int column);

    abstract protected void executionBody(Player player, int column);

    protected void execute(Player player, int column){
        if(isAllowed(player, column))
            executionBody(player, column);
    }

    protected List<Integer> getOptions(Player player){
        List<Integer> optionalColumns = new ArrayList<>();
        for(int col=0;col<this.board.columns.intValue();col++)
            if(isAllowed(player, col))
                optionalColumns.add(col);

        return optionalColumns;
    }

    protected int getRandomOption(Player player){
        List<Integer> optionalColumns = getOptions(player);
        int chosenColumn = this.random.nextInt(optionalColumns.size());

        return optionalColumns.get(chosenColumn);
    }

    protected boolean hasOption(Player player){
        return getOptions(player).size() > 0;
    }
}
