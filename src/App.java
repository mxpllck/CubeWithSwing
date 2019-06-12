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
				//updates look angle and movement
				player.update(e);
				//enables cube redrawing in the data
				cube.update(player);
				//puts redraw on screen
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
		//evaluates for coordinates relative to the player
		for(int i = 0; i < 8; ++i) {
			relativeXCoords[i] = objectiveXCoords[i] - player.XYZ[0];
			relativeYCoords[i] = objectiveYCoords[i] - player.XYZ[1];
			relativeZCoords[i] = objectiveZCoords[i] - player.XYZ[2];
		}
		//for each point within acceptable range:
		//it updates the point positions, or else puts offscreen

	}

	//this is called automatically by repaint() or revalidate() function.
	public void paint(Graphics g) {
		//prepares component for drawing
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
	//stores the direction it was pointing so it can be returned to when one looks away from the sky or the ground.
	private int[] XYInclination = {0, 1};

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
			XYZ[0] -= dirXYZ[1];
			XYZ[1] += dirXYZ[0];
			System.out.println("a key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_D) {
			XYZ[0] += dirXYZ[1];
			XYZ[1] -= dirXYZ[0];
			System.out.println("d key released.  Cordinates are: " + XYZ[0] + ", " + XYZ[1] + " & " + XYZ[2]);
		} else if (e.getKeyCode() == VK_UP) {
			//these 3 if-statements wont have any effect if the player is already looking up.
			if ((dirXYZ[2] <= 0)) {
				++dirXYZ[2];
			}
			if (dirXYZ[2] == 1) {
				dirXYZ[0] = 0;
				dirXYZ[1] = 0;
			}
			if (dirXYZ[2] == 0) {
				//restore XY look direction
				dirXYZ[0] = XYInclination[0];
				dirXYZ[1] = XYInclination[1];
			}
			System.out.println("up arrow released.  Look direction is: " + dirXYZ[0] + ", " + dirXYZ[1] + " & " + dirXYZ[2]);
		} else if (e.getKeyCode() == VK_DOWN) {
			//these 3 if-statements wont have any effect if the player is already looking down.
			if ((dirXYZ[2] >= 0)) {
				--dirXYZ[2];
			}
			if (dirXYZ[2] == -1) {
				dirXYZ[0] = 0;
				dirXYZ[1] = 0;
			}
			if (dirXYZ[2] == 0) {
				//restore XY look direction
				dirXYZ[0] = XYInclination[0];
				dirXYZ[1] = XYInclination[1];
			}
			System.out.println("down arrow released.  Look direction is: " + dirXYZ[0] + ", " + dirXYZ[1] + " & " + dirXYZ[2]);
		} else if (e.getKeyCode() == VK_LEFT) {
			//these 3 if-statements wont have any effect if the player is already looking left.
			//if looking at the flat plane
			if (dirXYZ[2] == 0) {
				if ((dirXYZ[0] >= 0)) {
					--dirXYZ[0];
				}
				if (dirXYZ[0] == -1) {
					dirXYZ[1] = 0;
				}
				if (dirXYZ[0] == 0) {
					dirXYZ[1] = 1;
				}
				//saves
				XYInclination[0] = dirXYZ[0];
				XYInclination[1] = dirXYZ[1];
			}
			System.out.println("left arrow released.  Look direction is: " + dirXYZ[0] + ", " + dirXYZ[1] + " & " + dirXYZ[2]);
		} else if (e.getKeyCode() == VK_RIGHT) {
			//these if-statements wont have any effect if the player is already looking right.
			//if looking at the flat plane:
			if (dirXYZ[2] == 0) {
				if ((dirXYZ[0] <= 0)) {
					++dirXYZ[0];
				}
				if (dirXYZ[0] == 1) {
					dirXYZ[1] = 0;
				}
				if (dirXYZ[0] == 0) {
					dirXYZ[1] = 1;
				}
				//save XY coords
				XYInclination[0] = dirXYZ[0];
				XYInclination[1] = dirXYZ[1];
			}
			System.out.println("right arrow released.  Look direction is: " + dirXYZ[0] + ", " + dirXYZ[1] + " & " + dirXYZ[2]);
		}
	}
	//1, 0, 0 // 0, 1, 0 // 0, 0, 1 // -1, 0, 0 // 0, -1, 0 // 0, 0, -1
}
