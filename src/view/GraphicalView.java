package view;
import controller.GameController;
import model.*;
import javax.swing.*;
import java.awt.*;

public class GraphicalView extends JFrame{
    private GameController _controller;
    private Game _model;

    public GraphicalView(GameController controller, Game model){
        super("Bataille Navale");
        _controller = controller;
        _model = model;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //écran de base → configuration
        showGameScreen(controller, model);
    }

    public void showConfigScreen() {
        setContentPane(new ConfigScreen(this));
        revalidate();
        repaint();
    }

    public void showPlacementScreen() {
        setContentPane(new PlacementScreen(this));
        revalidate();
        repaint();
    }

    public void showGameScreen(GameController controller, Game model) {
        setContentPane(new GameScreen(this, controller, model));
        revalidate();
        repaint();
    }

    public void showEndScreen() {
        setContentPane(new EndScreen(this));
        revalidate();
        repaint();
    }

    public void configureGame(String pseudo, int gridSize, boolean islandMode) {

    }

}
