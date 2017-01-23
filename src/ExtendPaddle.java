import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Group;

public class ExtendPaddle extends Powerup {
	private Timer countDown;
	private int timeDelay;

	public ExtendPaddle(int powerTime) {
		countDown = new Timer();
		timeDelay = powerTime;
	}

	@Override
	public void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle) {
		gamePaddle.setFitWidth(2 * gamePaddle.getFitWidth());
		countDown.schedule(new TimerTask() {
			public void run() {
				gamePaddle.setFitWidth(gamePaddle.getFitWidth() / 2);
			}
		}, timeDelay * 1000);
	}
}
