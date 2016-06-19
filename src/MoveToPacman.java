
public class MoveToPacman implements MoveAlgoritem {

	@Override
	public int moveTo(Ghost ghost, Pacman pacman) {
		double diffX = ghost.x -pacman.x;
		double diffY = ghost.y -pacman.y;
		if (Math.abs(diffX) > Math.abs(diffY)) {
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
