package smartsnake.ui;

import smartsnake.Garden;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden(80, 60);
        SnakeComponent component = new SnakeComponent(garden);
        SnakeFrame frame = new SnakeFrame(component);
        frame.addKeyListener(new SnakeKeyAdapter(garden.getSnake()));
        frame.setFocusable(true);
        frame.setVisible(true);
        new SnakeThread(component).start();
    }
}
