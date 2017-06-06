package chess;

import chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ListIterator;


public class Main extends JFrame implements MouseListener
{

	//Variable Declaration
	private static final int height = 700, width = 700;
	private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
	private static King wk, bk;
	private Cell square, previous;
	private int chance = 0;
	private Cell boardState[][];
	private ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	private JPanel board = new JPanel(new GridLayout(8,8));
	public static Main Mainboard;
	private Container content;
	static String move;

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

	for (int i=0;i<8;i++)
	{
		wp[i]=new Pawn("WP0"+(i+1),"assets/white_pawn.png",0);
		bp[i]=new Pawn("BP0"+(i+1),"assets/black_pawn.png",1);
	}

	Mainboard = new Main();
	Mainboard.setVisible(true);
	Mainboard.setResizable(false);
	Mainboard.setLocationRelativeTo(null);

	}

	private Main()
	{
		move = "White";
		board = new JPanel(new GridLayout(8, 8));
		board.setMinimumSize(new Dimension(700, 700));

		Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		chess.pieces.Piece piece;
		content = getContentPane();
		setSize(width, height);
		setTitle("Final Project: Chess");
		content.setBackground(Color.black);
		content.setLayout(new BorderLayout());


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

		board.setMinimumSize(new Dimension(700,700));

		content.add(board);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

	// A function to change the chance from White Player to Black Player or vice verse
	// It is made public because it is to be accessed in the Time Class
	public void changeChance()
	{
		if (boardState[getKing(chance).getx()][getKing(chance).gety()].isCheck())
		{
			chance ^= 1;
//			gameEnd();
		}
		if(!destinationlist.isEmpty())
			cleanDestinations(destinationlist);
		if(previous!=null)
			previous.deselect();
		previous=null;
		chance^=1;
	}

	//returns king of either color
	private King getKing(int color)
	{
		if (color == 0)
			return wk;
		else
			return bk;
	}

	//A function to clean the highlights of possible destination cells
    private void cleanDestinations(ArrayList<Cell> destlist)      //Function to clear the last move's destinations
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removePossibleDestination();
    }

    //A function that indicates the possible moves by highlighting the Cells
    private void highlightDestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setPossibleDestination();
    }


  //Function to check if the king will be in danger if the given move is made
    private boolean checkIfKingWillBeEndangered(Cell fromcell, Cell tocell)
    {
    	Cell newboardstate[][] = new Cell[8][8];
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}

    	if(newboardstate[tocell.x][tocell.y].getPiece()!=null)
			newboardstate[tocell.x][tocell.y].removePiece();

		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
		if(newboardstate[tocell.x][tocell.y].getPiece() instanceof King)
		{
			((King)(newboardstate[tocell.x][tocell.y].getPiece())).setx(tocell.x);
			((King)(newboardstate[tocell.x][tocell.y].getPiece())).sety(tocell.y);
		}
		newboardstate[fromcell.x][fromcell.y].removePiece();

		if (((King) (newboardstate[getKing(chance).getx()][getKing(chance).gety()].getPiece())).isindanger(newboardstate))
			return true;
		else
			return false;
    }

    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> filterDestination(ArrayList<Cell> destlist, Cell fromcell)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}

    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getPiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
    		x=getKing(chance).getx();
    		y=getKing(chance).gety();
    		if(newboardstate[fromcell.x][fromcell.y].getPiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((!((King) (newboardstate[x][y].getPiece())).isindanger(newboardstate)))
    			newlist.add(tempc);
    	}
    	return newlist;
    }

    //A Function to filter the possible moves when the king of the current player is under Check
    private ArrayList<Cell> filterForInCheck(ArrayList<Cell> destlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getPiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
    		x=getKing(color).getx();
    		y=getKing(color).gety();
    		if(newboardstate[tempc.x][tempc.y].getPiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getPiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }

    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color)
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{
    			if (boardState[i][j].getPiece()!=null && boardState[i][j].getPiece().getColor()==color)
    			{
    				dlist.clear();
    				dlist=boardState[i][j].getPiece().move(boardState, i, j);
    				dlist= filterForInCheck(dlist,boardState[i][j],color);
    				if(dlist.size()!=0)
    					return false;
    			}
    		}
    	}
    	return true;
    }


    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		square = (Cell)arg0.getSource();

		if (previous == null)
		{
			if (square.getPiece() != null)
			{
				if (square.getPiece().getColor()!= chance)
					return;

				square.select();
				previous= square;
				destinationlist.clear();
				destinationlist= square.getPiece().move(boardState, square.x, square.y);

				if (square.getPiece() instanceof King)
					destinationlist= filterDestination(destinationlist, square);

				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].isCheck())
						destinationlist = new ArrayList<Cell>(filterDestination(destinationlist, square));
					else if(!destinationlist.isEmpty() && checkIfKingWillBeEndangered(square,destinationlist.get(0)))
						destinationlist.clear();
				}

				highlightDestinations(destinationlist);
			}
		}

		else
		{
			if (square.x == previous.x && square.y == previous.y)
			{
				square.deselect();
				cleanDestinations(destinationlist);
				destinationlist.clear();
				previous=null;
			}

			else if (square.getPiece() == null || previous.getPiece().getColor()!= square.getPiece().getColor())
			{
				if (square.isPossibleDestination())
				{
					if (square.getPiece() != null)
						square.removePiece();

					square.setPiece(previous.getPiece());

					if (previous.isCheck())
						previous.removeCheck();

					previous.removePiece();

					if (getKing(chance^1).isindanger(boardState))
					{
						boardState[getKing(chance^1).getx()][getKing(chance^1).gety()].setCheck();

						if (checkmate(getKing(chance^1).getColor()))
						{
							previous.deselect();
							if(previous.getPiece()!=null)
								previous.removePiece();
						}
					}

					if (!getKing(chance).isindanger(boardState))
						boardState[getKing(chance).getx()][getKing(chance).gety()].removeCheck();

					if (square.getPiece() instanceof King)
					{
						((King) square.getPiece()).setx(square.x);
						((King) square.getPiece()).sety(square.y);
					}
					changeChance();

				}

				if (previous != null)
				{
					previous.deselect();
					previous = null;
				}

				cleanDestinations(destinationlist);
				destinationlist.clear();
			}

			else if (previous.getPiece().getColor() == square.getPiece().getColor())
			{
				previous.deselect();
				cleanDestinations(destinationlist);
				destinationlist.clear();
				square.select();
				previous= square;
				destinationlist= square.getPiece().move(boardState, square.x, square.y);

				if (square.getPiece() instanceof King)
					destinationlist= filterDestination(destinationlist, square);

				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].isCheck())
						destinationlist = new ArrayList<Cell>(filterDestination(destinationlist, square));
					else if(destinationlist.isEmpty()==false && checkIfKingWillBeEndangered(square,destinationlist.get(0)))
						destinationlist.clear();
				}
				highlightDestinations(destinationlist);
			}
		}
		if (square.getPiece()!= null && square.getPiece() instanceof King)
		{
			((King) square.getPiece()).setx(square.x);
			((King) square.getPiece()).sety(square.y);
		}
	}

    //Other Irrelevant abstract function. Only the Click Event is captured.
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}