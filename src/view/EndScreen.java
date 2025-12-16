package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import view.components.TitleBanner;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    public EndScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);
    }
}
