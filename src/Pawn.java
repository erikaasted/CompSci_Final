import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pawn extends ChessPiece
{
    String type;
    int movetally;

    Pawn (String col, int row, String color)
    {
        super(col, row, color);
        type = "Pawn";
    }

    public void move()
    {
        if (movetally == 0)
        {
            super.row += 2;
        }

        else
        {
            super.row ++;
        }
    }

//    BufferedImage img = null;
//                try
//    {
//        img = ImageIO.read(new File("CompSci_Final/Chess_Pieces/Black_Pawn.png"));
//    }
//    catch (IOException e) { }
}
