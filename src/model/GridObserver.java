package model;

import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

public interface GridObserver {
    void updateShoot(int joueur, int posx, int posy, boolean hit);

    void updateTrapActivated(int joueur, int posx, int posy, TrapTypes trapType);

    void updateSearch(int joueur, int posx, int posy, WeaponTypes objectFound);

    void updateShipCellPlaced(int joueur, int posx, int posy);

    void updateTrapPlaced(int joueur, int posx, int posy, TrapTypes trapType);

    void updateWeaponPlacedOnIsland(int joueur, int posx, int posy, WeaponTypes weaponType);

    void updateShipCellDrowned(int joueur, int posx, int posy);

    void updateNoMoreShips(int joueur);
}
