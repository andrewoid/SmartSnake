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
    Color darkGreen = new Color(0, 200, 0);
    Color lightGreen = new Color(0, 175, 0);

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

        garden.tick();
        drawGarden(g, garden);

    }

    private void drawGarden(Graphics g, Garden garden) {
        int fullGrid = garden.getHeight() * garden.getWidth();
        for (int i = 0; i < fullGrid; i++) {
            for (int ix = 0; ix < fullGrid; ix++) {
                if ((i + ix) % 2 == 0) {
                    g.setColor(darkGreen);
                } else {
                    g.setColor(lightGreen);
                }
                g.fillRect(i * SQUARE_SIZE, ix * SQUARE_SIZE, (SQUARE_SIZE * SQUARE_SIZE), (SQUARE_SIZE * SQUARE_SIZE));
            }

        }


    }

    private void drawFood(Graphics g, Food food) {

    }

    private void drawSnake(Graphics g, Snake snake) {

    }

}
