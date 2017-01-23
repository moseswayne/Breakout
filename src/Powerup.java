import java.util.ArrayList;

import javafx.scene.Group;

/**
 * powerups dealt with as extensions of an abstract class, allows easier use beacuse of polymorphism
 *
 */
public abstract class Powerup {
	public abstract void mainPower(Group root, ArrayList<Ball> ballArr, Paddle gamePaddle);

}
