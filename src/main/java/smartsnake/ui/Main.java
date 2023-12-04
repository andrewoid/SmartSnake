package smartsnake.ui;

import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden(20, 15);
        SnakeComponent component = new SnakeComponent(garden);
        SnakeFrame frame = new SnakeFrame(component);
        Snake snake = garden.getSnake();
        KeyListener keyListener = new SnakeKeyAdapter(snake);
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
        frame.setVisible(true);
        new SnakeThread(component).start();
    }
}
