package view;
import controller.GameController;
import controller.NavigationController;
import model.*;
import view.themes.GlobalTheme;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;

public class GraphicalView extends JFrame{
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    private CardLayout _cardLayout;
    private JPanel _mainPanel;

    public GraphicalView(GameController controller, Game model){
        super("Bataille Navale");
        _controller = controller;
        _model = model;
        _theme = new GlobalTheme();

        _cardLayout = new CardLayout();
        _mainPanel = new JPanel(_cardLayout);

        // Définition des différents onglets
        _navigationController = new NavigationController(_cardLayout, _mainPanel);

        _navigationController.saveCard("CONFIGURATION", new ConfigScreen(_controller, _model, _navigationController,_theme));
        _navigationController.saveCard("PLACEMENT", new PlacementScreen(_controller, _model, _navigationController,_theme));
        _navigationController.saveCard("GAME", new GameScreen(_controller, _model, _navigationController,_theme));
        _navigationController.saveCard("END", new EndScreen(_controller, _model, _navigationController,_theme));

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(_mainPanel);

    }

}
