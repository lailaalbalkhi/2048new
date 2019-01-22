package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
				board[i][j].setGridX(112 + 122 * j); // sets the actual coordinates position of the tile on the screen
				board[i][j].setGridY(62 + 123 * i);
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
		int value;

		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {

				value = board[i][j].getValue();

				if (value == 2) {
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

				if (value == 32){
					batch.draw(thirtyTwo,112 + 122 * j,62 + 123 * i);
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
		//generates a new random (2 or 4) tile given its coordinates
		int chance = (int) (Math.random() * 5);
		if (chance == 4) {
			return 4;
		}
		else {
			return 2;
		}
	}

	public void move(String direction){
		for (int i=0; i<4; i++){
			for (int j=0; j<4; j++){
				if (board[i][j].getValue()!=0){
					if (direction=="DOWN"){
						while (true){
							if (board[i+1][j].getValue()==0){
								board[i][j]=board[i+1][j];
							}
							break;
						}
					}
					if (direction=="UP"){
						while (true){
							if (board[i-1][j].getValue()==0){

							}
						}
					}
				}
			}
		}
	}

}
