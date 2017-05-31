public abstract class ChessPiece
{
    int row, col;
    String color;

    ChessPiece(String col, int row, String color)
    {
        this.col = Integer.parseInt(col);
        this.row = row;
        this.color = color;
    }

    public abstract void move();

}
