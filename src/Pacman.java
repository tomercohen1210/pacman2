import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Pacman extends gameCharacter implements KeyListener {
	private static final long serialVersionUID = 5L;

	private static Pacman _instance;
	private Board board;
	private Timer timer;
	private ImageIcon image;
	private double startingPointX;
	private double startingPointY;
	private int last;
	private Random rand;

	public enum PacmanType {
		Regular, Mighty, Super
	}

	public PacmanType pacmanType = PacmanType.Regular;

	private String m_imgName = "Pacman";

	// Constructor
	public Pacman(double startingPointX, double startingPointY, Board _board, String name) {
		deltaX = 0;
		deltaY = 0;
		board = _board;
		this.x = startingPointX;
		this.y = startingPointY;
		this.startingPointX = startingPointX;
		this.startingPointY = startingPointY;
		this.image = leftIcone();
		super.speed = 16;
		rand = new Random();
		last = 0;
		timer = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == timer) {
					if (name.contains(("Computer")))
						randMove();
					else
						move();
				}
				repaint();

			}
		});
		this.setVisible(true);
		if (!name.contains(("Computer")))
			this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus(true);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		image.paintIcon(this, g, (int) x, (int) y);
	}

	/**
	 * This function sets the creature direction to be up
	 */
	private void setMoveUp() {

		deltaX = 0;
		deltaY = -1;

	}

	/**
	 * This function sets the creature direction to be down
	 */
	private void setMoveDown() {
		deltaX = 0;
		deltaY = 1;
	}

	/**
	 * This function sets the creature direction to be right
	 */
	private void setMoveRight() {
		deltaX = 1;
		deltaY = 0;
	}

	/**
	 * This function sets the creature direction to be left
	 */
	private void setMoveLeft() {
		deltaX = -1;
		deltaY = 0;
	}

	/**
	 * initial the creature direction
	 */
	public void zeroDeltaXY() {
		this.deltaX = 0;
		this.deltaY = 0;
	}

	protected void move() {
		if (board.Cell((int) x + deltaX, (int) y + deltaY) == 5) {
			if (x < 0)
				setXindex(400);
			else
				setXindex(-10);
			// zeroDeltaXY();

		} else if (board.Cell((int) x + deltaX + 1, (int) y + deltaY) == 1
				|| board.Cell((int) x + deltaX - 3, (int) y + deltaY) == 1
				|| board.Cell((int) x + deltaX, (int) y + deltaY + 4) == 1
				|| board.Cell((int) x + deltaX, (int) y + deltaY - 2) == 1)
			zeroDeltaXY();
		else if (board.Cell((int) x + deltaX, (int) y + deltaY) == 2)
			board.eat((int) x + deltaX, (int) y + deltaY);
		else if (board.Cell((int) x + deltaX, (int) y + deltaY) == 4) {
			// super food
			pacmanType = PacmanType.Super;
			m_imgName = "SPacman";
			setImg();
			Timer t = new Timer(1000 * 10, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					pacmanType = PacmanType.Regular;
					m_imgName = "Pacman";
					setImg();
				}
			});
			t.setRepeats(false);
			t.start();
			board.eat((int) x + deltaX, (int) y + deltaY);
		} else if (board.Cell((int) x + deltaX, (int) y + deltaY) == 6) {
			// Mighty food
			pacmanType = PacmanType.Mighty;
			m_imgName = "MPacman";
			setImg();
			Timer t = new Timer(1000 * 10, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					pacmanType = PacmanType.Regular;
					m_imgName = "Pacman";
					setImg();
				}
			});
			t.setRepeats(false);
			t.start();
			board.eat((int) x + deltaX, (int) y + deltaY);
		}
		this.x = x + deltaX;
		this.y = y + deltaY;

	}

	private void setImg() {
		if (deltaX == 1) {
			image = rightIcone();
		} else if (deltaX == -1) {
			image = leftIcone();
		} else if (deltaY == -1) {
			image = upIcone();
		} else {
			image = downIcone();
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			image = leftIcone();
			setMoveLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			image = rightIcone();
			setMoveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			image = upIcone();
			setMoveUp();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			image = downIcone();
			setMoveDown();
		}
	}

	private void randMove() {

		if (true) {
			int moveTo = rand.nextInt(4) + 1;
			if ((deltaX == 0 && deltaY == 0)) {
				whereToMove(moveTo);
			}
		}

		if (board.Cell((int) x + deltaX + 1, (int) y + deltaY) == 1
				|| board.Cell((int) x + deltaX - 3, (int) y + deltaY) == 1
				|| board.Cell((int) x + deltaX, (int) y + deltaY + 4) == 1
				|| board.Cell((int) x + deltaX, (int) y + deltaY - 2) == 1)
			zeroDeltaXY();
		move();

	}

	private void whereToMove(int to) {
		for (int i = to; i < 5; i++) {
			if (i == 1 && board.Cell((int) x + 1, (int) y) != 1 && last != 2) {
				last = 1;
				image = rightIcone();
				setMoveRight();
				break;
			} else if (i == 2 && board.Cell((int) x - 1, (int) y) != 1 && last != 1) {
				last = 2;
				image = leftIcone();
				setMoveLeft();
				break;
			} else if (i == 3 && board.Cell((int) x, (int) y + 1) != 1 && last != 3) {
				last = 4;
				image = downIcone();
				setMoveDown();
				break;
			} else if (i == 4 && board.Cell((int) x, (int) y - 1) != 1 && last != 4) {
				last = 3;
				setMoveUp();
				image = upIcone();
				break;
			}
			if (i == 4)
				i = 1;
		}
	}

	private ImageIcon leftIcone() {
		Image img = null;
		try {
			img = ImageIO.read(ResourceLoder.load(m_imgName + "Left.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ImageIcon(img);
	}

	private ImageIcon rightIcone() {
		Image img = null;
		try {
			img = ImageIO.read(ResourceLoder.load(m_imgName + "Right.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ImageIcon(img);

	}

	private ImageIcon upIcone() {
		Image img = null;
		try {
			img = ImageIO.read(ResourceLoder.load(m_imgName + "Up.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ImageIcon(img);
	}

	private ImageIcon downIcone() {
		Image img = null;
		try {
			img = ImageIO.read(ResourceLoder.load(m_imgName + "Down.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new ImageIcon(img);
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void rePlace() {
		x = startingPointX;
		y = startingPointY;
		deltaX = 0;
		deltaY = 0;
	}

	public void stop() {
		x = startingPointX;
		y = startingPointY;
		deltaX = 0;
		deltaY = 0;
		timer.stop();

	}

	public static Pacman GetInstance() {
		if (_instance == null)
			_instance = new Pacman(14 * Board.squareWidth + Board.squareWidth / 2,
					22.5 * (Board.squareHeight) + Board.squareHeight / 2, Board.GetInstance(), "Pacman");
		return _instance;
	}

}
