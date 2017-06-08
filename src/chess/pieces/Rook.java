package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class Rook extends Piece
{
	public Rook(String i, String p, int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}

	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		int tempX = x-1;
		while (tempX >= 0)
		{
			if(state[tempX][y].getPiece() == null)
				possiblemoves.add(state[tempX][y]);

			else if(state[tempX][y].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][y]);
				break;
			}

			tempX--;
		}

		tempX = x+1;

		while (tempX < 8)
		{
			if (state[tempX][y].getPiece() == null)
				possiblemoves.add(state[tempX][y]);

			else if (state[tempX][y].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][y]);
				break;
			}

			tempX++;
		}

		int tempY = y-1;

		while (tempY >= 0)
		{
			if (state[x][tempY].getPiece() == null)
				possiblemoves.add(state[x][tempY]);

			else if (state[x][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[x][tempY]);
				break;
			}

			tempY--;
		}
		tempY = y+1;

		while(tempY < 8)
		{
			if(state[x][tempY].getPiece() == null)
				possiblemoves.add(state[x][tempY]);

			else if(state[x][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[x][tempY]);
				break;
			}

			tempY++;
		}
		return possiblemoves;
	}
}
