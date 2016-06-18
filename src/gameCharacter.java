import javax.swing.JPanel;

public class gameCharacter extends JPanel {

	protected double x;
	protected double y;	
	protected int deltaX;
	protected int deltaY;
	protected int speed;
/**
 * @return x = x index on the board
 */	
public double X(){
	return this.x;
}

/**
 * @return y = y index on the board
 */	
public double Y(){
	return this.y;
}

/**
 * @param x = x index on the board
 */	
public void setXindex(double x){
	this.x=x;
}

/**
 * @param y = y index on the board
 */	
public void setYindex(double y){
	this.y=y;
}


	
}
