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
            if (p.hasThisPosition(cell)){
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

    public void placeObject(Placeable placeable){
        _placedObjects.add(placeable);
    }

    public void placeImpact(Cell cell){
        _placedObjects.add(new Impact(cell));
    }

    public void shoot(Cell cell, Weapon weapon){

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
