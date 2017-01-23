import java.util.ArrayList;

import javafx.scene.Group;

public class Multiball extends Powerup {

	@Override
	public void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle) {
		Ball multi = new Ball(ballArr.get(0).getFitHeight() / 2, gamePaddle, ballArr.get(0).getBallVelocity(),
				ballArr.get(0).getImage());
		multi.setX(ballArr.get(0).getX());
		multi.setY(ballArr.get(0).getY());
		multi.setAngles(-1 * ballArr.get(0).getXAngle(), -1 * ballArr.get(0).getYAngle());
		ballArr.add(multi);
		root.getChildren().add(multi);
	}
}
