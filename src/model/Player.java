package model;

import model.weapons.Weapon;

public class Player {
    private String _name;
    private Grid _grid;
    private Weapon _equippedWeapon;

    public Player(String name) {
        _name = name;
        _grid = null;
        _equippedWeapon = null;
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

    public void shoot(Grid grid, Cell cell) {
        grid.shoot(cell,_equippedWeapon);
    }

    public void setEquippedWeapon(Weapon weapon) {
        _equippedWeapon = weapon;
    }
    public Weapon getEquippedWeapon() {
        return _equippedWeapon;
    }

    public boolean hasNoMoreShips() {
        return _grid.allShipsDead();
    }
}
