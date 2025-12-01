package model;

import model.traps.*;

import java.util.List;

public class Game {
    private int _turnNum;
    private int _gridSize;

    private Player[] _players;

    public Game() {}

    public void setPlayerName(String name){
        _players = new Player[] {new Player(name), new Player("Bot")};
    }

    public void setGrid(int size, boolean islandMode) {
        if (islandMode) {

        }
        _players[0].setGrid(new Grid(size, size));
        _players[1].setGrid(new Grid(size, size));
    }

    public int getGridSize() {return _gridSize;}
    public int otherPlayer(int joueur) {return (joueur+1)%2;}


    public boolean placeTrapOnGrid(int joueur, Trap trap) {
        return _players[joueur].placeTrap(trap);
    }

    public boolean placeShipOnGrid(int joueur, Ship ship) {
        return _players[joueur].placeShip(ship);
    }

    public boolean shootOnGrid(int joueur, Cell cell) {
        return _players[joueur].shoot(_players[otherPlayer(joueur)],cell);
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
