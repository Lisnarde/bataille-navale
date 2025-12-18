package controller.bots;

import controller.GameController;
import model.Axis;
import model.Game;
import model.ShipTypes;

import java.util.Map;
import java.util.Random;

public class RandomBot implements Bot {
    private Random _random;
    public RandomBot() {
        _random = new Random();
    }

    @Override
    public void placeShips(int player, Game model, GameController controller, Map<ShipTypes, Integer> numberPerShip) {
        int gridSize = model.getGridSize();

        for (Map.Entry<ShipTypes, Integer> entry : numberPerShip.entrySet()) {
            for (int i=0; i < entry.getValue(); i++) {
                while (!controller.placeShipOnGrid(player, entry.getKey(), _random.nextInt(gridSize), _random.nextInt(gridSize), Axis.values()[_random.nextInt(2)]));
            }
        }

        for (int t=0; t<model.getTrapInventorySize(); t++) {
            while (!controller.placeTrapOnGrid(player, t, _random.nextInt(gridSize), _random.nextInt(gridSize)));
        }

        if (model.isIslandModeActivated()) {
            for (int w=0; w < model.getGlobalWeaponInventorySize(); w++) {
                while (!controller.placeWeaponOnIsland(player, w, _random.nextInt(gridSize), _random.nextInt(gridSize)));
            }
        }
    }

    @Override
    public void shoot(int player, Game model, GameController controller) {
        int gridSize = model.getGridSize();
        while (!controller.shootOnGrid(player, _random.nextInt(gridSize), _random.nextInt(gridSize)));
    }
}
