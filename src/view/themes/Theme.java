package view.themes;

import javax.swing.*;
import java.awt.*;

public interface Theme {
    Font normalFont();
    Font boldFont();

    JButton button(String text);
}
