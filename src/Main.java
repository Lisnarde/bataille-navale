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

        controller.placeTrapOnGrid(0,0,0,3);

        controller.placeShipOnGrid(0,ShipTypes.Cruiser,0,0,Axis.VERTICAL);

        Ship ship2 = new Ship(Arrays.asList(new Cell(0,0),new Cell(0,1),new Cell(0,2)));
        controller.placeShipOnGrid(0,ShipTypes.Cruiser,0,0,Axis.VERTICAL);

        controller.setWeapon(1, 1);
        controller.shootOnGrid(1,new Cell(0,3));

        System.out.println(controller.isTheGameFinished());

        controller.setWeapon(0, 0);
        controller.shootOnGrid(0,new Cell(0,0));
        controller.shootOnGrid(0,new Cell(0,1));
        controller.shootOnGrid(0,new Cell(0,2));

        System.out.println(controller.isTheGameFinished());
    }

}
