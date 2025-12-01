package model;

import model.traps.Trap;
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
    public void setGrid(Grid grid) {_grid=grid;}

    public boolean placeShip(Ship ship) {
        return _grid.placeObject(ship);
    }

    public boolean placeTrap(Trap trap) {
        return _grid.placeObject(trap);
    }

    public boolean shoot(Player otherPlayer, Cell cell) {
        return otherPlayer._grid.shoot(cell,_equippedWeapon);
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
