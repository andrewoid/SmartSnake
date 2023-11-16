package smartsnake.ui;

import smartsnake.Garden;

import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden(40, 30);
        SnakeComponent component = new SnakeComponent(garden);
        SnakeFrame frame = new SnakeFrame(component);
        KeyListener keyListener = new SnakeKeyAdapter(garden.getSnake());
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
        frame.setVisible(true);
        new SnakeThread(component).start();
    }
}
