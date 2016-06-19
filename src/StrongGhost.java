
public class StrongGhost extends Ghost{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public StrongGhost(String color, int speed, Board _board, int _ghostTime) {
		super(color, speed, _board, _ghostTime);
		moveAlgoritem = new MoveToPacman();
	}

}
