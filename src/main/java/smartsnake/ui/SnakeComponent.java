package smartsnake.ui;

import org.jetbrains.annotations.VisibleForTesting;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

/**
 * Swing Component that displays the Garden, Snake and Food
 */
public class SnakeComponent extends JComponent {

    static final Color darkGreen = new Color(0, 200, 0);
    static final Color lightGreen = new Color(0, 175, 0);

    private final Garden garden;
    private int squareWidth;
    private int squareHeight;

    public SnakeComponent(Garden garden) {
        this.garden = garden;
    }

    @VisibleForTesting
    public void setSquareWidth(int squareWidth) {
        this.squareWidth = squareWidth;
    }

    @VisibleForTesting
    public void setSquareHeight(int squareHeight) {
        this.squareHeight = squareHeight;
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

        calculateSquareSizes();

        garden.tick();
        drawGarden(g, garden);
        drawFood(g, food);
        drawSnake(g, snake);
    }

    /**
     * This makes sure that the width and height of the squares do not go beyond
     * the bounds of the Component and are the same.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private void calculateSquareSizes() {
        int width = getWidth();
        int height = getHeight();
        squareWidth = width / garden.getWidth();
        squareHeight = height / garden.getHeight();
        if (squareWidth < squareHeight) {
            squareHeight = squareWidth;
        } else {
            squareWidth = squareHeight;
        }
    }

    public void drawGarden(Graphics g, Garden garden) {
        for (int x = 0; x < garden.getWidth(); x++) {
            for (int y = 0; y < garden.getHeight(); y++) {
                if ((x + y) % 2 == 0) {
                    g.setColor(darkGreen);
                } else {
                    g.setColor(lightGreen);
                }
                g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
            }
        }
    }

    public void drawFood(Graphics g, Food food) {
        double foodX = food.getX();
        int x = (int) foodX;
        double foodY = food.getY();
        int y = (int) foodY;

        g.setColor(Color.RED);
        g.fillRect(x * squareWidth, y * squareHeight, squareWidth, squareHeight);
    }

    public void drawSnake(Graphics g, Snake snake) {
        List<Point> segments = snake.getSegments();
        g.setColor(Color.BLUE);
        for (Point segment : segments) {
            g.fillRect(segment.x * squareWidth, segment.y * squareHeight, squareWidth, squareHeight);
        }
    }


}
