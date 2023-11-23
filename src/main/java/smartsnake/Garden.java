package smartsnake;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Chambre, Runge
 */
public class Garden {

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
            return false;
        } else if (snake.intersectsItself()) {
            return false;
        } else if (snake.getHeadLocation().x == width
                || snake.getHeadLocation().y == height
                || snake.getHeadLocation().x == -1
                || snake.getHeadLocation().y == -1) {
            return false;
        } else {
            if (snake.intersectsHead(food)) {
                snake.tick(true);
                createFood();
            } else {
                snake.tick(false);
            }
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
}
