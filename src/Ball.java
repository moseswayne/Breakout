import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball extends ImageView {
	public static final int DEFAULT_STRENGTH = 1;

	private Image ballPic;
	private int ballStrength;
	private int ballVelocity;
	private double xAngle;
	private double yAngle;

	public Ball(double radius, Paddle mainPaddle, int speed, Image ballIcon) {
		setBallImage(ballIcon);
		this.setImage(ballIcon);
		this.setFitHeight(2 * radius);
		this.setFitWidth(2 * radius);
		this.setX(mainPaddle.getX() + (mainPaddle.getFitWidth() / 2) - radius);
		this.setY(mainPaddle.getY() - (radius * 2));
		this.setAngles(0, 0);
		setBallVelocity(speed);
		setBallStrength(DEFAULT_STRENGTH);
	}

	private void setBallImage(Image newBallIcon) {
		ballPic = newBallIcon;
		this.setImage(ballPic);
	}

	public void setAngles(double newXAng, double newYAng) {
		xAngle = newXAng;
		yAngle = newYAng;
	}

	public double getXAngle() {
		return this.xAngle;
	}

	public double getYAngle() {
		return this.yAngle;
	}

	public void setBallVelocity(int newVelocity) {
		ballVelocity = newVelocity;
	}

	public int getBallVelocity() {
		return ballVelocity;
	}

	public void setBallStrength(int newStrength) {
		ballStrength = newStrength;
	}

	public int getBallStrength() {
		return ballStrength;
	}

	public boolean ballBrickCollision(Brick currBrick) {
		boolean didHit = false;
		if ((currBrick.brickHit(this.getX() + this.getFitWidth() / 2, this.getY()) && yAngle < 0)
				|| (currBrick.brickHit(this.getX() + this.getFitWidth() / 2, this.getY() + this.getFitHeight())
						&& yAngle >= 0)) {
			this.setAngles(xAngle, -1 * yAngle);
			didHit = true;
		}

		if ((currBrick.brickHit(this.getX() + this.getFitWidth(), this.getY() + this.getFitHeight() / 2) && xAngle < 0)
				|| (currBrick.brickHit(this.getX(), this.getY() + this.getFitHeight() / 2) && xAngle >= 0)) {
			this.setAngles(-1 * xAngle, yAngle);
			didHit = true;
		}
		return didHit;
	}

	public void ballWallCollision(double sceneWidth, double sceneHeight) {
		if (this.getY() < 0 && yAngle < 0) {
			this.setAngles(xAngle, -1 * yAngle);
		}
		if ((this.getX() < 0 && xAngle < 0) || (this.getX() + this.getFitWidth() > sceneWidth && xAngle >= 0)) {
			this.setAngles(-1 * xAngle, yAngle);
		}
	}

	public void ballPaddleCollision(Paddle currPaddle) {
		if ((this.getX() + this.getFitWidth() / 2 > currPaddle.getX())
				&& (this.getX() - this.getFitWidth() / 2 < currPaddle.getX() + currPaddle.getFitWidth())
				&& ((this.getY() + this.getFitHeight()) == currPaddle.getY() && yAngle > 0)) {
			// by preserving the scale of magnitude of the direction vectors,
			// the program guarantees consistent speed
			double newXAngle, newYAngle;
			if (currPaddle.getStickyPaddle()) {
				newXAngle = 0;
				newYAngle = 0;
			} else {
				double directionScalar = Math.sqrt(Math.pow(this.xAngle, 2) + Math.pow(this.yAngle, 2));
				newXAngle = ((this.getX() + (this.getFitWidth() / 2))
						- (currPaddle.getX() + currPaddle.getFitWidth() / 2)) / ((currPaddle.getFitWidth() / 2))
						* directionScalar;
				if (newXAngle >= directionScalar)
					newXAngle = xAngle;
				newYAngle = -1 * Math.sqrt(Math.pow(directionScalar, 2) - Math.pow(newXAngle, 2));
			}

			this.setAngles(newXAngle, newYAngle);
		}
	}

	public boolean deadBall(double yBound) {
		return (this.getY() > yBound);
	}

	public void moveFrame(double elapsedTime) {
		if (xAngle != 0 && yAngle != 0) {
			this.setX(this.getX() + xAngle * ballVelocity * elapsedTime);
			this.setY(this.getY() + yAngle * ballVelocity * elapsedTime);
		}
	}

}
