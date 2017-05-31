import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Board
{

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel header = new JLabel("Welcome to our Final Project...");
    private static final String columnlabels = "ABCDEFGH";

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
        tools.add(new JButton("New Game"));
        tools.add(new JButton("Undo"));
        tools.addSeparator();
        tools.add(new JButton("Resign"));
        tools.addSeparator();
        tools.add(header);

        gui.add(new JLabel(""), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++)
        {
            for (int j = 0; j < chessBoardSquares[i].length; j++)
            {
                JButton button = new JButton();
                button.setMargin(buttonMargin);


                ImageIcon icon = new ImageIcon(new BufferedImage(45, 45, BufferedImage.TYPE_INT_ARGB));
                button.setIcon(icon);

                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
                {
                    button.setBackground(Color.WHITE);
                }

                else
                {
                    button.setBackground(Color.BLACK);
                }

                chessBoardSquares[j][i] = button;
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

        chessBoard.add(new JLabel("extra square"));

        for (int i = 0; i < 8; i++)
        {
            chessBoard.add(new JLabel(columnlabels.substring(i, i + 1), SwingConstants.CENTER));
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
                Board cb = new Board();

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
