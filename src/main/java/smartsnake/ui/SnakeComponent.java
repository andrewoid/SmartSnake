package smartsnake.ui;

import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

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
    //Snake snake = new Snake();

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
        //drawSnake(g, snake);

        //}
    }

    private void drawGarden(Graphics g, Garden garden) {
        for (int i = 0; i < garden.getWidth(); i++) {
            for (int ix = 0; ix < garden.getHeight(); ix++) {
                if ((i + ix) % 2 == 0) {
                    g.setColor(darkGreen);
                } else {
                    g.setColor(lightGreen);
                }
                g.fillRect(i * SQUARE_SIZE, ix * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawFood(Graphics g, Food food) {
        int x = food.x;
        int y = food.y;
        g.setColor(Color.RED);
        g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSnake(Graphics g, Snake snake) {
        Point point = snake.getHeadLocation();
        for (int x = 0; x < snake.getSegments().size(); x++) {
            for (int y = 0; y < 1; y++) {

                g.setColor(Color.BLUE);
                //g.fillRect(x * SQUARE_SIZE, SQUARE_SIZE * 15, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(point.x * SQUARE_SIZE, point.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

}
