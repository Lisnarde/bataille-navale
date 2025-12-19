package model;

import model.weapons.WeaponTypes;

public interface GameObserver {
    void updateNoMoreShips(int player);

    void updateTurnNumber(int turnNum);

    void updateWeaponFound(int player, WeaponTypes weaponType);
}
