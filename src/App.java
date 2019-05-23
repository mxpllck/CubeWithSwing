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
	//hidden coords for the 3d cube, which is composed of two squares, one above the other
	//first square: (0, 0, 0), (0, 100, 0), (100, 100, 0), (100, 0, 0)
	//second square

	//available for printing onto a 2d screen; this is the projection
	private int[] displayX1 = {100, 0, 100, 0};
	private int[] displayY1 = {0, 100, 100, 0};


	//adjusts display coordinates
	void update(Player player) {
		//IF entire cube is within acceptable range, project the 3d cube onto the 400x400 window drawable area.
		//ELSE player cannot see the cube
		//player is facing towards

	}

	public void paint(Graphics g) {
		g.drawPolygon(displayX1, displayY1, 4);
	}
}

class Player {
	public int[] playerXYZ = {0, 0, 0};
	public int[] playerDirXYZ = {0, 1, 0};
}
