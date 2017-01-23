import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Breakout extends Application {

	public static final int SIZE = 400;
	public static final String TITLE = "Breakout";
	public static final double BALL_SIZE = 10.0;
	public static final int NUMBER_OF_LIVES = 3;
	public static final int POWERUP_TIME = 5;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final String LEVEL_NAMES = "breakout_levels.txt";
	public static final String BALL_IMAGE = "ball.gif";
	public static final String POWER_BRICK_IMG = "brick10.gif";
	public static final String PADDLE_IMAGE = "paddle.gif";
	public static final String BRICK_IMAGE_FILE = "brick_images.txt";

	private Timeline animation;
	private Group root;
	private Scene myScene;
	private ArrayList<Brick> myBricks;
	private ArrayList<Ball> myBouncers;
	private Paddle myPaddle;
	private ArrayList<Powerup> myPowerups;
	private ArrayList<String> levelNames;
	private ArrayList<String> brickIcons;
	private ArrayList<PowerIcon> brickPowers;
	private Text myLevel;
	private Text myScore;
	private Text myLives;
	private int LivesLeft;
	private int CurrentLevel;
	private int Score;

	@Override
	public void start(Stage s) throws Exception {
		Scene scene = setupGame(SIZE, SIZE, BACKGROUND);
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private Scene setupGame(int width, int height, Paint background) {
		CurrentLevel = 1;
		LivesLeft = NUMBER_OF_LIVES;
		levelNames = new ArrayList<>();
		readInFile(LEVEL_NAMES, levelNames);
		brickIcons = new ArrayList<>();
		readInFile(BRICK_IMAGE_FILE, brickIcons);

		// create one top level collection to organize the things in the scene
		root = new Group();
		// create a place to see the shapes
		initiatePowerups();
		startNextLevel();
		myScene = new Scene(root, width, height+35, background);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myScene.setOnMouseMoved(e -> handleMouseInput(e.getX()));
		return myScene;
	}

	private void step(double elapsedTime) {
		for (int i = 0; i < myBouncers.size(); i++) {
			myBouncers.get(i).moveFrame(elapsedTime);
			for (int j = 0; j < myBricks.size(); j++) {
				if (myBouncers.get(i).ballBrickCollision(myBricks.get(j))) {
					Score += myBricks.get(j).getScore();
					if (myBricks.get(j).getPower() >= 0) {
						PowerIcon powerup = new PowerIcon(myBricks.get(j), SIZE, getImage(BALL_IMAGE));
						brickPowers.add(powerup);
						root.getChildren().add(powerup);
					}
					myBricks.get(j).decrementHits(myBouncers.get(i).getBallStrength());
					if (myBricks.get(j).getHitsLeft() < 0) {
						root.getChildren().remove(myBricks.get(j));
						myBricks.remove(j--);
					} else
						myBricks.get(j).setNewImage(getImage(brickIcons.get(myBricks.get(j).getHitsLeft())));
				}
			}
			myBouncers.get(i).ballWallCollision(SIZE, SIZE);
			myBouncers.get(i).ballPaddleCollision(myPaddle);
			if (myBouncers.get(i).deadBall(SIZE)) {
				root.getChildren().remove(myBouncers.get(i));
				myBouncers.remove(i--);
			}
		}
		for (int i = 0; i < brickPowers.size(); i++) {
			brickPowers.get(i).dropPowerBall(elapsedTime);
			if (brickPowers.get(i).hitPaddle(myPaddle)) {
				myPowerups.get(brickPowers.get(i).getBallPower()).mainPower(root, myBouncers, myPaddle);
				brickPowers.get(i).setY(SIZE);
			}
			if (brickPowers.get(i).hitWall(SIZE)) {
				root.getChildren().remove(brickPowers.get(i));
				brickPowers.remove(i);
			}
		}
		if (myBouncers.size() < 1) {
			LivesLeft--;
			myBouncers.add(new Ball(10, myPaddle, 300, getImage(BALL_IMAGE)));
			root.getChildren().add(myBouncers.get(0));
		}
		if (myBricks.size() == 0) {
			animation.stop();
			CurrentLevel++;
			startNextLevel();
			animation.play();
		}
		if (LivesLeft == 0) {
			// initializeNextLevel();
		}
		updateStatus();
	}

	private void handleKeyInput(KeyCode code) {
		for (Ball currBall : myBouncers) {
			if (currBall.getXAngle() == 0 && currBall.getYAngle() == 0) {
				if (code == KeyCode.SPACE)
					currBall.setAngles(-1, -1);
			}
		}
		if (code == KeyCode.M) {
			myPowerups.get(0).mainPower(root, myBouncers, myPaddle);
		}
		if (code == KeyCode.P) {
			myPowerups.get(1).mainPower(root, myBouncers, myPaddle);
		}
		if (code == KeyCode.S) {
			myPowerups.get(4).mainPower(root, myBouncers, myPaddle);
		}
		if (code == KeyCode.ESCAPE) {
			System.exit(1);
		}
	}

	private void handleMouseInput(double x) {
		myPaddle.setX(x);
		for (Ball currBall : myBouncers) {
			if (currBall.getXAngle() == 0 && currBall.getYAngle() == 0) {
				currBall.setX(x + (myPaddle.getFitWidth() / 2) - currBall.getFitWidth() / 2);
			}
		}
	}

	private void readInFile(String fileName, ArrayList<String> readArr) {
		Scanner input = new Scanner(getClass().getClassLoader().getResourceAsStream((fileName)));
		while (input.hasNextLine()) {
			readArr.add(input.nextLine());
		}
		input.close();
	}

	private void initializeLevel(String fileName) {
		myBricks = new ArrayList<>();
		Scanner input = new Scanner(getClass().getClassLoader().getResourceAsStream((fileName)));
		while (input.hasNext()) {
			int brickX = input.nextInt();
			int brickY = input.nextInt();
			int brickStrength = input.nextInt();
			int brickPower = input.nextInt();
			Image brickImg = getImage(brickIcons.get(brickStrength));
			if (brickPower >= 0)
				brickImg = getImage(POWER_BRICK_IMG);
			myBricks.add(new Brick(SIZE, brickX, brickY, brickStrength, brickPower, brickImg));
		}
		input.close();
	}

	private Image getImage(String imgFile) {
		return (new Image(getClass().getClassLoader().getResourceAsStream(imgFile)));
	}

	public void initiatePowerups() {
		myPowerups = new ArrayList<>();
		myPowerups.add(new Multiball());
		myPowerups.add(new ExtendPaddle(POWERUP_TIME));
		myPowerups.add(new SpeedBall(POWERUP_TIME));
		myPowerups.add(new PaddleStick(POWERUP_TIME));
		myPowerups.add(new StrongBall(POWERUP_TIME));
	}
	
	public void updateStatus() {
		myLives.setText("Lives Left: "+LivesLeft);
		myLevel.setText("Level: "+CurrentLevel);
		myScore.setText("Score: "+Score);
	}
	
	public void startNextLevel() {
		root.getChildren().clear();
		initializeLevel(levelNames.get(CurrentLevel - 1));
		brickPowers = new ArrayList<>();
		myPaddle = new Paddle(SIZE, getImage(PADDLE_IMAGE));
		root.getChildren().add(myPaddle);
		myBouncers = new ArrayList<>();
		myBouncers.add(new Ball(BALL_SIZE, myPaddle, 300, getImage(BALL_IMAGE)));
		for (Brick currBrick : myBricks)
			root.getChildren().add(currBrick);
		root.getChildren().add(myBouncers.get(0));
		myLives = new Text(4, SIZE+30, "Lives Left: "+LivesLeft);
		myLevel = new Text(4, SIZE+20, "Level: "+CurrentLevel);
		myScore = new Text(4, SIZE+10, "Score: "+Score);
		root.getChildren().add(myLives);
		root.getChildren().add(myLevel);
		root.getChildren().add(myScore);
	}

	public static void main(String[] args) {
		launch(args);
	}

}