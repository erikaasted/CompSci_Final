package chess;

import chess.pieces.King;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * This is the Cell Class. It is the token class of our GUI.
 * There are total of 64 cells that together makes up the Chess Board
 *
 */

public class Cell extends JPanel implements Cloneable
{
	
	//Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	public int x, y;
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	//Constructors
	public Cell(int x, int y, Piece p)
	{		
		this.x = x;
		this.y = y;
		
		setLayout(new BorderLayout());
	
	 	if((x+y) % 2 == 0)
	  		setBackground(new Color(113, 198, 113));
	
		else
	  		setBackground(Color.white);
	 
		if(p != null)
			setPiece(p);
	}
	
	//A constructor that takes a cell as argument and returns a new cell will the same data but different reference
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x = cell.x;
		this.y = cell.y;

		setLayout(new BorderLayout());

		if ((x+y) % 2 == 0)
			setBackground(new Color(113, 198, 113));

		else
			setBackground(Color.white);

		if (cell.getPiece() != null)
			setPiece(cell.getPiece().getCopy());

		else
			piece = null;
	}
	
	public void setPiece(Piece p)    //Function to inflate a cell with a piece
	{
		piece = p;

		ImageIcon img = new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));

		content=new JLabel(img);

		this.add(content);
	}
	
	public void removePiece()      //Function to remove a piece from the cell
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	
	public Piece getPiece()    //returns piece of a particular cell
	{
		return this.piece;
	}
	
	public void select()       //Function to mark a cell indicating it's selection
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected=true;
	}
	
	public boolean isSelected()   //Function to return if the cell is under selection
	{
		return this.isSelected;
	}
	
	public void deselect()      //Function to delselect the cell
	{
		this.setBorder(null);
		this.isSelected=false;
	}
	
	public void setPossibleDestination()     //Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
	public void removePossibleDestination()      //Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
	public boolean isPossibleDestination()    //Function to check if the cell is a possible destination
	{
		return this.ispossibledestination;
	}
	
	public void setCheck()     //Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}

	public boolean isCheck()
	{
		return this.ischeck;
	}
	
	public void removeCheck()   //Function to deselect check
	{
		this.setBorder(null);
		if((x+y)%2==0)
			setBackground(new Color(113,198,113));
		else
			setBackground(Color.white);
		this.ischeck=false;
	}
	
	public boolean isInCheck()    //Function to check if the current cell is in check
	{
		return ischeck;
	}
}