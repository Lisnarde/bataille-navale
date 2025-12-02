package model;

public interface GridObserver {
    void updateShoot(int posx, int posy, boolean hit);

    void updateTrapActivated(int posx, int posy);
}
