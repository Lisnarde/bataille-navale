package view;
import model.*;
import javax.swing.*;
import java.awt.*;

public class GraphicalView extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public GraphicalView(){
        super("Bataille Navale");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //écran de base --> configuration
        showConfigScreen();
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

    public void showGameScreen() {
        setContentPane(new GameScreen(this));
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
