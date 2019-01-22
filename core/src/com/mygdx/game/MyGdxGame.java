package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture image;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		image = new Texture("grid.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(image, 100, 75);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		image.dispose();
	}
	private static final int SIDE = 4; //constant used for the size of the board (instead of always typing 4)
    private static Tile [][] board = new Tile[SIDE][SIDE];

    public static void startBoard(){

        int startX1 = (int)(Math.random()*SIDE);
        int startY1 = (int)(Math.random()*SIDE);
        int startX2 = (int)(Math.random()*SIDE);
        int startY2 = (int)(Math.random()*SIDE);

        randomTile(startX1,startY1);
        randomTile(startX2,startY2);

    }

    public static void randomTile(int x, int y){
        //generates a new random (2 or 4) tile given its coordinates
        int chance = (int)(Math.random()*5);
        if (chance == 4){
            board[x][y].setValue(4);
        }
        else{
            board[x][y].setValue(2);
        }
    }

    public static void generateTile() {

        ArrayList <Integer[]> availablePoints = new ArrayList<Integer[]>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 0) {
                    Integer[] point = {i,j}; //
                    availablePoints.add(point); //if a Tile has a value of 0, then it is added into the arrayList
                }
            }
        }

        int index = (int)(Math.random()*availablePoints.size()); //chooses a random one of these points

        randomTile(availablePoints.get(index)[0],availablePoints.get(index)[1]);
        //generates a new tile at the set random points

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

