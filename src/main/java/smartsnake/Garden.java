package smartsnake;

import java.awt.*;
import java.util.ArrayList;

/**
 * Chambre, Runge
 */
public class Garden {

    private final int width;
    private final int height;
    private int tickCounter;
    private Snake snake;
    private Food food;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        createSnake();
        createFood();
    }

    private void createSnake() {
        snake = new Snake();
    }

    private void createFood() {
        ArrayList<Point> points = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                points.add(new Point(x, y));
            }
        }

        points.remove(snake.getHeadLocation());

        int randomIndex = (int) (Math.random() * points.size());
        food = new Food(points.get(randomIndex));

        //need to remove snakes body points from list
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

        boolean canContinue = true;

        if (snake.starved()) {
            canContinue = false;
        } else {
            if (snake.intersectsItself()) {
                canContinue = false;
            } else if (snake.getHeadLocation().x == width || snake.getHeadLocation().y == height) {
                canContinue = false;
            }
            else if (snake.intersects(food)) {
                canContinue = false;
            } else {
                if (snake.intersectsHead(food)) {
                    snake.tick(true);
                    createFood();
                } else {
                    snake.tick(false);
                }
            }
        }
        return canContinue;
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
