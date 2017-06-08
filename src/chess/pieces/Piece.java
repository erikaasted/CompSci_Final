package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public abstract class Piece implements Cloneable
{
    private int color;
    private String id = null;
    private String path;
    ArrayList<Cell> possiblemoves = new ArrayList<Cell>();

    public abstract ArrayList<Cell> move(Cell pos[][], int x, int y);

    void setId(String id)
    {
        this.id = id;
    }

    void setPath(String path)
    {
        this.path = path;
    }

    void setColor(int c)
    {
        this.color = c;
    }

    public String getPath()
    {
        return path;
    }

    public String getID()
    {
        return id;
    }

    public int getColor()
    {
        return this.color;
    }

    public Piece getCopy() throws CloneNotSupportedException
    {
        return (Piece) this.clone();
    }
}