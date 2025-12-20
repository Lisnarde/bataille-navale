package model;

import model.traps.Trap;
import model.traps.TrapTypes;
import model.weapons.Missile;
import model.weapons.Weapon;
import model.weapons.WeaponTypes;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String _name;
    private Grid _grid;
    private Weapon _equippedWeapon;

    private List<Weapon> _weaponInventory;
    private List<WeaponTypes> _usedWeapons = new ArrayList<>();

    public Player(String name) {
        _name = name;
        _grid = null;
        _weaponInventory = new ArrayList<>();
        _weaponInventory.add(new Missile());
        setEquippedWeapon(0);
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
    public int getNumberOfTrapsByType(TrapTypes trapType) {
        return _grid.getNumberOfTrapByType(trapType);
    }
    public int getNumberOfWeaponOnIslandByType(WeaponTypes weaponType) {
        return _grid.getNumberOfWeaponOnIslandByType(weaponType);
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
        Placeable p = otherPlayer._grid.getObjectByPosition(cell);
        if (p!=null && p.getType() == PlaceableTypes.TRAP) {
            Trap trap = (Trap) p;
            trap.execTrap(this._grid, otherPlayer._grid, cell, _equippedWeapon);
        }
        if (!_usedWeapons.contains(_equippedWeapon.getWeaponType())) {
            _usedWeapons.add(_equippedWeapon.getWeaponType());
        }
        return otherPlayer._grid.shoot(cell,_equippedWeapon);
    }

    public void addWeaponInInventory(Weapon weapon) {_weaponInventory.add(weapon);}
    public void removeWeaponInInventory(Weapon weapon) {_weaponInventory.remove(weapon);}
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

    public void washGrid() {
        _grid.washGrid();
    }

    public boolean hasNoMoreShips() {
        return _grid.allShipsDead();
    }

    public int getIntactShipsCount() { return _grid.getIntactShipsCount(); }
    public int getHitShipsCount() { return _grid.getHitShipsCount(); }
    public int getDrownedShipsCount() { return _grid.getDrownedShipsCount(); }
    public int getShotsInWater() { return _grid.getShotsInWater(); }
    public int getRemainingShipCells() { return _grid.getRemainingShipCells(); }
    public int getRemainingIslandCells() { return _grid.getRemainingIslandCells(); }
    public List<WeaponTypes> getUsedWeapons() {
        return _usedWeapons;
    }

    public List<WeaponTypes> getWeaponInventory() {
        return _weaponInventory.stream()
                .map(Weapon::getWeaponType)
                .toList();
    }

}
