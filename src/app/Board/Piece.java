package app.Board;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static app.Board.Board.touchedPiece;
import static app.Board.Board.getsEaten;

/**
 * Created by Ramky Abel on 12/18/2016.
 */
public class Piece extends JLabel implements  MouseListener
{

    public int posX;
    public int posY;
    public int width;
    public int height;
    public String name;
    boolean touched;

    Piece(ImageIcon image, String name)
    {
        this.name = name;
        this.touched = false;
        this.setIcon(image);
        this.width = image.getIconWidth();
        this.height = image.getIconHeight();
        posX = 0;
        posY = 0;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println (this.name + " Clicked has been printed.");
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println (this.name + " Pressed has been printed.");
        touched = true;
        if (Board.turn != this.name.charAt(0))
        {
            System.out.println ("not turn");

            if (Board.beingTouched == "") {
             System.out.println ("enters");
                return;
            }
            else //gets eaten
            {
                getsEaten (this);
                super.repaint();
                return;
            }
        }

        touchedPiece (this.name);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
