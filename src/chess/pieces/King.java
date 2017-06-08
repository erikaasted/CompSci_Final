package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class King extends Piece
{
	private int x, y;

	public King (String i, String p ,int c ,int x ,int y)
	{
		setX(x);
		setY(y);
		setId(i);
		setPath(p);
		setColor(c);
	}

	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}


	public ArrayList<Cell> move(Cell state[][], int x, int y)
	{
		possiblemoves.clear();

		int positionX[] = {x, x, x+1, x+1, x+1, x-1, x-1, x-1};
		int positionY[] = {y-1, y+1, y-1, y, y+1, y-1, y, y+1};

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
	

	public boolean kingIsInDanger(Cell state[][])
    {
    	for (int i = x+1; i < 8; i++)
    	{
    		if(state[i][y].getPiece() == null)
    			continue;

    		else if (state[i][y].getPiece().getColor()==this.getColor())
    			break;

    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}

    	for (int i = x-1; i >= 0; i--)
    	{
    		if (state[i][y].getPiece() == null)
    			continue;

    		else if (state[i][y].getPiece().getColor() == this.getColor())
    			break;

    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for (int i = y+1; i < 8; i++)
    	{
    		if(state[x][i].getPiece() == null) {
			}

    		else if(state[x][i].getPiece().getColor() == this.getColor())
    			break;

    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i = y-1; i >= 0; i--)
    	{
    		if(state[x][i].getPiece() == null)
    			continue;

    		else if (state[x][i].getPiece().getColor() == this.getColor())
    			break;

    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}

    	int tempX = x+1, tempY = y-1;
		while (tempX < 8 && tempY >= 0)
		{
			if(state[tempX][tempY].getPiece() == null)
			{
				tempX++;
				tempY--;
			}

			else if (state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				if (state[tempX][tempY].getPiece() instanceof Bishop || state[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}

		tempX = x-1;
		tempY = y+1;

		while (tempX >= 0 && tempY < 8)
		{
			if (state[tempX][tempY].getPiece() == null)
			{
				tempX--;
				tempY++;
			}

			else if(state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				if (state[tempX][tempY].getPiece() instanceof Bishop || state[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}

		tempX = x-1;
		tempY = y-1;

		while (tempX >= 0 && tempY >= 0)
		{
			if (state[tempX][tempY].getPiece() == null)
			{
				tempX--;
				tempY--;
			}

			else if(state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				if (state[tempX][tempY].getPiece() instanceof Bishop || state[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}

		tempX = x+1;
		tempY = y+1;

		while (tempX < 8 && tempY < 8)
		{
			if (state[tempX][tempY].getPiece() == null)
			{
				tempX++;
				tempY++;
			}

			else if (state[tempX][tempY].getPiece().getColor() == this.getColor())
				break;

			else
			{
				if (state[tempX][tempY].getPiece() instanceof Bishop || state[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}

		int positionX[] = {x+1, x+1, x+2, x+2, x-1, x-1, x-2, x-2};
		int positionY[] = {y-2, y+2, y-1, y+1, y-2, y+2, y-1, y+1};
		for (int i = 0; i < 8; i++)
		{
			if ((positionX[i] >= 0 && positionX[i] < 8 && positionY[i] >= 0 && positionY[i] < 8))
			{
				if (state[positionX[i]][positionY[i]].getPiece() != null && state[positionX[i]][positionY[i]].getPiece().getColor() != this.getColor() && (state[positionX[i]][positionY[i]].getPiece() instanceof Knight))
					return true;
			}
		}

		int positionX2[] = {x+1, x+1, x+1, x, x, x-1, x-1, x-1};
		int positionY2[] = {y-1, y+1, y, y+1, y-1, y+1, y-1, y};
		for (int i = 0; i < 8; i++)
		{
			if ((positionX2[i] >= 0 && positionX2[i] < 8 && positionY2[i] >= 0 && positionY2[i] < 8))
			{
				if (state[positionX2[i]][positionY2[i]].getPiece() != null && state[positionX2[i]][positionY2[i]].getPiece().getColor() != this.getColor() && (state[positionX2[i]][positionY2[i]].getPiece() instanceof King))
						return true;
			}
		}

		if (getColor() == 0)
		{
			if (x > 0 && y > 0 && state[x-1][y-1].getPiece() != null && state[x-1][y-1].getPiece().getColor() == 1 && (state[x-1][y-1].getPiece() instanceof Pawn))
				return true;
			if (x > 0 && y < 7 && state[x-1][y+1].getPiece() != null && state[x-1][y+1].getPiece().getColor() == 1&&(state[x-1][y+1].getPiece() instanceof Pawn))
				return true;
		}

		else
		{
			if (x < 7 && y > 0 && state[x+1][y-1].getPiece() != null && state[x+1][y-1].getPiece().getColor() == 0 && (state[x+1][y-1].getPiece() instanceof Pawn))
				return true;
			if (x < 7 && y < 7 && state[x+1][y+1].getPiece() != null && state[x+1][y+1].getPiece().getColor() == 0 && (state[x+1][y+1].getPiece() instanceof Pawn))
				return true;
		}

    	return false;
    }
}