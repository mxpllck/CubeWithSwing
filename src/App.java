//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import static java.awt.event.KeyEvent.VK_UP;
//import static java.awt.event.KeyEvent.VK_W;
//import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
//
//public class App {
//	//x y z, z is up
//	private int[] playerPos = {
//			0, 0, 0
//	};
//	//3 numbers define a point on the sphere given by
//	//1=x^2+y^2+z^2
//	private int[] playerDirection = {
//			1, 0, 0
//	};
//	public static void main(String[] args)
//    {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		JFrame frame = new JFrame("Cube");
//        frame.setSize(400, 400);
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		//just example values.  Will contain data later.
//		int[] xCoords = {0, 0, 50, 50};
//		int[] yCoords = {0, 50, 50, 0};
//		Poly square = new Poly(xCoords, yCoords);
//
//		frame.addKeyListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				if ((e.getKeyCode() == VK_W)) {
//					System.out.println("w key released.");
//					frame.add(square);
//					frame.revalidate();
//					frame.repaint();
//				}
//				//else if (e.getKeyChar() == 'a') {
////
////				} else if (e.getKeyChar() == 's')
////
////				} else if (e.getKeyChar() == 'd') {
////
////				}
//				else if (e.getKeyCode() == VK_UP) {
//					System.out.println("up arrow released");
//					//frame.remove(square);
//					frame.repaint();
//				}
//			}
//		});
//    }
//}
//
//class Poly extends JPanel {
//	private int[] x;
//	private int[] y;
//
//	Poly(int[] xCoords, int[] yCoords) {
//		x = xCoords;
//		y = yCoords;
//    }
//    void update(int[] cubeXCoords, int[] cubeYCoords, int[] cubeZCoords, int[] playerXYZ, int[] playerAngle){
//
//	}
//    public void paint(Graphics g){
//        g.drawPolygon(x, y, 4);
//    }
//}

import javax.swing.*;

public class App extends JFrame {
	public static void main(String[] args) {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		App app = new App();
	}

	private App() {
		setTitle("Cube");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
