package model;

import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

public interface GridObserver {
    void updateShoot(int player, int posx, int posy, boolean hit);

    void updateTrapActivated(int player, int posx, int posy, TrapTypes trapType);

    void updateSearch(int player, int posx, int posy, WeaponTypes objectFound);

    void updateShipCellPlaced(int player, int posx, int posy);

    void updateTrapPlaced(int player, int posx, int posy, TrapTypes trapType);

    void updateWeaponPlacedOnIsland(int player, int posx, int posy, WeaponTypes weaponType);

    void updateShipCellDrowned(int player, int posx, int posy);

    void updateNoMoreShips(int player);
}
