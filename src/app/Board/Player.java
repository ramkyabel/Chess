package app.Board;

import javax.swing.*;

/**
 * Created by Ramky Abel on 12/29/2016.
 */

//incorporated into Board
public class Player
{
    Piece Pawns [];
    Piece Rooks [];
    Piece Bishops [];
    Piece Knights [];
    Piece King;
    Piece Queen;
    String color;

    Player (String color, int relativeSize, int pieceOffset)
    {
        this.color = color;
        int colorPos = (color == "black" ? 0 : 7);

        Rooks = new Piece[2];

        Bishops = new Piece[2];

        Knights = new Piece[2];

        Pawns = new Piece[8];
        King = new Piece( new ImageIcon(
                "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "King.png"),
                color.charAt(0) + "K");
        Queen = new Piece( new ImageIcon(
                "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "Queen.png"),
                color.charAt(0) + "Q");

        King.posY = colorPos * relativeSize + pieceOffset;
        King.posX = 4 * relativeSize + pieceOffset;
        King.setBounds( King.posX, King.posY,
                King.getIcon().getIconWidth(), King.getIcon().getIconHeight());


        Queen.posY = colorPos * relativeSize + pieceOffset;
        Queen.posX = 3 * relativeSize + pieceOffset;
        Queen.setBounds( Queen.posX, Queen.posY,
                Queen.getIcon().getIconWidth(), Queen.getIcon().getIconHeight());

        for (int i = 0; i < 2; i++)
        {
            //rooks
            Rooks [i] = new Piece(new ImageIcon (
                    "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "Rook.png"),
                    color.charAt(0) + "R" + i);

            Rooks[i].posY = colorPos * relativeSize + pieceOffset;
            Rooks [i].posX =  (7 * ( i + 1) - 7) * relativeSize + pieceOffset;

            Rooks [i].setBounds( Rooks [i].posX, Rooks [i].posY,
                    Rooks [i].getIcon().getIconWidth(), Rooks [i].getIcon().getIconHeight());

            //Bishops
            Bishops [i] = new Piece(new ImageIcon (
                    "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "Bishop.png"),
                    color.charAt(0) + "B" + i);

            Bishops[i].posY = colorPos * relativeSize + pieceOffset;
            Bishops [i].posX = ((3 * (i + 1) - 1) * relativeSize + pieceOffset);
            Bishops [i].setBounds( Bishops [i].posX, Bishops [i].posY,
                    Bishops [i].getIcon().getIconWidth(), Bishops [i].getIcon().getIconHeight());

            //Knights
            Knights [i] = new Piece(new ImageIcon (
                    "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "Knight.png"),
                    color.charAt(0) + "K" + i);
            Knights[i].posY = colorPos * relativeSize + pieceOffset;
            Knights [i].posX = ((5 * (i + 1) - 4) * relativeSize + pieceOffset);
            Knights [i].setBounds( Knights [i].posX, Knights [i].posY,
                    Knights [i].getIcon().getIconWidth(), Knights [i].getIcon().getIconHeight());

        }
        System.out.println (color + " " + colorPos) ;


        for (int i = 0; i < 8; i++)
        {
            Pawns [i] = new Piece(new ImageIcon (
                "C:\\Users\\Ramky Abel\\IdeaProjects\\Chess\\src\\app\\images\\" + color + "Pawn.png"),
                    color.charAt(0) + "P" + i);
            Pawns [i].posX = i * relativeSize + pieceOffset;
            Pawns [i].posY = (color == "black" ? 1 : 6) * relativeSize + pieceOffset;
            Pawns [i].setBounds( Pawns [i].posX,Pawns [i].posY,
                    Pawns [i].getIcon().getIconWidth(), Pawns [i].getIcon().getIconHeight());
        }

    }

}
