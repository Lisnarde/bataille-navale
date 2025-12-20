package model.weapons;

import model.*;

public class Sonar extends model.weapons.Weapon{
    @Override
    public boolean execShoot(Grid grid, Cell cell) {
        int number = 0;
        for (int x=cell.getX()-1; x< cell.getX()+2; x++) {
            for (int y=cell.getY()-1; y< cell.getY()+2; y++) {
                Placeable p = grid.getObjectByPosition(new Cell(x,y));
                if (p != null && p.getType() == PlaceableTypes.SHIP) {
                    number++;
                }
                System.out.println("Hmm");
            }
        }
        grid.notifyObserversSonarUsed(cell.getX(), cell.getY(), number);
        return true;
    }

    @Override
    public WeaponTypes getWeaponType() {
        return WeaponTypes.SONAR;
    }
}
