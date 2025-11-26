package model;

public class Game {
    private int _turnNum;
    private int _gridSize;

    private Player[] _players;

    public Game() {}

    public void setPlayerName(String name){
        _players = new Player[] {new Player(name), new Player("Bot")};
    }

    public void setGrid(int size, boolean islandMode) {
        Grid grid = new Grid(size, size);
        _gridSize = size;
    }

    public int getGridSize() {return _gridSize;}


    public boolean placeShipOnGrid() {
        return false;
    }


    public Boolean isTheGameFinished() {
        return _players[0].hasNoMoreShips() || _players[1].hasNoMoreShips();
    }
    public Player whoWon() {
        if (isTheGameFinished()) {
            return _players[1].hasNoMoreShips() ? _players[0] : _players[1];
        }
        return null;
    }
}
