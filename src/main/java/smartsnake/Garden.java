package smartsnake;

import java.awt.*;

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
        food = new Food((int) (Math.random() * 40), (int) (Math.random() * 30));
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
        } else{
            if (snake.intersectsItself()) {
                canContinue = false;
            } //else if (snake.hitsWall()) {
            //QUESTION: how does the snake hit the wall?
            //}
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
