package view;

import view.components.TitleBanner;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private GraphicalView _parent;

    public EndScreen(GraphicalView parent) {
        _parent = parent;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);
    }
}
