import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Group;

public class SpeedBall extends Powerup {
	private Timer countDown;
	private int timeDelay;

	public SpeedBall(int powerTime) {
		countDown = new Timer();
		timeDelay = powerTime;
	}

	@Override
	public void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle) {
		// TODO Auto-generated method stub
		for (Ball myBall : ballArr) {
			myBall.setBallVelocity(2 * myBall.getBallVelocity());
			countDown.schedule(new TimerTask() {
				public void run() {
					myBall.setBallVelocity(myBall.getBallVelocity()/2);
				}
			}, timeDelay * 1000);
		}
	}

}
