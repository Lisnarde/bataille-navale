import model.*;
import model.traps.BlackHole;
import model.traps.Tornado;
import model.traps.Trap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setPlayerName("Bast");
        game.setGrid(10,false);

        Trap trap = new Tornado(new Cell(0,3));
        game.placeTrapOnGrid(0,trap);

        Ship ship = new Ship(Arrays.asList(new Cell(0,0),new Cell(0,1),new Cell(0,2)));
        game.placeShipOnGrid(0,ship);

        Ship ship2 = new Ship(Arrays.asList(new Cell(0,0),new Cell(0,1),new Cell(0,2)));
        game.placeShipOnGrid(1,ship2);

        boolean test = game.shootOnGrid(0,new Cell(0,1));

        System.out.println(test);
    }
}
