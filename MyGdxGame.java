package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class MyGdxGame extends ApplicationAdapter {

	private static final int SIDE = 4;
	private static Tile[][] board = new Tile[SIDE][SIDE];
	private static int score = 0; //used to keep track of the points the user has accumulated
	private static boolean canMove = true;

	BitmapFont font, textFont, scoreFont;
	SpriteBatch batch;
	Texture grid, two, four, eight, sixteen, thirtyTwo, sixtyFour, oneTwentyEight, twoFiftySix, fiveTwelve, tenTwentyFour, twentyFourtyEight,
			newGameButton, scoreDisplay, gameOver;

	@Override
	public void create() {
		batch = new SpriteBatch();
		grid = new Texture("grid.png");
		two = new Texture("2.png");
		four = new Texture("4.png");
		eight = new Texture("8.png");
		sixteen = new Texture("16.png");
		thirtyTwo = new Texture("32.png");
		sixtyFour = new Texture("64.png");
		oneTwentyEight = new Texture("128.png");
		twoFiftySix = new Texture("256.png");
		fiveTwelve = new Texture("512.png");
		tenTwentyFour = new Texture("1024.png");
		twentyFourtyEight = new Texture("2048.png");
		newGameButton = new Texture("newGameButton.png");
		scoreDisplay = new Texture("scoreDisplay.png");
		gameOver = new Texture("gameOver.png");

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				board[i][j] = new Tile(i, j, 0);

			}
		}
		startGame();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(grid, 100, 50);
		batch.draw(newGameButton, 475, 625);
		batch.draw(scoreDisplay, 400, 625);
		//System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());

		font = new BitmapFont(Gdx.files.internal("2048font.fnt"));
		textFont = new BitmapFont(Gdx.files.internal("textfont.fnt"));
		scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {

				int value = board[i][j].getValue();

				if (value == 2) {
					batch.draw(two, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 4) {
					batch.draw(four, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 8) {
					batch.draw(eight, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 16) {
					batch.draw(sixteen, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 32) {
					batch.draw(thirtyTwo, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 64) {
					batch.draw(sixtyFour, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 128) {
					batch.draw(oneTwentyEight, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 256) {
					batch.draw(twoFiftySix, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 512) {
					batch.draw(fiveTwelve, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 1024) {
					batch.draw(tenTwentyFour, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 2048) {
					batch.draw(twentyFourtyEight, 112 + 122 * j, 62 + 123 * i);
				}
			}
		}
		font.draw(batch, "2048", 100, 675);
		textFont.draw(batch, "Join the numbers and get to the 2048 tile!", 100, 600);
		Integer scoreText = new Integer(score);
		scoreFont.draw(batch, scoreText.toString(), 400, 650);


		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			System.out.println("up");
			moveUp();
			printBoard();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			System.out.println("down");
			moveDown();
			printBoard();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			System.out.println("left");
			moveLeft();
			printBoard();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			System.out.println("right");
			moveRight();
			printBoard();
		}
		if (Gdx.input.getX() >= 425 && Gdx.input.getX() <= 600 && Gdx.input.getY() >= 35 && Gdx.input.getY() <= 70
				&& Gdx.input.isTouched()) { //if the button gets pressed
			batch.draw(grid, 100, 50); //clear the grid by redrawing it again
			newGame(); //generate tiles like a new game
		}
		if (!checkMoves()) { //if there are no more moves to be made
			batch.draw(gameOver, 100, 50);
		}

		batch.end();
	}

	public void printBoard() {
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				System.out.print(board[i][j].getValue() + " ");
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void startGame() { // generates 2 random tiles to start the game
		generateTile();
		generateTile();
	}

	public void newGame() {
		canMove = true;
		score = 0;
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				board[i][j].setValue(0);
			}
		}
		startGame();
	}

	public void generateTile() {

		ArrayList<Integer[]> availablePoints = new ArrayList<Integer[]>();

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				if (board[i][j].getValue() == 0) {
					Integer[] point = {i, j}; //
					availablePoints.add(point); //if a Tile has a value of 0, then it is added into the arrayList
				}
			}
		}
		int index = (int) (Math.random() * availablePoints.size()); //chooses a random one of these points
		board[availablePoints.get(index)[0]][availablePoints.get(index)[1]] = new Tile(availablePoints.get(index)[0], availablePoints.get(index)[1], randomValue());
	}

	public int randomValue() {
		//generates a new random (2 or 4)
		int chance = (int) (Math.random() * 5);
		if (chance == 4) {
			return 4;
		} else {
			return 2;
		}
	}

	public void moveUp() {
		moveTile();
	}

	public void moveDown() {
		rotate();
		rotate();
		moveTile();
		rotate();
		rotate();
	}

	public void moveRight() {
		rotate();
		rotate();
		rotate();
		moveTile();
		rotate();
	}

	public void moveLeft() {
		rotate();
		moveTile();
		rotate();
		rotate();
		rotate();
	}

	public void moveTile() {
		if (canMove) {
			boolean turn = false;
			for (int row = 0; row < SIDE ; row++) {
				for (int col = 0; col < SIDE; col++) {

					while (row + 1 < SIDE) {

						if (board[row + 1][col].getValue() == 0) {
							board[row + 1][col].setValue(board[row][col].getValue());
							board[row][col].setValue(0);
							turn = true;
						}

						if (row == 0) {
							if (board[row][col].canMerge(board[row + 1][col]) && board[row + 2][col].canMerge(board[row + 3][col])) {
								int value1 = board[row][col].getValue() * 2;
								int value2 = board[row + 2][col].getValue() * 2;

								for (int i = 0; i < SIDE; i++) {
									board[i][col].setValue(0);
								}
								board[row + 2][col].setValue(value1);
								board[row + 2][col].setMerged();
								board[row + 3][col].setValue(value2);
								board[row + 3][col].setMerged();
								turn = true;
							}
						}

						if (board[row][col].canMerge(board[row + 1][col])) {
							int value = board[row][col].getValue() * 2;
							board[row + 1][col].setValue(value);
							score += value;
							board[row + 1][col].setMerged();
							board[row][col].setValue(0);
							turn = true;
						}
						break; //i dunno fuck it bro
					}
				}
			}
			if (turn == true) { //if a turn is completed, all tiles are unmerged
				for (int i = 0; i < SIDE; i++) {
					for (int j = 0; j < SIDE; j++) {
						board[i][j].unMerge(); //once the turn is complete, the tiles must be reset to unmerged
					}
				}
				generateTile();
			}
			System.out.println("Score: " + score);
		}
	}

	public boolean checkMoves() {

		boolean horizontal = false;
		boolean vertical = false;
		boolean empty = false;

		for (int i = 0; i < SIDE; i++) { //checks if any moves moving left/right are possiblefor (int j = 0; j < SIDE - 1; j++){
			for (int j = 0; j < SIDE - 1; j++) {
				if (board[i][j].getValue() == board[i][j + 1].getValue()) {
					horizontal = true;
				}
			}
		}
		for (int i = 0; i < SIDE - 1; i++) { //checks if any moves moving up/down are possible
			for (int j = 0; j < SIDE; j++) {
				if (board[i][j].getValue() == board[i + 1][j].getValue()) {
					vertical = true;
				}

			}
		}
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				if (board[i][j].getValue() == 0) {
					empty = true;
				}
			}
		}
		if (vertical == true || horizontal == true || empty == true) {
			return true;
		} else {
			canMove = false;
			return false;

		}
	}

	public void rotate() { //rotates the entire board instead of having 4 different move functions

		Tile[][] rotated = new Tile[SIDE][SIDE];


		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				rotated[(SIDE - 1) - j][i] = board[i][j];
			}
		}

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				board[i][j] = rotated[i][j];
			}
		}

	}

}
