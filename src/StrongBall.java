import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Group;

public class StrongBall extends Powerup{
	private Timer countDown;
	private int timeDelay;

	public StrongBall(int powerTime) {
		countDown = new Timer();
		timeDelay = powerTime;
	}
	
	@Override
	public void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle) {
		for(Ball myBall : ballArr) {
			myBall.setBallStrength(2*myBall.getBallStrength());
			countDown.schedule(new TimerTask() {
				public void run() {
					myBall.setBallStrength(myBall.getBallStrength() / 2);
				}
			}, timeDelay * 1000);
		}
	}

}
