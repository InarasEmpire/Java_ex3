package Logic;

public class PushinOperation extends BoardOperation {
    public PushinOperation(Board board) {
        super(board);
    }

    @Override
    public boolean isAllowed(Player player, int column) {
        return !board.isColumnFull(column);
    }

    @Override
    protected void executionBody(Player player, int column) {
        int row = board.getTopRow(column);
        Disc disc = new Disc(player);
        board.insertDisc(disc, row, column);
        board.checkConnect(row, column);
    }

    @Override
    public String toString(){ return "push"; }
}
