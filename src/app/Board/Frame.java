package app.Board; /**
 * Created by Ramky Abel on 12/18/2016.
 */

import javax.swing.*;

public class Frame extends JFrame {
    final int height;
    final int width;
    public Frame()
    {
        height = 720;
        width = 720;
    }

    public void run()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add (new Board(width,height));
        setSize(height + 500, width + 50);
        setVisible(true);

    }


}
