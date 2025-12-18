package view.components;

import javax.swing.*;
import java.awt.*;

public class TitleBanner extends JPanel {
    JLabel _title;
    public TitleBanner() {
        this("BATAILLE NAVALE");
    }
    public TitleBanner(String text) {
        setLayout(new BorderLayout());
        _title = new JLabel(text, SwingConstants.CENTER);
        _title.setFont(new Font("Arial", Font.BOLD, 46));
        add(_title, BorderLayout.CENTER);
    }

    public void setText(String text) {
        _title.setText(text);
    }
}
