package controller;

import javax.swing.*;
import java.awt.*;

public class NavigationController {
    private final CardLayout _layout;
    private final JPanel _container;

    public NavigationController(CardLayout layout, JPanel container) {
        _layout = layout;
        _container = container;
    }

    public void showConfig() {
        _layout.show(_container, "CONFIGURATION");
    }

    public void showPlacement() {
        _layout.show(_container, "PLACEMENT");
    }

    public void showGame() {
        _layout.show(_container, "GAME");
    }

    public void showEnd() {
        _layout.show(_container, "END");
    }

}
