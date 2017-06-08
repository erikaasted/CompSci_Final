package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class Pawn extends Piece
{
    public Pawn(String i, String p, int c)
    {
        setId(i);
        setPath(p);
        setColor(c);
    }

    public ArrayList<Cell> move(Cell state[][], int x, int y)
    {
        possiblemoves.clear();

        if (getColor() == 0)
        {
            if (x == 0)
                return possiblemoves;

            if (state[x-1][y].getPiece() == null)
            {
                possiblemoves.add(state[x-1][y]);
                if (x == 6)
                {
                    if (state[4][y].getPiece() == null)
                        possiblemoves.add(state[4][y]);
                }
            }

            if ((y>0) && (state[x-1][y-1].getPiece() != null) && (state[x-1][y-1].getPiece().getColor() != this.getColor()))
                possiblemoves.add(state[x-1][y-1]);

            if ((y<7) && (state[x-1][y+1].getPiece() != null) && (state[x-1][y+1].getPiece().getColor() != this.getColor()))
                possiblemoves.add(state[x-1][y+1]);
        }

        else
        {
            if (x == 8)
                return possiblemoves;

            if (state[x+1][y].getPiece() == null)
            {
                possiblemoves.add(state[x+1][y]);
                if (x == 1)
                {
                    if (state[3][y].getPiece() == null)
                        possiblemoves.add(state[3][y]);
                }
            }

            if ((y>0) && (state[x+1][y-1].getPiece() != null) && (state[x+1][y-1].getPiece().getColor() != this.getColor()))
                possiblemoves.add(state[x+1][y-1]);

            if ((y<7) && (state[x+1][y+1].getPiece() != null) && (state[x+1][y+1].getPiece().getColor() != this.getColor()))
                possiblemoves.add(state[x+1][y+1]);
        }
        return possiblemoves;
    }
}
