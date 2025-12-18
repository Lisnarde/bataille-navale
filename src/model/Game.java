package model;

import model.traps.*;
import model.weapons.Bomb;
import model.weapons.IslandSearch;
import model.weapons.Sonar;
import model.weapons.Weapon;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;

public class Game implements GridObserver{
    private List<GameObserver> _observers;
    private int _turnNum;
    private int _gridSize;
    private boolean _islandMode;
    private List<Trap> _traps;
    private Map<ShipTypes,Integer> _numberPerShip;

    private Player[] _players;

    public Game() {
        _observers = new ArrayList<>();
        _traps = new ArrayList<>( Arrays.asList(new Tornado(), new BlackHole()) );
    }
    private void notifyObserversNoMoreShip(int joueur) {
        for (GameObserver obs : _observers) {
            obs.updateNoMoreShips(joueur);
        }
        System.out.println("TEST : plus de bateaux game");
    }
    private void notifyObserversTurnNumber() {
        for (GameObserver obs : _observers) {
            obs.updateTurnNumber(_turnNum);
        }
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
        _players[0].setGrid(new Grid(size, size, 0, islandMode));
        _players[1].setGrid(new Grid(size, size, 1, islandMode));
        _gridSize = size;
        _islandMode = islandMode;

        addGridObserver(this);
    }
    public void addGridObserver(GridObserver observer) {
        _players[0].addGridObserver(observer);
        _players[1].addGridObserver(observer);
    }
    public void addGameObserver(GameObserver observer) {
        _observers.add(observer);
    }

    public int getGridSize() {return _gridSize;}
    public int otherPlayer(int joueur) {return (joueur+1)%2;}

    public int getMaxShipsPossible() {
        int cellNumber = _gridSize*_gridSize;
        if (_islandMode) {
            cellNumber = cellNumber - (4*4);
        }
        int maxShips = (int) (cellNumber * 0.35)+1;
        return Math.max(maxShips,18);
    }
    public boolean setMaxShipsCells(int maxShip) {
        if (maxShip < 17 || getMaxShipsPossible() < maxShip) {
            return false;
        }
        _players[0].setMaxShipsCells(maxShip);
        _players[1].setMaxShipsCells(maxShip);
        return true;
    }

    public boolean setNumberPerShip(Map<ShipTypes,Integer> numberPerShip) {
        Integer total = 0;
        for (Map.Entry<ShipTypes, Integer> entry : numberPerShip.entrySet()) {
            total += entry.getKey().getSize() * entry.getValue();
        }
        if (17 <= total && total <= getMaxShipsPossible()) {
            _numberPerShip = numberPerShip;
            return true;
        }
        return false;
    }

    public int getNumberMaxOfShip(ShipTypes shipTypes) {
        return _numberPerShip.get(shipTypes);
    }


    public boolean placeTrapOnGrid(int joueur, Trap trap) {
        return _players[joueur].placeTrap(trap);
    }

    public boolean placeShipOnGrid(int joueur, Ship ship) {
        if (_players[joueur].getNumberOfShipByType(ship.geyShipType()) < getNumberMaxOfShip(ship.geyShipType())) {
            return _players[joueur].placeShip(ship);
        }
        return false;
    }

    public boolean placeWeaponOnIsland(int joueur, Weapon weapon, Cell cell) {
        return _players[joueur].placeWeaponOnIsland(weapon,cell);
    }

    public boolean shootOnGrid(int joueur, Cell cell) {
        if (joueur == 1) {
            _turnNum++;
            notifyObserversTurnNumber();
        }
        return _players[joueur].shoot(_players[otherPlayer(joueur)],cell);
    }

    public PlaceableTypes getObjectTypeByPosition(int joueur, int posx, int posy) {
        Placeable p = _players[joueur].getObjectByPosition(new Cell(posx, posy));
        if (p != null) {
            return p.getType();
        }
        return null;
    }

    public int getWeaponInventorySize(int joueur) {
        return _players[joueur].getWeaponInventorySize();
    }
    public String getWeaponNameInInventory(int joueur, int weaponIndex) {
        return _players[joueur].getWeaponInInventory(weaponIndex).getWeaponType().name();
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


    @Override public void updateShoot(int joueur, int posx, int posy, boolean hit) {}
    @Override public void updateTrapActivated(int joueur, int posx, int posy, TrapTypes trapType) {}
    @Override public void updateSearch(int joueur, int posx, int posy, PlaceableTypes objectFound) {}
    @Override public void updateShipCellPlaced(int joueur, int posx, int posy) {}
    @Override public void updateTrapPlaced(int joueur, int posx, int posy, TrapTypes trapType) {}
    @Override public void updateShipCellDrowned(int joueur, int posx, int posy) {}
    @Override
    public void updateNoMoreShips(int joueur) {
        notifyObserversNoMoreShip(joueur);
    }
}
