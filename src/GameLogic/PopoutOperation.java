package Logic;

public class PopoutOperation extends BoardOperation {
    public PopoutOperation(Board board) {
        super(board);
    }

    @Override
    public boolean isAllowed(Player player, int column) {
        return board.isPopout() && !board.isCellEmpty(0, column)
                && board.getCell(0, column).getPlayer().equals(player);
    }

    @Override
    protected void executionBody(Player player, int column) {
        board.removeDisc(0, column);

        for (int row=0; row<board.rows; row++){
            if (!board.isCellEmpty(row,column))
                board.checkConnect(row, column);
            else
                break;
        }
    }

    @Override
    public String toString(){ return "pop"; }
}
