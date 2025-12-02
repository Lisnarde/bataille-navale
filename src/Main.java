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
        controller.run();
    }

}
