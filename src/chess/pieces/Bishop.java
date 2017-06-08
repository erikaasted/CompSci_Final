package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class Bishop extends Piece
{
	public Bishop(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}

	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		int tempX = x+1, tempY = y-1;
		while (tempX < 8 && tempY >= 0)
		{
			if (state[tempX][tempY].getPiece() == null)
			{
				possiblemoves.add(state[tempX][tempY]);
			}

			else if (state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][tempY]);
				break;
			}
			tempX++;
			tempY--;
		}
		tempX = x-1;
		tempY = y+1;

		while (tempX >= 0 && tempY < 8)
		{
			if (state[tempX][tempY].getPiece() == null)
				possiblemoves.add(state[tempX][tempY]);

			else if(state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][tempY]);
				break;
			}
			tempX--;
			tempY++;
		}
		tempX = x-1;
		tempY = y-1;

		while (tempX >= 0 && tempY >= 0)
		{
			if(state[tempX][tempY].getPiece() == null)
				possiblemoves.add(state[tempX][tempY]);

			else if(state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][tempY]);
				break;
			}
			tempX--;
			tempY--;
		}
		tempX = x+1;
		tempY = y+1;

		while (tempX < 8 && tempY < 8)
		{
			if(state[tempX][tempY].getPiece() == null)
				possiblemoves.add(state[tempX][tempY]);

			else if(state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				possiblemoves.add(state[tempX][tempY]);
				break;
			}
			tempX++;
			tempY++;
		}
		return possiblemoves;
	}
}