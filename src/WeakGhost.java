public class WeakGhost extends Ghost{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	public WeakGhost(String color, int speed, Board _board, int _ghostTime) {
		super(color, speed, _board, _ghostTime);
		moveAlgoritem = new RandMove();
	}

}
