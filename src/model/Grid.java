package model;

import model.weapons.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int _width;
    private int _height;
    private int _joueur;
    private boolean _islandMode;
    private List<Placeable> _placedObjects;
    private List<GridObserver> _observers;

    public Grid(int width, int height, int joueur){
        this(width,height,joueur,false);
    }
    public Grid(int width, int height, int joueur, boolean islandMode) {
        _width = width;
        _height = height;
        _joueur = joueur;
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
            observer.updateShoot(_joueur, posx, posy, hit);
        }
    }
    public void notifyObserversTrapActivated(int posx, int posy) {
        for (GridObserver observer : _observers) {
            observer.updateTrapActivated(_joueur, posx, posy);
        }
    }

    public void notifyObserversSearch(int posx, int posy, PlaceableTypes objectFound) {
        for (GridObserver observer : _observers) {
            observer.updateSearch(_joueur, posx, posy, objectFound);
        }
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

    public Boolean isHit(Cell cell){
        return isOccupiedBy(cell, PlaceableTypes.IMPACT);
    }

    public boolean isInGrid(Cell cell) {
        return 0 <= cell.getX() && cell.getX() < _width && 0<= cell.getY() && cell.getY() < _height;
    }

    public boolean canPlaceObject(Placeable placeable) {
        for (int i=0; i< placeable.getSize(); i++) {
            if (isOccupied(placeable.getCell(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean placeObject(Placeable placeable){
        if (canPlaceObject(placeable)) {
            _placedObjects.add(placeable);
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
        if (isInGrid(cell)) {
            return weapon.execShoot(this, cell);
        }
        return false;
    }

    public Placeable getIslandObject(Cell cell) {
        Placeable object = getObjectByPosition(cell);
        if (object != null && object.getType() == PlaceableTypes.ISLANDPART) {
            IslandPart island = (IslandPart) object;
            return island.getPlacedObject();
        }
        return null;
    }

    public boolean placeWeaponOnIsland(Weapon weapon, Cell cell) {
        Placeable thing = getObjectByPosition(cell);
        if (thing!=null && thing.getType() == PlaceableTypes.ISLANDPART) {
            IslandPart island = (IslandPart) thing;
            island.setPlacedObject(weapon);
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
