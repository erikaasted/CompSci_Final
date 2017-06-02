package chess.pieces;

import chess.Cell;

import java.util.ArrayList;

public class King extends Piece{
	
	private int x,y; //Extra variables for King class to keep a track of king's position
	
	//King Constructor
	public King(String i,String p,int c,int x,int y)
	{
		setx(x);
		sety(y);
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//general value access functions
	public void setx(int x)
	{
		this.x=x;
	}
	public void sety(int y)
	{
		this.y=y;
	}
	public int getx()
	{
		return x;
	}
	public int gety()
	{
		return y;
	}
	//Move Function for King Overridden from Pieces
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//King can move only one step. So all the adjacent 8 cells have been considered.
		possiblemoves.clear();
		int posx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
		int posy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getPiece()==null||state[posx[i]][posy[i]].getPiece().getColor()!=this.getColor()))
					possiblemoves.add(state[posx[i]][posy[i]]);
		return possiblemoves;
	}
	
	
	
	//Function to check if king is under threat
	//It checks whether there is any piece of opposite color that can attack king for a given board state
	public boolean isindanger(Cell state[][])
    {
		
		//Checking for attack from left,right,up and down
    	for(int i=x+1;i<8;i++)
    	{
    		if(state[i][y].getPiece()==null)
    			continue;
    		else if(state[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=x-1;i>=0;i--)
    	{
    		if(state[i][y].getPiece()==null)
    			continue;
    		else if(state[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y+1;i<8;i++)
    	{
    		if(state[x][i].getPiece()==null)
    			continue;
    		else if(state[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y-1;i>=0;i--)
    	{
    		if(state[x][i].getPiece()==null)
    			continue;
    		else if(state[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	
    	//checking for attack from diagonal direction
    	int tempx=x+1,tempy=y-1;
		while(tempx<8&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy--;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy++;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy--;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x+1;tempy=y+1;
		while(tempx<8&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy++;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		
		//Checking for attack from the Knight of opposite color
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if(state[posx[i]][posy[i]].getPiece()!=null && state[posx[i]][posy[i]].getPiece().getColor()!=this.getColor() && (state[posx[i]][posy[i]].getPiece() instanceof Knight))
				{
					return true;
				}
		
		
		//Checking for attack from the Pawn of opposite color
		int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
		int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
		{
			for(int i=0;i<8;i++)
				if((pox[i]>=0&&pox[i]<8&&poy[i]>=0&&poy[i]<8))
					if(state[pox[i]][poy[i]].getPiece()!=null && state[pox[i]][poy[i]].getPiece().getColor()!=this.getColor() && (state[pox[i]][poy[i]].getPiece() instanceof King))
					{
						return true;
					}
		}
		if(getColor()==0)
		{
			if(x>0&&y>0&&state[x-1][y-1].getPiece()!=null&&state[x-1][y-1].getPiece().getColor()==1&&(state[x-1][y-1].getPiece() instanceof Pawn))
				return true;
			if(x>0&&y<7&&state[x-1][y+1].getPiece()!=null&&state[x-1][y+1].getPiece().getColor()==1&&(state[x-1][y+1].getPiece() instanceof Pawn))
				return true;
		}
		else
		{
			if(x<7&&y>0&&state[x+1][y-1].getPiece()!=null&&state[x+1][y-1].getPiece().getColor()==0&&(state[x+1][y-1].getPiece() instanceof Pawn))
				return true;
			if(x<7&&y<7&&state[x+1][y+1].getPiece()!=null&&state[x+1][y+1].getPiece().getColor()==0&&(state[x+1][y+1].getPiece() instanceof Pawn))
				return true;
		}
    	return false;
    }
}