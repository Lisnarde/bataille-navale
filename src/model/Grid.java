package model;

import model.traps.Trap;
import model.traps.TrapTypes;
import model.weapons.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int _width;
    private int _height;
    private int _player;
    private boolean _islandMode;
    private int _maxShipsCells;
    private List<Placeable> _placedObjects;
    private List<GridObserver> _observers;

    public Grid(int width, int height, int player){
        this(width,height,player,false);
    }
    public Grid(int width, int height, int player, boolean islandMode) {
        _width = width;
        _height = height;
        _player = player;
        _maxShipsCells = 0;
        _placedObjects = new ArrayList<>();
        _observers = new ArrayList<>();
        _islandMode = islandMode;
        if (_islandMode) {
            constructIsland();
        }
    }

    private void constructIsland() {
        if (_width == 10 && _height == 10) {
            for (int y=3; y<7; y++) {
                for (int x=3; x<7; x++) {
                    _placedObjects.add(new IslandPart(new Cell(x,y)));
                }
            }
        }
    }

    public void addObserver(GridObserver observer) {
        _observers.add(observer);
    }
    public void notifyObserversShoot(int posx, int posy, boolean hit) {
        for (GridObserver observer : _observers) {
            observer.updateShoot(_player, posx, posy, hit);
        }
    }
    public void notifyObserversTrapActivated(int posx, int posy, TrapTypes trapType) {
        for (GridObserver observer : _observers) {
            observer.updateTrapActivated(_player, posx, posy, trapType);
        }
    }
    public void notifyObserversSearch(int posx, int posy, WeaponTypes objectFound) {
        for (GridObserver observer : _observers) {
            observer.updateSearch(_player, posx, posy, objectFound);
        }
    }
    public void notifyObserversShipCellPlaced(int posx, int posy) {
        for (GridObserver observer : _observers) {
            observer.updateShipCellPlaced(_player, posx, posy);
        }
    }
    public void notifyObserversTrapPlaced(int posx, int posy, TrapTypes trapType) {
        for (GridObserver observer : _observers) {
            observer.updateTrapPlaced(_player, posx, posy, trapType);
        }
    }
    public void notifyObserversWeaponPlaced(int posx, int posy, WeaponTypes weaponType) {
        for (GridObserver observer : _observers) {
            observer.updateWeaponPlacedOnIsland(_player, posx, posy, weaponType);
        }
    }
    public void notifyObserversShipCellDrowned(int posx, int posy){
        for (GridObserver obs : _observers) {
            obs.updateShipCellDrowned(_player, posx, posy);
        }
    }
    public void notifyObserversNoMoreShip() {
        for (GridObserver obs : _observers) {
            obs.updateNoMoreShips(_player);
        }
    }

    public void setMaxShipsCells(int maxShipsCells) {
        _maxShipsCells = maxShipsCells;
    }


    public boolean isOccupied(Cell cell){
        for (Placeable p : _placedObjects){
            if (p.getType()!=PlaceableTypes.IMPACT && p.hasThisPosition(cell)){
                return true;
            }
        }
        return false;
    }
    public boolean isOccupiedBy(Cell cell, PlaceableTypes type) {
        for (Placeable p : _placedObjects){
            if (p.getType() == type && p.hasThisPosition(cell)) {
                return true;
            }
        }
        return false;
    }

    public Placeable getObjectByPosition(Cell cell) {
        for (Placeable p : _placedObjects) {
            if (p.hasThisPosition(cell)) {
                return p;
            }
        }
        return null;
    }

    public int getNumberOfShipByType(ShipTypes shipType) {
        int nb = 0;
        for (Placeable p : _placedObjects) {
            if (p.getType() == PlaceableTypes.SHIP) {
                if ( ((Ship)p).getShipType() == shipType ) {
                    nb++;
                }
            }
        }
        return nb;
    }
    public int getNumberOfTrapByType(TrapTypes trapType) {
        int nb = 0;
        for (Placeable p : _placedObjects) {
            if (p.getType() == PlaceableTypes.TRAP) {
                if ( ((Trap)p).getTrapType() == trapType ) {
                    nb++;
                }
            }
        }
        return nb;
    }
    public int getNumberOfWeaponOnIslandByType(WeaponTypes weaponType) {
        int nb = 0;
        for (Placeable p : _placedObjects) {
            if (p.getType() == PlaceableTypes.ISLANDPART) {
                Placeable p2 = ((IslandPart)p).getPlacedObject();
                if ( p2 != null && p2.getType() == PlaceableTypes.WEAPON && ((Weapon)p2).getWeaponType() == weaponType ) {
                    nb++;
                }
            }
        }
        return nb;
    }

    public boolean isInGrid(Cell cell) {
        return 0 <= cell.getX() && cell.getX() < _width && 0<= cell.getY() && cell.getY() < _height;
    }

    public boolean canPlaceObject(Placeable placeable) {
        if (placeable.getType() == PlaceableTypes.TRAP && getNumberOfTrapByType(((Trap)placeable).getTrapType()) > 0 ) {return false;}
        for (int i=0; i< placeable.getSize(); i++) {
            if (!isInGrid(placeable.getCell(i)) || isOccupied(placeable.getCell(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean placeObject(Placeable placeable){
        if (canPlaceObject(placeable)) {
            _placedObjects.add(placeable);
            for (int i=0; i< placeable.getSize(); i++) {
                if (placeable.getType() == PlaceableTypes.SHIP) {
                    notifyObserversShipCellPlaced(placeable.getCell(i).getX(), placeable.getCell(i).getY());
                } else if (placeable.getType() == PlaceableTypes.TRAP) {
                    notifyObserversTrapPlaced(placeable.getCell(i).getX(), placeable.getCell(i).getY(), ((Trap)placeable).getTrapType());
                }
            }
            return true;
        }
        return false;
    }

    public boolean placeImpact(Cell cell){
        if (!isOccupiedBy(cell,PlaceableTypes.IMPACT)) {
            _placedObjects.add(new Impact(cell));
            return true;
        }
        return false;
    }

    public boolean shoot(Cell cell, Weapon weapon){
        if (isInGrid(cell) && !isOccupiedBy(cell,PlaceableTypes.IMPACT)) {
            return weapon.execShoot(this, cell);
        }
        return false;
    }

    public boolean isTheShipDrowned(Ship ship){
        for (int i = 0; i < ship.getSize(); i++) {
            Cell c = ship.getCell(i);
            if (!isOccupiedBy(c, PlaceableTypes.IMPACT)) {
                return false;
            }
        }
        return true;
    }

    public Placeable getIslandObject(Cell cell) {
        Placeable object = getObjectByPosition(cell);
        if (object != null && object.getType() == PlaceableTypes.ISLANDPART) {
            IslandPart island = (IslandPart) object;
            return island.getPlacedObject();
        }
        return null;
    }

    public void washGrid() {
        for (int i=_placedObjects.size()-1; i>=0; i--) {
            Placeable p = _placedObjects.get(i);
            if (p.getType() != PlaceableTypes.ISLANDPART) {
                _placedObjects.remove(i);
            } else {
                ((IslandPart)p).setPlacedObject(null);
            }
        }
    }

    public boolean placeWeaponOnIsland(Weapon weapon, Cell cell) {
        if (getNumberOfWeaponOnIslandByType(weapon.getWeaponType()) > 0 ) {return false;}
        Placeable thing = getObjectByPosition(cell);
        if (thing!=null && thing.getType() == PlaceableTypes.ISLANDPART) {
            IslandPart island = (IslandPart) thing;
            if (island.getPlacedObject()==null) {
                island.setPlacedObject(weapon);
                notifyObserversWeaponPlaced(cell.getX(), cell.getY(), weapon.getWeaponType());
                return true;
            }
        }
        return false;
    }

    public boolean allShipsDead() {
        for (Placeable p: _placedObjects) {
            if (p.getType() == PlaceableTypes.SHIP) {
                Ship ship = (Ship)p;
                for (int i=0; i< ship.getSize(); i++) {
                    if (!isOccupiedBy(ship.getCell(i),PlaceableTypes.IMPACT)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
