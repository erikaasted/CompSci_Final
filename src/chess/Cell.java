package chess;

import chess.pieces.King;
import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;


public class Cell extends JPanel implements Cloneable
{
	private boolean isPossibleDestination;
	private JLabel content;
	private Piece piece;
	public int x, y;
	private boolean isSelected = false;
	private boolean ischeck = false;


	Cell (int x, int y, Piece piece)
	{		
		this.x = x;
		this.y = y;
		
		setLayout(new BorderLayout());
	
	 	if((x + y) % 2 == 0)
	  		setBackground(new Color(112, 93, 20));
	
		else
	  		setBackground(new Color(77, 57, 21));
	 
		if(piece != null)
			setPiece(piece);
	}


	Cell (Cell cell) throws CloneNotSupportedException
	{
		this.x = cell.x;
		this.y = cell.y;

		setLayout(new BorderLayout());

		if ((x + y) % 2 == 0)
			setBackground(new Color(112, 93, 20));

		else
			setBackground(new Color(77, 57, 21));

		if (cell.getPiece() != null)
			setPiece(cell.getPiece().getCopy());

		else
			piece = null;
	}
	
	void setPiece(Piece piece)
	{
		this.piece = piece;

		ImageIcon img = new javax.swing.ImageIcon(this.getClass().getResource(piece.getPath()));

		content = new JLabel(img);

		this.add(content);
	}
	
	void deletePiece()
	{
		if (piece instanceof King)
		{
			piece = null;
			this.remove(content);
		}

		else
		{
			piece = null;
			this.remove(content);
		}
	}
	
	
	public Piece getPiece()
	{
		return this.piece;
	}
	
	void select()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.isSelected = true;
	}
	
	public boolean isSelected()
	{
		return this.isSelected;
	}
	
	void deselect()
	{
		this.setBorder(null);
		this.isSelected = false;
	}
	
	void setPossibleDestination()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.green, 4));
		this.isPossibleDestination = true;
	}
	
	void removePossibleDestination()
	{
		this.setBorder(null);
		this.isPossibleDestination = false;
	}
	
	boolean isPossibleDestination()
	{
		return this.isPossibleDestination;
	}
	
	void setCheck()
	{
		this.setBackground(Color.RED);
		this.ischeck = true;
	}

	boolean isCheck()
	{
		return this.ischeck;
	}
	
	void removeCheck()
	{
		this.setBorder(null);

		if((x + y) % 2 == 0)
			setBackground(new Color(112, 93, 20));

		else
			setBackground(new Color(77, 57, 21));

		this.ischeck = false;
	}
	
	public boolean getCheckStatus()
	{
		return ischeck;
	}
}