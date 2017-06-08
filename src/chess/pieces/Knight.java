package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class Knight extends Piece
{
	public Knight(String i, String p, int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}

	public ArrayList<Cell> move(Cell state[][], int x, int y)
	{
		possiblemoves.clear();
		int positionX[] = {x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
		int positionY[] = {y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};

		for (int i = 0; i < 8; i++)
		{
			if ((positionX[i] >= 0 && positionX[i] < 8 && positionY[i] >= 0 && positionY[i] < 8))
			{
				if ((state[positionX[i]][positionY[i]].getPiece() == null || state[positionX[i]][positionY[i]].getPiece().getColor() != this.getColor()))
					possiblemoves.add(state[positionX[i]][positionY[i]]);
			}
		}
		return possiblemoves;
	}
}