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

	SpriteBatch batch;
	Texture grid, two, four, eight, sixteen, thirtyTwo, sixtyFour, oneTwentyEight, twoFiftySix, fiveTwelve, tenTwentyFour, twentyFourtyEight;

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

		for (int i = 0; i < SIDE; i++){
			for (int j = 0; j < SIDE; j++) {
				board[i][j] = new Tile(i,j,0);

			}
		}
		startGame();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(grid,100,50);

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {

				int value = board[i][j].getValue();

				if (value == 2){
					batch.draw(two, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 4){
					batch.draw(four,112 + 122 * j, 62 + 123 * i);
				}
				if (value == 8){
					batch.draw(eight,112 + 122 * j,62 + 123 * i);
				}
				if (value == 16){
					batch.draw(sixteen,112 + 122 * j,62 + 123 * i);
				}
				if (value == 32) {
					batch.draw(thirtyTwo, 112 + 122 * j, 62 + 123 * i);
				}
				if (value == 64){
					batch.draw(sixtyFour,112 + 122 * j,62 + 123 * i);
				}
				if (value == 128){
					batch.draw(oneTwentyEight,112 + 122 * j,62 + 123 * i);
				}
				if (value == 256){
					batch.draw(twoFiftySix,112 + 122 * j,62 + 123 * i);
				}
				if (value == 512){
					batch.draw(fiveTwelve,112 + 122 * j,62 + 123 * i);
				}
				if (value == 1024){
					batch.draw(tenTwentyFour,112 + 122 * j,62 + 123 * i);
				}
				if (value == 2048){
					batch.draw(twentyFourtyEight,112 + 122 * j,62 + 123 * i);
				}
			}
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void startGame(){ // generates 2 random tiles to start the game
		generateTile();
		generateTile();
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
		board[availablePoints.get(index)[0]][availablePoints.get(index)[1]] = new Tile(availablePoints.get(index)[0], availablePoints.get(index)[1],randomValue());
	}

	public int randomValue(){
		//generates a new random (2 or 4)
		int chance = (int) (Math.random() * 5);
		if (chance == 4) {
			return 4;
		}
		else {
			return 2;
		}
	}

	public void game(){

		while (checkMoves()) {

			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				moveUp();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				moveDown();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				moveLeft();
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				moveRight();
			}
			if (checkMoves() == false){
				break;
			}

		}
	}

	public void moveUp(){
		rotate();
		rotate();
		moveTile();
		rotate();
		rotate();
	}

	public void moveDown(){
		moveTile();
	}

	public void moveRight(){
		rotate();
		rotate();
		rotate();
		moveTile();
		rotate();
	}

	public void moveLeft(){
		rotate();
		moveTile();
		rotate();
		rotate();
		rotate();
	}

	public boolean moveTile(){

		boolean turn = false; //used to check if a move was made or not (therefore generating a new tile)
		int xIncrease = 1;
		int yIncrease = 0;

		for (int row = 0; row < SIDE; row++) {
			for (int col = 0; col < SIDE; col++) {

				if (board[row][col].getValue() == 0) { //

					int nextRow = row + xIncrease;
					int nextCol = col + yIncrease;

					while (nextRow >= 0 && nextRow <= SIDE && nextCol >= 0 && nextCol <= SIDE) {

						Tile currentTile = board[row][col];
						Tile nextTile = board[nextRow][nextCol];

						if (nextTile.getValue() == 0) { //checks the case where the next tile is 0

							board[nextRow][nextCol].setValue(currentTile.getValue()); //tile being changed has its value changed
							board[row][col].setValue(0); //previous tile is set to 0

							row = nextRow;
							col = nextCol;
							nextRow += xIncrease; //next row is increased by 1 every time
							nextCol += yIncrease;
							turn = true; //turn has been completed so boolean is set to true

						}
						else if (nextTile.canMerge(currentTile)) { //checks the other case where the next tile is able to merge with the current one

							int value = nextTile.getValue() * 2;

							nextTile.setValue(value); //the next tile has it's value multiplied by 2 and then set to that amount
							nextTile.setMerged();
							score += value; //current score is added to total score
							board[row][col].setValue(0); //previous tile is set to a value of 0
							turn = true; //turn has been completed so boolean is set to true
							break;

						}
						else {
							break;
						}
					}
				}
			}
		}

		if (turn) { //if a turn is completed, all tiles are unmerged

			for (int i = 0; i < SIDE; i++) {
				for (int j = 0; j < SIDE; j++) {
					board[i][j].unMerge(); //once the turn is complete, the tiles must be reset to unmerged
				}
			}
		}

		generateTile(); //after a turn is completed, a new random tile is generated
		return turn;

	}

	public boolean checkMoves(){

		boolean horizontal = false;
		boolean vertical = false;

		for (int i = 0; i < SIDE; i++){ //checks if any moves moving left/right are possiblefor (int j = 0; j < SIDE - 1; j++){
			for (int j = 0; j < SIDE - 1; j++){
				if (board[i][j].getValue() == board[i][j + 1].getValue()){
					horizontal = true;
				}
			}
		}

		for (int i = 0; i < SIDE - 1; i++){ //checks if any moves moving up/down are possible
			for (int j = 0; j < SIDE; j++){
				if (board[i][j].getValue() == board[i + 1][j].getValue()){
					vertical = true;
				}

			}
		}

		if (vertical == true || horizontal == true || vertical == true && horizontal == true){
			return true;
		}

		else{
			return false;
		}

	}

	public void rotate(){
		for (int i = 0; i < SIDE; i++){
			for (int j = 0; j < SIDE; j++){
				//board[i][j].setGridX(board[i][j].getGridY());
				//board[i][j].setGridY(Math.abs(board[i][j].getGridX() - 3));
				board[(SIDE-1)-j][i] = board[i][j];
			}
		}
	}

}
