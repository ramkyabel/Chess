package app.Board;

/**
 * Created by Ramky Abel on 12/23/2016.
 */
public class Tile
{
    public int x, y;
    public boolean taken;
    String piece;


    Tile (int x, int y)
    {
        this.piece = null;
        this.x = x;
        this.y = y;
        this.taken = false;
    }

    public void setTaken (boolean taken, String piece)
    {
        this.taken = taken;
        this.piece = piece;
    }
}
