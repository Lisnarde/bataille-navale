package view.themes;

import javax.swing.*;
import java.awt.*;

public interface Theme {
    Font normalFont();
    Font boldFont();
    Font titleFont();

    void buttonTheme(AbstractButton button);
    void buttonConfigMinusTheme(AbstractButton button);
    void buttonConfigPlusTheme(AbstractButton button);
}
