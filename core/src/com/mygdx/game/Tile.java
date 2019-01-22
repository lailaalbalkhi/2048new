package com.mygdx.game;

public class Tile {

    private int gridX,gridY,value;

    public Tile(int x, int y, int num) {
        gridX = x;
        gridY = y;
        value = num;
    }
    //------------------------------------------
    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getValue() {
        return value;
    }
    //------------------------------------------

    public void setValue(int num) {
        value = num;
    }

    //------------------------------------------
    public boolean canMerge(Tile otherTile){
        return value == otherTile.getValue();
    }

    public int merge(Tile otherTile){
        if (canMerge(otherTile)){
            value *= 2;
            return value;
        }
        return 1;
    }

}
