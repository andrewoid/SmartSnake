package smartsnake.ui;

import smartsnake.Brain;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden(40, 30);
        SnakeComponent component = new SnakeComponent(garden);
        SnakeFrame frame = new SnakeFrame(component);
        Snake snake = garden.getSnake();

        try {
            String brainClassName = System.getProperty("astarClassName");
            Brain brain = (Brain) Class.forName(brainClassName).getDeclaredConstructor().newInstance();
            snake.setBrain(brain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyListener keyListener = new SnakeKeyAdapter(snake);
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
        frame.setVisible(true);
        new SnakeThread(component).start();
    }
}
