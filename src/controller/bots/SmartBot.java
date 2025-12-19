package controller.bots;

import controller.GameController;
import model.*;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;

import java.util.*;

public class SmartBot implements Bot, GridObserver {

    private final Random random = new Random();

    private int gridSize;

    private final Set<Cell> shotsDone = new HashSet<>();
    private final Stack<Cell> targets = new Stack<>();

    private boolean hunting = false;

    // --------------------------------------------------
    // Placement : même logique que RandomBot
    // --------------------------------------------------
    @Override
    public void placeShips(int player, Game model, GameController controller, Map<ShipTypes, Integer> numberPerShip) {
        Bot randomBot = new RandomBot();
        randomBot.placeShips(player,model,controller,numberPerShip);
    }

    // --------------------------------------------------
    // Tir intelligent
    // --------------------------------------------------
    @Override
    public void shoot(int player, Game model, GameController controller) {
        if (gridSize == 0) {
            gridSize = model.getGridSize();
            model.addGridObserver(this);
        }

        boolean shotDone = false;
        while (!shotDone) {
            Cell target;
            // PRIORITÉ : cibles de chasse
            if (!targets.isEmpty()) {
                target = targets.pop();
            } else {
                target = randomCell();
            }

            // Si déjà tenté → on ignore
            if (shotsDone.contains(target)) { continue; }

            shotDone = controller.shootOnGrid(player, target.getX(), target.getY());
            if (shotDone) {
                shotsDone.add(target);
            }
        }
    }


    // --------------------------------------------------
    // Observer : réception des infos
    // --------------------------------------------------
    @Override
    public void updateShoot(int player, int x, int y, boolean hit) {
        if (player == 0) {
            if (hit) {
                hunting = true;
                addAdjacentTargets(x, y);
                System.out.println("BOT J'AI TIRÉ LA " + x + " " + y + " " + hit);
            }
        }
    }

    @Override
    public void updateShipCellDrowned(int player, int x, int y) {
        // Bateau coulé → on arrête la chasse
        if (player == 0) {
            hunting = false;
            targets.clear();
        }
    }

    @Override
    public void updateNoMoreShips(int player) {
        hunting = false;
        targets.clear();
    }

    // --------------------------------------------------
    // Méthodes utilitaires
    // --------------------------------------------------
    private void addAdjacentTargets(int x, int y) {
        addIfValid(x + 1, y);
        addIfValid(x - 1, y);
        addIfValid(x, y + 1);
        addIfValid(x, y - 1);
    }

    private void addIfValid(int x, int y) {
        Cell c = new Cell(x, y);
        if (x >= 0 && y >= 0 && x < gridSize && y < gridSize
                && !shotsDone.contains(c)
                && !targets.contains(c)) {
            targets.add(c);
            System.out.println("Je testerai "+x+" "+y);
        }
    }

    private Cell randomCell() {
        return new Cell(random.nextInt(gridSize), random.nextInt(gridSize));
    }

    // --------------------------------------------------
    // Méthodes GridObserver non utilisées
    // --------------------------------------------------
    @Override public void updateTrapActivated(int p, int x, int y, TrapTypes t) {}
    @Override public void updateSearch(int p, int x, int y, WeaponTypes w) {}
    @Override public void updateShipCellPlaced(int p, int x, int y) {}
    @Override public void updateTrapPlaced(int p, int x, int y, TrapTypes t) {}
    @Override public void updateWeaponPlacedOnIsland(int p, int x, int y, WeaponTypes w) {}
}
