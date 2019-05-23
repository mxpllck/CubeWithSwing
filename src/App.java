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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;

public class App extends JFrame {
	public static void main(String[] args) {
		App app = new App();
	}

	private App() {
		setTitle("Cube");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		addKeyListener(new KeyListener() {
			//these two are empty and do not trigger anything.
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			//keyReleased is overridden to allow for movement and panning controls.
			@Override
			public void keyReleased(KeyEvent e) {
				releasedKey(e);
			}
		});
		Cube cube = new Cube();
		add(cube);

	}

	//used for overloading KeyListener.keyReleased() for a specific instance in the App constructor.
	private void releasedKey(KeyEvent e) {
		if ((e.getKeyCode() == VK_W)) {
			System.out.println("w key released.");
		} else if (e.getKeyCode() == VK_S) {
			System.out.println("s key released.");
		} else if (e.getKeyCode() == VK_A) {
			System.out.println("a key released.");
		} else if (e.getKeyCode() == VK_D) {
			System.out.println("d key released.");
		} else if (e.getKeyCode() == VK_UP) {
			System.out.println("up arrow released");
		} else if (e.getKeyCode() == VK_DOWN) {
			System.out.println("down arrow released");
		} else if (e.getKeyCode() == VK_LEFT) {
			System.out.println("left arrow released");
		} else if (e.getKeyCode() == VK_RIGHT) {
			System.out.println("right arrow released");
		}
		revalidate();
		repaint();
	}
}

//holds cube coordinates, the update function changes displayX and displayY in keeping with the player angle and movement
class Cube extends JPanel {
	//hidden coords for the 3d cube
	private int[] cubeX = {0, 100, 100, 0,};
	private int[] cubeY = {0, 0, 100, 100,};
	private int[] cubeZ = {0, 0, 0, 0, 100, 100, 100, 100};

	//available for printing onto a 2d screen; this is the projection
	private int[] displayX = {0, 100, 100, 0,};
	private int[] displayY = {0, 0, 100, 100,};

	void update(Player player) {

	}

	public void paint(Graphics g) {
		g.drawPolygon(displayX, displayY, 4);
	}
}

class Player {
	public int[] playerXYZ = {0, 0, 0};
	public int[] playerDirXYZ = {0, 1, 0};
}
