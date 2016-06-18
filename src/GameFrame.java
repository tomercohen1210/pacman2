import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * This class represents the game
 * @author Hadar Polad.
 */

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Board gameBoard;

	//Constructor
	public GameFrame(String name, String time, String dif){
		super("The Pac-Man Game");
		createBoard(name,time,dif);
	}

	/**
	 * This class crates the board of the game 
	 * @param dif 
	 * @param time 
	 * @param name 
	 */
	private void createBoard(String name, String time, String dif)
	{
		// Create and set up the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create the content pane
		gameBoard = new Board(name,time,dif);
		this.add(gameBoard,0);
		// Display the window
		this.setSize(new Dimension(445,454));
		this.setVisible(true);
	}


	


}
