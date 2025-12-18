package model;

import model.weapons.WeaponTypes;

public interface GameObserver {
    void updateNoMoreShips(int joueur);

    void updateTurnNumber(int turnNum);

    void updateWeaponFound(int joueur, WeaponTypes weaponType);
}
