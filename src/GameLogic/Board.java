package Logic;

import sun.invoke.empty.Empty;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="rows" use="required" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="columns" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Board")
public class Board implements Serializable {
    public enum Direction {
        UP, RIGHT, DOWN, LEFT, STATIC
    }

    @XmlAttribute(name = "rows", required = true)
    protected byte rows;
    @XmlAttribute(name = "columns", required = true)
    protected BigInteger columns;

    protected BigInteger target;
    private Disc[][] discs;
    private int[] discAmount;
    private List<Connect> connects = new ArrayList<>();
    private boolean isCircular = false;
    private boolean isPopout = false;
    private static final Disc EMPTY = null;

    protected BoardOperation popOut = new PopoutOperation(this);
    protected BoardOperation pushIn = new PushinOperation(this);

    /**
     * Gets the value of the rows property.
     *
     */
    public byte getRows() {
        return rows;
    }

    /**
     * Sets the value of the rows property.
     *
     */
    public void setRows(byte value) {
        this.rows = value;
    }

    /**
     * Gets the value of the columns property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setColumns(BigInteger value) {
        this.columns = value;
    }

    public Disc[][] getDiscs(){
        Disc[][] discs = new Disc[rows][columns.intValue()];
        for(int i=0;i<this.rows;i++)
            discs[i] = this.discs[i].clone();

        return discs;
    }

    public void setTarget(BigInteger value) {
        this.target = value;
    }

    public List<Connect> getConnects(){
        return this.connects;
    }

    public BoardOperation getPopOutOperation(){ return this.popOut; }

    public BoardOperation getPushInOperation() { return this.pushIn; }

    public void setCircular(boolean isCircular){
        this.isCircular = isCircular;
    }

    public void setPopout(boolean isPopout){ this.isPopout = isPopout; }

    public boolean isPopout(){return this.isPopout;}

    public boolean isConnectExists(){
        return this.connects.size()>0;
    }

    public boolean isCellEmpty(int row, int col){
        return this.discs[row][col] == EMPTY;
    }

    public boolean isColumnFull(int col){
        return this.discAmount[col] == this.rows;
    }

    public boolean isFull() {
        for(int i = 0; i<this.columns.intValue();i++){
            if (this.discAmount[i] != this.rows)
                return false;
        }
        return true;
    }

    public Disc getCell(int row, int col){ return this.discs[row][col]; }

    public int getTopRow(int col){ return this.discAmount[col]; }

    public void init(){
        this.discs = new Disc[rows][columns.intValue()];
        this.discAmount = new int[columns.intValue()];

        for (int i=0; i<this.rows; i++)
            for (int j=0; j<this.columns.intValue(); j++)
                this.discs[i][j]=EMPTY;

        for(int i=0;i<this.columns.intValue();i++)
            this.discAmount[i] = 0;

        this.connects.clear();
        Disc.init();
    }

    public void removeAllPlayerDiscs(Player player){
        for (int i=0;i<this.rows;i++){
            for (int j=0; j<this.columns.intValue(); j++){
                while (!isCellEmpty(i,j) && this.discs[i][j].getPlayer().equals(player))
                    removeDisc(i,j);
            }
        }
    }

    protected void removeDisc(int row, int col){
        if (!isCellEmpty(row,col)){
            this.discAmount[col]--;
            for(int i = row; i<this.rows-1; i++){
                this.discs[i][col] = this.discs[i+1][col];
            }
            this.discs[this.rows-1][col] = EMPTY;
        }
    }

    protected void insertDisc(Disc disc, int row, int col){
        if(isCellEmpty(row,col)){
            this.discAmount[col]++;
            this.discs[row][col] = disc;
        }
    }

    protected void checkConnectFullBoard(){
        for(int i = 0; i<this.rows; i++)
            for(int j = 0; j<this.columns.intValue(); j++)
                if(!isCellEmpty(i,j))
                    checkConnect(i,j);
    }

    protected void checkConnect(int row, int col){
        connectWrapper(row, col, this::horizontalCheck );
        connectWrapper(row, col, this::verticalCheck );
        connectWrapper(row, col, this::diagonalPosCheck );
        connectWrapper(row, col, this::diagonalNegCheck );
    }

    private void connectWrapper(int row, int col, IConnectDirection connectDirection){
        Disc disc = this.discs[row][col];
        Connect connect = new Connect(disc.getPlayer());
        connect.add(disc);
        connectDirection.check(row, col, connect);

        if (connect.size() >= this.target.intValue()) {
            connect.setReachedTarget(true);
            this.connects.add(connect);
        }
    }

    private void horizontalCheck(int row, int col, Connect connect){
        checkConnectDirection(row, col, Direction.STATIC, Direction.RIGHT, connect);
        checkConnectDirection(row, col, Direction.STATIC, Direction.LEFT, connect);
    }

    private void verticalCheck(int row, int col, Connect connect){
        checkConnectDirection(row, col, Direction.UP, Direction.STATIC, connect);
        checkConnectDirection(row, col, Direction.DOWN, Direction.STATIC, connect);
    }

    private void diagonalPosCheck(int row, int col, Connect connect){
        checkConnectDirection(row, col, Direction.UP, Direction.RIGHT, connect);
        checkConnectDirection(row, col, Direction.DOWN, Direction.LEFT, connect);
    }

    private void diagonalNegCheck(int row, int col, Connect connect){
        checkConnectDirection(row, col, Direction.UP, Direction.LEFT, connect);
        checkConnectDirection(row, col, Direction.DOWN, Direction.RIGHT, connect);
    }

    private void checkConnectDirection(int row, int col, Direction dirRow, Direction dirCol, Connect connect){
        int newRow = row;
        int newCol = col;

        switch(dirRow){
            case UP:
                newRow = row+1;
                break;
            case DOWN:
                newRow = row-1;
                break;
        }
        switch(dirCol){
            case RIGHT:
                newCol = col+1;
                break;
            case LEFT:
                newCol = col-1;
                break;
        }

        if (this.isCircular && (dirRow == Direction.STATIC || dirCol == Direction.STATIC)){
            newRow = (newRow + this.rows) % this.rows;
            newCol = (newCol +this.columns.intValue()) % this.columns.intValue();
        }
        else if (newRow < 0 || newRow >= this.rows || newCol < 0 || newCol >= this.columns.intValue()){
            return;
        }

        if (!isCellEmpty(newRow,newCol) && this.discs[newRow][newCol].equals(this.discs[row][col])) {
            connect.add(this.discs[newRow][newCol]);
            checkConnectDirection(newRow, newCol, dirRow, dirCol, connect);
        }
    }
}
