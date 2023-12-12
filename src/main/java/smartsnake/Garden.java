package smartsnake;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Garden class that manages objects of Snake and handles the rules of the game.
 */
public class Garden {

    private static final Logger logger = Logger.getLogger(Garden.class.getName());

    private final int width;
    private final int height;
    private final Random random = new Random();

    private int tickCounter;
    private Snake snake;
    private Food food;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        createSnake();
        createFood();
    }

    public Garden(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        createFood();
    }

    public void createSnake() {
        snake = new Snake();
    }

    public void createFood() {
        ArrayList<Point> points = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                points.add(new Point(x, y));
            }
        }

        points.removeAll(snake.getSegments());

        int randomIndex = (random.nextInt(points.size()));
        food = new Food(points.get(randomIndex));
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    /**
     * This method will move the Snake one square in its direction,
     * consume the food and spawn more food
     * or end the game if the Snake hits itself or the wall or starves.
     *
     * @return true if the simulation should continue, otherwise false
     */
    public boolean tick() {
        tickCounter++;

        if (snake.starved()) {
            logger.info("snake starved");
            return false;
        } else if (snake.intersectsItself()) {
            logger.info("snake intersects self");
            return false;
        } else if (!contains(snake.getHeadLocation())) {
            logger.info("snake intersects garden wall");
            return false;
        } else {
            boolean shouldGrow = snake.intersectsHead(food);

            if (shouldGrow) {
                createFood();
            }

            Brain brain = snake.getBrain();
            Direction newDirection = null;
            try {
                newDirection = brain.move(snake, food, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            snake.setDirection(newDirection);

            snake.tick(shouldGrow);
        }

        return true;
    }


    /**
     * @return the number of times tick() was called successfully.
     */
    public int getTurns() {
        return tickCounter;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * @return true if the Point is inside the Garden, otherwise false.
     */
    public boolean contains(Point location) {
        return location.x >= 0
                && location.y >= 0
                && location.x < width
                && location.y < height;
    }
}
