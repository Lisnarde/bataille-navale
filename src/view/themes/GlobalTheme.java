package view.themes;

import javax.swing.*;
import java.awt.*;

public class GlobalTheme {
    private Font _normalFont;
    private Font _boldFont;
    private JButton _button;

    public GlobalTheme() {
        _normalFont = new Font("Arial",Font.PLAIN, 16);
        _boldFont = new Font("Arial",Font.BOLD, 16);

        _button = new JButton();
        _button.setPreferredSize(new Dimension(120, 40));
        _button.setFont(new Font("Arial", Font.BOLD, 16));
        _button.setBackground(new Color(100, 100, 210));
        _button.setForeground(Color.WHITE);
    }

    public Font normalFont() {
        return _normalFont;
    }
    public Font boldFont() {
        return _boldFont;
    }

    public JButton button(String text) {
        _button.setText(text);
        return _button;
    }
}
