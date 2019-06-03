import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

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
		Cube cube = new Cube();
		add(cube);
		Player player = new Player();
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
				player.update(e);
				revalidate();
				repaint();
			}
		});
	}
}

//holds cube coordinates, the update function changes displayX and displayY in keeping with the player angle and movement
class Cube extends JPanel {
	//hidden coords for the 3d cube, which is composed of two squares, one above the other
	//first square: (0, 0, 0), (0, 100, 0), (100, 100, 0), (100, 0, 0)
	//second square: (0, 0, 100), (0, 100, 0), (100, 100, 0), (100, 0, 0)

	private final int[] objectiveXCoords = {0, 0, 100, 100, 0, 0, 100, 100};
	private final int[] objectiveYCoords = {0, 100, 100, 0, 0, 100, 100, 0};
	private final int[] objectiveZCoords = {0, 0, 0, 0, 100, 100, 100, 100};


	private int[] relativeXCoords = objectiveXCoords;
	private int[] relativeYCoords = objectiveYCoords;
	private int[] relativeZCoords = objectiveZCoords;

	//available for printing onto a 2d screen; this is the projection
	private int[] displayX = relativeXCoords;
	private int[] displayZ = relativeYCoords;

	//adjusts display coordinates for the cube
	void update(Player player) {
		for (int i = 0; i < 8; ++i)
			relativeXCoords[0] = objectiveXCoords[0] - player.XYZ[0];
		//for each point within acceptable range:
		//it updates the point positions, or else puts offscreen

	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Assume x, y, and diameter are instance variables.
		Ellipse2D.Double[] circles;
		//for(int i = 0; i < 8; i++)
		//Ellipse2D.Double circle = new Ellipse2D.Double(100, 200, 10, 10);
		//g2d.fill(circle);
		Ellipse2D.Double circle = new Ellipse2D.Double(100, 200, 10, 10);
		g2d.fill(circle);
	}
}

class Player {
	int[] XYZ = {0, 0, 0};
	private int[] dirXYZ = {0, 1, 0};

	void update(KeyEvent e) {
		if ((e.getKeyCode() == VK_W)) {
			for (int i = 0; i < 3; ++i)
				XYZ[i] += dirXYZ[i];
			System.out.println("w key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_S) {
			for (int i = 0; i < 3; ++i)
				XYZ[i] -= dirXYZ[i];
			System.out.println("s key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_A) {
			//XYZ[0]+=dirXYZ[1];XYZ[1]-=dirXYZ[1];XYZ[2]+=dirXYZ[2];
			System.out.println("a key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_D) {
			//XYZ[0]-=dirXYZ[0];XYZ[1]+=dirXYZ[1];XYZ[2]+=dirXYZ[2];
			System.out.println("d key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_UP) {
			System.out.println("up arrow released");
		} else if (e.getKeyCode() == VK_DOWN) {
			System.out.println("down arrow released");
		} else if (e.getKeyCode() == VK_LEFT) {
			System.out.println("left arrow released");
		} else if (e.getKeyCode() == VK_RIGHT) {
			System.out.println("right arrow released");
		}
	}
}
