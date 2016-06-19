import java.util.Random;

public class RandMove implements MoveAlgoritem {
	private Random rand;
	@Override
	public int moveTo(Ghost ghost, Pacman pacman) {
		return rand.nextInt(4)+1;
	}

}
