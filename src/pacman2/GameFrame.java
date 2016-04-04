package pacman2;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	private gameCharacter pacman;
	private gameCharacter ghost;


	public GameFrame(){
		super("The Pac-Man Game");
		createGame();
	}
	
	
	public void createGame(){
		// Create and set up the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create the content pane
		gameBoard = new Board(this);
		this.add(gameBoard,0);
		// Display the window
		this.setSize(new Dimension(445,454));
		this.setVisible(true);
		pacman = new Pacman(14*squareWidth+squareWidth/2,22.5*(squareHeight)+squareHeight/2,this);
		pacman.setOpaque(false);
		pacman.setSize(mapWidth, mapHeight);
		gameBoard.multiBoard.add(pacman,new Integer(3));
		pacman.setFocusable(true);
		
	}
	
	public static void main(String args[]) {
		new GameFrame();
	}

}
