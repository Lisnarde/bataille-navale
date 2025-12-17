package model;

import model.traps.Trap;
import model.weapons.Missile;
import model.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _name;
    private Grid _grid;
    private Weapon _equippedWeapon;

    private List<Weapon> _weaponInventory;

    public Player(String name) {
        _name = name;
        _grid = null;
        _equippedWeapon = new Missile();
        _weaponInventory = new ArrayList<>();
        _weaponInventory.add(new Missile());
    }

    public String getName() {
        return _name;
    }
    public void setGrid(Grid grid) {_grid=grid;}

    public void addGridObserver(GridObserver gridObserver) {
        _grid.addObserver(gridObserver);
    }

    public void setMaxShipsCells(int maxShipsCells) {
        _grid.setMaxShipsCells(maxShipsCells);
    }

    public Placeable getObjectByPosition(Cell cell) {
        return _grid.getObjectByPosition(cell);
    }

    public int getNumberOfShipByType(ShipTypes shipTypes) {
        return _grid.getNumberOfShipByType(shipTypes);
    }

    public boolean placeShip(Ship ship) {
        return _grid.placeObject(ship);
    }

    public boolean placeTrap(Trap trap) {
        return _grid.placeObject(trap);
    }

    public boolean placeWeaponOnIsland(Weapon weapon, Cell cell) {
        return _grid.placeWeaponOnIsland(weapon,cell);
    }

    public boolean shoot(Player otherPlayer, Cell cell) {
        return otherPlayer._grid.shoot(cell,_equippedWeapon);
    }

    public void addWeaponInInventory(Weapon weapon) {_weaponInventory.add(weapon);}
    public boolean setEquippedWeapon(int weaponIndex) {
        if (0 <= weaponIndex && weaponIndex < _weaponInventory.size()) {
            _equippedWeapon = _weaponInventory.get(weaponIndex);
            return true;
        }
        return false;
    }
    public Weapon getEquippedWeapon() {
        return _equippedWeapon;
    }
    public int getWeaponInventorySize() {return _weaponInventory.size();}
    public Weapon getWeaponInInventory(int weaponIndex) {return _weaponInventory.get(weaponIndex);}

    public boolean hasNoMoreShips() {
        return _grid.allShipsDead();
    }
}
