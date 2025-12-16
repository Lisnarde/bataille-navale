package controller;

import view.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class NavigationController {

    private CardLayout _layout;
    private JPanel _container;
    private Map<String, JPanel> _views;

    public NavigationController(CardLayout layout, JPanel container) {
        _layout = layout;
        _container = container;
        _views = new HashMap<>();
    }

    public void saveCard(String name, JPanel view) {
        _views.put(name, view);
        _container.add(view, name);
    }

    private void show(String name) {
        JPanel view = _views.get(name);
        _layout.show(_container, name);
        if (view instanceof ViewPanel v) {
            v.onShow();
        }
    }

    public void showConfig() {
        show("CONFIGURATION");
    }

    public void showPlacement() {
        show("PLACEMENT");
    }

    public void showGame() {
        show("GAME");
    }

    public void showEnd() {
        show("END");
    }
}
