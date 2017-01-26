// This entire file is part of my masterpiece.
// Moses Wayne
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * This abstract class allows additional bricks to be added to the game for
 * different fucntionality
 * 
 * @author moses
 *
 */
public abstract class Brick extends ImageView {
	public static final int SCORE_PER_BRICK = 100;
	public static final double SCREEN_RATIO = 25;
	public static final double HW_RATIO = 2.5;

	public Brick(double screenSize, double x, double y) {
		this.setX(x);
		this.setY(y);
		this.setFitHeight(screenSize / SCREEN_RATIO);
		this.setFitWidth(this.getFitHeight() * HW_RATIO);
	}
	/**
	 * Determines quickly whether a brick is hit
	 * 
	 * @param ballCoordX x coordinate of the ball
	 * @param ballCoordY y coordinate of the ball
	 */
	public boolean brickHit(double ballCoordX, double ballCoordY) {
		return (ballCoordX > this.getX() && ballCoordX < (this.getX() + this.getFitWidth()) && ballCoordY > this.getY()
				&& ballCoordY < (this.getY() + this.getFitHeight()));
	}

	/**
	 * determines if and where a ball collides with the brick, alters the ball's angle
	 * @param myBouncer the ball to test
	 * @return whether the ball collided with this brick
	 */
	public boolean brickCollision(Ball myBouncer) {
		boolean didHit = false;
		if ((this.brickHit(myBouncer.getX() + myBouncer.getFitWidth() / 2, myBouncer.getY())
				&& myBouncer.getYAngle() < 0)
				|| (this.brickHit(myBouncer.getX() + myBouncer.getFitWidth() / 2,
						myBouncer.getY() + myBouncer.getFitHeight()) && myBouncer.getYAngle() >= 0)) {
			myBouncer.setAngles(myBouncer.getXAngle(), -1 * myBouncer.getYAngle());
			didHit = true;
		}

		if ((this.brickHit(myBouncer.getX() + myBouncer.getFitWidth(), myBouncer.getY() + myBouncer.getFitHeight() / 2)
				&& myBouncer.getXAngle() < 0)
				|| (this.brickHit(myBouncer.getX(), myBouncer.getY() + myBouncer.getFitHeight() / 2)
						&& myBouncer.getXAngle() >= 0)) {
			myBouncer.setAngles(-1 * myBouncer.getXAngle(), myBouncer.getYAngle());
			didHit = true;
		}
		return didHit;
	}

	/**
	 * Sets a new image for the brick
	 * @param newImage image to change to
	 */
	public void setNewImage(Image newImage) {
		this.setImage(newImage);
	}

	/**
	 * determines if the brick is broken, different for each type of brick
	 * @param myBouncer ball to test
	 * @return
	 */
	public abstract boolean isBroken(Ball myBouncer);

	/**
	 * determines the score returned from breaking a brick
	 * @return
	 */
	public abstract int getScore();
}
