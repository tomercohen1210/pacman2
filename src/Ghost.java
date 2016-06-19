import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public abstract class Ghost extends gameCharacter {
	
	private static final long serialVersionUID = 1L;

	
	private Board board;
	private Timer timer;
	private ImageIcon image;
	boolean wait;
	boolean out;
	int last;
	int ghostTime;
	
	protected MoveAlgoritem moveAlgoritem;

	private String color;


	
	public Ghost(String color,int speed, Board _board,int _ghostTime){
		this.color =color;
		last=0;
		out =false;

		deltaX=0;
		deltaY=0;
		board=_board;
		wait=true;
		ghostTime=_ghostTime;
		
		this.x=210;
		this.y=180;
		Image img;
		try {
			img = ImageIO.read(ResourceLoder.load(color+".png"));
			this.image = new ImageIcon(img);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		super.speed =speed;
		timer=new Timer(speed,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==timer){
					randMove();
					
			}
				repaint();

			}
		});
		this.setVisible(true);
		timer.setDelay(ghostTime*1000);
		timer.start();
		
		
	}
			
			
		public void paint(Graphics g){
			super.paint(g);
			image.paintIcon(this, g, (int) x,(int) y);		
		}
		
	
		
	
	private void randMove(){
		if (wait){
			timer.setDelay(speed);
			wait=false;
		}
		
		if(!out){
			setMoveUp();
			if (this.y==140)
				out=true;
		}
		else{
		
		if (true){
			if (!out || (deltaX==0 && deltaY==0)){
				
				whereToMove(moveAlgoritem.moveTo(this, Pacman.GetInstance()));
		}
		}
		}
		if (out &&( board.Cell((int)x+deltaX+1,(int) y+deltaY)==1 || board.Cell((int)x+deltaX-3,(int) y+deltaY)==1 || board.Cell((int)x+deltaX,(int) y+deltaY +4)==1||board.Cell((int)x+deltaX,(int) y+deltaY-2)==1))
			zeroDeltaXY();
		this.x=x+deltaX;
		this.y=y+deltaY;

		
	
	}
		
	public void zeroDeltaXY(){
		this.deltaX=0;
		this.deltaY=0;
	}
	private void whereToMove(int to){
		for (int i=to ;i<5;i++){
			if (i==1 && board.Cell((int)x+1,(int) y)!=1 && last!=2){
				last=1;
				setMoveRight();
			break;
		}
			else if (i==2 && board.Cell((int)x-1,(int) y)!=1 && last!=1){
				last=2;
				setMoveLeft();
			break;
		}
			else if (i==3 && board.Cell((int)x,(int) y+1)!=1 && last!=3){
				last=4;
				setMoveDown();
			break;
		}
			else if (i==4 && board.Cell((int)x,(int) y-1)!=1 && last!=4){
				last=3;
				setMoveUp();
				break;
			}
		if (i==4)
			i=1;
		}
		
		
		
		
	}
	
	
	private boolean checkJunction(){
		boolean junk= false;
		int count=0;
		if (board.Cell((int)x+1,(int) y)!=1)
			count++;
		if (board.Cell((int)x-1,(int) y)!=1)
			count++;
		if (board.Cell((int)x,(int) y+1)!=1)
			count++;
		if (board.Cell((int)x,(int) y-1)!=1)
			count++;
		if (count>=3)
			junk=true;
		else if (board.Cell((int)x+deltaX*3,(int) y+(deltaY*10))==1){
				junk=true;
					
			}

		return junk;
	}
	
	
private void setMoveUp(){
		
		deltaX=0; deltaY=-1;
	
}
/**
 * This function sets the creature direction to be down 
 */	
private void setMoveDown(){
	deltaX=0; deltaY=1;
}
/**
 * This function sets the creature direction to be right 
 */	
private void setMoveRight(){
	deltaX=1; deltaY=0;
}
/**
 * This function sets the creature direction to be left 
 */	
private void setMoveLeft(){
	deltaX=-1; deltaY=0;
}	


public void rePlace() {
	deltaX=0;
	deltaY=0;
	wait=true;
	timer.setDelay(ghostTime*1000);
	this.x=210;
	this.y=180;
	out=false;
	
}


public void stop() {
	this.x=210;
	this.y=180;
	timer.stop();
	
	
}




}
