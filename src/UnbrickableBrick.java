// This entire file is part of my masterpiece.
// Moses Wayne
import javafx.scene.image.Image;

public class UnbrickableBrick extends Brick{

	public UnbrickableBrick(double screenSize, double x, double y, Image brickIcon) {
		super(screenSize, x, y);
		this.setNewImage(brickIcon);
	}
	/**
	 * see super class
	 * can't be broken, returns 0
	 */
	public int getScore() {
		return 0;
	}
	
	/**
	 * see super class
	 * can't be broken, always returns false after collision
	 */
	public boolean isBroken(Ball myBouncer) {
		brickCollision(myBouncer);
		return false;
	}
}
