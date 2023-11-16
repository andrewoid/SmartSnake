package smartsnake.ui;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    public SnakeFrame(SnakeComponent component) {
        setLayout(new BorderLayout());
        setTitle("Smart Snake");
        add(component, BorderLayout.CENTER);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
