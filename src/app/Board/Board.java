package app.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by Ramky Abel on 12/18/2016.
 */
class Board extends JPanel implements MouseListener {
    static public char turn;

    static Player playerBlack;
    static Player playerWhite;


    static Tile [][] tiles;

    static public int width, height, relativeSize, pieceOffset;
    static public int blackGraveyard;
    static public int whiteGraveyard;
    public static String beingTouched;

    Board (int w, int h)
    {
        blackGraveyard = 0;
        whiteGraveyard = 0;

        turn = 'b';
        pieceOffset = 10;

        beingTouched = "";
        this.width = w;
        this.height = h;
        this.setLayout(null);
        this.relativeSize = w / 8;
        tiles = new Tile[8][8];

        playerBlack = new Player("black",relativeSize, pieceOffset);
        playerWhite = new Player("white",relativeSize, pieceOffset);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
            {
                tiles[i][j] = new Tile(i*relativeSize,j * relativeSize);
            }

        this.add (playerBlack.King);
        tiles [playerBlack.King.posY / relativeSize][playerBlack.King.posX / relativeSize].
                setTaken(true, playerBlack.King.name);
        this.add (playerBlack.Queen);
        tiles [playerBlack.Queen.posY / relativeSize][playerBlack.Queen.posX / relativeSize].
                setTaken(true, playerBlack.Queen.name);
        this.add (playerWhite.King);
        tiles [playerWhite.King.posY / relativeSize][playerWhite.King.posX / relativeSize].
                setTaken(true, playerWhite.King.name);
        this.add (playerWhite.Queen);
        tiles [playerWhite.Queen.posY / relativeSize][playerWhite.Queen.posX / relativeSize].
                setTaken(true, playerWhite.Queen.name);

        for (int i = 0; i < 2; i++)
        {

            this.add (playerBlack.Knights[i]);
            tiles [playerBlack.Knights[i].posY / relativeSize][playerBlack.Knights[i].posX / relativeSize].
                    setTaken(true, playerBlack.Knights[i].name);
            this.add (playerBlack.Bishops[i]);
            tiles [playerBlack.Bishops[i].posY / relativeSize][playerBlack.Bishops[i].posX / relativeSize].
                    setTaken(true, playerBlack.Bishops[i].name);
            this.add (playerBlack.Rooks[i]);
            tiles [playerBlack.Rooks[i].posY / relativeSize][playerBlack.Rooks[i].posX / relativeSize].
                    setTaken(true, playerBlack.Rooks[i].name);

            this.add (playerWhite.Knights[i]);
            tiles [playerWhite.Knights[i].posY / relativeSize][playerWhite.Knights[i].posX / relativeSize].
                    setTaken(true, playerWhite.Knights[i].name);
            this.add (playerWhite.Bishops[i]);
            tiles [playerWhite.Bishops[i].posY / relativeSize][playerWhite.Bishops[i].posX / relativeSize].
                    setTaken(true, playerWhite.Bishops[i].name);
            this.add (playerWhite.Rooks[i]);
            tiles [playerWhite.Rooks[i].posY / relativeSize][playerWhite.Rooks[i].posX / relativeSize].
                    setTaken(true, playerWhite.Rooks[i].name);

        }

        //Pawns
        for (int i = 0; i < 8; i++)
        {

            this.add(playerBlack.Pawns [i]);
            tiles [playerBlack.Pawns[i].posY / relativeSize][playerBlack.Pawns[i].posX / relativeSize].
                    setTaken(true, playerBlack.Pawns[i].name);

            this.add(playerWhite.Pawns [i]);
            tiles [playerWhite.Pawns[i].posY / relativeSize][playerWhite.Pawns[i].posX / relativeSize].
                    setTaken(true, playerWhite.Pawns[i].name);

        }
        addMouseListener(this);
    }

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        boolean brown = true;

        for (int i = 0; i < 8; i++)
        {
            if (brown)
                brown = false;
            else
                brown = true;
            for (int j = 0; j < 8; j++)
            {
                if (brown == true) {
                    g.setColor(new Color(100, 51, 0));
                    brown = false;
                }
                else
                {
                    g.setColor(new Color(193, 125, 51));
                    brown = true;
                }
                g.fillRect (j * relativeSize,i * relativeSize,relativeSize,relativeSize);
            }
        }
        g.setColor(Color.lightGray);
        g.fillRect(8 * relativeSize, 0, 500, 720);

        //true = black
        if (turn == 'b')
        {
            g.setColor(Color.black);
        }
        else
            g.setColor(Color.white);

        g.fillOval(800, 10, 50,50);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println ("board mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println ("board mouse pressed");

        if (beingTouched != "") {
            if (beingTouched.charAt(0) != turn)
            {
                beingTouched = "";
                return;
            }
            Piece obj = null;

            int posX = e.getX();
            int posY = e.getY();
            boolean UpdateTile = true;

            //Choosing piece outside board. Don't update any tiles.
            if (posX > this.width || posY > this.height || posX < 0 || posY < 0)
            {
                UpdateTile = false;
            }
            if (!(e.getX() % width / 8 == 0)) {
                posX = Math.round(e.getX() / (width / 8) * (width / 8));
            }
            if (!(e.getY() % width / 8 == 0)) {
                posY = Math.round(e.getY() / (width / 8) * (width / 8));
            }


            obj = getObjPiece (obj, beingTouched);

            //Changes turn
            if (beingTouched.charAt(0) == 'b')
                turn = 'w';
            else
                turn = 'b';
            repaint();


            System.out.println (obj == null);

            int relativePosY = obj.posY / relativeSize;
            int relativePosX = obj.posX / relativeSize;

            if (! (obj.posX > relativeSize * 7  || obj.posY > relativeSize * 7)) {
                tiles [relativePosY][relativePosX].setTaken(false, null);
            }
            obj.posX = posX + pieceOffset;
            obj.posY = posY + pieceOffset;

            obj.setBounds(obj.posX, obj.posY, obj.getIcon().getIconWidth(), obj.getIcon().getIconHeight());

            if (UpdateTile)
            {
                if (tiles[obj.posY / relativeSize][obj.posX / relativeSize].taken)
                {
                    Piece eatenPiece = null;
                    eatenPiece = getObjPiece(eatenPiece, tiles[obj.posY / relativeSize][obj.posX / relativeSize].piece);
                    if (eatenPiece.name.charAt(0) == 'w') {
                        eatenPiece.posY = 7 * relativeSize;
                        eatenPiece.posX = whiteGraveyard++ * relativeSize + width;
                    }
                    else
                    {
                        eatenPiece.posY = 0;
                        eatenPiece.posX = blackGraveyard++ * relativeSize + width;
                    }

                    eatenPiece.setBounds(eatenPiece.posX, eatenPiece.posY, eatenPiece.getWidth(), eatenPiece.getHeight());

                    System.out.println ("Entrooo "  + eatenPiece.name);
                }
                tiles[obj.posY / relativeSize][obj.posX / relativeSize].setTaken(true, obj.name);

            }
            obj.touched = false;
            beingTouched = "";
//            System.out.print(tiles[obj.posY / relativeSize][obj.posX / relativeSize].piece);
        }

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++) {
                System.out.print(tiles[i][j].piece + " ");
            }
            System.out.println();

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println ("board mouse release");

    }

    static public Piece getObjPiece(Piece obj, String beingTouched)
    {
        switch (beingTouched)
        {
            case "bQ":
                obj = playerBlack.Queen;
                break;
            case "bK":
                obj = playerBlack.King;
                break;
            case "bK0":
                obj = playerBlack.Knights [0];
                break;
            case "bK1":
                obj = playerBlack.Knights [1];
                break;
            case "bR0":
                obj = playerBlack.Rooks [0];
                break;
            case "bR1":
                obj = playerBlack.Rooks [1];
                break;
            case "bB0":
                obj = playerBlack.Bishops [0];
                break;
            case "bB1":
                obj = playerBlack.Bishops [1];
                break;

            case "wQ":
                obj = playerWhite.Queen;
                break;
            case "wK":
                obj = playerWhite.King;
                break;
            case "wK0":
                obj = playerWhite.Knights [0];
                break;
            case "wK1":
                obj = playerWhite.Knights [1];
                break;
            case "wR0":
                obj = playerWhite.Rooks [0];
                break;
            case "wR1":
                obj = playerWhite.Rooks [1];
                break;
            case "wB0":
                obj = playerWhite.Bishops [0];
                break;
            case "wB1":
                obj = playerWhite.Bishops [1];
                break;
        }

        if (obj == null)
        {
            if (beingTouched.charAt(0) == 'b')
            {
                obj = playerBlack.Pawns [beingTouched.charAt(beingTouched.length () - 1) - '0'];
            }
            else
                obj = playerWhite.Pawns [beingTouched.charAt(beingTouched.length () - 1) - '0'];

        }

        return obj;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public static void touchedPiece (String piece)
    {
        beingTouched = piece;
        System.out.println (piece + " is being pressed.");
    }

    public static void getsEaten (Piece eatenPiece)
    {
        Piece eatingPiece = null;
        eatingPiece = getObjPiece(eatingPiece, beingTouched);
        beingTouched = "";
        System.out.println ("The eating piece is " + eatingPiece.name);
        eatingPiece.setBounds(eatenPiece.posX, eatenPiece.posY, eatingPiece.getWidth(),eatingPiece.getHeight());

        if (eatenPiece.name.charAt(0) == 'w') {
            turn = 'w';
            eatenPiece.posY = 7 * relativeSize;
            eatenPiece.posX = whiteGraveyard++ * relativeSize + width;
        }
        else
        {
            turn = 'b';
            eatenPiece.posY = 0;
            eatenPiece.posX = blackGraveyard++ * relativeSize + width;
        }

        eatenPiece.setBounds(eatenPiece.posX,eatenPiece.posY,eatenPiece.getWidth(),eatenPiece.getHeight());
//        System.out.println (piece + " is being eaten.");
    }
}
