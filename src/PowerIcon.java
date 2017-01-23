import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerIcon extends ImageView {
	public static final int SPEED_RATIO = 10;
	public static final int BRICK_SIZE_RATIO = 2;

	private int ballPower;
	private double dropSpeed;

	public PowerIcon(Brick powerBrick, double screenSize, Image ballIcon) {
		this.setX(powerBrick.getX() + powerBrick.getFitWidth() / 2);
		this.setY(powerBrick.getY() + powerBrick.getFitHeight());
		this.setFitHeight(powerBrick.getFitHeight() / BRICK_SIZE_RATIO);
		this.setFitWidth(powerBrick.getFitHeight() / BRICK_SIZE_RATIO);
		this.setImage(ballIcon);
		dropSpeed = screenSize / SPEED_RATIO;
		ballPower = powerBrick.getPower();
	}

	public int getBallPower() {
		return ballPower;
	}

	public void dropPowerBall(double elapsedTime) {
		this.setY(this.getY() + (dropSpeed * elapsedTime));
	}

	public boolean hitWall(double screenSize) {
		return (this.getY() + this.getFitHeight() >= screenSize);
	}

	public boolean hitPaddle(Paddle myPaddle) {
		return (this.getBoundsInParent().intersects(myPaddle.getBoundsInParent()));
	}

}
