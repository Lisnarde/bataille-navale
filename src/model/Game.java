package model;

import model.traps.*;
import model.weapons.Bomb;
import model.weapons.IslandSearch;
import model.weapons.Sonar;
import model.weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private int _turnNum;
    private int _gridSize;
    private boolean _islandMode;
    private List<Trap> _traps;

    private Player[] _players;

    public Game() {
        _traps = new ArrayList<>( Arrays.asList(new Tornado(), new BlackHole()) );
    }

    public int getTurnNum() {return _turnNum;}

    public String getPlayerName(int player){
        return _players[player].getName();
    }

    public int getTrapInventorySize() {return _traps.size();}
    public Trap getTrapInInventory(int trapIndex) {return _traps.get(trapIndex);}

    public void setPlayerName(String name){
        _players = new Player[] {new Player(name), new Player("Bot")};
    }

    public void setGrid(int size, boolean islandMode) {
        if (islandMode) {
            for (Player p : _players) {
                p.addWeaponInInventory(new IslandSearch(p));
            }
        }
        else {
            for (Player p : _players) {
                p.addWeaponInInventory(new Bomb());
                p.addWeaponInInventory(new Sonar());
            }
        }
        _players[0].setGrid(new Grid(size, size, 0));
        _players[1].setGrid(new Grid(size, size, 1));
        _gridSize = size;
        _islandMode = islandMode;
    }
    public void addGridObserver(GridObserver observer) {
        _players[0].addGridObserver(observer);
        _players[1].addGridObserver(observer);
    }

    public int getGridSize() {return _gridSize;}
    public int otherPlayer(int joueur) {return (joueur+1)%2;}

    public int getMaxShipsPossible() {
        int cellNumber = _gridSize*_gridSize;
        if (_islandMode) {
            cellNumber = cellNumber - (4*4);
        }
        int maxShips = (int) (cellNumber * 0.35)+1;
        return Math.max(maxShips,17);
    }
    public boolean setMaxShipsCells(int maxShip) {
        if (maxShip < 17 || getMaxShipsPossible() < maxShip) {
            return false;
        }
        _players[0].setMaxShipsCells(maxShip);
        _players[1].setMaxShipsCells(maxShip);
        return true;
    }


    public boolean placeTrapOnGrid(int joueur, Trap trap) {
        return _players[joueur].placeTrap(trap);
    }

    public boolean placeShipOnGrid(int joueur, Ship ship) {
        return _players[joueur].placeShip(ship);
    }

    public boolean placeWeaponOnIsland(int joueur, Weapon weapon, Cell cell) {
        return _players[joueur].placeWeaponOnIsland(weapon,cell);
    }

    public boolean shootOnGrid(int joueur, Cell cell) {
        return _players[joueur].shoot(_players[otherPlayer(joueur)],cell);
    }

    public PlaceableTypes getObjectTypeByPosition(int joueur, int posx, int posy) {
        return _players[joueur].getObjectByPosition(new Cell(posx, posy)).getType();
    }

    public boolean setWeapon(int joueur, int weaponIndex) {
        return _players[joueur].setEquippedWeapon(weaponIndex);
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
