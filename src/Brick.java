import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Brick extends ImageView {
	public static final int SCORE_PER_BRICK = 100;
	public static final double SCREEN_RATIO = 25;
	public static final double HW_RATIO = 2.5;

	private Image brickPic;
	private int hitsLeft;
	private int powerUp;

	public Brick(double screenSize, double x, double y, int hardness, int powerVal, Image brickIcon) {
		powerUp = powerVal;
		this.setNewImage(brickIcon);
		this.setX(x);
		this.setY(y);
		this.setFitHeight(screenSize / SCREEN_RATIO);
		this.setFitWidth(this.getFitHeight() * HW_RATIO);
		hitsLeft = hardness;
	}

	public int getPower() {
		return this.powerUp;
	}

	public int getHitsLeft() {
		return hitsLeft;
	}

	public int getScore() {
		return (hitsLeft + 1) * (hitsLeft + 1) * SCORE_PER_BRICK;
	}

	public void decrementHits(int numHits) {
		hitsLeft -= numHits;
	}

	public boolean brickHit(double ballCoordX, double ballCoordY) {
		/**
		 * determines quickly whether a brick is hit
		 */
		if (ballCoordX > this.getX() && ballCoordX < (this.getX() + this.getFitWidth()) && ballCoordY > this.getY()
				&& ballCoordY < (this.getY() + this.getFitHeight())) {
			return true;
		}
		return false;
	}

	public void setNewImage(Image newImage) {
		/**
		 * changes image so that the brick can transition between states
		 */
		brickPic = newImage;
		this.setImage(brickPic);
	}
}
