package chess;

import chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ListIterator;


public class ChessBoard extends JFrame implements MouseListener
{
	private static final int height = 700, width = 700;
	private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
	private static King wk, bk;
	private Cell previous;
	private int chance = 0;
	private Cell boardState[][];
	private ArrayList<Cell> destoList = new ArrayList<Cell>();
	private static String move;

	public static void main(String[] args)
	{

		wr01 = new Rook("WR01","assets/white_rook.png",0);
		wr02 = new Rook("WR02","assets/white_rook.png",0);
		br01 = new Rook("BR01","assets/black_rook.png",1);
		br02 = new Rook("BR02","assets/black_rook.png",1);
		wk01 = new Knight("WK01","assets/white_knight.png",0);
		wk02 = new Knight("WK02","assets/white_knight.png",0);
		bk01 = new Knight("BK01","assets/black_knight.png",1);
		bk02 = new Knight("BK02","assets/black_knight.png",1);
		wb01 = new Bishop("WB01","assets/white_bishop.png",0);
		wb02 = new Bishop("WB02","assets/white_bishop.png",0);
		bb01 = new Bishop("BB01","assets/black_bishop.png",1);
		bb02 = new Bishop("BB02","assets/black_bishop.png",1);
		wq = new Queen("WQ","assets/white_queen.png",0);
		bq = new Queen("BQ","assets/black_queen.png",1);
		wk = new King("WK","assets/white_king.png",0,7,3);
		bk = new King("BK","assets/black_king.png",1,0,3);
		wp = new Pawn[8];
		bp = new Pawn[8];

	for (int i = 0; i < 8; i++)
	{
		wp[i] = new Pawn("WP0" + (i+1), "assets/white_pawn.png", 0);
		bp[i] = new Pawn("BP0" + (i+1), "assets/black_pawn.png", 1);
	}

	ChessBoard mainboard = new ChessBoard();
	mainboard.setVisible(true);
	mainboard.setResizable(false);
	mainboard.setLocationRelativeTo(null);

	}

	private ChessBoard()
	{
		move = "White";
		JPanel board = new JPanel(new GridLayout(8, 8));
		board.setMinimumSize(new Dimension(700, 700));

		Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		chess.pieces.Piece piece;
		Container window = getContentPane();
		setSize(width, height);
		setTitle("Final Project: Chess");
		window.setBackground(Color.black);
		window.setLayout(new BorderLayout());

		boardState = new Cell[8][8];

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				piece = null;
				if (i == 0 && j == 0)
					piece = br01;
				else if (i == 0 && j == 7)
					piece = br02;
				else if (i == 7 && j == 0)
					piece = wr01;
				else if (i == 7 && j == 7)
					piece = wr02;
				else if (i == 0 && j == 1)
					piece = bk01;
				else if (i == 0 && j == 6)
					piece = bk02;
				else if (i == 7 && j == 1)
					piece = wk01;
				else if (i == 7 && j == 6)
					piece = wk02;
				else if (i == 0 && j == 2)
					piece = bb01;
				else if (i == 0 && j == 5)
					piece = bb02;
				else if (i == 7 && j == 2)
					piece = wb01;
				else if (i == 7 && j == 5)
					piece = wb02;
				else if (i == 0 && j == 3)
					piece = bk;
				else if (i == 0 && j == 4)
					piece = bq;
				else if (i == 7 && j == 3)
					piece = wk;
				else if (i == 7 && j == 4)
					piece = wq;
				else if (i == 1)
					piece = bp[j];
				else if (i == 6)
					piece = wp[j];

				cell = new Cell(i, j, piece);
				cell.addMouseListener(this);
				board.add(cell);
				boardState[i][j] = cell;
			}
		}

		board.setMinimumSize(new Dimension(700, 700));

		window.add(board);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

	private void changeChance()
	{
		if (boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
		{
			chance ^= 1;
		}

		if (!destoList.isEmpty())
			cleanDestinations(destoList);

		if (previous != null)
			previous.deselect();

		previous = null;
		chance ^= 1;
	}

	private King getKing(int color)
	{
		if (color == 0)
			return wk;
		else
			return bk;
	}

    private void cleanDestinations(ArrayList<Cell> destinationlist)
    {

		for (Cell aDestinationlist : destinationlist)
			aDestinationlist.removePossibleDestination();
    }

    private void highlightDestinations(ArrayList<Cell> destinationlist)
    {
		for (Cell aDestinationlist : destinationlist)
			aDestinationlist.setPossibleDestination();
    }

    private boolean checkIfKingWillBeEndangeredByCurrentMove(Cell fromcell, Cell tocell)
    {
    	Cell newboardstate[][] = new Cell[8][8];

    	for(int i = 0; i < 8; i++)
    	{
    		for(int j = 0; j < 8; j++)
    		{
    			try
				{
					newboardstate[i][j] = new Cell(boardState[i][j]);
				}
				catch (CloneNotSupportedException e)
				{
					e.printStackTrace();
					System.out.println("Cloning Error");
				}
    		}
    	}

    	if (newboardstate[tocell.x][tocell.y].getPiece() != null)
			newboardstate[tocell.x][tocell.y].deletePiece();

		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());

		if (newboardstate[tocell.x][tocell.y].getPiece() instanceof King)
		{
			((King) (newboardstate[tocell.x][tocell.y].getPiece())).setX(tocell.x);
			((King) (newboardstate[tocell.x][tocell.y].getPiece())).setY(tocell.y);
		}

		newboardstate[fromcell.x][fromcell.y].deletePiece();

		if (((King) (newboardstate[getKing(chance).getX()][getKing(chance).getY()].getPiece())).kingIsInDanger(newboardstate))
			return true;

		else
			return false;
    }

    private ArrayList<Cell> filterDestination(ArrayList<Cell> destinationlist, Cell fromcell)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> iterator = destinationlist.listIterator();
    	int x, y;

    	while (iterator.hasNext())
    	{
    		for(int i = 0; i < 8; i++)
    		{
				for (int j = 0; j < 8; j++)
				{
					try
					{
						newboardstate[i][j] = new Cell(boardState[i][j]);
					}
					catch (CloneNotSupportedException e)
					{
						e.printStackTrace();
					}
				}
			}

    		Cell tempcell = iterator.next();

    		if (newboardstate[tempcell.x][tempcell.y].getPiece() != null)
    			newboardstate[tempcell.x][tempcell.y].deletePiece();

    		newboardstate[tempcell.x][tempcell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());

    		x = getKing(chance).getX();
    		y = getKing(chance).getY();

    		if(newboardstate[fromcell.x][fromcell.y].getPiece() instanceof King)
    		{
    			((King)(newboardstate[tempcell.x][tempcell.y].getPiece())).setX(tempcell.x);
    			((King)(newboardstate[tempcell.x][tempcell.y].getPiece())).setY(tempcell.y);
    			x = tempcell.x;
    			y = tempcell.y;
    		}

    		newboardstate[fromcell.x][fromcell.y].deletePiece();

    		if ((!((King) (newboardstate[x][y].getPiece())).kingIsInDanger(newboardstate)))
    			newlist.add(tempcell);
    	}
    	return newlist;
    }

    private ArrayList<Cell> filterMovesWhenInCheck(ArrayList<Cell> destinationlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> iterator = destinationlist.listIterator();
    	int x, y;

    	while (iterator.hasNext())
    	{
    		for(int i = 0; i < 8; i++)
			{
        		for (int j = 0; j < 8; j++)
        		{
        			try
					{
						newboardstate[i][j] = new Cell(boardState[i][j]);
					}
					catch (CloneNotSupportedException e)
					{
						e.printStackTrace();
					}
        		}
			}

    		Cell tempcell = iterator.next();

    		if(newboardstate[tempcell.x][tempcell.y].getPiece() != null)
    			newboardstate[tempcell.x][tempcell.y].deletePiece();

    		newboardstate[tempcell.x][tempcell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());

    		x = getKing(color).getX();
    		y = getKing(color).getY();

    		if (newboardstate[tempcell.x][tempcell.y].getPiece() instanceof King)
    		{
    			((King) (newboardstate[tempcell.x][tempcell.y].getPiece())).setX(tempcell.x);
    			((King) (newboardstate[tempcell.x][tempcell.y].getPiece())).setY(tempcell.y);
    			x = tempcell.x;
    			y = tempcell.y;
    		}

    		newboardstate[fromcell.x][fromcell.y].deletePiece();

    		if ((!((King) (newboardstate[x][y].getPiece())).kingIsInDanger(newboardstate)))
    			newlist.add(tempcell);
    	}
    	return newlist;
    }

    private boolean checkmate(int color)
    {
    	ArrayList<Cell> destinationlist = new ArrayList<Cell>();

    	for (int i = 0; i < 8; i++)
    	{
    		for (int j = 0; j < 8; j++)
    		{
    			if (boardState[i][j].getPiece() != null && boardState[i][j].getPiece().getColor() == color)
    			{
    				destinationlist.clear();
    				destinationlist = boardState[i][j].getPiece().move(boardState, i, j);
    				destinationlist = filterMovesWhenInCheck(destinationlist, boardState[i][j], color);

    				if (destinationlist.size() != 0)
    					return false;
    			}
    		}
    	}

    	return true;
    }

	public void mouseClicked(MouseEvent arg0)
	{
		Cell square = (Cell) arg0.getSource();

		if (previous == null)
		{
			if (square.getPiece() != null)
			{
				if (square.getPiece().getColor() != chance)
					return;

				square.select();
				previous = square;
				destoList.clear();
				destoList = square.getPiece().move(boardState, square.x, square.y);

				if (square.getPiece() instanceof King)
					destoList = filterDestination(destoList, square);

				else
				{
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
						destoList = new ArrayList<Cell>(filterDestination(destoList, square));

					else if(!destoList.isEmpty() && checkIfKingWillBeEndangeredByCurrentMove(square, destoList.get(0)))
						destoList.clear();
				}

				highlightDestinations(destoList);
			}
		}

		else
		{
			if (square.x == previous.x && square.y == previous.y)
			{
				square.deselect();
				cleanDestinations(destoList);
				destoList.clear();
				previous = null;
			}

			else if (square.getPiece() == null || previous.getPiece().getColor() != square.getPiece().getColor())
			{
				if (square.isPossibleDestination())
				{
					if (square.getPiece() != null)
						square.deletePiece();

					square.setPiece(previous.getPiece());

					if (previous.isCheck())
						previous.removeCheck();

					previous.deletePiece();

					if (getKing(chance ^ 1).kingIsInDanger(boardState))
					{
						boardState[getKing(chance ^ 1).getX()][getKing(chance ^ 1).getY()].setCheck();

						if (checkmate(getKing(chance ^ 1).getColor()))
						{
							previous.deselect();

							if(previous.getPiece() != null)
								previous.deletePiece();
						}
					}

					if (!getKing(chance).kingIsInDanger(boardState))
						boardState[getKing(chance).getX()][getKing(chance).getY()].removeCheck();

					if (square.getPiece() instanceof King)
					{
						((King) square.getPiece()).setX(square.x);
						((King) square.getPiece()).setY(square.y);
					}

					changeChance();
				}

				if (previous != null)
				{
					previous.deselect();
					previous = null;
				}

				cleanDestinations(destoList);
				destoList.clear();
			}

			else if (previous.getPiece().getColor() == square.getPiece().getColor())
			{
				previous.deselect();
				cleanDestinations(destoList);
				destoList.clear();
				square.select();
				previous = square;
				destoList = square.getPiece().move(boardState, square.x, square.y);

				if (square.getPiece() instanceof King)
					destoList = filterDestination(destoList, square);

				else
				{
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
						destoList = new ArrayList<Cell>(filterDestination(destoList, square));
					else if(!destoList.isEmpty() && checkIfKingWillBeEndangeredByCurrentMove(square, destoList.get(0)))
						destoList.clear();
				}

				highlightDestinations(destoList);
			}
		}

		if (square.getPiece() != null && square.getPiece() instanceof King)
		{
			((King) square.getPiece()).setX(square.x);
			((King) square.getPiece()).setY(square.y);
		}
	}

    //unused mouse classes
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}