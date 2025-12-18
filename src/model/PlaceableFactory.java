package model;

import model.traps.*;
import model.weapons.*;

import java.util.List;

public class PlaceableFactory {
    public PlaceableFactory(){
    }

    public Placeable createShip(List<Cell> cells){
        return new Ship(null,cells);
    }

    public Placeable createTrapTornado(Cell cell){
        return new Tornado(cell);
    }

    public Placeable createTrapBlackHole(Cell cell){
        return new BlackHole(cell);
    }

    public Placeable createImpact(Cell cell){
        return new Impact(cell);
    }

    public Placeable createIslandPart(Cell cell){
        return new IslandPart(cell);
    }

    public Placeable createWeaponBomb(){
        return new Bomb();
    }

    public Placeable createWeaponMissile(){
        return new Missile();
    }

    public Placeable createWeaponSonar(){
        return new Sonar();
    }
}
