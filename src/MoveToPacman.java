import java.util.Random;

public class MoveToPacman implements MoveAlgoritem {
	private Random rand=new Random();
	@Override
	public int moveTo(Ghost ghost, Pacman pacman) {
		double diffX = ghost.x -pacman.x;
		double diffY = ghost.y -pacman.y;
		
		if (rand.nextInt(2)>0) {
			if (diffX>0) {
				return 2; //left
			} else {
				return 1; //right
			}
		} else {
			if (diffY>0) {
				return 4; //down
			} else {
				return 3; //up
			}
		}
	}

}
