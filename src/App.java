import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class App {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Squares");
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Poly square = new Poly();
        frame.add(square);
        frame.setVisible(true);
    }
}

class Poly extends JPanel {
    private int[] x = {
            0, 0, 50, 50
    };
    private int[] y = {
            0, 50, 50, 0
    };
    Poly(){

    }
    public void paint(Graphics g){
        g.drawPolygon(x, y, 4);
    }
}