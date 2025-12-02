package view.components;

import javax.swing.*;
import java.awt.*;

public class TitleBanner extends JPanel {
    public TitleBanner() {
        String text = "BATAILLE NAVALE";
        setLayout(new BorderLayout());
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 46));
        add(title, BorderLayout.CENTER);
    }
}
