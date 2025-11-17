package model;

import model.weapons.Weapon;

public class Player {
    private String _name;
    private Grid _grid;

    public Player(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
    public Grid getGrid() {
        return _grid;
    }

    public void placeShip(Ship ship) {
        _grid.placeObject(ship);
    }

    public void shoot(Cell cell, Weapon weapon) {
        Grid.shoot(cell,weapon);
    }
}
