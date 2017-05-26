public abstract class ChessPiece
{
    int[][] location;
    String type;

    ChessPiece(int[] location, String type)
    {

    }

    public abstract void move();

}
