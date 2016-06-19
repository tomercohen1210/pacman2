import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Board extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Board _instance;
	private Pacman pacman;
	private JPanel background;
	private JPanel itemsBoard;
	private JLayeredPane multiBoard;
	private int mapHeight;
	private int mapWidth;
	protected static double squareHeight;
	protected static double squareWidth;
	private Ghost[] ghosts;
	private Timer timer;
	private int lives;
	public String ghostStartTime;
	public String PlayerName;
	public String difficulty;
	private long startTime, endTime;
	private int startFood;

	// This takes care of the game board
	final int W = 1; // Wall.
	final int F = 2; // Crossroads with food
	final int E = 3; // Empty crossroads
	final int S = 4; // Crossroads with Super food
	final int M = 6; // Crossroads with Mighty food
	// represents the board game
	private int board[][] = {
			// -----------------------X-----------------------------//
			{ W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
			{ W, F, F, F, F, F, F, F, F, F, F, F, F, W, W, F, F, F, F, F, F, F, F, F, F, F, F, W },
			{ W, F, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, F, W },
			{ W, F, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, F, W },
			{ W, F, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, F, W },
			{ W, F, F, F, F, F, F, F, F, F, F, F, F, S, F, F, F, F, F, F, F, F, F, F, F, F, F, W },
			{ W, F, W, W, W, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
			{ W, F, W, W, W, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
			{ W, F, F, F, F, F, F, W, W, F, F, F, F, W, W, F, F, F, F, W, W, F, F, F, F, F, F, W },
			{ W, W, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, W, W },
			{ E, E, E, E, E, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, E, E, E, E, E },
			{ E, E, E, E, E, W, F, W, W, F, F, F, F, F, F, F, F, F, F, W, W, F, W, E, E, E, E, E },
			{ E, E, E, E, E, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, E, E, E, E, E },
			{ W, W, W, W, W, W, F, W, W, F, W, E, E, E, E, E, E, W, F, W, W, F, W, W, W, W, W, W },
			{ F, F, F, F, F, F, F, F, F, M, W, E, E, E, E, E, E, W, M, F, F, F, F, F, F, F, F, F },
			{ W, W, W, W, W, W, F, W, W, F, W, E, E, E, E, E, E, W, F, W, W, F, W, W, W, W, W, W },
			{ E, E, E, E, E, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, E, E, E, E, E },
			{ E, E, E, E, E, W, F, W, W, F, F, F, F, F, F, F, F, F, F, W, W, F, W, E, E, E, E, E },
			{ E, E, E, E, E, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, E, E, E, E, E },
			{ W, W, W, W, W, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, W, W, W, W, W },
			{ W, F, F, F, F, F, F, F, F, F, F, F, F, W, W, F, F, F, F, F, F, F, F, F, F, F, F, W },
			{ W, F, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, F, W },
			{ W, F, W, W, W, W, F, W, W, W, W, W, F, W, W, F, W, W, W, W, W, F, W, W, W, W, F, W },
			{ W, F, F, F, W, W, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, W, W, F, F, F, W },
			{ W, W, W, F, W, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, W, F, W, W, W },
			{ W, W, W, F, W, W, F, W, W, F, W, W, W, W, W, W, W, W, F, W, W, F, W, W, F, W, W, W },
			{ W, F, F, F, F, F, F, W, W, F, F, F, F, W, W, F, F, F, F, W, W, F, F, F, F, F, F, W },
			{ W, F, W, W, W, W, W, W, W, W, W, W, F, W, W, F, W, W, W, W, W, W, W, W, W, W, F, W },
			{ W, F, W, W, W, W, W, W, W, W, W, W, F, W, W, F, W, W, W, W, W, W, W, W, W, W, F, W },
			{ W, F, F, F, F, F, F, F, F, F, F, F, F, F, S, F, F, F, F, F, F, F, F, F, F, F, F, W },
			{ W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W } };
	private int foodCount;

	// Constructor
	private Board() {

	}

	public void setBoard() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		mapHeight = 416;
		mapWidth = 429;
		squareHeight = mapHeight / 31;
		squareWidth = mapWidth / 28;
		// Creates and set-up the layered pane.
		multiBoard = new JLayeredPane();
		multiBoard.setPreferredSize(new Dimension(mapHeight, mapWidth));
		// Creates the background.
		JPanel background = createBackground();
		background.setBounds(0, 0, mapWidth, mapHeight);
		multiBoard.add(background, new Integer(0));
		// Creates the items board - Placing food in proper places
		CreateItemBoard();
		itemsBoard.setOpaque(false);
		itemsBoard.setSize(mapWidth, mapHeight);
		itemsBoard.setBackground(null);
		multiBoard.add(itemsBoard, new Integer(1));
		setFood();
		// set lives
		lives = 3;
		// Places Pacman on the board.
		pacman = Pacman.GetInstance();
		pacman.setOpaque(false);
		pacman.setSize(mapWidth + 1, mapHeight + 1);
		multiBoard.add(pacman, new Integer(3));
		pacman.setFocusable(true);
		if (difficulty.equals("Hard")) {
			ghosts = new Ghost[4];
			ghosts[0] = new StrongGhost("Sorange", 17, this, Integer.parseInt(ghostStartTime));
			ghosts[1] = new WeakGhost("pink", 18, this, Integer.parseInt(ghostStartTime));
			ghosts[2] = new StrongGhost("Sred", 15, this, Integer.parseInt(ghostStartTime));
			ghosts[3] = new WeakGhost("blue", 14, this, Integer.parseInt(ghostStartTime));

			for (int i = 0; i < 4; i++) {
				ghosts[i].setOpaque(false);
				ghosts[i].setSize(mapWidth + 1, mapHeight + 1);
				multiBoard.add(ghosts[i], new Integer(3));
			}
		} else {
			ghosts = new Ghost[2];
			ghosts[0] = new WeakGhost("orange", 17, this, Integer.parseInt(ghostStartTime));
			ghosts[1] = new StrongGhost("Sred", 15, this, Integer.parseInt(ghostStartTime));

			for (int i = 0; i < 2; i++) {
				ghosts[i].setOpaque(false);
				ghosts[i].setSize(mapWidth + 1, mapHeight + 1);
				multiBoard.add(ghosts[i], new Integer(3));
			}
		}

		// Final stuff
		multiBoard.setVisible(true);
		this.add(multiBoard);

		timer = new Timer(16, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == timer)
					checkCollide();

			}

		});
		startTime = System.currentTimeMillis();
		timer.start();

	}

	private void setFood() {
		foodCount = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == F) {
					foodCount++;
				}
			}
		}
		startFood = foodCount + 4;

	}

	private void checkCollide() {
		for (Ghost ghost : ghosts) {
			if (ghost != null && Math.abs(ghost.Y() - pacman.Y()) < 10 && Math.abs(ghost.X() - pacman.X()) < 10) {
				if (ghost.CanEat(pacman.pacmanType)) {
					// can eat
					endTime = (System.currentTimeMillis() - startTime) / 1000;
					if (lives == 3) {
						try {
							if (!Files.exists(Paths.get("firstTimeDead.txt")))
								Files.createFile(Paths.get("firstTimeDead.txt"));
							Files.write(Paths.get("firstTimeDead.txt"),
									(PlayerName + " " + endTime + " Sec" + " \n").getBytes(),
									StandardOpenOption.APPEND);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					lives--;

					if (lives == 0) {
						GameOver();
					} else {

						pacman.rePlace();
						rePlaceGhosts();
						JOptionPane.showMessageDialog(null, "You have " + lives + " lives");
					}
				} else {
					ghost.rePlace();
				}
			}
		}
	}


	private void rePlaceGhosts() {
		for (Ghost ghost : ghosts) {
			if (ghost != null) {
				ghost.rePlace();
			}
		}
	}

	private void GameOver() {
		for (Ghost ghost : ghosts) {
			if (ghost != null) {
				ghost.stop();
			}

		}

		pacman.stop();
		endTime = (System.currentTimeMillis() - startTime) / 1000;

		try {
			if (!Files.exists(Paths.get("Points.txt")))
				Files.createFile(Paths.get("Points.txt"));

			Files.write(Paths.get("Points.txt"),
					(PlayerName + " " + Integer.toString(startFood - foodCount + lives * 20) + " Points" + " \n")
							.getBytes(),
					StandardOpenOption.APPEND);

			if (lives == 0) {
				if (!Files.exists(Paths.get("TimeDead.txt")))
					Files.createFile(Paths.get("TimeDead.txt"));

				Files.write(Paths.get("TimeDead.txt"), (PlayerName + " " + endTime + " Sec" + " \n").getBytes(),
						StandardOpenOption.APPEND);
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		JOptionPane.showMessageDialog(null, "GAME OVER!! BYE BYE", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	public void restartApplication() {
		timer.stop();
		new GameFrame(PlayerName, ghostStartTime, difficulty);
		Component comp = SwingUtilities.getRoot(this);
		((Window) comp).dispose();
	}

	/**
	 * This function draws the food on the board
	 */
	private void CreateItemBoard() {
		try {
			itemsBoard = new JPanel() {
				private static final long serialVersionUID = 1L;
				private Image food = ImageIO.read(ResourceLoder.load("NormalPoint.png"));
				private Image mfood = ImageIO.read(ResourceLoder.load("MPoint.png"));
				private Image sfood = ImageIO.read(ResourceLoder.load("SPoint.png"));

				public void paint(Graphics g) {
					super.paint(g);
					for (int i = 0; i < board.length; i++)
						for (int j = 0; j < board[i].length; j++) {
							if (board[i][j] == F)
								g.drawImage(food, (int) (j * (squareWidth) + squareWidth / 2),
										(int) (i * (squareHeight) + squareHeight / 2), null);
							else if (board[i][j] == M) {
								g.drawImage(mfood, (int) (j * (squareWidth) + squareWidth / 2),
										(int) (i * (squareHeight) + squareHeight / 2), null);
							} else if (board[i][j] == S) {
								g.drawImage(sfood, (int) (j * (squareWidth) + squareWidth / 2),
										(int) (i * (squareHeight) + squareHeight / 2), null);
							}
						}
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function draws the background of the board
	 * 
	 * @return JPanel = the background
	 */
	private JPanel createBackground() {
		try {
			background = new JPanel() {
				private static final long serialVersionUID = 1L;
				private Image map = ImageIO.read(ResourceLoder.load("Map.bmp"));

				public void paint(Graphics g) {
					super.paint(g);
					g.drawImage(map, 0, 0, null);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setVisible(true);
		return background;
	}

	public int Cell(int x, int y) {

		if (x < -10 || x > 400) {
			return 5;
		}
		int newX = (int) ((x - squareWidth / 2) / squareWidth);
		int newY = (int) ((y - squareHeight / 2) / squareHeight);
		if (x < 10 && newY != 13) {
			return 1;
		}
		return board[newY + 1][newX + 1];

	}

	public void eat(int x, int y) {
		try {
			int newX = (int) ((x - squareWidth / 2) / squareWidth);
			int newY = (int) ((y - squareHeight / 2) / squareHeight);
			board[newY + 1][newX + 1] = 3;
			foodCount--;
			if (foodCount == 0) {
				GameOver();
			}
		} catch (Exception e) {
			System.out.println(x + "." + y);
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				s = s + "," + board[i][j];
			s = s + "\n";
		}
		return s;
	}

	public static Board GetInstance() {
		if (_instance == null)
			_instance = new Board();
		return _instance;
	}
}
