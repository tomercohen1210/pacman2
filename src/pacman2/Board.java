package pacman2;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Board extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel background;
	private JPanel itemsBoard; 	
	public JLayeredPane multiBoard;
	public int mapHeight;
	public int mapWidth;
	public double squareHeight;
	public double squareWidth;
	
	final int W=1; // Wall.
	final int F=2; // Crossroads with food 
	final int E=3; // Empty crossroads
	

	private int board[][] = {
			//-----------------------X-----------------------------//
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{F,F,F,F,F,F,F,F,F,F,W,E,E,E,E,E,E,W,F,F,F,F,F,F,F,F,F,F},
			{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
			{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
			{W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
			{W,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
			{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
			{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
			{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}
	};
	
	public Board(GameFrame thisGame){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		mapHeight=416;
		mapWidth=429;
		squareHeight=mapHeight/31;
		squareWidth=mapWidth/28;
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
		multiBoard.add(itemsBoard,new Integer (1));
		
		// Final stuff
		multiBoard.setVisible(true);
		this.add(multiBoard);
	}
	
	private JPanel createBackground(){
		try {
			background = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image map = ImageIO.read(new File("Map.bmp"));
				public void paint( Graphics g ) { 
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
	
	private void CreateItemBoard(){
		try {
			itemsBoard = new JPanel(){
				private static final long serialVersionUID = 1L;
				private Image food = ImageIO.read(new File("NormalPoint.png"));
				public void paint (Graphics g){
					super.paint(g);
					for (int i=0; i<board.length; i++)
						for (int j=0; j<board[i].length; j++) {
							if(board[i][j]==F)
								g.drawImage(food, (int)(j*(squareWidth)+squareWidth/2), (int)(i*(squareHeight)+squareHeight/2), null);
						}
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
