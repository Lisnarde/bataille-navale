package model.weapons;

import model.*;

public class IslandSearch extends Weapon {
    private Player _player;
    public IslandSearch(Player player) {
        _player = player;
    }

    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        grid.placeImpact(cell);
        if (!grid.isOccupiedBy(cell,PlaceableTypes.ISLANDPART)) {return false;}
        Placeable object = grid.getIslandObject(cell);
        if (object!=null && object.getType() == PlaceableTypes.WEAPON) {
            Weapon weapon = (Weapon)object;
            _player.addWeaponInInventory(weapon);
        }

        grid.notifyObserversSearch(cell.getX(), cell.getY(), object.getType());

        return true;
    }
}
