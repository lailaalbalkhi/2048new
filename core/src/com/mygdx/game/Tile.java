package com.mygdx.game;

public class Tile {

    private int gridX,gridY,value;

    public Tile(int x, int y, int num) {
        gridX = x;
        gridY = y;
        value = num;
    }
    //------------------------------------------

    public int getValue() {
        return value;
    }
    //------------------------------------------

    public void setValue(int num) {
        value = num;
    }

    public void setGridX(int x) {
        gridX = x;
    }

    public void setGridY(int y) {
        gridY = y;
    }

    //------------------------------------------
    public boolean canMerge(Tile otherTile){
        return value == otherTile.getValue();
    }

    public int mergeWith(Tile otherTile){
        if (canMerge(otherTile)){
            value *= 2;
            return value;
        }
        return 1;
    }

}
