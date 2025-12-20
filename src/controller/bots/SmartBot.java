package controller.bots;

import controller.GameController;
import model.*;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

import java.util.*;

public class SmartBot implements Bot, GridObserver {

    private final Random random = new Random();

    private int _gridSize;

    private final Set<Cell> _shotsDone = new HashSet<>();
    private final Stack<Cell> _targets = new Stack<>();

    private boolean _hunting = false;


    @Override
    public void placeShips(int player, Game model, GameController controller, Map<ShipTypes, Integer> numberPerShip) {
        Bot randomBot = new RandomBot();
        randomBot.placeShips(player,model,controller,numberPerShip);
    }


    @Override
    public void shoot(int player, Game model, GameController controller) {
        if (_gridSize == 0) {
            _gridSize = model.getGridSize();
            model.addGridObserver(this);
        }

        boolean shotDone = false;
        while (!shotDone) {
            Cell target;
            if (!_targets.isEmpty()) {
                target = _targets.pop();
            } else {
                target = randomCell();
            }

            if (_shotsDone.contains(target)) { continue; }

            shotDone = controller.shootOnGrid(player, target.getX(), target.getY());
            if (shotDone) {
                _shotsDone.add(target);
            }
        }
    }

    @Override
    public void updateShoot(int player, int x, int y, boolean hit) {
        if (player == 0) {
            if (hit) {
                _hunting = true;
                addAdjacentTargets(x, y);
            }
        }
    }

    @Override
    public void updateShipCellDrowned(int player, int x, int y) {
        if (player == 0) {
            _hunting = false;
            _targets.clear();
        }
    }

    @Override
    public void updateNoMoreShips(int player) {
        _hunting = false;
        _targets.clear();
    }

    private void addAdjacentTargets(int x, int y) {
        addIfValid(x + 1, y);
        addIfValid(x - 1, y);
        addIfValid(x, y + 1);
        addIfValid(x, y - 1);
    }

    private void addIfValid(int x, int y) {
        Cell c = new Cell(x, y);
        if (x >= 0 && y >= 0 && x < _gridSize && y < _gridSize
                && !_shotsDone.contains(c)
                && !_targets.contains(c)) {
            _targets.add(c);
        }
    }

    private Cell randomCell() {
        return new Cell(random.nextInt(_gridSize), random.nextInt(_gridSize));
    }

    @Override public void updateTrapActivated(int p, int x, int y, TrapTypes t) {}
    @Override public void updateSearch(int p, int x, int y, WeaponTypes w) {}
    @Override public void updateShipCellPlaced(int p, int x, int y) {}
    @Override public void updateTrapPlaced(int p, int x, int y, TrapTypes t) {}
    @Override public void updateWeaponPlacedOnIsland(int p, int x, int y, WeaponTypes w) {}
}
