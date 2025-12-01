package model;

import model.weapons.*;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int _width;
    private int _height;
    private List<Placeable> _placedObjects;

    public Grid(int width, int height){
        _width = width;
        _height = height;
        _placedObjects = new ArrayList<>();
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
