
// This entire file is part of my masterpiece.
// Moses Wayne
import java.util.ArrayList;
import javafx.scene.image.Image;

public class NormalBrick extends Brick {

	private int hitsLeft;
	private ArrayList<String> brickPics;

	public NormalBrick(double screenSize, double x, double y, int hardness, ArrayList<String> brickIcons) {
		super(screenSize, x, y);
		hitsLeft = hardness;
		brickPics = brickIcons;
		this.setNewImage(new Image(getClass().getClassLoader().getResourceAsStream(brickPics.get(hardness))));
	}

	/**
	 * see super class
	 */
	public int getScore() {
		return (hitsLeft + 1) * (hitsLeft + 1) * SCORE_PER_BRICK;
	}

	/**
	 * void function that decrements hits left
	 * 
	 * @param numHits
	 * @return whether it was hit
	 */
	public boolean decrementHits(int numHits) {
		hitsLeft -= numHits;
		if (hitsLeft < 0) {
			return true;
		} else {
			this.setNewImage(new Image(getClass().getClassLoader().getResourceAsStream(brickPics.get(hitsLeft))));
			return false;
		}
	}

	/**
	 * see suepr class
	 */
	public boolean isBroken(Ball myBouncer) {
		if (this.brickCollision(myBouncer)) {
			return this.decrementHits(myBouncer.getBallStrength());
		}
		return false;
	}
}
