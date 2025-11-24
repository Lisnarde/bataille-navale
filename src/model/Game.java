package model;

public class Game {
    private int _turnNum;

    private Player[] _player;

    public Game() {}

    public void setPlayerName(String name){

    }

    public void setGrid(int width, int height, boolean islandMode) {
        Grid grid = new Grid(width, height);
    }

    public Boolean isTheGameFinished(){
        return false;
    }
}
