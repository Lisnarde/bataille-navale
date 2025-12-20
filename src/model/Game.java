package model;

import model.traps.*;
import model.weapons.*;

import java.util.*;

public class Game implements GridObserver{
    private List<GameObserver> _observers;
    private int _turnNum;
    private int _gridSize;
    private boolean _islandMode;
    private List<TrapTypes> _traps;
    private List<WeaponTypes> _globalWeaponInventory;
    private Map<ShipTypes,Integer> _numberPerShip;

    private Player[] _players;

    public Game() {
        _observers = new ArrayList<>();
        _traps = new ArrayList<>( Arrays.asList(TrapTypes.TORNADO, TrapTypes.BLACKHOLE) );
        _globalWeaponInventory = new ArrayList<>();
    }
    private void notifyObserversNoMoreShip(int player) {
        for (GameObserver obs : _observers) {
            obs.updateNoMoreShips(player);
        }
        System.out.println("TEST : plus de bateaux game");
    }
    private void notifyObserversTurnNumber() {
        for (GameObserver obs : _observers) {
            obs.updateTurnNumber(_turnNum);
        }
    }
    private void notifyObserversWeaponFound(int player, WeaponTypes weaponType) {
        for (GameObserver obs : _observers) {
            obs.updateWeaponFound(player, weaponType);
        }
    }
    private void notifyObserversWeaponRemoved(int player, WeaponTypes weaponType) {
        for (GameObserver obs : _observers) {
            obs.updateWeaponRemoved(player, weaponType);
        }
    }

    public boolean isIslandModeActivated() {
        return _islandMode;
    }

    public int getTurnNum() {return _turnNum;}

    public String getPlayerName(int player){
        return _players[player].getName();
    }

    public int getTrapInventorySize() {return _traps.size();}
    public TrapTypes getTrapInInventory(int trapIndex) {return _traps.get(trapIndex);}

    public int getGlobalWeaponInventorySize() {return _globalWeaponInventory.size();}
    public WeaponTypes getWeaponInGlobalInventory(int weaponIndex) {return _globalWeaponInventory.get(weaponIndex);}

    public void setPlayerName(String name){
        _players = new Player[] {new Player(name), new Player("Bot")};
    }

    public void setGrid(int size, boolean islandMode) {
        if (islandMode) {
            for (Player p : _players) {
                p.addWeaponInInventory(new IslandSearch(p));
            }
            _globalWeaponInventory.add(WeaponTypes.BOMB);
            _globalWeaponInventory.add(WeaponTypes.SONAR);
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

        _players[0].setMaxShipsCells(getMaxShipsCellsPossible());
        _players[1].setMaxShipsCells(getMaxShipsCellsPossible());

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
    public int otherPlayer(int player) {return (player+1)%2;}

    public int getMaxShipsCellsPossible() {
        int cellNumber = _gridSize*_gridSize;
        if (_islandMode) {
            cellNumber = cellNumber - (4*4);
        }
        int maxShips = (int) (cellNumber * 0.35);
        return Math.max(maxShips,17);
    }

    public boolean setNumberPerShip(Map<ShipTypes,Integer> numberPerShip) {
        Integer total = 0;
        for (Map.Entry<ShipTypes, Integer> entry : numberPerShip.entrySet()) {
            total += entry.getKey().getSize() * entry.getValue();
        }
        if (17 <= total && total <= getMaxShipsCellsPossible()) {
            _numberPerShip = numberPerShip;
            return true;
        }
        return false;
    }
    public Map<ShipTypes,Integer> getNumberPerShip() {
        return _numberPerShip;
    }

    public int getNumberMaxOfShip(ShipTypes shipTypes) {
        return _numberPerShip.get(shipTypes);
    }

    public boolean isAllTheShipsAndTrapsPlaced(int player) {
        for (Map.Entry<ShipTypes,Integer> entry : _numberPerShip.entrySet()) {
            if (entry.getValue() != _players[player].getNumberOfShipByType(entry.getKey())) {
                return false;
            }
        }
        for (TrapTypes trapType : _traps) {
            if (_players[player].getNumberOfTrapsByType(trapType) != 1) {
                return false;
            }
        }
        return true;
    }
    public boolean isAllTheWeaponsPlaced(int player) {
        for (WeaponTypes weaponType : _globalWeaponInventory) {
            if (_players[player].getNumberOfWeaponOnIslandByType(weaponType) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean placeTrapOnGrid(int player, Trap trap) {
        return _players[player].placeTrap(trap);
    }

    public boolean placeShipOnGrid(int player, Ship ship) {
        if (_players[player].getNumberOfShipByType(ship.getShipType()) < getNumberMaxOfShip(ship.getShipType())) {
            return _players[player].placeShip(ship);
        }
        return false;
    }

    public boolean placeWeaponOnIsland(int player, Weapon weapon, Cell cell) {
        return _players[player].placeWeaponOnIsland(weapon,cell);
    }

    public boolean shootOnGrid(int player, Cell cell) {
        boolean valide = _players[player].shoot(_players[otherPlayer(player)],cell);
        if (valide && player == 1) {
            _turnNum++;
            notifyObserversTurnNumber();
        }
        Weapon weaponUsed = _players[player].getEquippedWeapon();
        if (valide && weaponUsed.getWeaponType() != WeaponTypes.MISSILE && weaponUsed.getWeaponType() != WeaponTypes.ISLANDSEARCH) {
            _players[player].removeWeaponInInventory(weaponUsed);
            _players[player].setEquippedWeapon(0);
            notifyObserversWeaponRemoved(player,weaponUsed.getWeaponType());
        }
        return valide;
    }

    public PlaceableTypes getObjectTypeByPosition(int player, int posx, int posy) {
        Placeable p = _players[player].getObjectByPosition(new Cell(posx, posy));
        if (p != null) {
            return p.getType();
        }
        return null;
    }

    public int getWeaponInventorySize(int player) {
        return _players[player].getWeaponInventorySize();
    }
    public WeaponTypes getWeaponTypeInInventory(int player, int weaponIndex) {
        return _players[player].getWeaponInInventory(weaponIndex).getWeaponType();
    }
    public boolean setWeapon(int player, int weaponIndex) {
        return _players[player].setEquippedWeapon(weaponIndex);
    }

    public void washGrid(int player) {
        _players[player].washGrid();
    }

    public Boolean isTheGameFinished() {
        return _players[0].hasNoMoreShips() || _players[1].hasNoMoreShips();
    }
    public int whoWon() {
        if (isTheGameFinished()) {
            return _players[1].hasNoMoreShips() ? 0 : 1;
        }
        return -1;
    }
    public Player getPlayer(int index) {
        return _players[index];
    }


    @Override public void updateShoot(int player, int posx, int posy, boolean hit) {}
    @Override public void updateTrapActivated(int player, int posx, int posy, TrapTypes trapType) {}
    @Override public void updateSearch(int player, int posx, int posy, WeaponTypes objectFound) {
        if (objectFound != null) {
            notifyObserversWeaponFound(player, objectFound);
        }
    }
    @Override public void updateShipCellPlaced(int player, int posx, int posy) {}
    @Override public void updateTrapPlaced(int player, int posx, int posy, TrapTypes trapType) {}
    @Override
    public void updateWeaponPlacedOnIsland(int player, int posx, int posy, WeaponTypes weaponType) {}
    @Override public void updateShipCellDrowned(int player, int posx, int posy) {}
    @Override
    public void updateNoMoreShips(int player) {
        notifyObserversNoMoreShip(player);
    }
}
