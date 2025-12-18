package model;

public interface GameObserver {
    void updateNoMoreShips(int joueur);

    void updateTurnNumber(int turnNum);
}
