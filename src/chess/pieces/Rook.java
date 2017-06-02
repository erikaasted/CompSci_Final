package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */
public class Rook extends Piece{
	
	//Constructor
	public Rook(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//Move function defined
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//Rook can move only horizontally or vertically
		possiblemoves.clear();
		int tempx=x-1;
		while(tempx>=0)
		{
			if(state[tempx][y].getPiece()==null)
				possiblemoves.add(state[tempx][y]);
			else if(state[tempx][y].getPiece().getColor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx--;
		}
		tempx=x+1;
		while(tempx<8)
		{
			if(state[tempx][y].getPiece()==null)
				possiblemoves.add(state[tempx][y]);
			else if(state[tempx][y].getPiece().getColor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx++;
		}
		int tempy=y-1;
		while(tempy>=0)
		{
			if(state[x][tempy].getPiece()==null)
				possiblemoves.add(state[x][tempy]);
			else if(state[x][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy--;
		}
		tempy=y+1;
		while(tempy<8)
		{
			if(state[x][tempy].getPiece()==null)
				possiblemoves.add(state[x][tempy]);
			else if(state[x][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy++;
		}
		return possiblemoves;
	}
}
