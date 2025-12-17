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
    public void placeShips(Game model, GameController controller, Map<ShipTypes, Integer> numberPerShip) {
        int gridSize = model.getGridSize();

        for (Map.Entry<ShipTypes, Integer> entry : numberPerShip.entrySet()) {
            while (!controller.placeShipOnGrid(1, entry.getKey(), _random.nextInt(gridSize), _random.nextInt(gridSize), Axis.values()[_random.nextInt(2)]));
        }
    }

    @Override
    public void shoot(Game model, GameController controller) {
        int gridSize = model.getGridSize();
        while (!controller.shootOnGrid(1, _random.nextInt(gridSize), _random.nextInt(gridSize)));
    }
}
