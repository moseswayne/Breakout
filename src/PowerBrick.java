
// This entire file is part of my masterpiece.
// Moses Wayne
import javafx.scene.image.Image;

public class PowerBrick extends Brick {

	private int myPower;

	public PowerBrick(double screenSize, double x, double y, int power, Image brickIcon) {
		super(screenSize, x, y);
		myPower = power;
		this.setNewImage(brickIcon);
	}

	/**
	 * returns which power to use
	 * 
	 * @return the power to use
	 */
	public int getPower() {
		return myPower;
	}

	/**
	 * see super class
	 */
	public int getScore() {
		return SCORE_PER_BRICK;
	}

	/**
	 * see super class
	 */
	public boolean isBroken(Ball myBouncer) {
		return (brickCollision(myBouncer));
	}
}
