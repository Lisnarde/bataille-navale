package model;

public class Game {
    private int _turnNum;

    private Player[] _players;

    public Game() {}

    public void setPlayerName(String name){
        _players = new Player[] {new Player(name), new Player("Bot")};
    }

    public void setGrid(int width, int height, boolean islandMode) {
        Grid grid = new Grid(width, height);
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
