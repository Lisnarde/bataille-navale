package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import view.components.TitleBanner;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;

    public EndScreen(GameController controller, Game model, NavigationController navigationController) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);
    }
}
