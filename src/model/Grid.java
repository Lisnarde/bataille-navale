package model;

import model.weapons.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int _width;
    private int _height;
    private List<Placeable> _placedObjects;
    private List<GridObserver> _observers;

    public Grid(int width, int height){
        _width = width;
        _height = height;
        _placedObjects = new ArrayList<>();
        _observers = new ArrayList<>();
    }

    public void addObserver(GridObserver observer) {
        _observers.add(observer);
    }
    public void notifyObserversShoot(int posx, int posy, boolean hit) {
        for (GridObserver observer : _observers) {
            observer.updateShoot(posx, posy, hit);
        }
    }
    public void notifyObserversTrapActivated(int posx, int posy) {
        for (GridObserver observer : _observers) {
            observer.updateTrapActivated(posx, posy);
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
            weapon.execShoot(this, cell);
            return true;
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
