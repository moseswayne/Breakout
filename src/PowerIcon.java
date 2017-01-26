import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerIcon extends ImageView {
	public static final int SPEED_RATIO = 10;
	public static final int BRICK_SIZE_RATIO = 2;

	private int ballPower;
	private double dropSpeed;

	public PowerIcon(PowerBrick myBrick, double screenSize, Image ballIcon) {
		this.setX(myBrick.getX() + myBrick.getFitWidth() / 2);
		this.setY(myBrick.getY() + myBrick.getFitHeight());
		this.setFitHeight(myBrick.getFitHeight() / BRICK_SIZE_RATIO);
		this.setFitWidth(myBrick.getFitHeight() / BRICK_SIZE_RATIO);
		this.setImage(ballIcon);
		dropSpeed = screenSize / SPEED_RATIO;
		ballPower = myBrick.getPower();
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
