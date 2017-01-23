import java.util.ArrayList;

import javafx.scene.Group;

public class GameUpdate {
	public void updateBalls(Group root, ArrayList<Ball> myBouncers, ArrayList<Ball> toAdd, ArrayList<Ball> toRemove) {
		for (Ball currBall : toRemove) {
			myBouncers.remove(currBall);
			root.getChildren().remove(currBall);
		}
		for (Ball currBall : toAdd) {
			myBouncers.add(currBall);
			root.getChildren().add(currBall);
		}
		toRemove.clear();
		toAdd.clear();
	}

	public void updateBricks(Group root, ArrayList<Brick> myBouncers, ArrayList<Brick> toAdd,
			ArrayList<Brick> toRemove) {
		for (Brick currBrick : toRemove) {
			myBouncers.remove(currBrick);
			root.getChildren().remove(currBrick);
		}
		for (Brick currBrick : toAdd) {
			myBouncers.add(currBrick);
			root.getChildren().add(currBrick);
		}
		toRemove.clear();
		toAdd.clear();
	}

	public void updateScene(Group root, ArrayList<Ball> myBouncers) {
		for (Ball currBall : myBouncers)
			root.getChildren().add(currBall);
	}

}
