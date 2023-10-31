package smartsnake.ui;

import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_LEFT;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden(30, 20);
        SnakeComponent component = new SnakeComponent(garden);
        SnakeFrame frame = new SnakeFrame(component);
        frame.addKeyListener(new SnakeKeyAdapter(garden.getSnake()));
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
