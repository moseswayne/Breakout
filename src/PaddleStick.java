import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Group;

public class PaddleStick extends Powerup {
	private Timer countDown;
	private int timeDelay;

	public PaddleStick(int powerTime) {
		countDown = new Timer();
		timeDelay = powerTime;
	}

	@Override
	public void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle) {
		gamePaddle.setStickyPaddle(true);
		countDown.schedule(new TimerTask() {
			public void run() {
				gamePaddle.setStickyPaddle(false);
			}
		}, timeDelay * 1000);
	}

}
