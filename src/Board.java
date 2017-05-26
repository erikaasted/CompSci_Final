import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.*;
import javax.imageio.*

public class Board
{

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel message = new JLabel("Welcome to our Final Project...");
    private static final String COLS = "ABCDEFGH";

    Board()
    {
        startGUI();
    }

    public final void startGUI()
    {
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New Game")); // TODO - add functionality
        tools.add(new JButton("Undo")); // TODO - add functionality
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
            for (int j = 0; j < chessBoardSquares[i].length; j++)
            {
                JButton b = new JButton();
                b.setMargin(buttonMargin);

//                ImageIcon icon = new ImageIcon(new BufferedImage(45, 45, BufferedImage.TYPE_INT_ARGB));

                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File("strawberry.jpg"));
                } catch (IOException e) {
                }



                ImageIcon icon = new ImageIcon(new BufferedImage())
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
                {
                    b.setBackground(Color.WHITE);
                }
                else
                {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[j][i] = b;
            }
        }



        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                switch (j)
                {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[j][i]);
                }
            }
        }

        chessBoard.add(new JLabel(""));

        for (int i = 0; i < 8; i++)
        {
            chessBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
    }

    public final JComponent getChessBoard()
    {
        return chessBoard;
    }

    public final JComponent getGui()
    {
        return gui;
    }

    public static void main(String[] args)
    {
        Runnable r = new Runnable()
        {

            @Override
            public void run()
            {
                Board cb =
                        new Board();

                JFrame f = new JFrame("Final Project: Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
