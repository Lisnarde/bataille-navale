import controller.GameController;
import model.*;
import model.traps.BlackHole;
import model.traps.Tornado;
import model.traps.Trap;

import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        Game model = new Game();
        GameController controller = new GameController(model);

        controller.setPlayerName("Bast");
        controller.setGrid(10,false);

        controller.placeTrapOnGrid(0,0,0,0);

        controller.placeShipOnGrid(0,ShipTypes.Cruiser,3,0, Axis.VERTICAL);
        controller.placeShipOnGrid(1,ShipTypes.Cruiser,3,0, Axis.VERTICAL);

        controller.setWeapon(1, 1);
        controller.shootOnGrid(1,0,3);

        System.out.println(model.isTheGameFinished());

        controller.setWeapon(0, 0);
        controller.shootOnGrid(0,3,0);
        controller.shootOnGrid(0,3,1);
        controller.shootOnGrid(0,3,2);
        controller.shootOnGrid(0,3,3);

        System.out.println(model.isTheGameFinished());
    }

}
