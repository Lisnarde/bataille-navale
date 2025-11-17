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

    public Boolean isOccupied(Cell cell){
        for (Placeable p : _placedObjects){
            for (Cell c : p.getCells()){
                if (c.equals(cell)){
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean isHit(Cell cell){
        return cell.isHit();
    }

    public void placeObject(Placeable placeable){
        _placedObjects.add(placeable);
    }

    public void placeImpact(Cell cell){
        _placedObjects.add(new Impact(cell));
    }

    public void shoot(Cell cell, Weapon weapon){

    }
}
