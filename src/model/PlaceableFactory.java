package model;

import java.util.List;

public class PlaceableFactory {
    public Placeable createPlaceable(){

    }

    public Placeable createShip(List<Cell> cells){
        return new Ship(cells);
    }

    public Placeable createTrap(){

    }

    public Placeable createImpact(){

    }

    public Placeable createIslandPart(){

    }

    public Placeable createWeapon(){

    }
}
