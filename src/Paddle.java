import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Paddle extends ImageView {
	public static final double PADDLE_RATIO = 8;
	public static final double DEFAULT_ASPECT_RATIO = 50;

	private boolean stick;

	public Paddle(double screenSize, Image paddleIcon) {
		this.setFitHeight(screenSize / DEFAULT_ASPECT_RATIO);
		this.changeWidth(PADDLE_RATIO * (screenSize / DEFAULT_ASPECT_RATIO));
		this.setX((screenSize - this.getFitWidth()) / 2);
		this.setY(screenSize - this.getFitHeight());
		this.changePaddleImage(paddleIcon);
	}

	public void changeWidth(double newWidth) {
		this.setFitWidth(newWidth);
	}

	private void changePaddleImage(Image newIcon) {
		this.setImage(newIcon);
	}

	public void setStickyPaddle(boolean newStick) {
		this.stick = (true && newStick);
	}

	public boolean getStickyPaddle() {
		return stick;
	}
}
