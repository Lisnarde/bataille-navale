package model;

import model.traps.TrapTypes;

public interface GridObserver {
    void updateShoot(int joueur, int posx, int posy, boolean hit);

    void updateTrapActivated(int joueur, int posx, int posy, TrapTypes trapType);

    void updateSearch(int joueur, int posx, int posy, PlaceableTypes objectFound);

    void updateShipCellPlaced(int joueur, int posx, int posy);

    void updateTrapPlaced(int joueur, int posx, int posy, TrapTypes trapType);

    void updateShipCellDrowned(int joueur, int posx, int posy);
}
