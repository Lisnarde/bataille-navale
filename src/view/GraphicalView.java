package view;
import controller.GameController;
import controller.NavigationController;
import model.*;
import javax.swing.*;
import java.awt.*;

public class GraphicalView extends JFrame{
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;

    private CardLayout _cardLayout;
    private JPanel _mainPanel;

    public GraphicalView(GameController controller, Game model){
        super("Bataille Navale");
        _controller = controller;
        _model = model;

        _cardLayout = new CardLayout();
        _mainPanel = new JPanel(_cardLayout);

        // Définition des différents onglets
        _navigationController = new NavigationController(_cardLayout, _mainPanel);
        _mainPanel.add(new ConfigScreen(_controller, _model, _navigationController),"CONFIGURATION");
        _mainPanel.add(new PlacementScreen(_controller, _model, _navigationController),"PLACEMENT");
        _mainPanel.add(new GameScreen(_controller, _model, _navigationController),"GAME");
        _mainPanel.add(new EndScreen(_controller, _model, _navigationController),"END");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(_mainPanel);

    }

}
