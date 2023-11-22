package smartsnake.ui;

import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Weiss
 */
public class SnakeComponent extends JComponent {

    /**
     * Each square should be SQUARE_SIZE x SQUARE_SIZE pixels when drawn.
     */
    private static final int SQUARE_SIZE = 10;

    private final Garden garden;
    static final Color darkGreen = new Color(0, 200, 0);
    static final Color lightGreen = new Color(0, 175, 0);

    public SnakeComponent(Garden garden) {
        this.garden = garden;
    }

    /**
     * Calls garden.tick() then draws the Garden, Snake and Food
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {


        Snake snake = garden.getSnake();
        Food food = garden.getFood();

        //if(garden.tick()) {
        drawGarden(g, garden);
        drawFood(g, food);
        drawSnake(g, snake);

        //}
    }

    public void drawGarden(Graphics g, Garden garden) {
        for (int x = 0; x < garden.getWidth(); x++) {
            for (int y = 0; y < garden.getHeight(); y++) {
                if ((x + y) % 2 == 0) {
                    g.setColor(darkGreen);
                } else {
                    g.setColor(lightGreen);
                }
                g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void drawFood(Graphics g, Food food) {
        int x = food.x;
        int y = food.y;
        g.setColor(Color.RED);
        g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void drawSnake(Graphics g, Snake snake) {
        for(int i = 0; i < snake.getSegments().size(); i++){
            Point point = snake.getSegments().get(i);
            g.setColor(Color.BLUE);
            g.fillRect(point.x * SQUARE_SIZE, point.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

}
